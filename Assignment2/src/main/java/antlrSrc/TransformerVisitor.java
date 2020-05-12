package antlrSrc;

import lexer.Token;
import lexer.TypeToken;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import parser.node.ASTProgram;
import parser.node.Type;
import parser.node.expression.ASTExpression;
import parser.node.expression.ASTFloatLiteral;
import parser.node.expression.ASTIdentifier;
import parser.node.expression.ASTIntegerLiteral;
import parser.node.statement.*;

import java.util.ArrayList;

public class TransformerVisitor extends SmallLangBaseVisitor<Object> {
    SmallLangLexer lexer;

    public TransformerVisitor(SmallLangLexer lexer)
    {
        this.lexer = lexer;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @return
     */
    @Override public ASTExpression visitLiteral(SmallLangParser.LiteralContext ctx) {

        //get type of literal
        TerminalNodeImpl terminalNode = (TerminalNodeImpl) ctx.children.get(0);
        //get actual name from vocabulary
        String type = this.lexer.getVocabulary().getSymbolicName(terminalNode.getSymbol().getType());

        switch (type)
        {
            case "IntegerLiteral" : return new ASTIntegerLiteral(new Token(TypeToken.INTEGER_LITERAL, terminalNode.toString()));
            case "FloatLiteral" : return new ASTFloatLiteral(new Token(TypeToken.FLOAT_LITERAL, terminalNode.toString()));
            default: return new ASTFloatLiteral(new Token(TypeToken.BOOLEAN_LITERAL, terminalNode.toString()));
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitActualParams(SmallLangParser.ActualParamsContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFunctionCall(SmallLangParser.FunctionCallContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitSubExpression(SmallLangParser.SubExpressionContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitUnary(SmallLangParser.UnaryContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFactor(SmallLangParser.FactorContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitTerm(SmallLangParser.TermContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitSimpleExpression(SmallLangParser.SimpleExpressionContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitExpression(SmallLangParser.ExpressionContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTAssignment visitAssignment(SmallLangParser.AssignmentContext ctx) {
        //get identifier name
        TerminalNodeImpl identifierToken = (TerminalNodeImpl) ctx.children.get(0);
        //get expression
        ParseTree expressionNode = ctx.children.get(2);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        //create identifier
        ASTIdentifier identifier = createIdentifier(null, identifierToken.toString());

        return new ASTAssignment(identifier, expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTVariableDecl visitVariableDecl(SmallLangParser.VariableDeclContext ctx) {
        //get identifier name
        TerminalNodeImpl identifierToken = (TerminalNodeImpl) ctx.children.get(1);
        //get identifier's name
        TerminalNodeImpl typeToken = (TerminalNodeImpl) ctx.children.get(3);

        //get expression
        ParseTree expressionNode = ctx.children.get(5);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        //create identifier
        ASTIdentifier identifier = createIdentifier(typeToken.toString(), identifierToken.toString());
        return new ASTVariableDecl(identifier, expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTPrint visitPrintStatement(SmallLangParser.PrintStatementContext ctx) {
        //get expression
        ParseTree expressionNode = ctx.children.get(1);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        return new ASTPrint(expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTReturn visitRtrnStatement(SmallLangParser.RtrnStatementContext ctx) {
        //get expression
        ParseTree expressionNode = ctx.children.get(1);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        return new ASTReturn(expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTIf visitIfStatement(SmallLangParser.IfStatementContext ctx) {
        //get expression
        ParseTree expressionNode = ctx.children.get(2);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        //get block
        ParseTree blockNode = ctx.children.get(4);
        ASTBlock block = (ASTBlock) blockNode.accept(this);

        ASTBlock elseBlock = new ASTBlock();
        //check if there is else, if there are more than 5 means that there is an else block
        if(ctx.children.size() > 5)
        {
            //get else block
            ParseTree elseBlockNode = ctx.children.get(6);
            elseBlock = (ASTBlock) elseBlockNode.accept(this);
        }

        return new ASTIf(expression, block, elseBlock);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitForStatement(SmallLangParser.ForStatementContext ctx) {
        //variable to hold the index of the expression
        int expressionIndex = -1;
        //variable to hold the index of the assignment
        int assignmentIndex = -1;
        //variable to hold the index of the block
        int blockIndex = -1;

        //variable to hold the declaration
        ASTVariableDecl variableDecl = new ASTVariableDecl();
        //variable to hold the expression
        ASTExpression expression = new ASTExpression();
        //variable to hold the assignment
        ASTAssignment assignment = new ASTAssignment();


        //check if there is a declaration
        //if there is a declaration
        if(ctx.children.get(2).getChildCount() > 0)
        {
            expressionIndex = 4;
            assignmentIndex = 6;
            blockIndex = 8;

            //get variable decl
            ParseTree variableDeclarationNode = ctx.children.get(2);
            variableDecl =  (ASTVariableDecl)variableDeclarationNode.accept(this);
        }
        //else if there is no declaration
        {
            expressionIndex = 3;
            assignmentIndex = 5;
            blockIndex = 7;
        }

        //get expression
        ParseTree expressionNode = ctx.children.get(expressionIndex);
        expression =  (ASTExpression)expressionNode.accept(this);

        //check if there is an assignment, and if so get it
        if(ctx.children.get(assignmentIndex).getChildCount() > 0) {
            //get assignment
            ParseTree assignmentNode = ctx.children.get(assignmentIndex);
            assignment =  (ASTAssignment) assignmentNode.accept(this);
        }
        //if there is not, reduce block index
        else
        {
            blockIndex--;
        }

        //get block
        ParseTree blockNode = ctx.children.get(4);
        ASTBlock block = (ASTBlock) blockNode.accept(this);

        return new ASTFor(variableDecl, expression, assignment, block);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitWhileStatement(SmallLangParser.WhileStatementContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFormalParam(SmallLangParser.FormalParamContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFormalParams(SmallLangParser.FormalParamsContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFunctionDecl(SmallLangParser.FunctionDeclContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTStatement visitStatement(SmallLangParser.StatementContext ctx) {
        //get actual statement
        ParseTree statement = ctx.children.get(0);
        return (ASTStatement)statement.accept(this);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTBlock visitBlock(SmallLangParser.BlockContext ctx) {
        //holds statements
        ArrayList<ASTStatement> statements = new ArrayList<ASTStatement>();

        //get the statements by not going through the first and last elements since they are used for curly brackets only
        for(int i =1; i < ctx.children.size()-1; i++)
        {
            ParseTree child = ctx.children.get(i);
            ASTStatement statement = (ASTStatement)child.accept(this);
            statements.add(statement);
        }

        return new ASTBlock(statements);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTProgram visitProgram(SmallLangParser.ProgramContext ctx) {
        //holds statements
        ArrayList<ASTStatement> statements = new ArrayList<ASTStatement>();

        for(int i =0; i < ctx.children.size(); i++)
        {
            ParseTree child = ctx.children.get(i);
            ASTStatement statement = (ASTStatement)child.accept(this);
            statements.add(statement);
        }

        return new ASTProgram(statements);

    }

    /**
     * Method to create an identifier node
     * @param type type of identifier
     * @param value value of identifier
     * @return
     */
    public ASTIdentifier createIdentifier(String type, String value)
    {
        //variable to hold actual type
        Type actualType = null;

        //if a type is passed, get actual type enum
        if(type != null)
            actualType = getTypeEnum(type);

        //return identifier
        return new ASTIdentifier(value, actualType);
    }

    /**
     * Method to get enum type from string
     * @param type in string
     * @return type in enum
     */
    public static Type getTypeEnum(String type)
    {
        //set type
        switch(type)
        {
            case "int" : return Type.INT;
            case "float" : return Type.FLOAT;
            case "bool" : return Type.BOOL;
            case "char" : return Type.CHAR;
            default : return Type.AUTO;
        }
    }

}
