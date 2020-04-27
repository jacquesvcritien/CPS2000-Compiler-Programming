package lexer;

import exceptions.InvalidSyntaxException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * Class for Lexer
 */
public class Lexer
{
    //hashmap containing keywords
    private HashMap<String, Token> keywords = new HashMap<String, Token>(){{
        put("auto", Keyword.AUTO);
        put("int", Keyword.INT);
        put("float", Keyword.FLOAT);
        put("bool", Keyword.BOOL);
        put("if", Keyword.IF);
        put("else", Keyword.ELSE);
        put("for", Keyword.FOR);
        put("while", Keyword.WHILE);
        put("let", Keyword.LET);
        put("return", Keyword.RETURN);
        put("ff", Keyword.FF);
        put("print", Keyword.PRINT);
        put("true", Keyword.TRUE);
        put("false", Keyword.FALSE);
        put("not", Keyword.NOT);
        put("*", Operand.MULTIPLY);
        put("/", Operand.DIVIDE);
        put("+", Operand.ADDITION);
        put("-", Operand.SUBTRACT);
        put("and", Operand.AND_OP);
        put("or", Operand.OR_OP);
        put(">", Operand.GT);
        put("<", Operand.LT);
        put(">=", Operand.GTE);
        put("<=", Operand.LTE);
        put("==", Operand.EQUAL);
        put("<>", Operand.NOTEQUAL);
    }};
    //stack
    private Stack<State> stack = new Stack<State>();

    //map containing acceptable states
    private static Map<State, TypeToken> acceptableStates = new HashMap<State, TypeToken>();
    //map containing transition table
    private static final Map<Transition, State> transitionTable = new HashMap<Transition, State>();

    //used to read the file
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    //holds current line under execution
    private int currentLine = 1;

    /**
     * Constructor
     * @param filename filename to check
     * @throws FileNotFoundException
     * @throws URISyntaxException
     */
    public Lexer(String filename) throws FileNotFoundException, URISyntaxException {
        //get absolute path from resources path
        URL url = Lexer.class.getClassLoader().getResource(filename);
        File file = Paths.get(url.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();

        //read file
        fileReader = new FileReader(absolutePath);
        bufferedReader =new BufferedReader(fileReader);

        setAcceptableStates();
        setTransitionTable();
    }

    /**
     * Method to set the transition table
     */
    private void setTransitionTable() {
        Transition transition;

        /* ************ START ROW ************ */
        transition = new Transition(State.START, Category.DIGIT);
        transitionTable.put(transition, State.S1);
        transition = new Transition(State.START, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.START, Category.LETTER);
        transitionTable.put(transition, State.S4);
        transition = new Transition(State.START, Category.UNDERSCORE);
        transitionTable.put(transition, State.S4);
        transition = new Transition(State.START, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.S5);
        transition = new Transition(State.START, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.S6);
        transition = new Transition(State.START, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.S7);
        transition = new Transition(State.START, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.S8);
        transition = new Transition(State.START, Category.ST_OPERAND);
        transitionTable.put(transition, State.S9);
        transition = new Transition(State.START, Category.GT_OPERAND);
        transitionTable.put(transition, State.S10);
        transition = new Transition(State.START, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.S14);
        transition = new Transition(State.START, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.S16);
        transition = new Transition(State.START, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.S17);
        transition = new Transition(State.START, Category.CURLY_OPEN);
        transitionTable.put(transition, State.S18);
        transition = new Transition(State.START, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.S19);
        transition = new Transition(State.START, Category.COLON);
        transitionTable.put(transition, State.S20);
        transition = new Transition(State.START, Category.SEMI_COLON);
        transitionTable.put(transition, State.S21);
        transition = new Transition(State.START, Category.COMMA);
        transitionTable.put(transition, State.S22);
        transition = new Transition(State.START, Category.NEWLINE);
        transitionTable.put(transition, State.START);
        transition = new Transition(State.START, Category.SPACE);
        transitionTable.put(transition, State.START);
        transition = new Transition(State.START, Category.EOF);
        transitionTable.put(transition, State.S27);
        transition = new Transition(State.START, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S1 ROW ************ */
        transition = new Transition(State.S1, Category.DIGIT);
        transitionTable.put(transition, State.S1);
        transition = new Transition(State.S1, Category.DOT);
        transitionTable.put(transition, State.S2);
        transition = new Transition(State.S1, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S1, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S2 ROW ************ */
        transition = new Transition(State.S2, Category.DIGIT);
        transitionTable.put(transition, State.S3);
        transition = new Transition(State.S2, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S2, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S3 ROW ************ */
        transition = new Transition(State.S3, Category.DIGIT);
        transitionTable.put(transition, State.S3);
        transition = new Transition(State.S3, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S3, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S4 ROW ************ */
        transition = new Transition(State.S4, Category.DIGIT);
        transitionTable.put(transition, State.S4);
        transition = new Transition(State.S4, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.LETTER);
        transitionTable.put(transition, State.S4);
        transition = new Transition(State.S4, Category.UNDERSCORE);
        transitionTable.put(transition, State.S4);
        transition = new Transition(State.S4, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S4, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S5 ROW ************ */
        transition = new Transition(State.S5, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.S25);
        transition = new Transition(State.S5, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S5, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S6 ROW ************ */
        transition = new Transition(State.S6, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S6, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S6, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S6, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S7 ROW ************ */
        transition = new Transition(State.S7, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S7, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S8 ROW ************ */
        transition = new Transition(State.S8, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S8, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S9 ROW ************ */
        transition = new Transition(State.S9, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.GT_OPERAND);
        transitionTable.put(transition, State.S11);
        transition = new Transition(State.S9, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.S12);
        transition = new Transition(State.S9, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S9, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S10 ROW ************ */
        transition = new Transition(State.S10, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.S13);
        transition = new Transition(State.S10, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S10, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S11 ROW ************ */
        transition = new Transition(State.S11, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S11, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S12 ROW ************ */
        transition = new Transition(State.S12, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S12 ROW ************ */
        transition = new Transition(State.S12, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S12, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S13 ROW ************ */
        transition = new Transition(State.S13, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S13, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S14 ROW ************ */
        transition = new Transition(State.S14, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.S15);
        transition = new Transition(State.S14, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S14, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S15 ROW ************ */
        transition = new Transition(State.S15, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S15, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S16 ROW ************ */
        transition = new Transition(State.S16, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S16, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S17 ROW ************ */
        transition = new Transition(State.S17, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S17, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S18 ROW ************ */
        transition = new Transition(State.S18, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S18, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S19 ROW ************ */
        transition = new Transition(State.S19, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S19, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S20 ROW ************ */
        transition = new Transition(State.S20, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S20, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S21 ROW ************ */
        transition = new Transition(State.S21, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S21, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S22 ROW ************ */
        transition = new Transition(State.S22, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S22, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S23 ROW ************ */
        transition = new Transition(State.S23, Category.DIGIT);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.DOT);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.LETTER);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.UNDERSCORE);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.ST_OPERAND);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.GT_OPERAND);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.CURLY_OPEN);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.COLON);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.SEMI_COLON);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.COMMA);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.NEWLINE);
        transitionTable.put(transition, State.START);
        transition = new Transition(State.S23, Category.SPACE);
        transitionTable.put(transition, State.S23);
        transition = new Transition(State.S23, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S23, Category.OTHER);
        transitionTable.put(transition, State.S23);
        
        /* ************ S24 ROW ************ */
        transition = new Transition(State.S24, Category.DIGIT);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.DOT);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.LETTER);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.UNDERSCORE);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S24, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.ST_OPERAND);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.GT_OPERAND);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.CURLY_OPEN);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.COLON);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.SEMI_COLON);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.COMMA);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.NEWLINE);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.SPACE);
        transitionTable.put(transition, State.S24);
        transition = new Transition(State.S24, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S24, Category.OTHER);
        transitionTable.put(transition, State.S24);

        /* ************ S25 ROW ************ */
        transition = new Transition(State.S25, Category.DIGIT);
        transitionTable.put(transition, State.S1);
        transition = new Transition(State.S25, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S25, Category.LETTER);
        transitionTable.put(transition, State.S4);
        transition = new Transition(State.S25, Category.UNDERSCORE);
        transitionTable.put(transition, State.S4);
        transition = new Transition(State.S25, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.S5);
        transition = new Transition(State.S25, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.S6);
        transition = new Transition(State.S25, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.S7);
        transition = new Transition(State.S25, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.S8);
        transition = new Transition(State.S25, Category.ST_OPERAND);
        transitionTable.put(transition, State.S9);
        transition = new Transition(State.S25, Category.GT_OPERAND);
        transitionTable.put(transition, State.S10);
        transition = new Transition(State.S25, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.S14);
        transition = new Transition(State.S25, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.S16);
        transition = new Transition(State.S25, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.S17);
        transition = new Transition(State.S25, Category.CURLY_OPEN);
        transitionTable.put(transition, State.S18);
        transition = new Transition(State.S25, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.S19);
        transition = new Transition(State.S25, Category.COLON);
        transitionTable.put(transition, State.S20);
        transition = new Transition(State.S25, Category.SEMI_COLON);
        transitionTable.put(transition, State.S21);
        transition = new Transition(State.S25, Category.COMMA);
        transitionTable.put(transition, State.S22);
        transition = new Transition(State.S25, Category.NEWLINE);
        transitionTable.put(transition, State.START);
        transition = new Transition(State.S25, Category.SPACE);
        transitionTable.put(transition, State.START);
        transition = new Transition(State.S25, Category.EOF);
        transitionTable.put(transition, State.S27);
        transition = new Transition(State.S25, Category.OTHER);
        transitionTable.put(transition, State.BAD);

        /* ************ S26 ROW ************ */
        transition = new Transition(State.S26, Category.DIGIT);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.DOT);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.LETTER);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.UNDERSCORE);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.S25);
        transition = new Transition(State.S26, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.ST_OPERAND);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.GT_OPERAND);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.CURLY_OPEN);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.COLON);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.SEMI_COLON);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.COMMA);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.NEWLINE);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.SPACE);
        transitionTable.put(transition, State.S26);
        transition = new Transition(State.S26, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S26, Category.OTHER);
        transitionTable.put(transition, State.S26);

        /* ************ S27 ROW ************ */
        transition = new Transition(State.S27, Category.DIGIT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.DOT);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.LETTER);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.UNDERSCORE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.MULTIPLY_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.DIVIDE_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.ADDITION_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.SUBTRACT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.ST_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.GT_OPERAND);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.EQUAL_SIGN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.BRACKET_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.BRACKET_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.CURLY_OPEN);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.CURLY_CLOSE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.SEMI_COLON);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.COMMA);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.NEWLINE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.SPACE);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.EOF);
        transitionTable.put(transition, State.BAD);
        transition = new Transition(State.S27, Category.OTHER);
        transitionTable.put(transition, State.BAD);
    }

    /**
     * Method to set the acceptable states
     */
    private void setAcceptableStates()
    {
        acceptableStates.put(State.S1, TypeToken.INTEGER_LITERAL);
        acceptableStates.put(State.S3, TypeToken.FLOAT_LITERAL);
        acceptableStates.put(State.S4, TypeToken.IDENTIFIER);
        acceptableStates.put(State.S5, TypeToken.MULTIPLICATIVE_OP); //MULTIPLY_OPERAND
        acceptableStates.put(State.S6, TypeToken.MULTIPLICATIVE_OP); //DIVIDE_OPERAND
        acceptableStates.put(State.S7, TypeToken.ADDITIVE_OP); //ADDITION_OPERAND
        acceptableStates.put(State.S8, TypeToken.ADDITIVE_OP); //SUBTRACT_OPERAND
        acceptableStates.put(State.S9, TypeToken.RELATIONAL_OP); //ST_OPERAND
        acceptableStates.put(State.S10, TypeToken.RELATIONAL_OP); //GT_OPERAND
        acceptableStates.put(State.S11, TypeToken.RELATIONAL_OP); //NOTEQUAL_OPEAND
        acceptableStates.put(State.S12, TypeToken.RELATIONAL_OP); //STE_OPERAND
        acceptableStates.put(State.S13, TypeToken.RELATIONAL_OP); //GTE_OPERAND
        acceptableStates.put(State.S14, TypeToken.EQUAL_SIGN);
        acceptableStates.put(State.S15, TypeToken.RELATIONAL_OP); //EQUAL_OPERAND
        acceptableStates.put(State.S16, TypeToken.BRACKET_OPEN);
        acceptableStates.put(State.S17, TypeToken.BRACKET_CLOSE);
        acceptableStates.put(State.S18, TypeToken.CURLY_OPEN);
        acceptableStates.put(State.S19, TypeToken.CURLY_CLOSE);
        acceptableStates.put(State.S20, TypeToken.COLON);
        acceptableStates.put(State.S21, TypeToken.SEMI_COLON);
        acceptableStates.put(State.S22, TypeToken.COMMA);
        acceptableStates.put(State.S23, TypeToken.COMMENT_1LINE);
        acceptableStates.put(State.S25, TypeToken.COMMENT_MULTI_CLOSE);
        acceptableStates.put(State.S27, TypeToken.EOF);

    }

    /**
     * Method to get line
     * @return current line number
     */
    public int getCurrentLine() {
        return currentLine;
    }

    /**
     * Method to get next character
     * @return next chaarcter
     * @throws IOException
     */
    public char nextChar() throws IOException {

        //mark reader for when to rollback
        bufferedReader.mark(1);
        //get character value
        int char_val =  bufferedReader.read();

        //if new line increment line
        if((char) char_val == '\n')
            currentLine++;

        //return character from int ascii value
        return (char) char_val;
    }

    public void rollback() throws IOException {
        bufferedReader.reset();
    }

    /**
     * Get character's category
     * @param character character to check
     * @return character's category
     */
    public Category charCat(char character)
    {
        //if letter
        if(Character.isLetter(character))
        {
            return Category.LETTER;
        }
        //if digit
        else if (Character.isDigit(character))
        {
            return Category.DIGIT;
        }

        switch(character)
        {
            case '.' : return Category.DOT;
            case '*' : return Category.MULTIPLY_OPERAND;
            case '/' : return Category.DIVIDE_OPERAND;
            case '-' : return Category.SUBTRACT_OPERAND;
            case '+' : return Category.ADDITION_OPERAND;
            case '=' : return Category.EQUAL_SIGN;
            case '>' : return Category.GT_OPERAND;
            case '<' : return Category.ST_OPERAND;
            case '(' : return Category.BRACKET_OPEN;
            case ')' : return Category.BRACKET_CLOSE;
            case '{' : return Category.CURLY_OPEN;
            case '}' : return Category.CURLY_CLOSE;
            case ';' : return Category.SEMI_COLON;
            case ':' : return Category.COLON;
            case ',' : return Category.COMMA;
            case '_' : return Category.UNDERSCORE;
            case '\n' :
            case '\r' : return Category.NEWLINE;
            case ' ' : return Category.SPACE;
            case '\uFFFF' : return Category.EOF;
            default : return Category.OTHER;
        }

    }

    /**
     * Method to get next token
     * @return next token
     * @throws IOException
     * @throws InvalidSyntaxException
     */
    public Token nextToken() throws IOException, InvalidSyntaxException {
        //initialisation
        State previous = State.START;
        State state = State.START;
        StringBuilder lexeme = new StringBuilder("");
        stack.clear();
        char nextChar;
        stack.push(State.SE);

        //scanning loop
        while(state != State.BAD)
        {
            //get next character
            nextChar = nextChar();

            //if state is an acceptableState, clear stack
            if(acceptableStates.containsKey(state))
                stack.clear();

            //push state to stack
            stack.push(state);

            //get category
            Category cat = charCat(nextChar);

            //if you are in a comment, and encounter a star but the next character is not a'/' i.e. end of comment, clear lexeme
            if(previous ==State.S24 && state == State.S26 && cat != Category.DIVIDE_OPERAND) {
                lexeme.setLength(0);
                continue;
            }

            //set transition
            Transition transition = new Transition(state, cat);
            //set previous state
            previous = state;
            //get state
            state = transitionTable.get(transition);

            //if start of comment or end of comment
            if(state == State.S23 ||  state == State.S24 || state == State.S25)
            {
                //clear lexeme and continue, i.e. do not add character to lexeme
                lexeme.setLength(0);
                continue;
            }

            //If there was a whitespace or a newline at the start
            if((previous == State.START && state == State.START) ||
            //OR end of comment
            ((previous == State.S25)  && state == State.START) || previous == State.S23)
            {
                //do not add character to lexeme
                continue;
            }

            //add character to lexeme
            lexeme.append(nextChar);
        }

        //rollback loop
        while(!acceptableStates.containsKey(state))
        {
            //pop stack
            state = stack.pop();

            //if cannot reduce length, there must be an error
            if(lexeme.length()-1 < 0)
                throw new InvalidSyntaxException("LINE "+currentLine+": Syntax is invalid - Unexpected "+nextChar());

            //trim lexeme
            lexeme.setLength(lexeme.length()-1);
            rollback();
        }

        // if identifier, check if keyword
        if(acceptableStates.get(state) == TypeToken.IDENTIFIER)
            return (keywords.containsKey(lexeme.toString())) ?  keywords.get(lexeme.toString()) : new Token(acceptableStates.get(state), lexeme.toString());
        else
            return new Token(acceptableStates.get(state), lexeme.toString());

    }

    /**
     * Method to get tokens
     */
    public ArrayList<Token> getTokens() throws IOException, InvalidSyntaxException {
        ArrayList<Token> tokens = new ArrayList<>();
        Token nextToken;
        do{
            nextToken = nextToken();
            tokens.add(nextToken);
        }while(nextToken.getType() != TypeToken.EOF);

        return tokens;
    }

    /**
     * Method to compare 2 lists of tokens
     */
    public boolean compareTokens(ArrayList<Token> expected, ArrayList<Token> actual) throws IOException, InvalidSyntaxException {

        //check size
        if(expected.size() != actual.size())
            return false;

        //loop through and if you find one different return false
        for(int i=0; i < expected.size(); i++)
        {
            if(!expected.get(i).equals(actual.get(i)))
                return false;
        }

        return true;
    }

}
