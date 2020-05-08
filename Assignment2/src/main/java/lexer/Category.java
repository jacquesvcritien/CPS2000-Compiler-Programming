package lexer;

/**
 * Enum for category
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
    SQUARE_OPEN,
    SQUARE_CLOSE,
    COLON,
    COMMA,
    UNDERSCORE,
    NEWLINE,
    SPACE,
    EOF,
    OTHER
}
