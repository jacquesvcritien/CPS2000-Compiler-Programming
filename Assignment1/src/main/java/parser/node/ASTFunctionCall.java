package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
import visitor.Visitor;

public class ASTFunctionCall extends ASTExpression {
    ASTIdentifier identifier;
    ASTActualParams params;

    public ASTFunctionCall(ASTIdentifier identifier, ASTActualParams params)
    {
        this.identifier = identifier;
        this.params = params;
    }

    public ASTIdentifier getIdentifier() {
        return identifier;
    }

    public ASTActualParams getParams() {
        return params;
    }

    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException {
        visitor.visit(this);
    }
}
