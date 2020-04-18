package visitor;

import exceptions.*;
import parser.node.*;

public interface Visitor {
    void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException;
    void visit(ASTAdditiveOp additiveOp);
    void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException;
    void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException;
    void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException;
    void visit(ASTBooleanLiteral booleanLiteral);
    void visit(ASTFloatLiteral floatLiteral);
    void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException;
    void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException, UndeclaredException;
    void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException;
    void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException;
    void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException, InvalidNodeException;
    void visit(ASTIdentifier identifier) throws AlreadyDeclaredException, UndeclaredException;
    void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException;
    void visit(ASTIntegerLiteral integerLiteral);
    void visit(ASTMultiplicativeOp multiplicativeOp);
    void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException;
    void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException;
    void visit(ASTRelationalOp relationalOp);
    void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, InvalidNodeException, UndeclaredException;
    void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException;
    void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException;
    void visit(ASTWhile whileNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, InvalidNodeException, ReturnTypeMismatchException;
    void visit(ASTExpression astExpression);
    void visit(ASTStatement astStatement);
}
