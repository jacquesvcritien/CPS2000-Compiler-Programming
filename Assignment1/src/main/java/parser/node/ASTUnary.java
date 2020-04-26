package parser.node;

import exceptions.*;
import lexer.Token;
import visitor.Visitor;

/**
 * Class for unary node
 */
public class ASTUnary extends ASTExpression {

    //lexeme
    private String lexeme;
    // expression
    private ASTExpression next;

    /**
     * Constructor
     * @param lexeme lexeme
     * @param next expression
     */
    public ASTUnary(String lexeme, ASTExpression next)
    {
        this.lexeme = lexeme;
        this.next = next;
    }

    /**
     * Getter for expression
     * @return expression
     */
    public ASTExpression getNext() {
        return next;
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
