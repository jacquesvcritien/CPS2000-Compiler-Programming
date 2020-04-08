package lexer;

/**
 * Enum for type
 */
public enum Category {
    LETTER,
    DIGIT,
    DOT,
    MULTIPLY_OPERAND,
    DIVIDE_OPERAND,
    SUBTRACT_OPERAND,
    ADDITION_OPERAND,
    EQUAL_SIGN,
    GT_OPERAND,
    ST_OPERAND,
    BRACKET_OPEN,
    BRACKET_CLOSE,
    CURLY_OPEN,
    CURLY_CLOSE,
    SEMI_COLON,
    COLON,
    COMMA,
    UNDERSCORE,
    NEWLINE,
    SPACE,
    EOF,
    OTHER
}
