package parser.node.statement;

import exceptions.*;
import parser.node.expression.ASTExpression;
import parser.node.expression.identifier.ASTAbstractIdentifier;
import parser.node.expression.identifier.ASTIdentifier;
import visitor.Visitor;

/**
 * Class for assignment node
 */
public class ASTAssignment extends ASTStatement {
    //identifier
    private ASTAbstractIdentifier identifier;
    //expression
    private ASTExpression expression;

    /**
     * Empty constructor
     */
    public ASTAssignment(){};

    /**
     * Constructor
     * @param identifier identifier to set
     * @param expression expression to set
     */
    public ASTAssignment(ASTAbstractIdentifier identifier, ASTExpression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    /**
     * Getter for identifier
     * @return identifier
     */
    public ASTAbstractIdentifier getIdentifier() {
        return identifier;
    }

    /**
     * Getter for expression
     * @return expression
     */
    public ASTExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(Visitor visitor) throws UndeclaredException, AlreadyDeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        visitor.visit(this);
    }

}
