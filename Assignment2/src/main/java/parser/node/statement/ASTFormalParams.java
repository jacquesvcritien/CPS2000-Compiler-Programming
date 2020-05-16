package parser.node.statement;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.ReturnTypeMismatchException;
import exceptions.UndeclaredException;
import parser.node.ASTNode;
import visitor.Visitor;

import java.util.ArrayList;

/**
 * Class for formal params node
 */
public class ASTFormalParams implements ASTNode {
    //formal params
    private ArrayList<ASTFormalParam> formalParams;

    /**
     * Constructor
     * @param formalParams formalParams
     */
    public ASTFormalParams(ArrayList<ASTFormalParam> formalParams)
    {
        this.formalParams = formalParams;
    }

    /**
     * Getter for formal params
     * @return
     */
    public ArrayList<ASTFormalParam> getFormalParams() {
        return formalParams;
    }

    @Override
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException {
        visitor.visit(this);
    }
}
