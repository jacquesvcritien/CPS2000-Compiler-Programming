package lexer;

/**
 * Class for operands extending Token
 */
public class Operand extends Token{

    /**
     * Constructor for operand
     * @param name type of operand
     * @param lexeme value
     */
    public Operand(TypeToken name, String lexeme) {
        super(name, lexeme);
    }

    public static final Operand MULTIPLY = new Operand(TypeToken.MULTIPLICATIVE_OP, "*");
    public static final Operand DIVIDE = new Operand(TypeToken.MULTIPLICATIVE_OP, "/");
    public static final Operand SUBTRACT = new Operand(TypeToken.ADDITIVE_OP, "-");
    public static final Operand ADDITION = new Operand(TypeToken.ADDITIVE_OP, "+");
    public static final Operand AND_OP = new Operand(TypeToken.MULTIPLICATIVE_OP, "and");
    public static final Operand OR_OP = new Operand(TypeToken.ADDITIVE_OP, "or");
    public static final Operand GT = new Operand(TypeToken.RELATIONAL_OP, ">");
    public static final Operand LT = new Operand(TypeToken.RELATIONAL_OP, "<");
    public static final Operand GTE = new Operand(TypeToken.RELATIONAL_OP, ">=");
    public static final Operand LTE = new Operand(TypeToken.RELATIONAL_OP, "<=");
    public static final Operand EQUAL = new Operand(TypeToken.RELATIONAL_OP, "==");
    public static final Operand NOTEQUAL = new Operand(TypeToken.RELATIONAL_OP, "<>");
}
