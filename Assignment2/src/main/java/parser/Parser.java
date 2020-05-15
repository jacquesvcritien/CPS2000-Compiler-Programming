package parser;

import exceptions.InvalidSyntaxException;
import lexer.Lexer;
import lexer.Token;
import lexer.TypeToken;
import parser.node.*;
import parser.node.expression.*;
import parser.node.expression.identifier.ASTAbstractIdentifier;
import parser.node.expression.identifier.ASTArrayIdentifier;
import parser.node.expression.identifier.ASTIdentifier;
import parser.node.statement.*;
import parser.node.statement.declaration.ASTArrayDecl;
import parser.node.statement.declaration.ASTDecl;
import parser.node.statement.declaration.ASTVariableDecl;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for parser
 */
public class Parser {
    //lexer to use
    Lexer lexer;
    //holds the current token
    Token currentToken;

    /**
     * Constructor
     * @param lexer lexer to use
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    public Parser(Lexer lexer) throws IOException, InvalidSyntaxException {
        this.lexer = lexer;
        //se current token by getting
        this.currentToken = this.lexer.nextToken();
    }

    /**
     * Method to check if the current token is of the correct type
     * @param typeToken exprected token
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private void absorb(TypeToken typeToken) throws IOException, InvalidSyntaxException {
        /*
         compare current token's type with the type passed and if they match,
         absorb the token. Otherwise throw an error
         */
        if(typeToken == this.currentToken.getType())
            this.currentToken = this.lexer.nextToken();
        else
            throw new InvalidSyntaxException("LINE "+lexer.getCurrentLine()+": Syntax is invalid - Expected "+typeToken);
    }

    /**
     * Method for literal
     * <INTEGERLITERAL> | <FLOATLITERAL> | <BOOLLITERAL> | <CHARLITERAL>
     * @return an Expression
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTExpression literal() throws IOException, InvalidSyntaxException {
        //get current token
        Token token = this.currentToken;

        //check type of current token
        switch(token.getType()) {
            //if boolean
            case BOOLEAN_LITERAL: {
                absorb(TypeToken.BOOLEAN_LITERAL);
                return new ASTBooleanLiteral(token);
            }
            //if float
            case FLOAT_LITERAL: {
                absorb(TypeToken.FLOAT_LITERAL);
                return new ASTFloatLiteral(token);
            }
            //if character
            case CHARACTER_LITERAL: {
                absorb(TypeToken.CHARACTER_LITERAL);
                return new ASTCharacterLiteral(token);
            }
            //if integer
            default: {
                absorb(TypeToken.INTEGER_LITERAL);
                return new ASTIntegerLiteral(token);
            }
        }
    }

    /**
     * Method to parse an array index
     * '[' <INTEGERLITERAL> ']'
     * @return identifier node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTExpression arraySizeIndex() throws IOException, InvalidSyntaxException{
        //absorb opening square bracket
        absorb(TypeToken.SQUARE_OPEN);

        //get integer literal
        ASTExpression index = literal();

        //absorb closing square bracket
        absorb(TypeToken.SQUARE_CLOSE);

        //return new expression
        return index;
    }

    /**
     * Method to parse an identifier
     * ( '_' | <LETTER> ) { '_' | <LETTER> | <DIGIT>}
     * @return identifier node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTIdentifier identifier() throws IOException, InvalidSyntaxException{
        //get current token
        Token identifier = this.currentToken;
        //check it
        absorb(TypeToken.IDENTIFIER);
        //return new identifier token
        return new ASTIdentifier(identifier.getAttribute());
    }

    /**
     * Method to parse an identifier
     * <IDENTIFIER> '[' <EXPRESSION> ']'
     * @return array identifier node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTArrayIdentifier arrayIdentifier(ASTIdentifier identifier) throws IOException, InvalidSyntaxException{

        //get size or index
        ASTExpression sizeIndex = arraySizeIndex();

        //return new identifier token
        return new ASTArrayIdentifier(identifier.getName(), sizeIndex);
    }

    /**
     * Method to check type
     * 'float' | 'int' | 'bool'
     * @return Token
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private Token type() throws IOException, InvalidSyntaxException {
        //get current token
        Token token = this.currentToken;

        //check if the token is a type token
        absorb(TypeToken.TYPE);
        return token;
    }

    /**
     * Method to check for expressions
     * can be : LITERAL, IDENTIFIER, FUNCTIONCALL, SUBEXPRESSION, UNARY
     * @return
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTExpression factor() throws IOException, InvalidSyntaxException{
        //get current token
        Token token = this.currentToken;

        //check type of current token
        switch(token.getType())
        {
            //literal
            case INTEGER_LITERAL:
            case FLOAT_LITERAL:
            case BOOLEAN_LITERAL:
            case CHARACTER_LITERAL:
            {
                return literal();
            }
            //identifier
            case IDENTIFIER:
            {
                //get identifier
                ASTIdentifier identifier = identifier();
                //if it has an open bracket(it must be a function call)
                if(this.currentToken.getType() == TypeToken.BRACKET_OPEN)
                    return functionCall(identifier);
                //else if square open, it must be an array declaration
                else if(this.currentToken.getType() == TypeToken.SQUARE_OPEN)
                    return arrayIdentifier(identifier);
                //else its an identifier
                else
                    return identifier;
            }
            //subexpression
            case BRACKET_OPEN:
            {
                return subExpression();
            }
            //unary - not
            //unary - '-'
            default:
            {
                return unary();
            }

        }
    }

    /**
     * Method for term
     * <FACTOR> {<MULTIPLICATIVE OP (*, \, and)>} <FACTOR>
     * @return an expression node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTExpression term() throws IOException, InvalidSyntaxException{
        // get factor
        ASTExpression node = factor();

        // while the current token is a multiplicative operand
        while(this.currentToken.getType() == TypeToken.MULTIPLICATIVE_OP)
        {
            //get current token
            Token token = this.currentToken;
            //check the multiplicative op type (absorb it)
            absorb(TypeToken.MULTIPLICATIVE_OP);

            //get the right factor
            ASTExpression right = factor();
            //get operand
            String operand = token.getAttribute();
            //create a node with left, right and operand
            node = new ASTBinExpression(node, operand, right);
        }

        //return node
        return node;

    }

    /**
     * Method for sub expression
     * '(' <EXPRESSION> ')'
     * @return an Expression node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTExpression subExpression() throws IOException, InvalidSyntaxException{

        //make sure the first token is an open bracket
        absorb(TypeToken.BRACKET_OPEN);
        //check the expression
        ASTExpression node = expression();
        //make sure there is the ending closing bracket
        absorb(TypeToken.BRACKET_CLOSE);
        //return the node returned from expression
        return node;

    }


    /**
     * Method for actual params
     * <EXPRESSION> { ',' <EXPRESSION> }
     * @return an actual params node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTActualParams actualParams() throws IOException, InvalidSyntaxException{
        //create a list of expressions
        ArrayList<ASTExpression> expressions = new ArrayList<ASTExpression>();


        //check the expression and add it to the list
        expressions.add(expression());

        //while there is more commas (more expressions)
        while(this.currentToken.getType() == TypeToken.COMMA)
        {
            //absorb the comme
            absorb(TypeToken.COMMA);

            //get the next expression and add it to the list
            ASTExpression newExpression = expression();
            expressions.add(newExpression);
        }

        //return the actual params node with the expressions
        return new ASTActualParams(expressions);

    }

    /**
     * Method for array value
     * '{' [ <EXPRESSION> { ',' <EXPRESSION> } ] '}'
     * @return an actual params node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTArrayValue arrayValue() throws IOException, InvalidSyntaxException{
        //create a list of expressions
        ArrayList<ASTExpression> values = new ArrayList<ASTExpression>();

        //absorb opening curly
        absorb(TypeToken.CURLY_OPEN);

        //check if next is a curly close
        if(currentToken.getType() == TypeToken.CURLY_CLOSE)
        {
            //absorb curly close
            absorb(TypeToken.CURLY_CLOSE);
            return new ASTArrayValue(values);
        }


        //check the expression and add it to the list
        values.add(expression());

        //while there is more commas (more expressions)
        while(this.currentToken.getType() == TypeToken.COMMA)
        {
            //absorb the comma
            absorb(TypeToken.COMMA);

            //get the next value and add it to the list
            ASTExpression newExpression = expression();
            values.add(newExpression);
        }

        //absorb curly close
        absorb(TypeToken.CURLY_CLOSE);

        //return the array value node with the expressions
        return new ASTArrayValue(values);

    }

    /**
     * Method for functionCall
     * <IDENTIFIER> '(' [<ACTUALPARAMS>] ')'
     * @param identifier identifier of the function call
     * @return function call node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTFunctionCall functionCall(ASTIdentifier identifier) throws IOException, InvalidSyntaxException{

        //absorb the opening bracket
        absorb(TypeToken.BRACKET_OPEN);

        //if the next is a closing bracket, return an empty actual params node, else get the actual params
        ASTActualParams actualParamsNode = (this.currentToken.getType() != TypeToken.BRACKET_CLOSE) ? actualParams() : new ASTActualParams();

        //absorb the closing bracket
        absorb(TypeToken.BRACKET_CLOSE);

        //return new function call node with actual params and identifier
        return new ASTFunctionCall(identifier, actualParamsNode);
    }

    /**
     * Method for assignment
     * <IDENTIFIER> '=' <EXPRESSION>
     * @return an assignment node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTAssignment assignment() throws IOException, InvalidSyntaxException{

        //if the current token is not an identifier (in case of empty assignment in for loop), return empty assignment
        if((currentToken.getType()!= TypeToken.IDENTIFIER))
            return new ASTAssignment();

        //get identifier
        ASTAbstractIdentifier identifier = identifier();

        //else if square open, it must be an array declaration
        if(this.currentToken.getType() == TypeToken.SQUARE_OPEN)
            identifier =  arrayIdentifier((ASTIdentifier)identifier);

        //absorb equal sign
        absorb(TypeToken.EQUAL_SIGN);
        //get expression
        ASTExpression expression = expression();


        //create assignment node with identifier and expression
        return new ASTAssignment(identifier, expression);
    }

    /**
     * Method for declaration
     * <VARIABLEDECL> | <ARRAYDECL>
     * @return a variable declaration node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTDecl declaration() throws IOException, InvalidSyntaxException{

        //if the current token is not a LET (in case of empty variable declaration in for loop), return empty variable declaration
        if(currentToken.getType()!= TypeToken.LET)
            return new ASTDecl();

        //absorb let
        absorb(TypeToken.LET);
        //get identifier
        ASTIdentifier identifier = identifier();

        //if current token is an opening square bracket it must be an array declaration
        if(currentToken.getType() == TypeToken.SQUARE_OPEN)
            return arrayDeclaration(identifier);
        //if not an array declaration, it is a variable declaration
        else
            return variableDeclaration(identifier);

    }

    /**
     * Method for variable declaration
     * 'let' <IDENTIFIER> ':' ( <TYPE> | <AUTO> ) '=' <EXPRESSION>
     * @param identifier identifier of for the declaration
     * @return a variable declaration node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTVariableDecl variableDeclaration(ASTIdentifier identifier) throws IOException, InvalidSyntaxException{

        //absorb colon
        absorb(TypeToken.COLON);

        //get current token
        Token type = this.currentToken;

        //absorb type
        switch(type.getType())
        {
            case TYPE: absorb(TypeToken.TYPE);break;
            default: absorb(TypeToken.AUTO);break; //auto
        }

        //set type
        identifier.setType(getTypeEnum(type.getAttribute()));

        //absorb equal sign
        absorb(TypeToken.EQUAL_SIGN);
        //get expression
        ASTExpression expression = expression();

        //return variable declaration node with identifier and expression
        return new ASTVariableDecl(identifier, expression);
    }

    /**
     * Method for array declaration
     * 'let' <IDENTIFIER> <ARRAYINDEX> ':' <TYPE> [ '=' <ARRAYVALUE> ]
     * @param identifier identifier of for the declaration
     * @return a variable declaration node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTArrayDecl arrayDeclaration(ASTIdentifier identifier) throws IOException, InvalidSyntaxException{

        //get array size
        ASTExpression arraySize = arraySizeIndex();

        ASTArrayIdentifier arrayIdentifier = new ASTArrayIdentifier(identifier.getName());
        //absorb colon
        absorb(TypeToken.COLON);

        //get current token
        Token type = this.currentToken;

        //set type
        arrayIdentifier.setType(getTypeEnum(type.getAttribute()));

        //set array size
        arrayIdentifier.setSizeIndex(arraySize);

        //absorb type
        absorb(TypeToken.TYPE);

        ASTArrayValue arrayValue;

        //check if there is an equals, if so, there must be a value
        if(currentToken.getType() == TypeToken.EQUAL_SIGN)
        {
            //absorb equal sign
            absorb(TypeToken.EQUAL_SIGN);
            arrayValue = arrayValue();
        }
        //no value
        else
        {
            arrayValue = new ASTArrayValue();
        }

        //return array declaration node with identifier, size and value
        return new ASTArrayDecl(arrayIdentifier, arrayValue);
    }

    /**
     * Method for formal params
     * ( <IDENTIFIER> | <ARRAYIDENTIFIER> )  [ '[' ']' ] ':' <TYPE>
     * @return formal param node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTFormalParam formalParam() throws IOException, InvalidSyntaxException{
        //get identifier
        ASTAbstractIdentifier identifier = identifier();

        //check if it is an array type
        if(this.currentToken.getType() == TypeToken.SQUARE_OPEN)
        {
            //absorb open square
            absorb(TypeToken.SQUARE_OPEN);
            //absorb closing
            absorb(TypeToken.SQUARE_CLOSE);
            //set identifier as an array identifier
            identifier = new ASTArrayIdentifier(identifier.getName());
        }

        //absorb colon
        absorb(TypeToken.COLON);
        //get type
        Token type = type();

        //set identifier type to actual type from type got (int, float, bool or auto)
        identifier.setType(getTypeEnum(type.getAttribute()));

        //return new ast formal param with identifier
        return new ASTFormalParam(identifier);
    }

    /**
     * Method for for formal params
     * <FORMALPARAM> { ',' <FORMALPARAM>}
     * @return formal params node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTFormalParams formalParams() throws IOException, InvalidSyntaxException{
        //init list to hold params
        ArrayList<ASTFormalParam> params = new ArrayList<ASTFormalParam>();

        //if current token is close bracket, it means there is no params
        if(this.currentToken.getType() == TypeToken.BRACKET_CLOSE)
            return new ASTFormalParams(params);

        //else, get formal param and add it to the list
        params.add(formalParam());

        //while there is a comma (more params)
        while(this.currentToken.getType() == TypeToken.COMMA)
        {

            //absorb the comma
            absorb(TypeToken.COMMA);

            //get param and add it to the list
            ASTFormalParam newParam = formalParam();
            params.add(newParam);
        }

        //return new formal params node with params
        return new ASTFormalParams(params);
    }

    /**
     * Method for function declaration
     * 'ff' <IDENTIFIER> '(' [<FORMALPARAMS>] ')' ':' (<TYPE> | <AUTO>) <BLOCK>
     * @return function declaration node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTFunctionDecl functionDeclaration() throws IOException, InvalidSyntaxException{
        //absorb ff
        absorb(TypeToken.FF);
        //get identifier
        ASTIdentifier identifier = identifier();
        //absorb open bracker
        absorb(TypeToken.BRACKET_OPEN);
        //get formal params
        ASTFormalParams formalParamsNode = formalParams();
        //absorb closing bracket
        absorb(TypeToken.BRACKET_CLOSE);
        //absorb colon
        absorb(TypeToken.COLON);

        Token type = this.currentToken;
        //absorb type
        switch(type.getType())
        {
            case TYPE: absorb(TypeToken.TYPE);break;
            default: absorb(TypeToken.AUTO);break;
        }

        //set identifier's type to attribute of type token (int, float, bool, char or auto)
        identifier.setType(getTypeEnum(type.getAttribute()));

        //get block
        ASTBlock block = block();

        //return new function declaration node with identifier, formalParamsNode and block
        return new ASTFunctionDecl(identifier, formalParamsNode, block);
    }

    /**
     * Method for print
     * 'print' <EXPRESSION>
     * @return print node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTPrint printStatement() throws IOException, InvalidSyntaxException{
        //absorb print
        absorb(TypeToken.PRINT);
        //get expression
        ASTExpression expression = expression();
        return new ASTPrint( expression);
    }

    /**
     * Method for return
     * 'return' <EXPRESSION>
     * @return return node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTReturn returnStatement() throws IOException, InvalidSyntaxException{
        //get return
        absorb(TypeToken.RETURN);
        //get expression
        ASTExpression expression = expression();
        return new ASTReturn(expression);
    }

    /**
     * Method for unary
     * ( '-' | 'not' ) <EXPRESSION>
     * @return unary node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTUnary unary() throws IOException, InvalidSyntaxException{

        //get current token
        Token token = this.currentToken;
        //get type of token
        switch(token.getType())
        {
            case ADDITIVE_OP: absorb(TypeToken.ADDITIVE_OP);break; // case of '-'
            default: absorb(TypeToken.NOT);break; // case of '-'
        }

        return new ASTUnary(token.getAttribute(), expression());
    }

    /**
     * Method for simple expression
     * <TERM> {<ADDITIVEOP( +, -, 'or')> <TERM>}
     * @return an expression node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTExpression simpleExpression() throws IOException, InvalidSyntaxException{
        //get term
        ASTExpression node = term();

        //while there is an additive op
        while(this.currentToken.getType() == TypeToken.ADDITIVE_OP)
        {
            //get current token
            Token token = this.currentToken;
            //absorb the additive operand
            absorb(TypeToken.ADDITIVE_OP);
            //create the bin expr node with term, operand and he right term
            node = new ASTBinExpression(node, token.getAttribute(), term());
        }

        //return node
        return node;

    }

    /**
     * Method for if statement
     * 'if '(' <EXPRESSION> ')' <BLOCK> [ 'else' <BLOCK> ]
     * @return an if node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTIf ifStatement() throws IOException, InvalidSyntaxException{
        //absorb if
        absorb(TypeToken.IF);
        //absorb bracket open
        absorb(TypeToken.BRACKET_OPEN);
        //get expression
        ASTExpression expression = expression();
        //absorb bracket close
        absorb(TypeToken.BRACKET_CLOSE);
        //get block
        ASTBlock block = block();

        //init block for else
        ASTBlock elseBlock;
        //if the current token is else, there is an else block
        if(currentToken.getType() == TypeToken.ELSE)
        {
            //absorb else
            absorb(TypeToken.ELSE);
            //get else block
            elseBlock = block();
        }
        //else leave the else block empty
        else
            elseBlock = new ASTBlock();

        //return if node with expression, block and elseblock
        return new ASTIf(expression, block, elseBlock);
    }

    /**
     * Method for for statement
     * 'for' '(' [<VARIABLEDECL>] ';' <EXPRESSION> ';' [<ASSIGNMENT>] ')' <BLOCK>
     * @return for node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTFor forStatement() throws IOException, InvalidSyntaxException{
        //absorb for
        absorb(TypeToken.FOR);
        //absorb bracket open
        absorb(TypeToken.BRACKET_OPEN);
        //get declaration
        ASTDecl declaration = declaration();
        //absorb semi colon
        absorb(TypeToken.SEMI_COLON);
        //get expression
        ASTExpression expression = expression();
        //absorb semi colon
        absorb(TypeToken.SEMI_COLON);
        //get assignment
        ASTAssignment assignment = assignment();
        //absorb bracket close
        absorb(TypeToken.BRACKET_CLOSE);
        //get block
        ASTBlock block = block();

        //return for node with variable declaration, expression, assignment and block
        return new ASTFor(declaration, expression, assignment, block);
    }

    /**
     * Method for while
     * 'while' '(' <EXPRESSION> ')' <BLOCK>
     * @return while node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTWhile whileStatement() throws IOException, InvalidSyntaxException{
        //absorb while
        absorb(TypeToken.WHILE);
        //absorb bracket open
        absorb(TypeToken.BRACKET_OPEN);
        //get expression
        ASTExpression expression = expression();
        //absorb bracket close
        absorb(TypeToken.BRACKET_CLOSE);
        //get block
        ASTBlock block = block();

        //return new while node with expression and block
        return new ASTWhile(expression, block);
    }

    /**
     * Method for expression
     * <SIMPLEEXPR> { <RELATIONALOP(<, >, ==, <>, <=, >=)> <SIMPLEEXPR>}
     * @return expression node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTExpression expression() throws IOException, InvalidSyntaxException{
        //get simple expression
        ASTExpression node = simpleExpression();

        //while there is a relational op
        while(this.currentToken.getType() == TypeToken.RELATIONAL_OP)
        {
            //get current token
            Token token = this.currentToken;
            //absorb the operand
            absorb(TypeToken.RELATIONAL_OP);

            //set node to binary expression node with left simple expression, operand and right simple expression
            node = new ASTBinExpression(node, token.getAttribute(), simpleExpression());
        }

        return node;
    }

    /**
     * Method for statement
     * <VARIABLEDECL> ';' | <ASSIGNMENT> ';' | <PRINTSTMNT> ';' | <IFSTMNT>  | <FORSTMNT> | <WHILESTMNT>  | <RETURNSTMNT> ';' |
     * <FUNCTIONDECL> | <BLOCK>
     * @return a statment node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    private ASTStatement statement() throws IOException, InvalidSyntaxException{
        //get current token
        Token token = this.currentToken;
        //init node to return
        ASTStatement toReturn = null;

        //get type
        switch(token.getType())
        {
            //variable declaration
            case LET:
            {

                //get variable declaration
                toReturn = declaration();
                //absorb semi colon
                absorb(TypeToken.SEMI_COLON);
            };break;
            case IDENTIFIER:
            {
                //get assignment
                toReturn = assignment();
                //absorb semi colon
                absorb(TypeToken.SEMI_COLON);
            };break;
            case PRINT:
            {
                //get print
                toReturn = printStatement();
                //absorb semi colon
                absorb(TypeToken.SEMI_COLON);
            };break;
            case IF: return ifStatement();
            case FOR: return forStatement();
            case WHILE: return whileStatement();
            case RETURN:
            {
                //get return statment
                toReturn = returnStatement();
                //absorb semi colon
                absorb(TypeToken.SEMI_COLON);
            };break;
            case FF: return functionDeclaration();
            case CURLY_OPEN: return block();
            default: return null;//EOF
        }

        //return statement set
        return toReturn;
    }

    /**
     * Method for block
     * '{' {<STATEMENT>} '}'
     * @return a block node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    public ASTBlock block() throws IOException, InvalidSyntaxException{
        //absorb open curly
        absorb(TypeToken.CURLY_OPEN);
        //init list to store statements
        ArrayList<ASTStatement> statements = new ArrayList<ASTStatement>();

        //while it is not an ending curly bracket
        while(this.currentToken.getType() != TypeToken.CURLY_CLOSE)
        {
            //get statement and add to the list
            ASTStatement statement = statement();
            if(statement == null)
                break;
            statements.add(statement);
        }

        //absorb closing curly bracket
        absorb(TypeToken.CURLY_CLOSE);

        //return block node with list of statments
        return new ASTBlock(statements);
    }

    /**
     * Method for program
     * { <STATEMENT> }
     * @return return node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    public ASTProgram program() throws IOException, InvalidSyntaxException{
        //init list to store statements
        ArrayList<ASTStatement> statements = new ArrayList<ASTStatement>();
        //get statement
        ASTStatement statement = statement();

        //while statement is not null
        while(statement != null)
        {
            //add statement to list and get the next one
            statements.add(statement);
            statement = statement();
        }

        //return new program node with statements
        return new ASTProgram(statements);
    }


    /**
     * Method to start parsing
     * @return a program node
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    public ASTProgram parse() throws IOException, InvalidSyntaxException{
        return program();
    }

    /**
     * Method to get enum type from string
     * @param type in string
     * @return type in enum
     */
    public static Type getTypeEnum(String type)
    {
        //set type
        switch(type)
        {
            case "int" : return Type.INT;
            case "float" : return Type.FLOAT;
            case "bool" : return Type.BOOL;
            case "char" : return Type.CHAR;
            default : return Type.AUTO;
        }
    }

}
