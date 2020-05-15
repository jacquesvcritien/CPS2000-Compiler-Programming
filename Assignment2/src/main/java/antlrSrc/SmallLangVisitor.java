// Generated from C:/Users/dell/Documents/School/Year 2/Compiler Theory/CPS2000/Assignment2/src/main/antlr4\SmallLang.g4 by ANTLR 4.8

package antlrSrc;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SmallLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SmallLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(SmallLangParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#multiplicativeOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeOp(SmallLangParser.MultiplicativeOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#additiveOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveOp(SmallLangParser.AdditiveOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#relationalOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOp(SmallLangParser.RelationalOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#actualParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParams(SmallLangParser.ActualParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(SmallLangParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#subExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubExpression(SmallLangParser.SubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(SmallLangParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(SmallLangParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(SmallLangParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(SmallLangParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SmallLangParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(SmallLangParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#variableDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDecl(SmallLangParser.VariableDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(SmallLangParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#rtrnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRtrnStatement(SmallLangParser.RtrnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(SmallLangParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(SmallLangParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(SmallLangParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#formalParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParam(SmallLangParser.FormalParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#formalParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParams(SmallLangParser.FormalParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(SmallLangParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SmallLangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SmallLangParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SmallLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SmallLangParser.ProgramContext ctx);
}