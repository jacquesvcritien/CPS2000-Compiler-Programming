package parser.node;

import lexer.Token;
import lexer.TypeToken;

public class ASTRelationalOp extends ASTExpression {
    String value;

    //constructor
    public ASTRelationalOp(Token token)
    {
        this.value = token.getAttribute();
    }

}
