import lexer.Category;
import lexer.State;
import lexer.Transition;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransitionTest {

    Transition transition;
    @Before
    public void setup() {
        transition = new Transition(State.START, Category.DIGIT);
    }

    @After
    public void teardown() {
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
