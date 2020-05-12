package visitor;

import exceptions.*;
import parser.node.*;
import parser.node.expression.*;
import parser.node.statement.*;

import java.util.ArrayList;

/**
 * Visitor class for interpreter
 */
public class VisitorInterpreter implements Visitor {
    //symbol table
    private SymbolTable symbolTable = SymbolTable.getSymbolTable();

    @Override
    public void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
    }

    @Override
    public void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //check if empty in case of for loop with no assignment
        if(assignment.getExpression() != null)
        {
            //get expression
            ASTExpression expression = assignment.getExpression();
            //get identifier
            ASTIdentifier identifier = assignment.getIdentifier();
            //get actual identifier
            ASTIdentifier actualId = (ASTIdentifier) symbolTable.lookup(identifier.getValue());

            //get expression type
            expression.accept(this);

            //insert value
            symbolTable.insertValue(actualId.getValue(), symbolTable.getConstantValue());

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
        //set value for left
        Object leftValue = symbolTable.getConstantValue();
        //visit right
        right.accept(this);
        //set value for right
        Object rightValue = symbolTable.getConstantValue();
        //get type
        Type type = symbolTable.getConstant();

        //get operand
        String operand = expression.getOperand();
        //check operations
        switch(operand)
        {
            case "*":
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue * (Integer)rightValue);
                else //if float
                    symbolTable.setConstantValue((Float)leftValue * (Float)rightValue);
            };break;
            case "/":
            {
                //if int
                if(type == Type.INT)
                {
                    if((Integer)rightValue == 0)
                        throw new ArithmeticException("Cannot divide by 0");

                    symbolTable.setConstantValue((Integer)leftValue / (Integer)rightValue);
                }
                else //if float
                {
                    if((Float)rightValue == 0)
                        throw new ArithmeticException("Cannot divide by 0");

                    symbolTable.setConstantValue((Float)leftValue / (Float)rightValue);
                }
            };break;
            case "+":
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue + (Integer)rightValue);
                else //if float
                    symbolTable.setConstantValue((Float)leftValue + (Float)rightValue);
            };break;
            case "-":
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue - (Integer)rightValue);
                else //if float
                    symbolTable.setConstantValue((Float)leftValue - (Float)rightValue);
            };break;
            case ">":
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue > (Integer)rightValue);
                else //if float
                    symbolTable.setConstantValue((Float)leftValue > (Float)rightValue);
            };break;
            case "<":
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue < (Integer)rightValue);
                else //if float
                    symbolTable.setConstantValue((Float)leftValue < (Float)rightValue);
            };break;
            case "<=":
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue <= (Integer)rightValue);
                else //if float
                    symbolTable.setConstantValue((Float)leftValue <= (Float)rightValue);
            };break;
            case ">=":
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue((Integer)leftValue >= (Integer)rightValue);
                else //if float
                    symbolTable.setConstantValue(((Float)leftValue) >= (Float)rightValue);
            };break;
            case "and": symbolTable.setConstantValue((Boolean)leftValue && (Boolean)rightValue);break;
            case "or": symbolTable.setConstantValue((Boolean)leftValue || (Boolean)rightValue);break;
            case "==":
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue(((Integer)leftValue).equals((Integer)rightValue));
                //if float
                else if(type == Type.FLOAT)
                    symbolTable.setConstantValue(((Float)leftValue).equals((Float)rightValue));
                else //if bool
                    symbolTable.setConstantValue(((Boolean)leftValue).equals((Boolean)rightValue));
            };break;
            //<>
            default:
            {
                //if int
                if(type == Type.INT)
                    symbolTable.setConstantValue(!((Integer)leftValue).equals((Integer)rightValue));
                //if float
                else if(type == Type.FLOAT)
                    symbolTable.setConstantValue(!((Float)leftValue).equals((Float)rightValue));
                else //if bool
                    symbolTable.setConstantValue(!((Boolean)leftValue).equals((Boolean)rightValue));
            };break;
        }
    }

    @Override
    public void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //remove return declaration
        symbolTable.getGlobalScope().removeDeclarations("return");

        // declare new scope
        Scope scope = new Scope();
        symbolTable.insertScope(scope);

        //go through all statements
        for(int i=0; i <block.getStatements().size(); i++)
        {
            //visit statement
            block.getStatements().get(i).accept(this);
            //if there is already a return break
            if(symbolTable.getGlobalScope().isDefined("return"))
                break;
        }

        //pop scope
        symbolTable.popScope();

    }

    @Override
    public void visit(ASTBooleanLiteral booleanLiteral) {
        //set constant type
        symbolTable.setConstant(Type.BOOL);
        //set constant value
        symbolTable.setConstantValue(booleanLiteral.getValue());
    }

    @Override
    public void visit(ASTFloatLiteral floatLiteral) {
        //set constant type
        symbolTable.setConstant(Type.FLOAT);
        //set constant value
        symbolTable.setConstantValue(floatLiteral.getValue());
    }

    @Override
    public void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get declaration
        ASTDecl declaration = forNode.getDeclaration();
        //check declaration
        declaration.accept(this);

        //get expression
        ASTExpression expression = forNode.getExpression();
        //visit expression
        expression.accept(this);

        //get constant value
        boolean value = (Boolean) symbolTable.getConstantValue();

        // while there is value
        while(value)
        {
            //get block
            ASTBlock block = forNode.getBlock();
            //visit block
            block.accept(this);
            //get assignment
            ASTAssignment assignment = forNode.getAssignment();
            //visit assignment
            assignment.accept(this);
            //visit expression
            expression.accept(this);
            //get value
            value = (Boolean) symbolTable.getConstantValue();
        }
    }

    @Override
    public void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException {
        //get identifier
        ASTIdentifier identifier = formalParam.getIdentifier();
        //add identifier
        symbolTable.insertDecl(identifier.getValue(), identifier);
    }

    @Override
    public void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException {
    }

    @Override
    public void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        //get identifier
        ASTIdentifier identifier = functionCall.getIdentifier();
        //get actual function
        ASTFunctionDecl actualFunction = (ASTFunctionDecl)symbolTable.lookup(identifier.getValue());

        //get params
        ASTActualParams params = functionCall.getParams();

        //get actual parameters expressions
        ArrayList<ASTExpression> actualParamsExpressions = params.getExpressions();

        //get formal parameters
        ArrayList<ASTFormalParam> formalParams = actualFunction.getFormalParams().getFormalParams();

        //add new scope
        Scope functionCallScope = new Scope();
        symbolTable.insertScope(functionCallScope);

        ArrayList<Object> actualParamsValues = new ArrayList<>();
        //get actual parameters values
        for(int i=0; i<actualParamsExpressions.size(); i++) {
            //go into expression to set type constant
            actualParamsExpressions.get(i).accept(this);
            actualParamsValues.add(symbolTable.getConstantValue());
        }

        //check parameters types
        for(int i=0; i<formalParams.size(); i++)
        {
            //get formalParam
            ASTFormalParam formalParam = formalParams.get(i);
            formalParam.accept(this);

            //get formalParam Identifier
            ASTIdentifier formalParamIdentifier = formalParam.getIdentifier();

            //insert value
            functionCallScope.addValue(formalParamIdentifier.getValue(), actualParamsValues.get(i));
        }



        //get block
        ASTBlock functionBlock = actualFunction.getBlock();
        //visit block
        functionBlock.accept(this);

        //remove function call scope
        symbolTable.popScope();

    }

    @Override
    public void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException{
        //get identifier
        ASTIdentifier identifier = functionDecl.getIdentifier();

        //add the identifier to the global scope
        symbolTable.insertDecl(identifier.getValue(), functionDecl);
    }

    @Override
    public void visit(ASTIdentifier identifier) throws AlreadyDeclaredException, UndeclaredException {
        //store variable name
        String variable = identifier.getValue();

        //if identifier's type is not null, lookup variable
        if(identifier.getType() == null)
            identifier = (ASTIdentifier)symbolTable.lookup(variable);

        //get type
        Type type = identifier.getType();
        //set type
        symbolTable.setConstant(type);

        symbolTable.setConstantValue(symbolTable.getValue(variable));

    }

    @Override
    public void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = ifNode.getExpression();
        //check expression
        expression.accept(this);

        //get value
        boolean value = (Boolean) symbolTable.getConstantValue();

        //if the value is true
        if(value)
        {
            //get block if true
            ASTBlock trueBlock = ifNode.getBlock();

            //check block
            trueBlock.accept(this);
        }
        else
        {
            //get else block
            ASTBlock elseBlock = ifNode.getElseBlock();
            //check block
            elseBlock.accept(this);
        }
    }

    @Override
    public void visit(ASTIntegerLiteral integerLiteral) {
        //set constant type
        symbolTable.setConstant(Type.INT);
        //set value
        symbolTable.setConstantValue(integerLiteral.getValue());
    }

    @Override
    public void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = printNode.getExpression();
        //visit expression
        expression.accept(this);

        System.out.println(symbolTable.getConstantValue());
    }

    @Override
    public void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        // declare new scope
        Scope scope = new Scope();
        symbolTable.insertScope(scope);

        //loop through all statements
        for(int i=0; i <programNode.getStatements().size(); i++)
        {
            //get statement and visit statement
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
        symbolTable.insertDeclGlobal("return", new ASTIdentifier("return", returnType));
    }

    @Override
    public void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get unary expression
        ASTExpression expression = unary.getExpression();
        //visit expression
        expression.accept(this);

        //get type
        Type type = symbolTable.getConstant();
        //get value
        Object value = symbolTable.getConstantValue();

        //set value
        switch (type)
        {
            case INT: symbolTable.setConstantValue(-1 * (int)value);break;
            case FLOAT: symbolTable.setConstantValue(-1.0 * (float)value);break;
            default: symbolTable.setConstantValue(!(boolean)value);break;
        }
    }

    @Override
    public void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = variableDecl.getExpression();
        //visit expression
        expression.accept(this);

        ASTIdentifier identifier = variableDecl.getIdentifier();

        //add identity
        symbolTable.insertDecl(identifier.getValue(), identifier);

        //insert value
        symbolTable.insertValue(identifier.getValue(), symbolTable.getConstantValue());

        identifier.accept(this);

        //empty value and type
        symbolTable.setConstant(null);
        symbolTable.setConstantValue(null);

        //if there was a function call, remove the return
        symbolTable.getGlobalScope().removeDeclarations("return");
    }

    @Override
    public void visit(ASTWhile whileNode) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        //get expression
        ASTExpression expression = whileNode.getExpression();
        //check expression
        expression.accept(this);

        //get value
        boolean value = (Boolean) symbolTable.getConstantValue();

        //while value is true
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
     * Method to interpret
     * @param program program node to start from
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    public void interpret(ASTProgram program) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        //reset table
        symbolTable.reset();
        //visit program
        program.accept(this);
    }
}
