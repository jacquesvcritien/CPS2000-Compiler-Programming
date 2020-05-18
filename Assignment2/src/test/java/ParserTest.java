import exceptions.*;
import lexer.Lexer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.node.ASTProgram;
import visitor.VisitorChecker;

import java.io.IOException;
import java.net.URISyntaxException;

public class ParserTest {

    VisitorChecker visitorChecker = new VisitorChecker();
    Parser parser;
    Lexer lexer;
    @Before
    public void setup(){
        visitorChecker.reset();
    }

    @After
    public void teardown() {
        parser = null;
        lexer = null;
        visitorChecker = null;
    }

    /**
     * Test for good array declaration
     */
    @Test
    public void testCorrectArrayDecl() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("parser/arraydeclCorrect.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();

        visitorChecker.visit(program);

        //assert sum
        Assert.assertEquals("Asserting sum", 116, visitorChecker.sum);
        //assert array size
        Assert.assertEquals("Asserting array size", 6, visitorChecker.visitedIndexes.size());

    }

    /**
     * Test for array declaration with no size
     */
    @Test
    public void testArrayDeclNoSize() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("parser/arraydeclNoSizeNoValue.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        visitorChecker.visit(program);

        System.out.println(visitorChecker.visitedIndexes.toString());
        //assert sum
        Assert.assertEquals("Asserting sum", 74, visitorChecker.sum);
        //assert array size
        Assert.assertEquals("Asserting array size", 3, visitorChecker.visitedIndexes.size());

    }

    /**
     * Test for good array declaration but with no value initialisation
     */
    @Test
    public void testArrayDeclNoInit() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("parser/arraydeclShort.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();

        visitorChecker.visit(program);

        System.out.println(visitorChecker.visitedIndexes.toString());
        //assert sum
        Assert.assertEquals("Asserting sum", 88, visitorChecker.sum);
        //assert array size
        Assert.assertEquals("Asserting array size", 4, visitorChecker.visitedIndexes.size());

    }

    /**
     * Test for char variable declaration
     */
    @Test
    public void testCharVarDecl() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("parser/chardecl.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        visitorChecker.visit(program);

        //assert sum
        Assert.assertEquals("Asserting sum", 58, visitorChecker.sum);
        //assert array size
        Assert.assertEquals("Asserting array size", 3, visitorChecker.visitedIndexes.size());

    }

    /**
     * Test for using an array element
     */
    @Test
    public void testUseArrayElement() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("parser/usearrayelement.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        visitorChecker.visit(program);


        //assert sum
        Assert.assertEquals("Asserting sum", 54, visitorChecker.sum);
        //assert array size
        Assert.assertEquals("Asserting array size", 3, visitorChecker.visitedIndexes.size());


    }

    /**
     * Test for using an assignment for an array value
     */
    @Test
    public void testArrayAssignment() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("parser/arrayassignment.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();

        visitorChecker.visit(program);

        //assert sum
        Assert.assertEquals("Asserting sum", 55, visitorChecker.sum);
        //assert array size
        Assert.assertEquals("Asserting array size", 4, visitorChecker.visitedIndexes.size());

    }

    /**
     * Test for using an an array formal parameter
     */
    @Test
    public void testArrayFormalParam() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("parser/arrayformalparam.txt");
        parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        visitorChecker.visit(program);


        //assert sum
        Assert.assertEquals("Asserting sum", 125, visitorChecker.sum);
        //assert array size
        Assert.assertEquals("Asserting array size", 9, visitorChecker.visitedIndexes.size());

    }


}
