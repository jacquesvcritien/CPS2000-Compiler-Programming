package parser.node.statement;

import exceptions.*;
import parser.node.expression.ASTIdentifier;
import parser.node.expression.ASTExpression;
import visitor.Visitor;

/**
 * Class for variable declaration
 */
public class ASTVariableDecl extends ASTStatement {
    //identifier
    private ASTIdentifier identifier;
    //expression
    ASTExpression expression;

    /**
     * Empty constructor
     */
    public ASTVariableDecl(){};

    /**
     * Constructor
     * @param identifier identifier to set
     * @param expression expression to set
     */
    public ASTVariableDecl(ASTIdentifier identifier, ASTExpression expression)
    {
        this.identifier = identifier;
        this.expression = expression;
    }

    /**
     * Getter for expression
     * @return expression
     */
    public ASTExpression getExpression() {
        return expression;
    }

    /**
     * Getter for identifier
     * @return identifier
     */
    public ASTIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
