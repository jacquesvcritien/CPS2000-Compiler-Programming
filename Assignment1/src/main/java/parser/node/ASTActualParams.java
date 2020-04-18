package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
import visitor.Visitor;

import java.util.ArrayList;

public class ASTActualParams implements ASTNode {
    ArrayList<ASTExpression> expressions;

    public ASTActualParams() {};
    public ASTActualParams(ArrayList<ASTExpression> expressions)
    {
        this.expressions = expressions;
    }

    public ArrayList<ASTExpression> getExpressions() {
        return expressions;
    }

    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException {
        visitor.visit(this);
    }
}
