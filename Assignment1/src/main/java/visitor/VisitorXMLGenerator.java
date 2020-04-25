package visitor;

import exceptions.*;
import lexer.Token;
import parser.node.*;

public class VisitorXMLGenerator implements Visitor {
    private int indent = 0;

    public void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;
        if(actualParams.getExpressions().size() == 0)
            System.out.println(indentation+"<ActualParams>Empty</ActualParams>");
        else
        {
            System.out.println(indentation+"<ActualParams>");
            for(int i=0; i <actualParams.getExpressions().size(); i++)
            {
                actualParams.getExpressions().get(i).accept(this);
            }
            System.out.println(indentation+"</ActualParams>");
        }
        indent--;
    }

    public void visit(ASTAssignment assignment) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        this.indent++;

        ASTIdentifier identifier = assignment.getIdentifier();
        ASTExpression expression = assignment.getExpression();

        //if the assignment is null (for example in for loop)
        if(identifier == null)
        {
            System.out.println(indentation+"<Assignment>Empty</Assignment>");
        }
        else
        {
            System.out.println(indentation+"<Assignment>");
            identifier.accept(this);
            expression.accept(this);

            System.out.println(indentation+"</Assignment>");
        }
        indent--;

    }

    public void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;
        String operand = expression.getOperand();

        ASTExpression left = expression.getLeft();
        ASTExpression right = expression.getRight();

        System.out.println(indentation+"<BinaryExpr Op=\""+operand+"\">");

        left.accept(this);
        right.accept(this);
        System.out.println(indentation+"</BinaryExpr>");


        indent--;
    }

    public void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;
        if(block.getStatements().size() == 0)
            System.out.println(indentation+"<Block>Empty</Block>");
        else
        {
            System.out.println(indentation+"<Block>");
            for(int i=0; i <block.getStatements().size(); i++)
            {
                block.getStatements().get(i).accept(this);
            }                    
            System.out.println(indentation+"</Block>");
        }
        indent--;


    }

    public void visit(ASTBooleanLiteral booleanLiteral) {
        String indentation = getIndent().toString();

        System.out.println(indentation+"<BooleanLiteral>"+booleanLiteral.getValue()+"</BooleanLiteral>");

    }

    public void visit(ASTFloatLiteral floatLiteral) {
        String indentation = getIndent().toString();

        System.out.println(indentation+"<FloatLiteral>"+floatLiteral.getValue()+"</FloatLiteral>");

    }

    public void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;


        ASTVariableDecl variableDecl = forNode.getVariableDecl();
        ASTExpression expression = forNode.getExpression();
        ASTAssignment assignment = forNode.getAssignment();
        ASTBlock block = forNode.getBlock();


        System.out.println(indentation+"<For>");
        variableDecl.accept(this);
        expression.accept(this);
        assignment.accept(this);
        block.accept(this);
        indent--;
        System.out.println(indentation+"</For>");
    }

    public void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException, UndeclaredException {
        String indentation = getIndent().toString();
        indent++;


        ASTIdentifier identifier = formalParam.getIdentifier();

        System.out.println(indentation+"<FormalParam>");
        identifier.accept(this);
        System.out.println(indentation+"</FormalParam>");
        indent--;

    }

    public void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException {
        String indentation = getIndent().toString();
        indent++;
        if(formalParams.getFormalParams().size() == 0)
            System.out.println(indentation+"<FormalParams>Empty</FormalParams>");
        else
        {
            System.out.println(indentation+"<FormalParams>");
            for(int i=0; i <formalParams.getFormalParams().size(); i++)
            {
                formalParams.getFormalParams().get(i).accept(this);
            }
            System.out.println(indentation+"</FormalParams>");
        }
        indent--;
    }

    public void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        this.indent++;

        ASTIdentifier identifier = functionCall.getIdentifier();
        ASTActualParams params = functionCall.getParams();
        System.out.println(indentation+"<FunctionCall>");
        identifier.accept(this);
        params.accept(this);
        System.out.println(indentation+"</FunctionCall>");

        indent--;
    }

    public void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        this.indent++;


        ASTIdentifier identifier = functionDecl.getIdentifier();
        ASTFormalParams formalParams = functionDecl.getFormalParams();
        ASTBlock block = functionDecl.getBlock();

        System.out.println(indentation+"<FuncDecl>");
        identifier.accept(this);
        formalParams.accept(this);
        block.accept(this);
        System.out.println(indentation+"</FuncDecl>");
        indent--;
    }

    public void visit(ASTIdentifier identifier) {
        String indentation = getIndent().toString();

        String type = (identifier.getType() != null) ? " Type=\""+identifier.getType()+"\"" : "";
        System.out.println(indentation+"<Identifier"+type+">"+identifier.getValue()+"</Identifier>");
    }

    public void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;


        ASTExpression expression = ifNode.getExpression();
        ASTBlock block = ifNode.getBlock();
        ASTBlock elseBlock = ifNode.getElseBlock();


        System.out.println(indentation+"<If>");
        expression.accept(this);
        block.accept(this);
        elseBlock.accept(this);
        indent--;
        System.out.println(indentation+"</If>");
    }

    public void visit(ASTIntegerLiteral integerLiteral) {
        String indentation = getIndent().toString();

        System.out.println(indentation+"<IntegerLiteral>"+integerLiteral.getValue()+"</IntegerLiteral>");

    }

    public void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;
        ASTExpression expression = printNode.getExpression();
        System.out.println(indentation+"<Print>");
        expression.accept(this);

        indent--;
        System.out.println(indentation+"</Print>");
    }

    public void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {

        String indentation = getIndent().toString();
        this.indent++;
        System.out.println(indentation+"<Program>");
        for(int i=0; i <programNode.getStatements().size(); i++)
        {
            programNode.getStatements().get(i).accept(this);
        }
        System.out.println(indentation+"</Program>");
    }


    public void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;
        ASTExpression expression = returnNode.getExpression();
        System.out.println(indentation+"<Return>");
        expression.accept(this);

        indent--;
        System.out.println(indentation+"</Return>");
    }

    public void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;

        ASTExpression expression = unary.getNext();
        System.out.println(indentation+"<Unary Type=\""+unary.getLexeme()+"\">");
        expression.accept(this);
        System.out.println(indentation+"</Unary>");

        indent--;
    }

    public void visit(ASTVariableDecl variableDecl) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        this.indent++;


        ASTIdentifier identifier = variableDecl.getIdentifier();
        ASTExpression expression = variableDecl.getExpression();

        if(identifier == null)
        {
            System.out.println(indentation+"<VarDecl>Empty</VarDecl>");
        }
        else
        {
            System.out.println(indentation+"<VarDecl>");
            identifier.accept(this);
            expression.accept(this);
            System.out.println(indentation+"</VarDecl>");
        }
        indent--;

    }

    public void visit(ASTWhile whileNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        String indentation = getIndent().toString();
        indent++;


        ASTExpression expression = whileNode.getExpression();
        ASTBlock block = whileNode.getBlock();

        System.out.println(indentation+"<While>");
        expression.accept(this);
        block.accept(this);
        System.out.println(indentation+"</While>");
        indent--;
    }

    public void visit(ASTExpression astExpression) {}
    public void visit(ASTStatement astStatement) {}

    public StringBuilder getIndent()
    {
        StringBuilder indentation = new StringBuilder("");

        for(int i =0; i < this.indent; i++)
            indentation.append("\t");

        return indentation;
    }

    public void generate(ASTProgram program) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        program.accept(this);
    }
}
