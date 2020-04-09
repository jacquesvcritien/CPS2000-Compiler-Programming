package parser.node;

public class ASTFunctionCall extends ASTExpression {
    ASTIdentifier identifier;
    ASTActualParams params;

    public ASTFunctionCall(ASTIdentifier identifier, ASTActualParams params)
    {
        this.identifier = identifier;
        this.params = params;
    }
}
