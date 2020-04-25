package visitor;

import exceptions.*;
import parser.node.*;

public interface Visitor {
    void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;
    void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException;
    void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;
    void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;
    void visit(ASTBooleanLiteral booleanLiteral);
    void visit(ASTFloatLiteral floatLiteral);
    void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;
    void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException, UndeclaredException;
    void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException;
    void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;
    void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;
    void visit(ASTIdentifier identifier) throws AlreadyDeclaredException, UndeclaredException;
    void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;
    void visit(ASTIntegerLiteral integerLiteral);
    void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;
    void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;
    void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;
    void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;
    void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException;
    void visit(ASTWhile whileNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;
    void visit(ASTExpression astExpression);
    void visit(ASTStatement astStatement);
}
