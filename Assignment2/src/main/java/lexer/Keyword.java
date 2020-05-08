package lexer;

/**
 * Class for keywords extending Token
 */
public class Keyword extends Token{

    /**
     * Constructor for KeyWord
     * @param name type of keyword
     * @param lexeme value
     */
    public Keyword(TypeToken name, String lexeme) {
        super(name, lexeme);
    }

    public static final Keyword INT = new Keyword(TypeToken.TYPE, "int");
    public static final Keyword FLOAT = new Keyword(TypeToken.TYPE, "float");
    public static final Keyword BOOL = new Keyword(TypeToken.TYPE, "bool");
    public static final Keyword CHAR = new Keyword(TypeToken.TYPE, "char");
    public static final Keyword AUTO = new Keyword(TypeToken.AUTO, "auto");
    public static final Keyword IF = new Keyword(TypeToken.IF, "if");
    public static final Keyword ELSE = new Keyword(TypeToken.ELSE, "else");
    public static final Keyword FOR = new Keyword(TypeToken.FOR, "for");
    public static final Keyword WHILE = new Keyword(TypeToken.WHILE, "while");
    public static final Keyword LET = new Keyword(TypeToken.LET, "let");
    public static final Keyword RETURN = new Keyword(TypeToken.RETURN, "return");
    public static final Keyword FF = new Keyword(TypeToken.FF, "ff");
    public static final Keyword PRINT = new Keyword(TypeToken.PRINT, "print");
    public static final Keyword FALSE = new Keyword(TypeToken.BOOLEAN_LITERAL, "false");
    public static final Keyword TRUE = new Keyword(TypeToken.BOOLEAN_LITERAL, "true");
    public static final Keyword NOT = new Keyword(TypeToken.NOT, "not");

}
