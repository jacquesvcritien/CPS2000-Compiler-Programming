package lexer;

/**
 * Enum for type
 */
public enum TypeToken {
    INTEGER_LITERAL,
    FLOAT_LITERAL,
    BOOLEAN_LITERAL,
    IDENTIFIER,
    TYPE,
    AUTO,
    IF,
    ELSE,
    FOR,
    WHILE,
    LET,
    PRINT,
    RETURN,
    FF,
    MULTIPLICATIVE_OP,
    ADDITIVE_OP,
    RELATIONAL_OP,
    EQUAL_SIGN,
    BRACKET_OPEN,
    BRACKET_CLOSE,
    CURLY_OPEN,
    CURLY_CLOSE,
    SEMI_COLON,
    COLON,
    COMMA,
    COMMENT_1LINE,
    COMMENT_MULTI_CLOSE,
    EOF,
    NOT,
}
