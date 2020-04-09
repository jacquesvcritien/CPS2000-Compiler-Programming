package parser.node;

import lexer.Token;
import lexer.TypeToken;

public class ASTMultiplicativeOp extends ASTExpression {
    String value;

    //constructor
    public ASTMultiplicativeOp(Token token)
    {
        this.value = token.getAttribute();
    }

}
