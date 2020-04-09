package parser.node;

import lexer.Token;
import lexer.TypeToken;

public class ASTAdditiveOp extends ASTExpression {
    String value;

    //constructor
    public ASTAdditiveOp(Token token)
    {
        this.value = token.getAttribute();
    }

}
