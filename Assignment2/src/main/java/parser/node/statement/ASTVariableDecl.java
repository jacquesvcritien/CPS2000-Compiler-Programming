package parser.node;

import exceptions.*;
import lexer.Token;
import lexer.TypeToken;
import visitor.Visitor;

/**
 * Class for variable declaration
 */
public class ASTVariableDecl extends ASTDecl {
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
        super.identifier = identifier;
        this.expression = expression;
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
