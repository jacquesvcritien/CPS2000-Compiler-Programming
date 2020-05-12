package parser.node.statement;

import exceptions.*;
import parser.node.expression.ASTIdentifier;
import visitor.Visitor;

/**
 * Class for function declaration node
 */
public class ASTFunctionDecl extends ASTStatement {
    //idenifier
    private ASTIdentifier identifier;
    //formal params
    private ASTFormalParams formalParams;
    //block
    private ASTBlock block;

    /**
     * Constructor
     * @param identifier identifier to set
     * @param formalParams formal params to set
     * @param block block
     */
    public ASTFunctionDecl(ASTIdentifier identifier, ASTFormalParams formalParams, ASTBlock block)
    {
        this.identifier = identifier;
        this.formalParams = formalParams;
        this.block = block;
    }

    /**
     * Getter for block
     * @return block
     */
    public ASTBlock getBlock() {
        return block;
    }

    /**
     * Getter for formal params
     * @return formal params
     */
    public ASTFormalParams getFormalParams() {
        return formalParams;
    }

    /**
     * Getter for identifier
     * @return identifier
     */
    public ASTIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(Visitor visitor) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException{
        visitor.visit(this);
    }

}
