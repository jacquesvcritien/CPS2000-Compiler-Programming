// Generated from C:/Users/dell/Documents/School/Year 2/Compiler Theory/CPS2000/Assignment2/src/main/antlr4\SmallLang.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link SmallLangVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class SmallLangBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements SmallLangVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitLiteral(SmallLangParser.LiteralContext ctx) { return visitChildren(ctx); }
}