package lexer;

/**
 * Class for transition from state to Category
 */
public class Transition {
    private State startingState;
    private Category token;

    /**
     * Constructor
     * @param startingState starting state for transition
     * @param token category where this transition will take you
     */
    public Transition(State startingState, Category token)
    {
        this.startingState = startingState;
        this.token = token;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Transition))
            return false;

        Transition transition = (Transition) obj;
        return startingState == transition.startingState && token == transition.token;
    }

    @Override
    public int hashCode() {
        int result = startingState.hashCode();
        result = 31 * result + token.hashCode();
        return result;
    }
}
