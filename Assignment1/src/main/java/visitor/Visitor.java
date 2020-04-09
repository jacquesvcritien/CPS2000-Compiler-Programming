package visitor;

import parser.node.*;

public interface Visitor {
    void visit(ASTActualParams actualParams);
    void visit(ASTAdditiveOp additiveOp);
    void visit(ASTAssignment assignment);
    void visit(ASTBinExpression expression);
    void visit(ASTBlock block);
    void visit(ASTBooleanLiteral booleanLiteral);
    void visit(ASTFloatLiteral floatLiteral);
    void visit(ASTFor forNode);
    void visit(ASTFormalParam formalParam);
    void visit(ASTFormalParams formalParams);
    void visit(ASTFunctionCall functionCall);
    void visit(ASTFunctionDecl functionDecl);
    void visit(ASTIdentifier identifier);
    void visit(ASTIf ifNode);
    void visit(ASTIntegerLiteral integerLiteral);
    void visit(ASTMultiplicativeOp multiplicativeOp);
    void visit(ASTPrint printNode);
    void visit(ASTProgram programNode);
    void visit(ASTRelationalOp relationalOp);
    void visit(ASTReturn returnNode);
    void visit(ASTUnary unary);
    void visit(ASTVariableDecl variableDecl);
    void visit(ASTWhile whileNode);
    void visit(ASTExpression astExpression);
    void visit(ASTStatement astStatement);
}
