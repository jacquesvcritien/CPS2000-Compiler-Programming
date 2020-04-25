package parser.node;

import exceptions.*;
import visitor.Visitor;

public class ASTAssignment extends ASTStatement {
    ASTIdentifier identifier;
    ASTExpression expression;

    public ASTAssignment(){};
    public ASTAssignment(ASTIdentifier identifier, ASTExpression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public ASTIdentifier getIdentifier() {
        return identifier;
    }

    public ASTExpression getExpression() {
        return expression;
    }
    public void accept(Visitor visitor) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        visitor.visit(this);
    }

}
