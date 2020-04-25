package visitor;

import com.sun.org.apache.xpath.internal.operations.Bool;
import exceptions.*;
import parser.node.*;

import java.util.ArrayList;

public class VisitorInterpreter implements Visitor {
    private SymbolTable symbolTable = SymbolTable.getSymbolTable();
    boolean isFunctionCall =false;

    public void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
    }

    public void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //check if empty in case of for loop with no assignment
        if(assignment.getExpression() != null)
        {
            //get current scope
            Scope currentScope = symbolTable.getCurrentScope();

            ASTExpression expression = assignment.getExpression();
            ASTIdentifier identifier = assignment.getIdentifier();

            ASTIdentifier actualId = (ASTIdentifier) symbolTable.getDeclaration(identifier.getValue());

            //get expression type
            expression.accept(this);

            symbolTable.insertValue(actualId.getValue(), symbolTable.getConstantValue());

            //if there was a function call, remove the return
            symbolTable.getGlobalScope().removeDeclarations("return");
        }

    }

    public void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get current scope
        Scope currentScope = symbolTable.getCurrentScope();
        ASTExpression left = expression.getLeft();
        ASTExpression right = expression.getRight();

        left.accept(this);
        Object leftValue = symbolTable.getConstantValue();
        right.accept(this);
        Object rightValue = symbolTable.getConstantValue();
        Type type = symbolTable.getConstant();

        //get operand
        String operand = expression.getOperand();
        //check operations
        switch(operand)
        {
            case "*":
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue * (Integer)rightValue);
                else
                    symbolTable.setConstantValue((Float)leftValue * (Float)rightValue);
            };break;
            case "/":
            {

                if(type == Type.INT)
                {
                    if((Integer)rightValue == 0)
                        throw new ArithmeticException("Cannot divide by 0");

                    symbolTable.setConstantValue((Integer)leftValue / (Integer)rightValue);
                }
                else
                {
                    if((Float)rightValue == 0)
                        throw new ArithmeticException("Cannot divide by 0");

                    symbolTable.setConstantValue((Float)leftValue / (Float)rightValue);
                }
            };break;
            case "+":
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue + (Integer)rightValue);
                else
                    symbolTable.setConstantValue((Float)leftValue + (Float)rightValue);
            };break;
            case "-":
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue - (Integer)rightValue);
                else
                    symbolTable.setConstantValue((Float)leftValue - (Float)rightValue);
            };break;
            case ">":
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue > (Integer)rightValue);
                else
                    symbolTable.setConstantValue((Float)leftValue > (Float)rightValue);
            };break;
            case "<":
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue < (Integer)rightValue);
                else
                    symbolTable.setConstantValue((Float)leftValue < (Float)rightValue);
            };break;
            case "<=":
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue <= (Integer)rightValue);
                else
                    symbolTable.setConstantValue((Float)leftValue <= (Float)rightValue);
            };break;
            case ">=":
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue >= (Integer)rightValue);
                else
                    symbolTable.setConstantValue(((Float)leftValue) >= (Float)rightValue);
            };break;
            case "and": symbolTable.setConstantValue((Boolean)leftValue && (Boolean)rightValue);break;
            case "or": symbolTable.setConstantValue((Boolean)leftValue || (Boolean)rightValue);break;
            case "==":
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue(((Integer)leftValue).equals((Integer)rightValue));
                else if(type == Type.FLOAT)
                    symbolTable.setConstantValue(((Float)leftValue).equals((Float)rightValue));
                else
                    symbolTable.setConstantValue(((Boolean)leftValue).equals((Boolean)rightValue));
            };break;
            //<>
            default:
            {
                if(type == Type.INT)
                    symbolTable.setConstantValue(!((Integer)leftValue).equals((Integer)rightValue));
                else if(type == Type.FLOAT)
                    symbolTable.setConstantValue(!((Float)leftValue).equals((Float)rightValue));
                else
                    symbolTable.setConstantValue(!((Boolean)leftValue).equals((Boolean)rightValue));
            };break;
        }



    }

    public void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        symbolTable.getGlobalScope().removeDeclarations("return");

        // declare new scope
        Scope scope = new Scope();
        symbolTable.insertScope(scope);

        for(int i=0; i <block.getStatements().size(); i++)
        {
            block.getStatements().get(i).accept(this);
            if(symbolTable.getGlobalScope().isDefined("return"))
                break;
        }

        //pop scope
        symbolTable.popScope();

    }

    public void visit(ASTBooleanLiteral booleanLiteral) {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();
        //set constant type
        symbolTable.setConstant(Type.BOOL);
        symbolTable.setConstantValue(booleanLiteral.getValue());
    }

    public void visit(ASTFloatLiteral floatLiteral) {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();
        //set constant type
        symbolTable.setConstant(Type.FLOAT);
        symbolTable.setConstantValue(floatLiteral.getValue());
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

        boolean value = (Boolean) symbolTable.getConstantValue();

        while(value)
        {
            //get block
            ASTBlock block = forNode.getBlock();
            //check block
            block.accept(this);
            //get assignment
            ASTAssignment assignment = forNode.getAssignment();
            //check assignment
            assignment.accept(this);

            expression.accept(this);
            value = (Boolean) symbolTable.getConstantValue();
        }


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

    public void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //get identifier
        ASTIdentifier identifier = functionCall.getIdentifier();
        isFunctionCall = true;
        //get actual function
        ASTFunctionDecl actualFunction = (ASTFunctionDecl)symbolTable.getDeclaration(identifier.getValue());

        //get params
        ASTActualParams params = functionCall.getParams();

        //get actual parameters expressions
        ArrayList<ASTExpression> actualParamsExpressions = params.getExpressions();

        //get formal parameters
        ArrayList<ASTFormalParam> formalParams = actualFunction.getFormalParams().getFormalParams();

        //add new scope
        Scope functionCallScope = new Scope();
        symbolTable.insertScope(functionCallScope);

        //get current scope
        Scope currentScope = symbolTable.getCurrentScope();

        //check parameters types
        for(int i=0; i<actualParamsExpressions.size(); i++)
        {
            //go into expression to set type constant
            actualParamsExpressions.get(i).accept(this);

            //get formalParam
            ASTFormalParam formalParam = formalParams.get(i);
            formalParam.accept(this);

            //get formalParam Identifier
            ASTIdentifier formalParamIdentifier = formalParam.getIdentifier();


            currentScope.addValue(formalParamIdentifier.getValue(), symbolTable.getConstantValue());
        }

        ASTBlock functionBlock = actualFunction.getBlock();
        functionBlock.accept(this);
        isFunctionCall = false;

        //remove functioncall scope
        symbolTable.popScope();

    }

    public void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException{

        //create a scope for formal params
        Scope functionDeclerationScope = new Scope();
        symbolTable.insertScope(functionDeclerationScope);

        //get formal params
        ASTFormalParams formalParams = functionDecl.getFormalParams();
        //check formal paras
        formalParams.accept(this);

        //remove formal params scope
        symbolTable.popScope();

        //get identifier
        ASTIdentifier identifier = functionDecl.getIdentifier();

        //add the identifier to the global scope
        symbolTable.insertDeclGlobal(identifier.getValue(), functionDecl);
    }

    public void visit(ASTIdentifier identifier) throws AlreadyDeclaredException, UndeclaredException {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();

        //store variable name
        String variable = identifier.getValue();

        if(identifier.getType() == null)
            identifier = (ASTIdentifier)symbolTable.getDeclaration(variable);


        String type = identifier.getType();

        switch (type)
        {
            case "int": symbolTable.setConstant(Type.INT);break;
            case "float": symbolTable.setConstant(Type.FLOAT);break;
            default: symbolTable.setConstant(Type.BOOL);break;
        }

        symbolTable.setConstantValue(symbolTable.getValue(variable, isFunctionCall));

    }

    public void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = ifNode.getExpression();
        //check expression
        expression.accept(this);

        boolean value = (Boolean) symbolTable.getConstantValue();

        if(value)
        {
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
        }
        else
        {
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
    }

    public void visit(ASTIntegerLiteral integerLiteral) {
        //get current scope
        Scope scope = symbolTable.getCurrentScope();
        //set constant type
        symbolTable.setConstant(Type.INT);
        symbolTable.setConstantValue(integerLiteral.getValue());
    }

    public void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        ASTExpression expression = printNode.getExpression();
        expression.accept(this);
        System.out.println(symbolTable.getConstantValue());
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

        //get type
        Type type = symbolTable.getConstant();
        //get value
        Object value = symbolTable.getConstantValue();

        switch (type)
        {
            case INT: symbolTable.setConstantValue(-1 * (int)value);break;
            case FLOAT: symbolTable.setConstantValue(-1.0 * (float)value);break;
            default: symbolTable.setConstantValue(!(boolean)value);break;
        }
    }

    public void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        //if not an empty declaration (example in for loop)
        if(variableDecl.getExpression() != null)
        {
            Scope currentScope = symbolTable.getCurrentScope();
            ASTExpression expression = variableDecl.getExpression();
            expression.accept(this);

            ASTIdentifier identifier = variableDecl.getIdentifier();

            //add identity
            symbolTable.insertDecl(identifier.getValue(), identifier);

            //insert value
            symbolTable.insertValue(identifier.getValue(), symbolTable.getConstantValue());

            identifier.accept(this);

            //empty value
            symbolTable.setConstant(null);
            symbolTable.setConstantValue(null);

            //if there was a function call, remove the return
            symbolTable.getGlobalScope().removeDeclarations("return");
        }
    }

    @Override
    public void visit(ASTWhile whileNode) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = whileNode.getExpression();
        //check expression
        expression.accept(this);

        boolean value = (Boolean) symbolTable.getConstantValue();
        while(value)
        {
            //get block if true
            ASTBlock trueBlock = whileNode.getBlock();

            //check block
            trueBlock.accept(this);

            expression.accept(this);
            value = (Boolean) symbolTable.getConstantValue();
        }
        //empty constant
        symbolTable.setConstant(null);


    }


    public void visit(ASTExpression astExpression) {}
    public void visit(ASTStatement astStatement) {}

    public void interpret(ASTProgram program) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        symbolTable.reset();
        program.accept(this);
    }


}
