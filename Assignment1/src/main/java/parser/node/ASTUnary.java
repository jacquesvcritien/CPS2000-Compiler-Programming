package parser.node;

import exceptions.*;
import lexer.Token;
import visitor.Visitor;

public class ASTUnary extends ASTExpression {
    String lexeme;
    ASTExpression next;

    public ASTUnary(String lexeme, ASTExpression next)
    {
        this.lexeme = lexeme;
        this.next = next;
    }

    public ASTExpression getNext() {
        return next;
    }

    public String getLexeme() {
        return lexeme;
    }
    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
