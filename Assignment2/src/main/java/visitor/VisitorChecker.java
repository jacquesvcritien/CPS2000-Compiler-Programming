package visitor;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.ReturnTypeMismatchException;
import exceptions.UndeclaredException;
import parser.node.ASTProgram;
import parser.node.Type;
import parser.node.expression.*;
import parser.node.expression.identifier.ASTAbstractIdentifier;
import parser.node.expression.identifier.ASTArrayIdentifier;
import parser.node.expression.identifier.ASTIdentifier;
import parser.node.statement.*;
import parser.node.statement.declaration.ASTArrayDecl;
import parser.node.statement.declaration.ASTDecl;
import parser.node.statement.declaration.ASTVariableDecl;

import java.util.ArrayList;

/**
 * Visitor class for testing
 */
public class VisitorChecker implements Visitor {

    //holds visited indexes
    public ArrayList<Integer> visitedIndexes = new ArrayList<Integer>();
    //holds sum
    public int sum = 0;

    @Override
    public void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        int index = 1;
        sum+=index;
        visitedIndexes.add(index);

        for(ASTExpression expression: actualParams.getExpressions())
            expression.accept(this);
    }

    @Override
    public void visit(ASTAssignment assignment) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        int index = 2;
        sum+=index;
        visitedIndexes.add(index);
        assignment.getIdentifier().accept(this);
        assignment.getExpression().accept(this);
    }

    @Override
    public void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        int index = 3;
        sum+=index;
        visitedIndexes.add(index);
        expression.getLeft().accept(this);
        expression.getRight().accept(this);
    }

    @Override
    public void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        int index = 4;
        sum+=index;
        visitedIndexes.add(index);

        for(ASTStatement statement: block.getStatements())
            statement.accept(this);

    }

    @Override
    public void visit(ASTBooleanLiteral booleanLiteral) {
        int index = 5;
        sum+=index;
        visitedIndexes.add(index);
    }

    @Override
    public void visit(ASTFloatLiteral floatLiteral) {
        int index = 6;
        sum+=index;
        visitedIndexes.add(index);
    }

    @Override
    public void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        int index = 7;
        sum+=index;
        visitedIndexes.add(index);
        forNode.getAssignment().accept(this);
        forNode.getBlock().accept(this);
        forNode.getDeclaration().accept(this);
        forNode.getExpression().accept(this);
    }

    @Override
    public void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        int index = 8;
        sum+=index;
        visitedIndexes.add(index);
        formalParam.getIdentifier().accept(this);
    }

    @Override
    public void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        int index = 9;
        sum+=index;
        visitedIndexes.add(index);

        ArrayList<ASTFormalParam> formalParams1 = formalParams.getFormalParams();

        for(ASTFormalParam formalParam: formalParams1)
            formalParam.accept(this);
    }

    @Override
    public void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        int index = 10;
        sum+=index;
        visitedIndexes.add(index);
        functionCall.getIdentifier().accept(this);
        functionCall.getParams().accept(this);
    }

    @Override
    public void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        int index = 11;
        sum+=index;
        visitedIndexes.add(index);
        functionDecl.getFormalParams().accept(this);
        functionDecl.getIdentifier().accept(this);
        functionDecl.getBlock().accept(this);
    }

    @Override
    public void visit(ASTIdentifier identifier) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
        int index = 12;
        sum+=index;
        visitedIndexes.add(index);
    }

    @Override
    public void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        int index = 13;
        sum+=index;
        visitedIndexes.add(index);
        ifNode.getExpression().accept(this);
        ifNode.getBlock().accept(this);
        ifNode.getElseBlock().accept(this);
    }

    @Override
    public void visit(ASTIntegerLiteral integerLiteral) {
        int index = 14;
        sum+=index;
        visitedIndexes.add(index);
    }

    @Override
    public void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        int index = 15;
        sum+=index;
        visitedIndexes.add(index);
        printNode.getExpression().accept(this);
    }

    @Override
    public void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        ArrayList<ASTStatement> statements = programNode.getStatements();

        for(ASTStatement statement : statements)
            statement.accept(this);
    }

    @Override
    public void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        int index = 17;
        sum+=index;
        visitedIndexes.add(index);
        returnNode.getExpression().accept(this);
    }

    @Override
    public void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        int index = 18;
        sum+=index;
        visitedIndexes.add(index);
        unary.getExpression().accept(this);
    }

    @Override
    public void visit(ASTVariableDecl variableDecl) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        int index = 19;
        sum+=index;
        visitedIndexes.add(index);
        variableDecl.getExpression().accept(this);
        variableDecl.getIdentifier().accept(this);
    }

    @Override
    public void visit(ASTWhile whileNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        int index = 20;
        sum+=index;
        visitedIndexes.add(index);
        whileNode.getExpression().accept(this);
        whileNode.getBlock().accept(this);
    }

    @Override
    public void visit(ASTExpression astExpression) {
        int index = 21;
        sum+=index;
        visitedIndexes.add(index);
    }

    @Override
    public void visit(ASTStatement astStatement) {
        int index = 22;
        sum+=index;
        visitedIndexes.add(index);
    }

    @Override
    public void visit(ASTArrayValue astArrayValue) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        int index = 23;
        sum+=index;
        visitedIndexes.add(index);

        ArrayList<ASTExpression> values = astArrayValue.getValues();

        for(ASTExpression value : values)
            value.accept(this);
    }

    @Override
    public void visit(ASTDecl astDecl) {
        int index = 24;
        sum+=index;
        visitedIndexes.add(index);
    }

    @Override
    public void visit(ASTArrayIdentifier arrayIdentifier) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        int index = 25;
        sum+=index;
        visitedIndexes.add(index);
        if(arrayIdentifier.getSizeIndex() != null)
            arrayIdentifier.getSizeIndex().accept(this);

    }

    @Override
    public void visit(ASTArrayDecl arrayDecl) throws UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException, IncorrectTypeException {
        int index = 26;
        sum+=index;
        visitedIndexes.add(index);
        arrayDecl.getIdentifier().accept(this);
        arrayDecl.getValues().accept(this);
    }

    @Override
    public void visit(ASTCharacterLiteral characterLiteral) {
        int index = 27;
        sum+=index;
        visitedIndexes.add(index);
    }

    /**
     * Method to reset
     */
    public void reset()
    {
        visitedIndexes.clear();
        sum = 0;
    }
}
