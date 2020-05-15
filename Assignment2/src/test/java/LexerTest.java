import exceptions.InvalidSyntaxException;
import lexer.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class LexerTest {

    Lexer lexer;
    @Before
    public void setup() {
    }

    @After
    public void teardown() {
        lexer = null;
    }

    /**
     * Test for integer declaration
     */
    @Test
    public void testIntDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/intdecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "int"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for auto declaration
     */
    @Test
    public void testAutoDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/autodecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.AUTO, "auto"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for float declaration
     */
    @Test
    public void testFloatDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/floatdecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "float"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.FLOAT_LITERAL, "2.2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for bool declaration
     */
    @Test
    public void testBoolDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/booldecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "_x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "bool"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.BOOLEAN_LITERAL, "true"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "y"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "bool"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.BOOLEAN_LITERAL, "false"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }


    /**
     * Test for block with comments
     */
    @Test
    public void testBlockandComments() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/block.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.CURLY_OPEN, "{"),
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "int"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.CURLY_CLOSE, "}"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for expression operators
     */
    @Test
    public void testExpressionOperators() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/expressions.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.ADDITIVE_OP, "+"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.ADDITIVE_OP, "-"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.MULTIPLICATIVE_OP, "*"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.MULTIPLICATIVE_OP, "/"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.RELATIONAL_OP, ">"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.RELATIONAL_OP, ">="),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.RELATIONAL_OP, "<"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.RELATIONAL_OP, "<="),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.RELATIONAL_OP, "=="),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.RELATIONAL_OP, "<>"),
                new Token(TypeToken.INTEGER_LITERAL, "3"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.BOOLEAN_LITERAL, "true"),
                new Token(TypeToken.MULTIPLICATIVE_OP, "and"),
                new Token(TypeToken.BOOLEAN_LITERAL, "true"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.BOOLEAN_LITERAL, "true"),
                new Token(TypeToken.ADDITIVE_OP, "or"),
                new Token(TypeToken.BOOLEAN_LITERAL, "true"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.NOT, "not"),
                new Token(TypeToken.BOOLEAN_LITERAL, "true"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
                ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for function call
     */
    @Test
    public void testFunctionCall() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/functioncall.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "int"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.IDENTIFIER, "plus"),
                new Token(TypeToken.BRACKET_OPEN, "("),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.COMMA, ","),
                new Token(TypeToken.INTEGER_LITERAL, "5"),
                new Token(TypeToken.BRACKET_CLOSE, ")"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for if statement
     */
    @Test
    public void testIfStatement() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/ifstatement.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.IF, "if"),
                new Token(TypeToken.BRACKET_OPEN, "("),
                new Token(TypeToken.INTEGER_LITERAL, "1"),
                new Token(TypeToken.RELATIONAL_OP, "<"),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.BRACKET_CLOSE, ")"),
                new Token(TypeToken.CURLY_OPEN, "{"),
                new Token(TypeToken.PRINT, "print"),
                new Token(TypeToken.INTEGER_LITERAL, "1"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.CURLY_CLOSE, "}"),
                new Token(TypeToken.ELSE, "else"),
                new Token(TypeToken.CURLY_OPEN, "{"),
                new Token(TypeToken.PRINT, "print"),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.CURLY_CLOSE, "}"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for for statement
     */
    @Test
    public void testForLoop() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/forstatement.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.FOR, "for"),
                new Token(TypeToken.BRACKET_OPEN, "("),
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "int"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.RELATIONAL_OP, "<"),
                new Token(TypeToken.INTEGER_LITERAL, "10"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.ADDITIVE_OP, "+"),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.BRACKET_CLOSE, ")"),
                new Token(TypeToken.CURLY_OPEN, "{"),
                new Token(TypeToken.PRINT, "print"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.CURLY_CLOSE, "}"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for while statement
     */
    @Test
    public void testWhileLoop() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/whileloop.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.WHILE, "while"),
                new Token(TypeToken.BRACKET_OPEN, "("),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.RELATIONAL_OP, "<"),
                new Token(TypeToken.INTEGER_LITERAL, "10"),
                new Token(TypeToken.BRACKET_CLOSE, ")"),
                new Token(TypeToken.CURLY_OPEN, "{"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.ADDITIVE_OP, "+"),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.PRINT, "print"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.CURLY_CLOSE, "}"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for function declaration
     */
    @Test
    public void testFuncDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/funcdecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.FF, "ff"),
                new Token(TypeToken.IDENTIFIER, "plus"),
                new Token(TypeToken.BRACKET_OPEN, "("),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "int"),
                new Token(TypeToken.COMMA, ","),
                new Token(TypeToken.IDENTIFIER, "y"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "int"),
                new Token(TypeToken.BRACKET_CLOSE, ")"),
                new Token(TypeToken.CURLY_OPEN, "{"),
                new Token(TypeToken.RETURN, "return"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.ADDITIVE_OP, "+"),
                new Token(TypeToken.IDENTIFIER, "y"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.CURLY_CLOSE, "}"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertTrue("Asserting tokens", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for compare tokens for coverage
     */
    @Test
    public void testCompareTokensUnequalSize() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/intdecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertFalse("Asserting tokens not equal", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for compare tokens for coverage
     */
    @Test
    public void testCompareTokensNotTheSame() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/intdecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "float"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.INTEGER_LITERAL, "2"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertFalse("Asserting tokens not equal", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for array declaration
     */
    @Test
    public void testArrayDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/arraydecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.SQUARE_OPEN, "["),
                new Token(TypeToken.INTEGER_LITERAL, "w"),
                new Token(TypeToken.SQUARE_CLOSE, "]"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "int"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.CURLY_OPEN, "{"),
                new Token(TypeToken.INTEGER_LITERAL, "1"),
                new Token(TypeToken.COMMA, ","),
                new Token(TypeToken.INTEGER_LITERAL, "1"),
                new Token(TypeToken.CURLY_CLOSE, "}"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertFalse("Asserting tokens not equal", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for variable declaration of type char
     */
    @Test
    public void testCharVarDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/chardecl.txt");

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token(TypeToken.LET, "let"),
                new Token(TypeToken.IDENTIFIER, "x"),
                new Token(TypeToken.COLON, ":"),
                new Token(TypeToken.TYPE, "char"),
                new Token(TypeToken.EQUAL_SIGN, "="),
                new Token(TypeToken.CHARACTER_LITERAL, "'x'"),
                new Token(TypeToken.SEMI_COLON, ";"),
                new Token(TypeToken.EOF, "\uFFFF")
        ));

        Assert.assertFalse("Asserting tokens not equal", lexer.compareTokens(expected, lexer.getTokens()));
    }

    /**
     * Test for file which does not exist
     */
    @Test(expected = FileNotFoundException.class)
    public void testForBadFile() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("lexer/badfilename.txt");
    }


}
