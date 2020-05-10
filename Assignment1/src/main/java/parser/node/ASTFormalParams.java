package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.UndeclaredException;
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
    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException, IncorrectTypeException {
        visitor.visit(this);
    }
}
