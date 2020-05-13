package parser.node.statement.declaration;

import exceptions.*;
import parser.node.expression.ASTExpression;
import parser.node.expression.identifier.ASTIdentifier;
import visitor.Visitor;

/**
 * Class for variable declaration
 */
public class ASTVariableDecl extends ASTDecl {
    //expression
    ASTExpression expression;

    //identifier
    protected ASTIdentifier identifier;

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
     * Getter for identifier
     * @return identifier
     */
    public ASTIdentifier getIdentifier() {
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
    public void accept(Visitor visitor) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
