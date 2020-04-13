package visitor;

import parser.node.ASTIdentifier;

import java.util.HashMap;

public class Scope {
    private HashMap<String, ASTIdentifier> declarations;

    public Scope(){};

    public void addDeclaration(String identifier, ASTIdentifier identifierNode)
    {
        this.declarations.put(identifier, identifierNode);
    }

    public HashMap<String, ASTIdentifier> getDeclarations()
    {
        return declarations;
    }

    public boolean isDefined(String identifier)
    {
        return declarations.containsKey(identifier);
    }

    public String getTypeOfIdentifier(String identifer)
    {
        return declarations.get(identifer).getType();
    }
}
