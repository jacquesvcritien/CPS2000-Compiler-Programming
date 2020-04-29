package visitor;

import exceptions.AlreadyDeclaredException;
import parser.node.*;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Singleton class for symbol table
 */
public class SymbolTable {
    //for singleton
    private static SymbolTable symbolTable = new SymbolTable();
    //stack of scopes
    private Stack<Scope> scopes = new Stack<>();

    //variable to hold constant value for expressions
    private Object constantValue;
    //variable to hold constant type for expressions
    private Type constantType;

    /**
     * Private constructor
     */
    private SymbolTable(){
        this.scopes = new Stack<>();
    };

    /**
     * Instance getter for symbol table
     * @return symbol table
     */
    public static SymbolTable getSymbolTable()
    {
        return symbolTable;
    }

    /**
     * Method to get scopes
     * @return scopes
     */
    public Stack<Scope> getScopes() {
        return scopes;
    }

    /**
     * Method to get current scope
     * @return current scope
     */
    public Scope getCurrentScope()
    {
        return scopes.peek();
    }

    /**
     * Method to pop scope
     */
    public void popScope()
    {
        scopes.pop();
    }

    /**
     * Method to insert scope
     * @param scope scope to insert
     */
    public void insertScope(Scope scope)
    {
        scopes.add(scope);
    }

    /**
     * Method to insert declaration
     * @param identifier identifier to insert
     * @param node node for identifier
     * @throws AlreadyDeclaredException
     */
    public void insertDecl(String identifier, ASTNode node) throws AlreadyDeclaredException {
        //get current scope
        Scope current = getCurrentScope();

        //if defined, throw exception
        if(current.isDefined(identifier))
            throw new AlreadyDeclaredException(identifier+" is already declared");
        else
            current.addDeclaration(identifier, node);
    }

    /**
     * Method to insert a value in the first scope where it is declared
     * @param identifier identifier to add value to
     * @param value value to add
     */
    public void insertValue(String identifier, Object value){
        //iterator
        ListIterator<Scope> scopesIterator = scopes.listIterator(scopes.size());

        //loop through the iterator
        while (scopesIterator.hasPrevious()) {
            Scope scope = scopesIterator.previous();
            //if scope contains the identifier declared
            if(scope.getDeclarations().containsKey(identifier)) {
                //add value
                scope.addValue(identifier, value);
                break;
            }
        }
    }

    /**
     * Method to insert declaration in global scope
     * @param identifier identifier to insert
     * @param node node to insert
     * @throws AlreadyDeclaredException
     */
    public void insertDeclGlobal(String identifier, ASTNode node) throws AlreadyDeclaredException {
        //get global scope
        Scope global = scopes.firstElement();

        //if defined, throw an error
        if(global.isDefined(identifier) && identifier != "return")
            throw new AlreadyDeclaredException(identifier+" is already declared");
        else
            global.addDeclaration(identifier, node);
    }


    /**
     * Method to get declaration - LOOKUP
     * @param identifier id to get
     * @return node of identifier
     */
    public ASTNode lookup(String identifier)
    {
        //loop though scopes to see if the identifier was declared
        ListIterator<Scope> scopesIterator = scopes.listIterator(scopes.size());

        while (scopesIterator.hasPrevious()) {
            Scope scope = scopesIterator.previous();
            // if is declared in scope, return the identifier
            if(scope.isDefined(identifier))
                return scope.getDeclarations().get(identifier);
        }

        //else return null
        return null;
    }

    /**
     * Method to get value from where it is found first
     * @param identifier identifier to get
     * @return
     */
    public Object getValue(String identifier)
    {
        //loop though scopes to see if the identifier was declared
        ListIterator<Scope> scopesIterator = scopes.listIterator(scopes.size());

        while (scopesIterator.hasPrevious()) {
            Scope scope = scopesIterator.previous();
            //if scope has value, return it
            if(scope.getValues().containsKey(identifier))
                return scope.getValues().get(identifier);
        }

        //else return null
        return null;
    }

    /**
     * Method to get the global scope
     * @return global scope
     */
    public Scope getGlobalScope()
    {
        return scopes.get(0);
    }

    /**
     * Method to get constant value
     * @return constant value
     */
    public Object getConstantValue() {
        return constantValue;
    }

    /**
     * Method to set constant value
     * @param value value to set
     */
    public void setConstantValue(Object value) {
        this.constantValue = value;
    }

    /**
     * Method to get constant type
     * @return
     */
    public Type getConstant() {
        return constantType;
    }

    /**
     * Method to set constant
     * @param constant
     */
    public void setConstant(Type constant) {
        this.constantType = constant;
    }

    /**
     * Method to reset symbol table
     */
    public void reset()
    {
        symbolTable = new SymbolTable();
    }
}
