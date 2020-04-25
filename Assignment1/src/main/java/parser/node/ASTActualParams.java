package parser.node;

import exceptions.*;
import visitor.Visitor;

import java.util.ArrayList;

public class ASTActualParams implements ASTNode {
    ArrayList<ASTExpression> expressions;

    public ASTActualParams() {
        this.expressions = new ArrayList<ASTExpression>();
    };
    public ASTActualParams(ArrayList<ASTExpression> expressions)
    {
        this.expressions = expressions;
    }

    public ArrayList<ASTExpression> getExpressions() {
        return expressions;
    }

    public void accept(Visitor visitor) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
