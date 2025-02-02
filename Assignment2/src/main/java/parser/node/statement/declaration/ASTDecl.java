package parser.node.statement.declaration;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.ReturnTypeMismatchException;
import exceptions.UndeclaredException;
import parser.node.statement.ASTStatement;
import visitor.Visitor;

/**
 * Class for a declaration
 */
public class ASTDecl extends ASTStatement
{

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, AlreadyDeclaredException, UndeclaredException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
