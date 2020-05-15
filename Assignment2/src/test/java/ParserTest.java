import exceptions.InvalidSyntaxException;
import lexer.Lexer;
import lexer.Token;
import lexer.TypeToken;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.node.ASTProgram;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class ParserTest {


    Parser parser;
    Lexer lexer;
    @Before
    public void setup(){
    }

    @After
    public void teardown() {
        parser = null;
        lexer = null;
    }

    /**
     * Test for good array declaration
     */
    @Test
    public void testCorrectArrayDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("parser/arraydeclCorrect.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        Assert.assertNotNull("Asserting program is not null", program);

    }

    /**
     * Test for bad array declaration
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrectArrayDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("parser/arraydeclIncorrect.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();

    }

    /**
     * Test for good array declaration but with no value initialisation
     */
    @Test
    public void testArrayDeclNoInit() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("parser/arraydeclShort.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        Assert.assertNotNull("Asserting program is not null", program);
    }

    /**
     * Test for good array declaration with size 0
     */
    @Test
    public void testArrayDeclSize0() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("parser/arraydeclSize0.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        Assert.assertNotNull("Asserting program is not null", program);
    }

    /**
     * Test for char variable declaration
     */
    @Test
    public void testCharVarDecl() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("parser/chardecl.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        Assert.assertNotNull("Asserting program is not null", program);

    }

    /**
     * Test for using an array element
     */
    @Test
    public void testUseArrayElement() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("parser/usearrayelement.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        Assert.assertNotNull("Asserting program is not null", program);

    }

    /**
     * Test for using an assignment for an array value
     */
    @Test
    public void testArrayAssignment() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("parser/arrayassignment.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        Assert.assertNotNull("Asserting program is not null", program);

    }

    /**
     * Test for using an an array formal parameter
     */
    @Test
    public void testArrayFormalParam() throws IOException, URISyntaxException, InvalidSyntaxException {
        lexer = new Lexer("parser/arrayformalparam.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        Assert.assertNotNull("Asserting program is not null", program);

    }


}
