package lexer;


/**
 * Class for token
 */
public class Token {
    //enum holding type
    public TypeToken name;
    // String holding an identifier for the token
    public String attribute;

    /**
     * Constructor
     * @param name enum type
     * @param value value to add to the identifier
     */
    public Token(TypeToken name, Object value){
        this.name = name;
        this.attribute = value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Token))
            return false;

        Token token = (Token) obj;
        return name == token.getType() && attribute.equals(token.getAttribute());
    }

    /**
     * Getter for type
     * @return type
     */
    public TypeToken getType() {
        return name;
    }

    /**
     * Getter for attribute
     * @return attribute
     */
    public String getAttribute() {
        return attribute;
    }

}
