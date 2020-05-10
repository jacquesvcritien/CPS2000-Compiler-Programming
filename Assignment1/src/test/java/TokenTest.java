import lexer.Category;
import lexer.State;
import lexer.Token;
import lexer.TypeToken;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TokenTest {

    Token token;
    @Before
    public void setup() {
        token = new Token(TypeToken.IDENTIFIER, "x");
    }

    @After
    public void teardown() {
        token = null;
    }

    /**
     * Test for equals
     */
    @Test
    public void testEqualsWhenEqual() {
        Token newToken = new Token(TypeToken.IDENTIFIER, "x");
        Assert.assertTrue("Asserting token equal", token.equals(newToken));
    }

    /**
     * Test for equals not equal
     */
    @Test
    public void testEqualsWhenNotEqual() {
        Token newToken = new Token(TypeToken.IDENTIFIER, "y");
        Assert.assertFalse("Asserting token not equal", token.equals(newToken));
    }

    /**
     * Test for equals not equal
     */
    @Test
    public void testEqualsWhenNotEqual2() {
        Token newToken = new Token(TypeToken.INTEGER_LITERAL, "x");
        Assert.assertFalse("Asserting token not equal", token.equals(newToken));
    }

    /**
     * Test for equals not equal
     */
    @Test
    public void testEqualsWhenNotEqualBoth() {
        Token newToken = new Token(TypeToken.COMMA, ",");
        Assert.assertFalse("Asserting token not equal", token.equals(newToken));
    }

    /**
     * Test for equals when null
     */
    @Test
    public void testEqualsNull() {
        Assert.assertFalse("Asserting transitions not equal", token.equals(null));
    }

    /**
     * Test for equals when same object
     */
    @Test
    public void testEqualsSameObject() {
        Assert.assertTrue("Asserting transitions equal", token.equals(token));
    }

}
