package visitor;

import exceptions.AlreadyDeclaredException;
import parser.node.ASTExpression;
import parser.node.ASTIdentifier;
import parser.node.ASTNode;
import parser.node.ASTVariableDecl;

import java.util.HashMap;
import java.util.ListIterator;
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

    public void insertDecl(String identifier, ASTIdentifier identifierNode) throws AlreadyDeclaredException {
        Scope current = getCurrentScope();
        if(current.isDefined(identifier) && !identifier.equals("return"))
            throw new AlreadyDeclaredException(identifier+" is already declared");
        else if(current.isDefined(identifier) && identifier.equals("return"))
        {
            System.out.println("WARNING: Return statement already exists");
            current.addDeclaration(identifier, identifierNode);

        }
        else
            current.addDeclaration(identifier, identifierNode);
    }

    public void insertDeclGlobal(String identifier, ASTNode node) throws AlreadyDeclaredException {
        //get global scope
        Scope global = scopes.firstElement();
        if(global.isDefined(identifier))
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


}
