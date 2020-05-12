package visitor;

import exceptions.*;
import parser.node.*;
import parser.node.expression.*;
import parser.node.statement.*;

import java.util.ArrayList;

/**
 * Visitor class for semantic analysis
 */
public class VisitorSemanticAnalysis implements Visitor {
    //get symbol table
    private SymbolTable symbolTable = SymbolTable.getSymbolTable();
    //variable to hold current identifier for functiondecl for check return types
    private ASTIdentifier functionIdentifier;

    @Override
    public void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //get expressions
        ArrayList<ASTExpression> expressions = actualParams.getExpressions();

        //visit each expression
        for(ASTExpression expression: expressions)
            expression.accept(this);
    }


    @Override
    public void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //if not an empty assignment (example in for loop)
        if(assignment.getExpression() != null) {

            //get expression
            ASTExpression expression = assignment.getExpression();
            //get identifier
            ASTIdentifier identifier = assignment.getIdentifier();
            //get actual identifier
            ASTIdentifier actualId = null;
            //check whether this id can be assigned, i.e. is not a function decl
            try{
                actualId = (ASTIdentifier) symbolTable.lookup(identifier.getValue());
            }
            catch(ClassCastException exception)
            {
                throw new IncorrectTypeException("The identifier "+identifier.getValue()+" cannot be assigned a value");
            }

            //check if id exists
            if (actualId == null)
                throw new UndeclaredException(identifier.getValue() + " is not declared");

            //get expression type
            expression.accept(this);

            //check if there is a symbol for that type
            Type type = actualId.getType();

            //check if the expression led to a correct type
            if (symbolTable.getConstant() != type)
                throw new IncorrectTypeException("The value is not of type "+type);

            //empty value
            symbolTable.setConstant(null);
        }

    }

    @Override
    public void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get left expression
        ASTExpression left = expression.getLeft();
        //get right expression
        ASTExpression right = expression.getRight();

        //visit left
        left.accept(this);
        //get the type of the left expression
        Type leftType = symbolTable.getConstant();
        //visit right
        right.accept(this);
        //get the type of the right expression
        Type rightType = symbolTable.getConstant();

        //if types do not match
        if(leftType != rightType)
            throw new IncorrectTypeException("Types in expression do not match");

        //get operand
        String operand = expression.getOperand();
        //check operations
        switch(operand)
        {
            case "*":
            case "/":
            case "+":
            case "-":
            {
                //if one of the types is a boolean (only check one type since from the check above they should be same
                if(leftType == Type.BOOL)
                    throw new IncorrectTypeException(operand+" cannot work on type bool");

                //set type to left expression's type (same as right)
                symbolTable.setConstant(leftType);
            };break;
            case ">":
            case "<":
            case "<=":
            case ">=":
            {
                //if one of the types is a boolean (only check one type since from the check above they should be same
                if(leftType == Type.BOOL)
                    throw new IncorrectTypeException(operand+" cannot work on type bool");

                //set type as BOOL
                symbolTable.setConstant(Type.BOOL);
            };break;
            case "and":
            case "or":
            {
                //if one of the types is not a boolean (only check one type since from the check above they should be same
                if(leftType != Type.BOOL)
                    throw new IncorrectTypeException(operand+" can only work on type bool");
            };break;
            // == || <>
            default:
            {
                //set type as BOOL
                symbolTable.setConstant(Type.BOOL);
            };break;

        }

    }

    @Override
    public void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {

        // declare new scope
        Scope scope = new Scope();
        //boolean to hold if a there was a return statement before
        boolean alreadyReturn = false;

        //insert new scope
        symbolTable.insertScope(scope);

        //loop through scopes
        for(int i=0; i <block.getStatements().size(); i++)
        {
            //if a return statement already exists
            if(alreadyReturn)
                System.out.println("WARNING: Return statement already exists!");

            //get statement and visit it
            ASTStatement statement = block.getStatements().get(i);
            statement.accept(this);

            //if statement is of type return, set alreadyreturn
            if(statement.getClass() == ASTReturn.class)
                alreadyReturn = true;
        }

        //pop scope
        symbolTable.popScope();

    }

    @Override
    public void visit(ASTBooleanLiteral booleanLiteral) {
        //set constant type
        symbolTable.setConstant(Type.BOOL);
    }

    @Override
    public void visit(ASTFloatLiteral floatLiteral) {
        //set constant type
        symbolTable.setConstant(Type.FLOAT);
    }

    @Override
    public void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get declaration
        ASTDecl declaration = forNode.getDeclaration();
        //check declaration
        declaration.accept(this);

        //get expression
        ASTExpression expression = forNode.getExpression();
        //check expression
        expression.accept(this);

        //get assignment
        ASTAssignment assignment = forNode.getAssignment();
        //check assignment
        assignment.accept(this);

        //get block
        ASTBlock block = forNode.getBlock();
        //check block
        block.accept(this);
    }

    @Override
    public void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException {
        //get identifier
        ASTIdentifier identifier = formalParam.getIdentifier();
        //add identifier
        symbolTable.insertDecl(identifier.getValue(), identifier);
    }

    @Override
    public void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
        //loop through formal params
        for(int i=0; i <formalParams.getFormalParams().size(); i++)
        {
            formalParams.getFormalParams().get(i).accept(this);
        }

    }

    @Override
    public void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //get identifier
        ASTIdentifier identifier = functionCall.getIdentifier();

        //get actual function
        ASTFunctionDecl actualFunction = (ASTFunctionDecl)symbolTable.lookup(identifier.getValue());

        //if it doesn't exist
        if(actualFunction == null)
            throw new UndeclaredException(identifier.getValue()+" is not declared");

        //get params
        ASTActualParams params = functionCall.getParams();
        //check params
        params.accept(this);

        //get actual parameters expressions
        ArrayList<ASTExpression> actualParamsExpressions = params.getExpressions();

        //get formal parameters
        ArrayList<ASTFormalParam> formalParams = actualFunction.getFormalParams().getFormalParams();

        //check expressions with formal params
        //check amount of parameters
        if(actualParamsExpressions.size() != formalParams.size())
            throw new IncorrectTypeException("Function '"+identifier.getValue()+"' should have "+
                    actualFunction.getFormalParams().getFormalParams().size() + " but was given "+
                    params.getExpressions().size()+" parameters");


        //check parameters types
        for(int i=0; i<actualParamsExpressions.size(); i++)
        {
            //go into expression to set type constant
            actualParamsExpressions.get(i).accept(this);
            //get formalParam
            ASTIdentifier formalParam = formalParams.get(i).getIdentifier();
            //get formal param type
            Type formalParamType = formalParam.getType();
            //check formal params type
            Type expressionType = symbolTable.getConstant();

            //check param type
            //if it does not match to actual param
            if (!expressionType.equals(formalParamType))
                throw new IncorrectTypeException(formalParam.getValue()+" should be passed a value of type "+formalParamType);

        }

        //set constant
        symbolTable.setConstant(actualFunction.getIdentifier().getType());
    }

    @Override
    public void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException{

        //remove any return declarations
        symbolTable.getGlobalScope().removeDeclarations("return");

        //create a scope for formal params
        Scope functionDeclerationScope = new Scope();
        symbolTable.insertScope(functionDeclerationScope);

        //get identifier
        ASTIdentifier identifier = functionDecl.getIdentifier();
        this.functionIdentifier = identifier;

        //get formal params
        ASTFormalParams formalParams = functionDecl.getFormalParams();
        //check formal paras
        formalParams.accept(this);

        //get block
        ASTBlock block = functionDecl.getBlock();
        //start from block to get return type
        block.accept(this);

        //if there was no return statement, throw an error
        if(!symbolTable.getGlobalScope().isDefined("return"))
            throw new ReturnTypeMismatchException("Nothing is returned");

        //remove formal params scope
        symbolTable.popScope();

        //add the identifier to the global scope
        symbolTable.insertDecl(identifier.getValue(), functionDecl);
    }

    @Override
    public void visit(ASTIdentifier identifier) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
        //store variable name
        String variable = identifier.getValue();

        //get that identifier from table
        //ASTIdentifier actualId = (ASTIdentifier)symbolTable.lookup(identifier.getValue());
        //if identifier does not have a type get that identifier from table
        if(identifier.getType() == null)
        {
            //make sure that this cna be printed
            try{
                identifier = (ASTIdentifier) symbolTable.lookup(variable);
            }
            catch(ClassCastException exception)
            {
                throw new IncorrectTypeException("The identifier "+variable+" is a function");
            }
        }

        //if the identifier does not exist
        if(identifier == null)
            throw new UndeclaredException(variable+" is not declared");

        //set constant type
        symbolTable.setConstant(identifier.getType());
    }

    @Override
    public void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = ifNode.getExpression();
        //check expression
        expression.accept(this);

        //get block if true
        ASTBlock trueBlock = ifNode.getBlock();
        //check block
        trueBlock.accept(this);

        //get else block
        ASTBlock elseBlock = ifNode.getElseBlock();
        //check block
        elseBlock.accept(this);

    }

    @Override
    public void visit(ASTIntegerLiteral integerLiteral) {
        //set constant type
        symbolTable.setConstant(Type.INT);
    }

    @Override
    public void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = printNode.getExpression();
        expression.accept(this);
        //empty value
        symbolTable.setConstant(null);
    }

    @Override
    public void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {

        // declare new scope
        Scope scope = new Scope();
        symbolTable.insertScope(scope);

        //loop through all statements and visit them
        for(int i=0; i <programNode.getStatements().size(); i++)
        {
            programNode.getStatements().get(i).accept(this);
        }

        //pop scope
        symbolTable.popScope();
    }

    @Override
    public void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = returnNode.getExpression();
        //check expression
        expression.accept(this);
        //get expression type
        Type returnType = symbolTable.getConstant();

        //this is done just temporarily to store the return type
        //add a declaration as a return identifier with the type of return

        //check return type
        checkReturnType(returnType);
        //insert global decl
        symbolTable.insertDeclGlobal("return", new ASTIdentifier("return", returnType));

    }

    @Override
    public void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = unary.getExpression();
        //visit expression
        expression.accept(this);
    }

    @Override
    public void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        //if not an empty declaration (example in for loop)
        if(variableDecl.getExpression() != null) {
            //get expression
            ASTExpression expression = variableDecl.getExpression();
            //visit expression
            expression.accept(this);

            //get identifier
            ASTIdentifier identifier = variableDecl.getIdentifier();

            //check if there is a symbol for that type
            Type type = identifier.getType();

            //if type is auto
            if (type == Type.AUTO) {
                //set type for identifier
                identifier.setType(symbolTable.getConstant());
            } else {
                if (symbolTable.getConstant() != type)
                    throw new IncorrectTypeException("The value is not of type " + type);
            }

            //add identifier
            symbolTable.insertDecl(identifier.getValue(), identifier);
            //visit identifier
            identifier.accept(this);
            //empty value
            symbolTable.setConstant(null);
        }

    }

    @Override
    public void visit(ASTWhile whileNode) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = whileNode.getExpression();
        //check expression
        expression.accept(this);

        //empty constant
        symbolTable.setConstant(null);

        //get block if true
        ASTBlock block = whileNode.getBlock();

        //visit true block
        block.accept(this);
    }


    @Override
    public void visit(ASTExpression astExpression) {}
    @Override
    public void visit(ASTStatement astStatement) {}

    @Override
    public void visit(ASTArrayValue astArrayValue) {

    }


    @Override
    public void visit(ASTDecl astDecl) {

    }

    /**
     * Method to analyse the program
     * @param program program node to start from
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    public void analyse(ASTProgram program) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        //reset symbol table
        symbolTable.reset();
        //visit program
        program.accept(this);
    }


    /**
     * Method used by return visitor method to check the return type with the function's type
     * @param type type from the return statement
     * @throws ReturnTypeMismatchException
     */
    private void checkReturnType(Type type) throws ReturnTypeMismatchException {
        //get identifier's return type
        Type identifierType = functionIdentifier.getType();

        //if the return type of the function does not match the type returned from the block
        if(!identifierType.equals(type) && identifierType != Type.AUTO)
            throw new ReturnTypeMismatchException(functionIdentifier.getValue()+" should return a value of type "+identifierType);

        //if return type is auto, set it to return type from block
        if(identifierType == Type.AUTO)
            functionIdentifier.setType(type);
    }
}
