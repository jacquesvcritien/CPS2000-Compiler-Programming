package parser.node.statement;

import exceptions.*;
import lexer.Token;
import parser.node.ASTNode;
import visitor.Visitor;

/**
 * Class for statement node
 */
public class ASTStatement implements ASTNode {

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException{
        visitor.visit(this);
    }
}
