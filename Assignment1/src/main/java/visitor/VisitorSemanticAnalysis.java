package visitor;

import exceptions.*;
import parser.node.*;

import java.beans.Expression;
import java.util.ArrayList;

public class VisitorSemanticAnalysis implements Visitor {
    private SymbolTable symbolTable = SymbolTable.getSymbolTable();

    public void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        ArrayList<ASTExpression> expressions = actualParams.getExpressions();

        for(ASTExpression expression: expressions)
            expression.accept(this);
    }


    public void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //if not an empty assignment (example in for loop)
        if(assignment.getExpression() != null) {
            //get current scope
            Scope currentScope = symbolTable.getCurrentScope();

            ASTExpression expression = assignment.getExpression();
            ASTIdentifier identifier = assignment.getIdentifier();

            ASTIdentifier actualId = (ASTIdentifier) symbolTable.getDeclaration(identifier.getValue());

            //check if id exists
            if (actualId == null)
                throw new UndeclaredException(identifier.getValue() + " is not declared");

            //get expression type
            expression.accept(this);

            //check if there is a symbol for that type
            String type = actualId.getType();

            switch (type) {
                case "int": {
                    //check if the expression led to a correct type
                    if (symbolTable.getConstant() != Type.INT)
                        throw new IncorrectTypeException("The value is not of type int");
                }
                ;
                break;
                case "float": {
                    //check if the expression led to a correct type
                    if (symbolTable.getConstant() != Type.FLOAT)
                        throw new IncorrectTypeException("The value is not of type float");
                }
                ;
                break;
                //boolean
                default: {
                    //check if the expression led to a correct type
                    if (symbolTable.getConstant() != Type.BOOL)
                        throw new IncorrectTypeException("The value is not of type bool");
                };break;
            }
            //empty value
            symbolTable.setConstant(null);
        }

    }

    public void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get current scope
        Scope currentScope = symbolTable.getCurrentScope();
        ASTExpression left = expression.getLeft();
        ASTExpression right = expression.getRight();

        left.accept(this);
        Type leftType = symbolTable.getConstant();
        right.accept(this);
        Type rightType = symbolTable.getConstant();

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
                if(leftType == Type.BOOL)
                    throw new IncorrectTypeException(operand+" cannot work on type bool");
            };break;
            case ">":
            case "<":
            case "<=":
            case ">=":
            {
                if(leftType == Type.BOOL)
                    throw new IncorrectTypeException(operand+" cannot work on type bool");

                //set type as BOOL
                symbolTable.setConstant(Type.BOOL);
            };break;
            case "and":
            case "or":
            {
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

    public void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        symbolTable.getGlobalScope().removeDeclarations("return");


        // declare new scope
        Scope scope = new Scope();
        //boolean to hold if a there was a return statement before
        boolean alreadyReturn = false;
        symbolTable.insertScope(scope);

        for(int i=0; i <block.getStatements().size(); i++)
        {
            if(alreadyReturn)
                System.out.println("WARNING: Return statement already exists!");

            ASTStatement statement = block.getStatements().get(i);
            statement.accept(this);

            if(statement.getClass() == ASTReturn.class)
                alreadyReturn = true;
        }

        //pop scope
        symbolTable.popScope();

    }

    public void visit(ASTBooleanLiteral booleanLiteral) {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();
        //set constant type
        symbolTable.setConstant(Type.BOOL);
    }

    public void visit(ASTFloatLiteral floatLiteral) {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();
        //set constant type
        symbolTable.setConstant(Type.FLOAT);
    }

    public void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get variable declaration
        ASTVariableDecl variableDecl = forNode.getVariableDecl();
        //check variable declaration
        variableDecl.accept(this);

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

    public void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException {
        //get identifier
        ASTIdentifier identifier = formalParam.getIdentifier();
        //add identifier
        symbolTable.insertDecl(identifier.getValue(), identifier);
    }

    public void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException {
        //loop through formal params
        for(int i=0; i <formalParams.getFormalParams().size(); i++)
        {
            formalParams.getFormalParams().get(i).accept(this);
        }

    }

    public void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //get identifier
        ASTIdentifier identifier = functionCall.getIdentifier();

        //get actual function
        ASTFunctionDecl actualFunction = (ASTFunctionDecl)symbolTable.getDeclaration(identifier.getValue());
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
            String formalParamType = formalParam.getType();
            //check formal params type
            Type expressionType = symbolTable.getConstant();
            switch (formalParamType)
            {
                case "int":
                {
                    if (!expressionType.equals(Type.INT))
                        throw new IncorrectTypeException(formalParam.getValue()+" should be passed an int value");
                };break;
                case "float":
                {
                    if (!expressionType.equals(Type.FLOAT))
                        throw new IncorrectTypeException(formalParam.getValue()+" should be passed a float value");
                };break;
                //boolean
                default:
                {
                    if (!expressionType.equals(Type.BOOL))
                        throw new IncorrectTypeException(formalParam.getValue()+" should be passed an bool value");
                }
            }
        }

        switch (actualFunction.getIdentifier().getType())
        {
            case "int": symbolTable.setConstant(Type.INT);break;
            case "float": symbolTable.setConstant(Type.FLOAT);break;
            default: symbolTable.setConstant(Type.BOOL);
        }

    }

    public void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException{

        //create a scope for formal params
        Scope functionDeclerationScope = new Scope();
        symbolTable.insertScope(functionDeclerationScope);

        //get formal params
        ASTFormalParams formalParams = functionDecl.getFormalParams();
        //check formal paras
        formalParams.accept(this);

        //get block
        ASTBlock block = functionDecl.getBlock();
        //start from block to get return type
        block.accept(this);

        ASTIdentifier returnTypeNode;

        //if there was no return statement, throw an error
        if(!symbolTable.getGlobalScope().isDefined("return"))
            throw new ReturnTypeMismatchException("Nothing is returned");
        //else store the type of return
        else
        {
            returnTypeNode = (ASTIdentifier) symbolTable.getGlobalScope().getDeclaration("return");

        }

        //remove formal params scope
        symbolTable.popScope();

        //get identifier
        ASTIdentifier identifier = functionDecl.getIdentifier();

        //get function's return type
        String returnType = identifier.getType();

        //if the return type of the function does not match the type returned from the block
        if(!returnType.equals(returnTypeNode.getType()) && !returnType.equals("auto"))
            throw new ReturnTypeMismatchException(identifier.getValue()+" should return a value of type "+returnType);

        //if return type is auto, set it to return type from block
        if( returnType.equals("auto"))
            identifier.setType(returnTypeNode.getType());

        //add the identifier to the global scope
        symbolTable.insertDeclGlobal(identifier.getValue(), functionDecl);
        identifier.accept(this);
    }

    public void visit(ASTIdentifier identifier) throws AlreadyDeclaredException, UndeclaredException {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();

        //store variable name
        String variable = identifier.getValue();

        //get that identifier from table
        //ASTIdentifier actualId = (ASTIdentifier)symbolTable.getDeclaration(identifier.getValue());
        //if identifier does not have a type get that identifier from table
        if(identifier.getType() == null)
            identifier = (ASTIdentifier)symbolTable.getDeclaration(variable);

        //if the identifier does not exist
        if(identifier == null)
            throw new UndeclaredException(variable+" is not declared");

        //set constant type
        String type = identifier.getType();
        switch (type)
        {
            case "int": symbolTable.setConstant(Type.INT);break;
            case "float": symbolTable.setConstant(Type.FLOAT);break;
            default: symbolTable.setConstant(Type.BOOL);break;
        }
    }

    public void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = ifNode.getExpression();
        //check expression
        expression.accept(this);

        //empty constant
        symbolTable.setConstant(null);

        //get block if true
        ASTBlock trueBlock = ifNode.getBlock();

        //create a new scope
        Scope trueBlockScope = new Scope();
        //push true block scope to top of stack
        symbolTable.insertScope(trueBlockScope);
        //check block
        trueBlock.accept(this);
        //pop true block scope from stack
        symbolTable.popScope();

        //get else block
        ASTBlock elseBlock = ifNode.getElseBlock();
        //create a new scope
        Scope elseBlockScope = new Scope();
        //push else block scope to top of stack
        symbolTable.insertScope(elseBlockScope);
        //check block
        elseBlock.accept(this);
        //pop else block scope from stack
        symbolTable.popScope();

    }

    public void visit(ASTIntegerLiteral integerLiteral) {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();
        //set constant type
        symbolTable.setConstant(Type.INT);
    }

    public void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        ASTExpression expression = printNode.getExpression();
        expression.accept(this);
        //empty value
        symbolTable.setConstant(null);
    }

    public void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {

        // declare new scope
        Scope scope = new Scope();
        symbolTable.insertScope(scope);

        for(int i=0; i <programNode.getStatements().size(); i++)
        {
            programNode.getStatements().get(i).accept(this);
        }

        //pop scope
        symbolTable.popScope();
    }

    public void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = returnNode.getExpression();
        //check expression
        expression.accept(this);
        //get expression type
        Type returnType = symbolTable.getConstant();

        //this is done just temporarily to store the return type
        //add a declaration as a return identifier with the type of return
        switch(returnType)
        {
            case INT: symbolTable.insertDeclGlobal("return", new ASTIdentifier("return", "int"));break;
            case FLOAT: symbolTable.insertDeclGlobal("return", new ASTIdentifier("return", "float"));break;
            default: symbolTable.insertDeclGlobal("return", new ASTIdentifier("return", "bool"));break;
        }

    }

    public void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        ASTExpression expression = unary.getNext();
        expression.accept(this);
    }

    public void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        //if not an empty declaration (example in for loop)
        if(variableDecl.getExpression() != null)
        {
            Scope currentScope = symbolTable.getCurrentScope();
            ASTExpression expression = variableDecl.getExpression();
            expression.accept(this);

            ASTIdentifier identifier = variableDecl.getIdentifier();

            //check if there is a symbol for that type
            String type = identifier.getType();

            switch (type) {
                case "int": {
                    //check if the expression led to a correct type
                    if (symbolTable.getConstant() != Type.INT)
                        throw new IncorrectTypeException("The value is not of type int");
                };break;
                case "float": {
                    //check if the expression led to a correct type
                    if (symbolTable.getConstant() != Type.FLOAT)
                        throw new IncorrectTypeException("The value is not of type float");
                };break;
                case "bool": {
                    //check if the expression led to a correct type
                    if (symbolTable.getConstant() != Type.BOOL)
                        throw new IncorrectTypeException("The value is not of type bool");
                };break;
                //auto
                default: {
                    switch (symbolTable.getConstant()) {
                        case INT:
                            identifier.setType("int");
                            break;
                        case FLOAT:
                            identifier.setType("float");
                            break;
                        default:
                            identifier.setType("bool");
                            break;
                    }
                };break;
            }

            //add identity
            symbolTable.insertDecl(identifier.getValue(), identifier);
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
        ASTBlock trueBlock = whileNode.getBlock();

        //create a new scope
        Scope trueBlockScope = new Scope();
        //push true block scope to top of stack
        symbolTable.insertScope(trueBlockScope);
        //check block
        trueBlock.accept(this);
        //pop true block scope from stack
        symbolTable.popScope();
    }


    public void visit(ASTExpression astExpression) {}
    public void visit(ASTStatement astStatement) {}

    public void analyse(ASTProgram program) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        symbolTable.reset();
        program.accept(this);
    }
}
