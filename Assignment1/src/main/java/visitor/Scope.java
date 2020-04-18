package visitor;

import parser.node.ASTExpression;
import parser.node.ASTNode;
import parser.node.Type;

import java.util.HashMap;

public class Scope {
    private HashMap<String, ASTNode> declarations;
    private Type constant;

    public Scope(){
        this.declarations = new HashMap<String, ASTNode>();
    };

    public void addDeclaration(String identifier, ASTNode node)
    {
        this.declarations.put(identifier, node);
    }

    public void removeDeclaration(String identifier)
    {
        this.declarations.remove(identifier);
    }

    public HashMap<String, ASTNode> getDeclarations()
    {
        return declarations;
    }
    public ASTNode getDeclaration(String identifier)
    {
        return declarations.get(identifier);
    }

    public boolean isDefined(String identifier)
    {
        return declarations.containsKey(identifier);
    }
    public Type getConstant() {
        return constant;
    }

    public void setConstant(Type constant) {
        this.constant = constant;
    }
}
