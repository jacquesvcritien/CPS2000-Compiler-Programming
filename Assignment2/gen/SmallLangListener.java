// Generated from C:/Users/dell/Documents/School/Year 2/Compiler Theory/CPS2000/Assignment2/src/main/antlr4\SmallLang.g4 by ANTLR 4.8
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
}