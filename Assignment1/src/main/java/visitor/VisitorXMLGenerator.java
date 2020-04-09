package visitor;

import lexer.Token;
import parser.node.*;

public class VisitorXMLGenerator implements Visitor {
    private int indent = 0;

    public void visit(ASTActualParams actualParams) {

    }

    public void visit(ASTAdditiveOp additiveOp) {

    }

    public void visit(ASTAssignment assignment) {
        String indentation = getIndent().toString();
        this.indent++;

        ASTIdentifier identifier = assignment.getIdentifier();
        ASTExpression expression = assignment.getExpression();
        System.out.println(indentation+"<Assignment>");
        identifier.accept(this);
        expression.accept(this);

        indent--;
        System.out.println(indentation+"</Assignment>");
    }

    public void visit(ASTBinExpression expression) {
        String indentation = getIndent().toString();
        indent++;
        String operand = expression.getOperand();

        ASTExpression left = expression.getLeft();
        ASTExpression right = expression.getRight();


        System.out.println(indentation+"<BinaryExpr Op=\""+operand+"\">");
        left.accept(this);
        right.accept(this);
        indent--;
        System.out.println(indentation+"</BinaryExpr>");
    }

    public void visit(ASTBlock block) {

    }

    public void visit(ASTBooleanLiteral booleanLiteral) {

    }

    public void visit(ASTFloatLiteral floatLiteral) {

    }

    public void visit(ASTFor forNode) {

    }

    public void visit(ASTFormalParam formalParam) {

    }

    public void visit(ASTFormalParams formalParams) {

    }

    public void visit(ASTFunctionCall functionCall) {

    }

    public void visit(ASTFunctionDecl functionDecl) {

    }

    public void visit(ASTIdentifier identifier) {
        String indentation = getIndent().toString();

        String type = (identifier.getType() != null) ? " Type=\""+identifier.getType()+"\"" : "";
        System.out.println(indentation+"<Identifier"+type+">"+identifier.getValue()+"</Identifier>");
    }

    public void visit(ASTIf ifNode) {

    }

    public void visit(ASTIntegerLiteral integerLiteral) {
        String indentation = getIndent().toString();

        System.out.println(indentation+"<IntegerLiteral>"+integerLiteral.getValue()+"</IntegerLiteral>");

    }

    public void visit(ASTMultiplicativeOp multiplicativeOp) {

    }

    public void visit(ASTPrint printNode) {
        String indentation = getIndent().toString();
        indent++;
        ASTExpression expression = printNode.getExpression();
        System.out.println(indentation+"<Print>");
        expression.accept(this);

        indent--;
        System.out.println(indentation+"</Print>");
    }

    public void visit(ASTProgram programNode) {

        String indentation = getIndent().toString();
        this.indent++;
        System.out.println(indentation+"<Program>");
        for(int i=0; i <programNode.getStatements().size(); i++)
        {
            programNode.getStatements().get(i).accept(this);
        }
        System.out.println(indentation+"</Program>");
    }

    public void visit(ASTRelationalOp relationalOp) {

    }

    public void visit(ASTReturn returnNode) {

    }

    public void visit(ASTUnary unary) {

    }

    public void visit(ASTVariableDecl variableDecl) {
        String indentation = getIndent().toString();
        this.indent++;

        System.out.println(indentation+"<VarDecl>");
        ASTIdentifier identifier = variableDecl.getIdentifier();
        ASTExpression expression = variableDecl.getExpression();
        identifier.accept(this);
        expression.accept(this);

        indent--;

        System.out.println(indentation+"</VarDecl>");
    }

    public void visit(ASTWhile whileNode) {

    }

    public void visit(ASTExpression astExpression) {

    }

    public void visit(ASTStatement astStatement) {

    }

    public StringBuilder getIndent()
    {
        StringBuilder indentation = new StringBuilder("");

        for(int i =0; i < this.indent; i++)
            indentation.append("\t");

        return indentation;
    }
}
