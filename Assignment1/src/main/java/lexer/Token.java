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

    public TypeToken getType() {
        return name;
    }

    public String getAttribute() {
        return attribute;
    }
}
