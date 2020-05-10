package visitor;

import parser.node.ASTNode;

import java.util.HashMap;

/**
 * Class for scope
 */
public class Scope {
    //map of string to Node to hold declarations
    private HashMap<String, ASTNode> declarations;
    //map of string to object for values
    private HashMap<String, Object> values;

    /**
     * Constructor
     */
    public Scope(){
        this.declarations = new HashMap<String, ASTNode>();
        this.values = new HashMap<String, Object>();
    };

    /**
     * Method to add Declaration
     * @param identifier identifier to add
     * @param node node to add
     */
    public void addDeclaration(String identifier, ASTNode node)
    {
        this.declarations.put(identifier, node);
    }

    /**
     * Method to remove delcarations
     * @param identifier identifier to remove
     */
    public void removeDeclarations(String identifier)
    {
        this.declarations.remove(identifier);
    }

    /**
     * Method to add Value
     * @param identifier identifier to add
     * @param value value to add
     */
    public void addValue(String identifier, Object value)
    {
        this.values.put(identifier, value);
    }

    /**
     * Method to get declarations
     * @return declarations
     */
    public HashMap<String, ASTNode> getDeclarations() {
        return declarations;
    }

    /**
     * Method to get values
     * @return values
     */
    public HashMap<String, Object> getValues() {
        return values;
    }

    /**
     * Method to check if an id is set
     * @param identifier identifier to check
     * @return true if defined
     */
    public boolean isDefined(String identifier)
    {
        return declarations.containsKey(identifier);
    }


}
