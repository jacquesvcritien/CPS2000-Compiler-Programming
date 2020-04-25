package visitor;

import parser.node.ASTExpression;
import parser.node.ASTNode;
import parser.node.Type;

import java.util.HashMap;

public class Scope {
    private HashMap<String, ASTNode> declarations;
    private HashMap<String, Object> values;

    public Scope(){
        this.declarations = new HashMap<String, ASTNode>();
        this.values = new HashMap<String, Object>();
    };

    public void addDeclaration(String identifier, ASTNode node)
    {
        this.declarations.put(identifier, node);
    }
    public void removeDeclarations(String identifier)
    {
        this.declarations.remove(identifier);
    }

    public void addValue(String identifier, Object value)
    {
        this.values.put(identifier, value);
    }

    public HashMap<String, ASTNode> getDeclarations() {
        return declarations;
    }

    public ASTNode getDeclaration(String identifier)
    {
        return declarations.get(identifier);
    }

    public HashMap<String, Object> getValues() {
        return values;
    }

    public boolean isDefined(String identifier)
    {
        return declarations.containsKey(identifier);
    }


}
