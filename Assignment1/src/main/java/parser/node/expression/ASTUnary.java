package parser.node.expression;

import exceptions.*;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for unary node
 */
public class ASTUnary extends ASTExpression {

    //lexeme
    private String lexeme;
    // expression
    private ASTExpression expression;

    /**
     * Constructor
     * @param lexeme lexeme
     * @param expression expression
     */
    public ASTUnary(String lexeme, ASTExpression expression)
    {
        this.lexeme = lexeme;
        this.expression = expression;
    }

    /**
     * Getter for expression
     * @return expression
     */
    public ASTExpression getExpression() {
        return expression;
    }

    /**
     * Getter for lexeme
     * @return lexeme
     */
    public String getLexeme() {
        return lexeme;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
