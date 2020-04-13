package visitor;

import parser.node.ASTIdentifier;

import java.util.HashMap;
import java.util.Stack;

public class SymbolTable {
    private Stack<Scope> scopes;

    public SymbolTable(){
        this.scopes = new Stack<>();
    };

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
}
