package parser;

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

    private Node literal() throws IOException, InvalidSyntaxException {
        Token token = this.currentToken;

        switch(token.getType()) {
            //literal
            case INTEGER_LITERAL: {
                absorb(TypeToken.INTEGER_LITERAL);
                return new Node1(token);
            }
            case FLOAT_LITERAL: {
                absorb(TypeToken.FLOAT_LITERAL);
                return new Node1(token);
            }
            case BOOLEAN_LITERAL: {
                absorb(TypeToken.BOOLEAN_LITERAL);
                return new Node1(token);
            }
        }
        return null;
    }

    private Node type() throws IOException, InvalidSyntaxException {
        Token token = this.currentToken;

        switch(token.getType()) {
            //literal
            case TYPE: {
                absorb(TypeToken.TYPE);
                return new Node1(token);
            }
            default: throw new InvalidSyntaxException("Invalid Syntax");
        }
    }


    private Node factor() throws IOException, InvalidSyntaxException {
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
                Node identifier = identifier();
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

    private Node term() throws IOException, InvalidSyntaxException {
        Node node = factor();

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

            Node right = factor();
            node = new Node3(node, new Node1(token), right);
        }

        return node;

    }
    private Node subExpression() throws IOException, InvalidSyntaxException {

        absorb(TypeToken.BRACKET_OPEN);
        Node node = simpleExpression();
        absorb(TypeToken.BRACKET_CLOSE);
        return node;

    }
    private Node actualParams() throws IOException, InvalidSyntaxException {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(expression());

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

            Node newExpression = expression();
            nodes.add(newExpression);
        }

        return new ActualParamsNode(nodes);

    }
    private Node functionCall(Node identifier) throws IOException, InvalidSyntaxException {

        absorb(TypeToken.BRACKET_OPEN);
        Node actualParamsNode = (this.currentToken.getType() != TypeToken.BRACKET_CLOSE) ? actualParams() : empty();
        absorb(TypeToken.BRACKET_CLOSE);

        return new FunctionCallNode(identifier, actualParamsNode);
    }

    private Node identifier() throws IOException, InvalidSyntaxException {

        Token identifier = this.currentToken;
        absorb(TypeToken.IDENTIFIER);

        return new Node1(identifier);
    }

    private Node assignment() throws IOException, InvalidSyntaxException
    {
        Node identifier = identifier();
        absorb(TypeToken.EQUAL_SIGN);
        Node expression = expression();

        return new AssignmentNode(identifier, expression);
    }

    private Node variableDeclaration() throws IOException, InvalidSyntaxException
    {
        absorb(TypeToken.LET);

        Node identifier = identifier();
        absorb(TypeToken.COLON);
        TypeToken type = this.currentToken.getType();

        //absorb type
        switch(type)
        {
            case TYPE: absorb(TypeToken.TYPE);break;
            case AUTO: absorb(TypeToken.AUTO);break;
        }

        absorb(TypeToken.EQUAL_SIGN);

        Node expression = expression();

        return new VariableDeclarationNode(identifier, type, expression);
    }

    private Node formalParam() throws IOException, InvalidSyntaxException
    {
        Node identifier = identifier();
        absorb(TypeToken.COLON);
        Node type = type();

        return new StatementNode(identifier, type);
    }

    private Node formalParams() throws IOException, InvalidSyntaxException {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(formalParam());

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

            Node newExpression = formalParam();
            nodes.add(newExpression);
        }

        return new FormalParamsNode(nodes);

    }

    private Node functionDeclaration() throws IOException, InvalidSyntaxException
    {
        absorb(TypeToken.FF);

        Node identifier = identifier();
        absorb(TypeToken.BRACKET_OPEN);
        Node formalParamsNode = (this.currentToken.getType() != TypeToken.BRACKET_CLOSE) ? formalParams() : empty();
        absorb(TypeToken.BRACKET_CLOSE);
        absorb(TypeToken.COLON);
        TypeToken type = this.currentToken.getType();

        //absorb type
        switch(type)
        {
            case TYPE: absorb(TypeToken.TYPE);break;
            case AUTO: absorb(TypeToken.AUTO);break;
        }

        Node block = block();

        return new FunctionDeclarationNode(identifier, formalParamsNode, type, block);
    }

    private Node printStatement() throws IOException, InvalidSyntaxException
    {
        Token token = this.currentToken;
        Node print = new Node1(token);
        absorb(TypeToken.PRINT);
        Node expression = expression();
        return new StatementNode(print, expression);
    }

    private Node returnStatement() throws IOException, InvalidSyntaxException
    {
        Token token = this.currentToken;
        Node returnNode = new Node1(token);
        absorb(TypeToken.RETURN);
        Node expression = expression();
        return new StatementNode(returnNode, expression);
    }

    private Node empty()
    {
        return new Empty();
    }

    private Node unary() throws IOException, InvalidSyntaxException {

        Token token = this.currentToken;
        switch(token.getType())
        {
            case ADDITIVE_OP: absorb(TypeToken.ADDITIVE_OP);break; // case of '-'
            case NOT: absorb(TypeToken.NOT);break; // case of '-'
        }
        return new UnaryNode(new Token(TypeToken.UNARY, token.getAttribute()), simpleExpression());

    }

    private Node simpleExpression() throws IOException, InvalidSyntaxException {
        Node node = term();

        //fill accepted tokens
        ArrayList<TypeToken> acceptedTokens = new ArrayList<TypeToken>();
        acceptedTokens.add(TypeToken.ADDITIVE_OP);

        while(acceptedTokens.contains(this.currentToken.getType()))
        {
            Token token = this.currentToken;

            switch(token.getType())
            {
                case ADDITIVE_OP: absorb(TypeToken.ADDITIVE_OP);break;
                case MULTIPLICATIVE_OP: absorb(TypeToken.MULTIPLICATIVE_OP);break;
            }

            node = new Node3(node, new Node1(token), term());
        }

        return node;

    }
    private Node ifStatement() throws IOException, InvalidSyntaxException
    {
        absorb(TypeToken.IF);
        absorb(TypeToken.BRACKET_OPEN);
        Node expression = expression();
        absorb(TypeToken.BRACKET_CLOSE);

        Node block = block();
        Node elseBlock;
        if(currentToken.getType() == TypeToken.ELSE)
        {
            absorb(TypeToken.ELSE);
            elseBlock = block();
        }
        else
            elseBlock = empty();
        return new IfNode(expression, block, elseBlock);
    }

    private Node forStatement() throws IOException, InvalidSyntaxException
    {
        absorb(TypeToken.FOR);
        absorb(TypeToken.BRACKET_OPEN);
        Node variableDecl = (currentToken.getType()== TypeToken.LET) ? variableDeclaration() : empty();
        absorb(TypeToken.SEMI_COLON);
        Node expression = expression();
        absorb(TypeToken.SEMI_COLON);
        Node assignment = (currentToken.getType()== TypeToken.IDENTIFIER) ? assignment() : empty();
        absorb(TypeToken.BRACKET_CLOSE);

        Node block = block();
        return new ForNode(variableDecl, expression, assignment, block);
    }

    private Node whileStatement() throws IOException, InvalidSyntaxException
    {
        absorb(TypeToken.WHILE);
        absorb(TypeToken.BRACKET_OPEN);
        Node expression = expression();
        absorb(TypeToken.BRACKET_CLOSE);

        Node block = block();
        return new WhileNode(expression, block);
    }

    private Node expression() throws IOException, InvalidSyntaxException {
        Node node = simpleExpression();

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

            node = new Node3(node, new Node1(token), simpleExpression());
        }

        return node;

    }

    private Node statement() throws IOException, InvalidSyntaxException {

        Token token = this.currentToken;
        Node toReturn = null;

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
        }
        return toReturn;
    }

    public Node block() throws IOException, InvalidSyntaxException
    {
        absorb(TypeToken.CURLY_OPEN);
        Node statement = statement();
        absorb(TypeToken.CURLY_CLOSE);
        return statement;
    }

    public Node program() throws IOException, InvalidSyntaxException
    {
        ArrayList<Node> statements = new ArrayList<Node>();
        Node statement = statement();
        while(statement != null)
        {
            statements.add(statement);
            statement = statement();
        }

        return (statements.size() != 0) ? new ProgramNode(statements) : empty();
    }
    public Node parse() throws IOException, InvalidSyntaxException {
        return program();
    }

}
