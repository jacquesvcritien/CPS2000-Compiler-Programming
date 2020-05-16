package visitor;

import exceptions.*;
import parser.node.*;
import parser.node.expression.*;
import parser.node.expression.identifier.ASTArrayIdentifier;
import parser.node.expression.identifier.ASTIdentifier;
import parser.node.statement.*;
import parser.node.statement.declaration.ASTArrayDecl;
import parser.node.statement.declaration.ASTDecl;
import parser.node.statement.declaration.ASTVariableDecl;

public interface Visitor {

    /**
     * Visitor method for actual params
     * @param actualParams actual params to visit
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws UndeclaredException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for assignment
     * @param assignment assignment to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException;


    /**
     * Visitor method for expression
     * @param expression expression to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for block
     * @param block block to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for boolean literal
     * @param booleanLiteral literal to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTBooleanLiteral booleanLiteral);

    /**
     * Visitor method for float literal
     * @param floatLiteral literal to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTFloatLiteral floatLiteral);

    /**
     * Visitor method for for
     * @param forNode for node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for formal param
     * @param formalParam formal parameter to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException;

    /**
     * Visitor method for formal params
     * @param formalParams formal parameters node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException;

    /**
     * Visitor method for function call
     * @param functionCall function call node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for function declaration
     * @param functionDecl function declaration to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for identifier
     * @param identifier identifier node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTIdentifier identifier) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException;

    /**
     * Visitor method for ifnode
     * @param ifNode if node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for integer literal
     * @param integerLiteral integer literal node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTIntegerLiteral integerLiteral);

    /**
     * Visitor method for print
     * @param printNode print node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for program
     * @param programNode program node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for return
     * @param returnNode return node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for unary
     * @param unary unary node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for variable declaration
     * @param variableDecl variable declaration node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for while
     * @param whileNode while node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTWhile whileNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException;

    /**
     * Visitor method for expression - NEVER USED
     * @param astExpression expression node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTExpression astExpression);

    /**
     * Visitor method for statement - NEVER USED
     * @param astStatement statement node to visit
     * @throws UndeclaredException
     * @throws AlreadyDeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     */
    void visit(ASTStatement astStatement);

    /**
     * Visitor method for array value
     * @param astArrayValue array value node to visit
     */
    void visit(ASTArrayValue astArrayValue) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException;

    /**
     * Visitor method for declaration
     * @param astDecl declaration node to visit
     */
    void visit(ASTDecl astDecl);

    /**
     * Visitor method for array identifier
     * @param arrayIdentifier identifier node to visit
     */
    void visit(ASTArrayIdentifier arrayIdentifier) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException;

    /**
     * Visitor method for array declaration
     * @param arrayDecl declaration node to visit
     */
    void visit(ASTArrayDecl arrayDecl) throws UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException, IncorrectTypeException;

    /**
     * Visitor method for character literal
     * @param characterLiteral character literal node to visit
     */
    void visit(ASTCharacterLiteral characterLiteral);

}
