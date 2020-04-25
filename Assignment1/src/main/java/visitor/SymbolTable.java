package visitor;

import exceptions.AlreadyDeclaredException;
import parser.node.*;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Stack;

public class SymbolTable {
    private static SymbolTable symbolTable = new SymbolTable();
    private Stack<Scope> scopes = new Stack<>();
    private Object constantValue;
    private Type constantType;

    private SymbolTable(){
        this.scopes = new Stack<>();
    };

    public static SymbolTable getSymbolTable()
    {
        return symbolTable;
    }

    public Stack<Scope> getScopes() {
        return scopes;
    }

    public Scope getCurrentScope()
    {
        return scopes.peek();
    }

    public void popScope()
    {
        scopes.pop();
    }

    public void insertScope(Scope scope)
    {
        scopes.add(scope);
    }

    public void insertDecl(String identifier, ASTIdentifier identifierNode) throws AlreadyDeclaredException {
        Scope current = getCurrentScope();
        if(current.isDefined(identifier))
            throw new AlreadyDeclaredException(identifier+" is already declared");
        else
            current.addDeclaration(identifier, identifierNode);
    }

    /**
     * Method to insert a value in the first scope where it is declared
     * @param identifier identifier to add value to
     * @param value value to add
     */
    public void insertValue(String identifier, Object value){
        ListIterator<Scope> scopesIterator = scopes.listIterator(scopes.size());


        while (scopesIterator.hasPrevious()) {
            Scope scope = scopesIterator.previous();
            if(scope.getDeclarations().containsKey(identifier)) {
                scope.addValue(identifier, value);
                break;
            }
        }
    }

    public void insertDeclGlobal(String identifier, ASTNode node) throws AlreadyDeclaredException {
        //get global scope
        Scope global = scopes.firstElement();
        if(global.isDefined(identifier) && identifier != "return")
            throw new AlreadyDeclaredException(identifier+" is already declared");
        else
            global.addDeclaration(identifier, node);
    }



    public ASTNode getDeclaration(String identifier)
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

    public Object getValue(String identifier, boolean isFunctionCall)
    {
        //loop though scopes to see if the identifier was declared
        ListIterator<Scope> scopesIterator = scopes.listIterator(scopes.size());
        if(isFunctionCall)
            scopesIterator.previous();
        while (scopesIterator.hasPrevious()) {
            Scope scope = scopesIterator.previous();
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

    public Object getConstantValue() {
        return constantValue;
    }
    public void setConstantValue(Object value) {
        this.constantValue = value;
    }
    public Type getConstant() {
        return constantType;
    }

    public void reset()
    {
        symbolTable = new SymbolTable();
    }

    public void setConstant(Type constant) {
        this.constantType = constant;
    }


}
