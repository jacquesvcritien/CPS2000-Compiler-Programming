package parser.node;

import exceptions.AlreadyDeclaredException;
import exceptions.UndeclaredException;
import visitor.Visitor;

import java.util.ArrayList;

public class ASTFormalParams implements ASTNode {
    ArrayList<ASTFormalParam> formalParams;

    public ASTFormalParams(ArrayList<ASTFormalParam> expressions)
    {
        this.formalParams = expressions;
    }

    public ArrayList<ASTFormalParam> getFormalParams() {
        return formalParams;
    }

    public void accept(Visitor visitor) throws AlreadyDeclaredException, UndeclaredException {
        visitor.visit(this);
    }
}
