import exceptions.*;
import lexer.Lexer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.node.*;
import parser.node.expression.ASTActualParams;
import parser.node.expression.ASTExpression;
import parser.node.statement.ASTFormalParams;
import parser.node.statement.ASTStatement;
import visitor.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class IntegrationTest {

    /**
     * Method to get absolute path for filename
     * @param filename filename to get
     * @throws URISyntaxException when file does not exist
     * @return Absolute path for file
     */
    public String getAbsolutePath(String filename) throws URISyntaxException {
        //get absolute path from resources path
        URL url = Lexer.class.getClassLoader().getResource(filename);
        File file = Paths.get(url.toURI()).toFile();
        return file.getAbsolutePath();
    }

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream original = System.out;

    Lexer lexer;
    Parser parser;
    VisitorXMLGenerator xml = new VisitorXMLGenerator();
    VisitorSemanticAnalysis semanticAnalysis = new VisitorSemanticAnalysis();
    VisitorInterpreter interpreter = new VisitorInterpreter();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void restoreStreams() {
        System.setOut(original);
    }

    @Test
    public void test1() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test1.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", "1\r\n2\r\n0.1\r\n0.2\r\n0.3\r\n0.4\r\n0.5\r\n1\r\n2\r\n3\r\n4\r\n5\r\ntrue\r\n", output.toString());
    }

    @Test
    public void test2() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test2.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", "12\r\n4\r\n2\r\n15.200001\r\nfalse\r\n", output.toString());
    }

    @Test
    public void test3() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test3.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", "16\r\n3.0\r\ntrue\r\n", output.toString());
    }

    @Test
    public void test4() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test4.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", "1\r\n1\r\n", output.toString());
    }

    /**
     * Test involving incorrect symbol
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void test5IncorrectSymbol() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test5.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrect1() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid3.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrect2() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid4.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrect3() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid5.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrect4() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid6.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrect5() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid7.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrect6() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid8.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrect7() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid9.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void testIncorrect8() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid10.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving incorrect syntax
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = InvalidSyntaxException.class)
    public void test6IncorrectSyntax() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test6.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test for setting incorrect type
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testIncorrectType() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test7.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for setting value to an undeclared variable
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = UndeclaredException.class)
    public void testUndeclared() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test8.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for declaring a varibel with a taken identifier
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = AlreadyDeclaredException.class)
    public void testAlreadyDeclared() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test9.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for function with a bad return type
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = ReturnTypeMismatchException.class)
    public void testBadReturn() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test10.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for when assigning an undeclared variable
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = UndeclaredException.class)
    public void testAssignmentUndeclared() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test11.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for when assigning a wrong type to an int variable
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testAssignmentWrongInt() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test12.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for when assigning a wrong type to a float variable
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testAssignmentWrongFloat() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test13.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for when assigning a wrong type to a float variable
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testAssignmentWrongBoolean() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test14.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for evaluating expression with different types
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testExpressionDifferentTypes() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test15.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for evaluating incorrect expression operator use for non booleans
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testExpressionBadOperatorsNotBoolean() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test16.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for evaluating incorrect expression operator use for bool type
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testExpressionBadOperatorsBoolean() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test17.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test warning for multiple returns in a function declaration
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testMultipleReturnsWarning() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test18.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        Assert.assertTrue("Asserting warning output", output.toString().contains("WARNING"));
    }

    /**
     * Test for calling an inexistent function
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = UndeclaredException.class)
    public void testInexistentFunctionCall() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test19.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for calling a function with an amount of parameters different to the expected number
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testFunctionCallBadArgsSize() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test20.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for calling a function with a bad arg which is supposed to be int
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testFunctionCallBadArgInt() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test21.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for calling a function with a bad arg which is supposed to be float
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testFunctionCallBadArgFloat() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test22.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for calling a function with a bad arg which is supposed to be bool
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testFunctionCallBadArgBool() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test23.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Testing function call
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testFunctionCall() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test32.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", "1\r\n", output.toString());
    }

    /**
     * Testing the given source code in the assignment spec
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testGivenCode() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test33.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", "1667.9874\r\n", output.toString());
    }

    /**
     * Test function with no return
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = ReturnTypeMismatchException.class)
    public void testFunctionNoReturn() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test24.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for declaring a float variable with a bad type
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testBadVariableDeclFloat() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test25.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }


    /**
     * Test for declaring a bool variable with a bad type
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testBadVariableDeclBool() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test26.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for coverage reasons
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testJustForCoverage() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {

        ASTExpression expression = new ASTExpression();
        ASTStatement statement = new ASTStatement();
        ASTActualParams actualParams = new ASTActualParams();
        ASTFormalParams formalParams = new ASTFormalParams(new ArrayList<>());
        expression.accept(semanticAnalysis);
        statement.accept(semanticAnalysis);
        expression.accept(interpreter);
        statement.accept(interpreter);
        actualParams.accept(interpreter);
        formalParams.accept(interpreter);
    }

    /**
     * Test for division by 0
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = ArithmeticException.class)
    public void testDivisionBy0Int() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test27.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test for division by 0
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = ArithmeticException.class)
    public void testDivisionBy0Float() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test28.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test for expressions
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testExpressions() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test29.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        String expected = ("false\ntrue\ntrue\nfalse\ntrue\nfalse\ntrue\nfalse\ntrue\nfalse\ntrue\nfalse\n" +
                "true\nfalse\nfalse\nfalse\ntrue\nfalse\ntrue\ntrue\ntrue\nfalse\ntrue\nfalse\ntrue\nfalse\n"+
                "false\ntrue\nfalse\ntrue\nfalse\ntrue\nfalse\ntrue\n-1.0").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
    }

    /**
     * Test for bad expression (> with bool)
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test (expected = IncorrectTypeException.class)
    public void testGTWithBooleans() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test30.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test for function already declared
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test (expected = AlreadyDeclaredException.class)
    public void testAlreadyDeclaredFucntion() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/test31.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test for insert value
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testForInsertValue() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        SymbolTable symbolTable = SymbolTable.getSymbolTable();
        symbolTable.reset();
        Scope scope = new Scope();
        symbolTable.insertScope(scope);
        symbolTable.insertValue("inexistent_variable", 1);
        boolean found = false;

        for(int i=0; i <symbolTable.getScopes().size(); i++)
        {
            Scope innerScope = symbolTable.getScopes().get(i);
            if(innerScope.getValues().containsKey(innerScope))
            {
                found = true;
                break;
            }
        }

        symbolTable.popScope();

        assertFalse("Asserting variable is not found", found);
    }

    /**
     * Test for get value when there is not variable
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testForGetValueNoVariable() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        SymbolTable symbolTable = SymbolTable.getSymbolTable();
        symbolTable.reset();
        Scope scope = new Scope();
        symbolTable.insertScope(scope);

        Object value = symbolTable.getValue("inexistent_variable");
        symbolTable.popScope();

        assertNull("Asserting value is null", value);
    }

    /**
     * Test involving assignment to a function
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testAssignmentToFunction() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid11.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }

    /**
     * Test involving printing a function
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test(expected = IncorrectTypeException.class)
    public void testPrintFunction() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer(getAbsolutePath("integration/testinvalid12.txt"));
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }




}
