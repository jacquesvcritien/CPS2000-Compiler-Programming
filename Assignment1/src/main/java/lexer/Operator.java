package lexer;

/**
 * Class for operators extending Token
 */
public class Operator extends Token{

    /**
     * Constructor for operator
     * @param name type of operator
     * @param lexeme value
     */
    public Operator(TypeToken name, String lexeme) {
        super(name, lexeme);
    }

    public static final Operator MULTIPLY = new Operator(TypeToken.MULTIPLICATIVE_OP, "*");
    public static final Operator DIVIDE = new Operator(TypeToken.MULTIPLICATIVE_OP, "/");
    public static final Operator SUBTRACT = new Operator(TypeToken.ADDITIVE_OP, "-");
    public static final Operator ADDITION = new Operator(TypeToken.ADDITIVE_OP, "+");
    public static final Operator AND_OP = new Operator(TypeToken.MULTIPLICATIVE_OP, "and");
    public static final Operator OR_OP = new Operator(TypeToken.ADDITIVE_OP, "or");
    public static final Operator GT = new Operator(TypeToken.RELATIONAL_OP, ">");
    public static final Operator LT = new Operator(TypeToken.RELATIONAL_OP, "<");
    public static final Operator GTE = new Operator(TypeToken.RELATIONAL_OP, ">=");
    public static final Operator LTE = new Operator(TypeToken.RELATIONAL_OP, "<=");
    public static final Operator EQUAL = new Operator(TypeToken.RELATIONAL_OP, "==");
    public static final Operator NOTEQUAL = new Operator(TypeToken.RELATIONAL_OP, "<>");
}
