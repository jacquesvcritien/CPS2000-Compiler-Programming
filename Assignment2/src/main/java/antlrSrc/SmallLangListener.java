// Generated from C:/Users/dell/Documents/School/Year 2/Compiler Theory/CPS2000/Assignment2/src/main/antlr4\SmallLang.g4 by ANTLR 4.8

package antlrSrc;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SmallLangParser}.
 */
public interface SmallLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(SmallLangParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(SmallLangParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#actualParams}.
	 * @param ctx the parse tree
	 */
	void enterActualParams(SmallLangParser.ActualParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#actualParams}.
	 * @param ctx the parse tree
	 */
	void exitActualParams(SmallLangParser.ActualParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(SmallLangParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(SmallLangParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#subExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpression(SmallLangParser.SubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#subExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpression(SmallLangParser.SubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(SmallLangParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(SmallLangParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(SmallLangParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(SmallLangParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(SmallLangParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(SmallLangParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(SmallLangParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(SmallLangParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SmallLangParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SmallLangParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(SmallLangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(SmallLangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void enterVariableDecl(SmallLangParser.VariableDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void exitVariableDecl(SmallLangParser.VariableDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(SmallLangParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(SmallLangParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#rtrnStatement}.
	 * @param ctx the parse tree
	 */
	void enterRtrnStatement(SmallLangParser.RtrnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#rtrnStatement}.
	 * @param ctx the parse tree
	 */
	void exitRtrnStatement(SmallLangParser.RtrnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(SmallLangParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(SmallLangParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(SmallLangParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(SmallLangParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(SmallLangParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(SmallLangParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#formalParam}.
	 * @param ctx the parse tree
	 */
	void enterFormalParam(SmallLangParser.FormalParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#formalParam}.
	 * @param ctx the parse tree
	 */
	void exitFormalParam(SmallLangParser.FormalParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#formalParams}.
	 * @param ctx the parse tree
	 */
	void enterFormalParams(SmallLangParser.FormalParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#formalParams}.
	 * @param ctx the parse tree
	 */
	void exitFormalParams(SmallLangParser.FormalParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(SmallLangParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(SmallLangParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SmallLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SmallLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SmallLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SmallLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmallLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SmallLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmallLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SmallLangParser.ProgramContext ctx);
}