import exceptions.*;
import lexer.Category;
import lexer.Lexer;
import lexer.State;
import lexer.Transition;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.node.ASTExpression;
import parser.node.ASTProgram;
import parser.node.ASTStatement;
import visitor.VisitorInterpreter;
import visitor.VisitorXMLGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

public class TransitionTest {

    Transition transition;
    @Before
    public void setUpStreams() {
        transition = new Transition(State.START, Category.DIGIT);
    }

    @After
    public void restoreStreams() {
        transition = null;
    }

    /**
     * Test for equals
     */
    @Test
    public void testEqualsWhenEqual() {
        Transition newTransition = new Transition(State.START, Category.DIGIT);
        Assert.assertTrue("Asserting transitions equal", transition.equals(newTransition));
    }

    /**
     * Test for equals not equal
     */
    @Test
    public void testEqualsWhenNotEqual() {
        Transition newTransition = new Transition(State.S2, Category.DIGIT);
        Assert.assertFalse("Asserting transitions not equal", transition.equals(newTransition));
    }

    /**
     * Test for equals not equal
     */
    @Test
    public void testEqualsWhenNotEqual2() {
        Transition newTransition = new Transition(State.START, Category.ADDITION_OPERAND);
        Assert.assertFalse("Asserting transitions not equal", transition.equals(newTransition));
    }

    /**
     * Test for equals not equal
     */
    @Test
    public void testEqualsWhenNotEqualBoth() {
        Transition newTransition = new Transition(State.S5, Category.ADDITION_OPERAND);
        Assert.assertFalse("Asserting transitions not equal", transition.equals(newTransition));
    }

    /**
     * Test for equals when null
     */
    @Test
    public void testEqualsNull() {
        Assert.assertFalse("Asserting transitions not equal", transition.equals(null));
    }

    /**
     * Test for equals when same object
     */
    @Test
    public void testEqualsSameObject() {
        Assert.assertTrue("Asserting transitions equal", transition.equals(transition));
    }

}