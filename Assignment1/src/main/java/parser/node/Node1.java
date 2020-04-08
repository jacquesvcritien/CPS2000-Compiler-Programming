package parser.node;

import lexer.Token;
import lexer.TypeToken;

public class Node1 extends Node{
    TypeToken typeToken;
    String value;

    //constructor
    public Node1(Token token)
    {
        this.typeToken = token.getType();
        this.value = token.getAttribute();
    }

    //getter for value
    public String getValue() {
        return value;
    }

    //getter for type
    public TypeToken getTypeToken() {
        return typeToken;
    }
}
