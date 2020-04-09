package parser;

import exceptions.InvalidNodeException;
import exceptions.InvalidSyntaxException;
import lexer.Lexer;
import lexer.Token;
import lexer.TypeToken;
import parser.node.*;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    Lexer lexer;
    Token currentToken;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        this.currentToken = this.lexer.nextToken();
    }

    private void absorb(TypeToken typeToken) throws IOException, InvalidSyntaxException {
        /*
         compare current token's type with the type passed and if they match,
         absorb the token. Otherwise throw an error
         */
        if(typeToken == this.currentToken.getType())
            this.currentToken = this.lexer.nextToken();
        else
            throw new InvalidSyntaxException("Syntax is invalid");
    }

    private ASTExpression literal() throws IOException, InvalidSyntaxException {
        Token token = this.currentToken;

        switch(token.getType()) {
            //literal
            case INTEGER_LITERAL: {
                absorb(TypeToken.INTEGER_LITERAL);
                return new ASTIntegerLiteral(token);
            }
            case FLOAT_LITERAL: {
                absorb(TypeToken.FLOAT_LITERAL);
                return new ASTFloatLiteral(token);
            }
            case BOOLEAN_LITERAL: {
                absorb(TypeToken.BOOLEAN_LITERAL);
                return new ASTBooleanLiteral(token);
            }
        }
        return null;
    }

    private ASTIdentifier identifier() throws IOException, InvalidSyntaxException, InvalidNodeException {

        Token identifier = this.currentToken;
        absorb(TypeToken.IDENTIFIER);

        return new ASTIdentifier(identifier.getAttribute());
    }

    private Token type() throws IOException, InvalidSyntaxException {
        Token token = this.currentToken;

        switch(token.getType()) {
            //literal
            case TYPE: {
                absorb(TypeToken.TYPE);
                return token;
            }
            default: throw new InvalidSyntaxException("Invalid Syntax");
        }
    }


    private ASTExpression factor() throws IOException, InvalidSyntaxException, InvalidNodeException {
        //INTEGER_LITERAL | BOOLEAN_LITERAL |  FLOAT_LITERAL
        Token token = this.currentToken;

        switch(token.getType())
        {
            //literal
            case INTEGER_LITERAL:
            case FLOAT_LITERAL:
            case BOOLEAN_LITERAL:
            {
                return literal();
            }
            //identifier
            case IDENTIFIER:
            {
                ASTIdentifier identifier = identifier();
                if(this.currentToken.getType() != TypeToken.BRACKET_OPEN)
                    return identifier;
                else
                    return functionCall(identifier);
            }
            //subexpression
            case BRACKET_OPEN:
            {
                return subExpression();
            }
            //unary - not
            case NOT:
            //unary - '-'
            case ADDITIVE_OP:
            {
                return unary();
            }

        }
        return null;
    }

    private ASTExpression term() throws IOException, InvalidSyntaxException, InvalidNodeException {
        ASTExpression node = factor();

        //fill accepted tokens
        ArrayList<TypeToken> acceptedTokens = new ArrayList<TypeToken>();
        acceptedTokens.add(TypeToken.MULTIPLICATIVE_OP);

        while(acceptedTokens.contains(this.currentToken.getType()))
        {
            Token token = this.currentToken;

            switch(token.getType())
            {
                case MULTIPLICATIVE_OP: absorb(TypeToken.MULTIPLICATIVE_OP);break;
            }

            ASTExpression right = factor();
            node = new ASTBinExpression(node, token.getAttribute(), right);
        }

        return node;

    }
    private ASTExpression subExpression() throws IOException, InvalidSyntaxException, InvalidNodeException {

        absorb(TypeToken.BRACKET_OPEN);
        ASTExpression node = simpleExpression();
        absorb(TypeToken.BRACKET_CLOSE);
        return node;

    }
    private ASTActualParams actualParams() throws IOException, InvalidSyntaxException, InvalidNodeException {
        ArrayList<ASTExpression> expressions = new ArrayList<ASTExpression>();
        expressions.add(expression());

        //fill accepted tokens
        ArrayList<TypeToken> acceptedTokens = new ArrayList<TypeToken>();
        acceptedTokens.add(TypeToken.COMMA);

        while(acceptedTokens.contains(this.currentToken.getType()))
        {
            Token token = this.currentToken;

            switch(token.getType())
            {
                case COMMA: absorb(TypeToken.COMMA);break;
            }

            ASTExpression newExpression = expression();
            expressions.add(newExpression);
        }

        return new ASTActualParams(expressions);

    }
    private ASTFunctionCall functionCall(ASTIdentifier identifier) throws IOException, InvalidSyntaxException, InvalidNodeException {

        absorb(TypeToken.BRACKET_OPEN);
        ASTActualParams actualParamsNode = (this.currentToken.getType() != TypeToken.BRACKET_CLOSE) ? actualParams() : new ASTActualParams();
        absorb(TypeToken.BRACKET_CLOSE);

        return new ASTFunctionCall(identifier, actualParamsNode);
    }



    private ASTAssignment assignment() throws IOException, InvalidSyntaxException, InvalidNodeException {

        if((currentToken.getType()!= TypeToken.IDENTIFIER))
            return new ASTAssignment();
        ASTIdentifier identifier = identifier();
        absorb(TypeToken.EQUAL_SIGN);
        ASTExpression expression = expression();

        return new ASTAssignment(identifier, expression);
    }

    private ASTVariableDecl variableDeclaration() throws IOException, InvalidSyntaxException, InvalidNodeException {

        if(currentToken.getType()!= TypeToken.LET)
            return new ASTVariableDecl();

        absorb(TypeToken.LET);

        ASTIdentifier identifier = identifier();
        absorb(TypeToken.COLON);
        Token type = this.currentToken;

        //absorb type
        switch(type.getType())
        {
            case TYPE: absorb(TypeToken.TYPE);break;
            case AUTO: absorb(TypeToken.AUTO);break;
        }

        identifier.setType(type.getAttribute());

        absorb(TypeToken.EQUAL_SIGN);

        ASTExpression expression = expression();

        return new ASTVariableDecl(identifier, expression);
    }

    private ASTFormalParam formalParam() throws IOException, InvalidSyntaxException, InvalidNodeException {
        ASTIdentifier identifier = identifier();
        absorb(TypeToken.COLON);
        Token type = type();

        return new ASTFormalParam(identifier, type);
    }

    private ASTFormalParams formalParams() throws IOException, InvalidSyntaxException, InvalidNodeException {
        ArrayList<ASTFormalParam> params = new ArrayList<ASTFormalParam>();

        if(this.currentToken.getType() == TypeToken.BRACKET_CLOSE)
            return new ASTFormalParams(params);
        params.add(formalParam());

        //fill accepted tokens
        ArrayList<TypeToken> acceptedTokens = new ArrayList<TypeToken>();
        acceptedTokens.add(TypeToken.COMMA);

        while(acceptedTokens.contains(this.currentToken.getType()))
        {
            Token token = this.currentToken;

            switch(token.getType())
            {
                case COMMA: absorb(TypeToken.COMMA);break;
            }

            ASTFormalParam newParam = formalParam();
            params.add(newParam);
        }

        return new ASTFormalParams(params);

    }

    private ASTFunctionDecl functionDeclaration() throws IOException, InvalidSyntaxException, InvalidNodeException {
        absorb(TypeToken.FF);

        ASTIdentifier identifier = identifier();
        absorb(TypeToken.BRACKET_OPEN);
        ASTFormalParams formalParamsNode = formalParams();
        absorb(TypeToken.BRACKET_CLOSE);
        absorb(TypeToken.COLON);
        Token type = this.currentToken;

        //absorb type
        switch(type.getType())
        {
            case TYPE: absorb(TypeToken.TYPE);break;
            case AUTO: absorb(TypeToken.AUTO);break;
        }

        identifier.setType(type.getAttribute());

        ASTBlock block = block();

        return new ASTFunctionDecl(identifier, formalParamsNode, block);
    }

    private ASTPrint printStatement() throws IOException, InvalidSyntaxException, InvalidNodeException {
        Token token = this.currentToken;
        absorb(TypeToken.PRINT);
        ASTExpression expression = expression();
        return new ASTPrint(token, expression);
    }

    private ASTReturn returnStatement() throws IOException, InvalidSyntaxException, InvalidNodeException {
        Token token = this.currentToken;
//      ASTNode returnNode = new ASTIntegerLiteral(token);
        absorb(TypeToken.RETURN);
        ASTExpression expression = expression();
        return new ASTReturn(token, expression);
    }

    private ASTUnary unary() throws IOException, InvalidSyntaxException, InvalidNodeException {

        Token token = this.currentToken;
        switch(token.getType())
        {
            case ADDITIVE_OP: absorb(TypeToken.ADDITIVE_OP);break; // case of '-'
            case NOT: absorb(TypeToken.NOT);break; // case of '-'
        }
        return new ASTUnary(new Token(TypeToken.UNARY, token.getAttribute()), simpleExpression());

    }

    private ASTExpression simpleExpression() throws IOException, InvalidSyntaxException, InvalidNodeException {
        ASTExpression node = term();

        //fill accepted tokens
        ArrayList<TypeToken> acceptedTokens = new ArrayList<TypeToken>();
        acceptedTokens.add(TypeToken.ADDITIVE_OP);

        while(acceptedTokens.contains(this.currentToken.getType()))
        {
            Token token = this.currentToken;

            switch(token.getType())
            {
                case ADDITIVE_OP: absorb(TypeToken.ADDITIVE_OP);break;
            }

            node = new ASTBinExpression(node, token.getAttribute(), term());
        }

        return node;

    }
    private ASTIf ifStatement() throws IOException, InvalidSyntaxException, InvalidNodeException {
        absorb(TypeToken.IF);
        absorb(TypeToken.BRACKET_OPEN);
        ASTExpression expression = expression();
        absorb(TypeToken.BRACKET_CLOSE);

        ASTBlock block = block();
        ASTBlock elseBlock;
        if(currentToken.getType() == TypeToken.ELSE)
        {
            absorb(TypeToken.ELSE);
            elseBlock = block();
        }
        else
            elseBlock = new ASTBlock();
        return new ASTIf(expression, block, elseBlock);
    }

    private ASTFor forStatement() throws IOException, InvalidSyntaxException, InvalidNodeException {
        absorb(TypeToken.FOR);
        absorb(TypeToken.BRACKET_OPEN);
        ASTVariableDecl variableDecl = variableDeclaration();
        absorb(TypeToken.SEMI_COLON);
        ASTExpression expression = expression();
        absorb(TypeToken.SEMI_COLON);
        ASTAssignment assignment = assignment();
        absorb(TypeToken.BRACKET_CLOSE);

        ASTBlock block = block();
        return new ASTFor(variableDecl, expression, assignment, block);
    }

    private ASTWhile whileStatement() throws IOException, InvalidSyntaxException, InvalidNodeException {
        absorb(TypeToken.WHILE);
        absorb(TypeToken.BRACKET_OPEN);
        ASTExpression expression = expression();
        absorb(TypeToken.BRACKET_CLOSE);

        ASTBlock block = block();
        return new ASTWhile(expression, block);
    }

    private ASTExpression expression() throws IOException, InvalidSyntaxException, InvalidNodeException {
        ASTExpression node = simpleExpression();

        //fill accepted tokens
        ArrayList<TypeToken> acceptedTokens = new ArrayList<TypeToken>();
        acceptedTokens.add(TypeToken.RELATIONAL_OP);

        while(acceptedTokens.contains(this.currentToken.getType()))
        {
            Token token = this.currentToken;

            switch(token.getType())
            {
                case RELATIONAL_OP: absorb(TypeToken.RELATIONAL_OP);break;
            }

            node = new ASTBinExpression(node, token.getAttribute(), simpleExpression());
        }

        return node;

    }

    private ASTStatement statement() throws IOException, InvalidSyntaxException, InvalidNodeException {

        Token token = this.currentToken;
        ASTStatement toReturn = null;

        switch(token.getType())
        {
            //variable declaration
            case LET:
            {
                toReturn = variableDeclaration();
                absorb(TypeToken.SEMI_COLON);
            };break;
            case IDENTIFIER:
            {
                toReturn = assignment();
                absorb(TypeToken.SEMI_COLON);
            };break;
            case PRINT:
            {
                toReturn = printStatement();
                absorb(TypeToken.SEMI_COLON);
            };break;
            case IF: return ifStatement();
            case FOR: return forStatement();
            case WHILE: return whileStatement();
            case RETURN:
            {
                toReturn = returnStatement();
                absorb(TypeToken.SEMI_COLON);
            };break;
            case FF: return functionDeclaration();
            case CURLY_OPEN: return block();
            case EOF: return null;
            default: throw new InvalidSyntaxException("Invalid syntax");
        }
        return toReturn;
    }

    public ASTBlock block() throws IOException, InvalidSyntaxException, InvalidNodeException {
        absorb(TypeToken.CURLY_OPEN);
        ArrayList<ASTStatement> statements = new ArrayList<ASTStatement>();

        while(this.currentToken.getType() != TypeToken.CURLY_CLOSE)
        {
            ASTStatement statement = statement();
            statements.add(statement);
        }

        absorb(TypeToken.CURLY_CLOSE);
        return new ASTBlock(statements);
    }

    public ASTProgram program() throws IOException, InvalidSyntaxException, InvalidNodeException {
        ArrayList<ASTStatement> statements = new ArrayList<ASTStatement>();
        ASTStatement statement = statement();
        while(statement != null)
        {
            statements.add(statement);
            statement = statement();
        }

        return new ASTProgram(statements);
    }
    public ASTProgram parse() throws IOException, InvalidSyntaxException, InvalidNodeException {
        return program();
    }

}
