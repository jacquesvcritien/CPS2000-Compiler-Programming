package visitor;

import exceptions.*;
import parser.node.*;

import java.beans.Expression;
import java.util.ArrayList;

public class VisitorSemanticAnalysis implements Visitor {
    private SymbolTable symbolTable = new SymbolTable();
    private int indent = 0;

    public void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
        ArrayList<ASTExpression> expressions = actualParams.getExpressions();

        for(ASTExpression expression: expressions)
            expression.accept(this);
    }

    public void visit(ASTAdditiveOp additiveOp) {

    }

    public void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException {
        //if not an empty assignment (example in for loop)
        if(assignment.getIdentifier() != null && assignment.getExpression() != null) {
            //get current scope
            Scope currentScope = symbolTable.getCurrentScope();

            ASTExpression expression = assignment.getExpression();
            ASTIdentifier identifier = assignment.getIdentifier();

            ASTIdentifier actualId = (ASTIdentifier) symbolTable.getDeclaration(identifier.getValue());

            //check if is id exists
            if (actualId == null)
                throw new UndeclaredException(identifier.getValue() + " is not declared");

            //get expression type
            expression.accept(this);

            //check if there is a symbol for that type
            String type = actualId.getType();

            switch (type) {
                case "int": {
                    //check if the expression led to a correct type
                    if (currentScope.getConstant() != Type.INT)
                        throw new IncorrectTypeException("The value is not of type int");
                }
                ;
                break;
                case "float": {
                    //check if the expression led to a correct type
                    if (currentScope.getConstant() != Type.FLOAT)
                        throw new IncorrectTypeException("The value is not of type float");
                }
                ;
                break;
                case "boolean": {
                    //check if the expression led to a correct type
                    if (currentScope.getConstant() != Type.BOOL)
                        throw new IncorrectTypeException("The value is not of type bool");
                }
                case "auto": {
                    switch (currentScope.getConstant()) {
                        case INT:
                            identifier.setType("int");
                            break;
                        case FLOAT:
                            identifier.setType("float");
                            break;
                        case BOOL:
                            identifier.setType("bool");
                            break;
                    }
                }

                //empty value
                currentScope.setConstant(null);
            }
        }

    }

    public void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException {
        //get current scope
        Scope currentScope = symbolTable.getCurrentScope();
        ASTExpression left = expression.getLeft();
        ASTExpression right = expression.getRight();

        left.accept(this);
        Type leftType = currentScope.getConstant();
        right.accept(this);
        Type rightType = currentScope.getConstant();

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
            case ">":
            case "<":
            case "<=":
            case ">=":
            {
                if(leftType == Type.BOOL || rightType == Type.BOOL)
                    throw new IncorrectTypeException(operand+" cannot work on type bool");
            };break;
            case "and":
            case "or":
                if(leftType != Type.BOOL || rightType != Type.BOOL)
                    throw new IncorrectTypeException(operand+" can only work on type bool");
        }

    }

    public void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException {
        // declare new scope
        Scope scope = new Scope();
        symbolTable.insertScope(scope);

        for(int i=0; i <block.getStatements().size(); i++)
        {
            block.getStatements().get(i).accept(this);
        }

        ASTIdentifier returnIdentifier = null;
        //check if there was a return
        if(scope.isDefined("return"))
        {
            /*
            *get return identifier and store in the parent scope so that it could
            *be checked against the function identifier's return type
            */
            returnIdentifier = (ASTIdentifier)scope.getDeclaration("return");
        }

        //pop scope
        symbolTable.popScope();

        //if there was a return type, add it to the parent scope
        symbolTable.insertDecl("return", returnIdentifier);
    }

    public void visit(ASTBooleanLiteral booleanLiteral) {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();
        //set constant type
        scope.setConstant(Type.BOOL);
    }

    public void visit(ASTFloatLiteral floatLiteral) {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();
        //set constant type
        scope.setConstant(Type.FLOAT);
    }

    public void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException {
        //get variable declaration
        ASTVariableDecl variableDecl = forNode.getVariableDecl();
        //check variable declaration
        variableDecl.accept(this);

        //get expression
        ASTExpression expreesion = forNode.getExpression();
        //check expression
        expreesion.accept(this);

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
        //get current scope
        Scope current = symbolTable.getCurrentScope();

        //get identifier
        ASTIdentifier identifier = formalParam.getIdentifier();
        //add identifier
        current.addDeclaration(identifier.getValue(), identifier);
    }

    public void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException {
        //loop through formal params
        for(int i=0; i <formalParams.getFormalParams().size(); i++)
        {
            formalParams.getFormalParams().get(i).accept(this);
        }

    }

    public void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
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
            Type expressionType = symbolTable.getCurrentScope().getConstant();
            switch (expressionType)
            {
                case INT:
                {
                    if (!formalParamType.equals("int"))
                        throw new IncorrectTypeException(formalParam.getValue()+" should be passed an int value");
                };break;
                case FLOAT:
                {
                    if (!formalParamType.equals("float"))
                        throw new IncorrectTypeException(formalParam.getValue()+" should be passed a float value");
                };break;
                case BOOL:
                {
                    if (!formalParamType.equals("bool"))
                        throw new IncorrectTypeException(formalParam.getValue()+" should be passed an bool value");
                };break;
            }
        }

    }

    public void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException, InvalidNodeException {

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
        if(!functionDeclerationScope.isDefined("return"))
            throw new ReturnTypeMismatchException("Nothing is returned");
        //else store the type of return
        else
        {
            returnTypeNode = (ASTIdentifier) functionDeclerationScope.getDeclaration("return");
            //if it is still null
            if(returnTypeNode == null)
                throw new ReturnTypeMismatchException("Nothing is returned");

        }

        //remove formal params scope
        symbolTable.popScope();

        //get identifier
        ASTIdentifier identifier = functionDecl.getIdentifier();

        //get function's return type
        String returnType = identifier.getType();

        //if the return type of the function does not match the type returned from the block
        if(!returnType.equals(returnTypeNode.getType()))
            throw new ReturnTypeMismatchException(identifier.getValue()+" should return a value of type "+returnType);

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
            case "int": scope.setConstant(Type.INT);break;
            case "float": scope.setConstant(Type.FLOAT);break;
            case "bool": scope.setConstant(Type.BOOL);break;
        }
    }

    public void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = ifNode.getExpression();
        //check expression
        expression.accept(this);

        //empty constant
        symbolTable.getCurrentScope().setConstant(null);

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
        scope.setConstant(Type.INT);
    }

    public void visit(ASTMultiplicativeOp multiplicativeOp) {

    }

    public void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException {
        ASTExpression expression = printNode.getExpression();
        expression.accept(this);
        //empty value
        symbolTable.getCurrentScope().setConstant(null);
    }

    public void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException {

        // declare new scope
        Scope scope = new Scope();
        symbolTable.insertScope(scope);

        for(int i=0; i <programNode.getStatements().size(); i++)
        {
            programNode.getStatements().get(i).accept(this);
        }

        //pop scope
    }

    public void visit(ASTRelationalOp relationalOp) {

    }

    public void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, InvalidNodeException, UndeclaredException {
        //get expression
        ASTExpression expression = returnNode.getExpression();
        //check expression
        expression.accept(this);
        //get expression type
        Type returnType = symbolTable.getCurrentScope().getConstant();

        //this is done just temporarily to store the return type
        //add a declaration as a return identifier with the type of return
        switch(returnType)
        {
            case INT: symbolTable.insertDecl("return", new ASTIdentifier("return", "int"));break;
            case FLOAT: symbolTable.insertDecl("return", new ASTIdentifier("return", "float"));break;
            case BOOL: symbolTable.insertDecl("return", new ASTIdentifier("return", "bool"));break;
        }

    }

    public void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException {
        ASTExpression expression = unary.getNext();
        expression.accept(this);
    }

    public void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException {
        //if not an empty declaration (example in for loop)
        if(variableDecl.getIdentifier() != null && variableDecl.getExpression() != null)
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
                    if (currentScope.getConstant() != Type.INT)
                        throw new IncorrectTypeException("The value is not of type int");
                };break;
                case "float": {
                    //check if the expression led to a correct type
                    if (currentScope.getConstant() != Type.FLOAT)
                        throw new IncorrectTypeException("The value is not of type float");
                };break;
                case "boolean": {
                    //check if the expression led to a correct type
                    if (currentScope.getConstant() != Type.BOOL)
                        throw new IncorrectTypeException("The value is not of type bool");
                };break;
                case "auto": {
                    switch (currentScope.getConstant()) {
                        case INT:
                            identifier.setType("int");
                            break;
                        case FLOAT:
                            identifier.setType("float");
                            break;
                        case BOOL:
                            identifier.setType("bool");
                            break;
                    }
                };break;
            }

            //add identity
            symbolTable.insertDecl(identifier.getValue(), identifier);
            identifier.accept(this);
            //empty value
            currentScope.setConstant(null);
        }

    }

    @Override
    public void visit(ASTWhile whileNode) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, InvalidNodeException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = whileNode.getExpression();
        //check expression
        expression.accept(this);

        //empty constant
        symbolTable.getCurrentScope().setConstant(null);

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

}
