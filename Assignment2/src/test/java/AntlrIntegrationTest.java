import antlrSrc.SmallLangParserHelper;
import exceptions.*;
import lexer.Lexer;
import lexer.Token;
import lexer.TypeToken;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.node.ASTProgram;
import parser.node.expression.ASTActualParams;
import parser.node.expression.ASTCharacterLiteral;
import parser.node.expression.ASTExpression;
import parser.node.expression.identifier.ASTAbstractIdentifier;
import parser.node.expression.identifier.ASTArrayIdentifier;
import parser.node.statement.ASTArrayValue;
import parser.node.statement.ASTFormalParams;
import parser.node.statement.ASTStatement;
import parser.node.statement.declaration.ASTArrayDecl;
import parser.node.statement.declaration.ASTVariableDecl;
import visitor.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class AntlrIntegrationTest {

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test1.txt"));

        String expected = ("1\n2\n0.1\n0.2\n0.3\n0.4\n0.5\n1\n2\n3\n4\n5\ntrue").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
    }

    @Test
    public void test2() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test2.txt"));

        String expected = ("12\n4\n2\n15.200001\nfalse").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
    }

    @Test
    public void test3() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test3.txt"));

        String expected = ("16\n3.0\ntrue").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
    }

    @Test
    public void test4() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test4.txt"));


        String expected = ("1\n1").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test5.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid3.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid4.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid5.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid6.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid7.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid8.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid9.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid10.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test6.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test7.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test8.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test9.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test10.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test11.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test12.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test13.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test14.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test15.txt"));
        semanticAnalysis.analyse(node);
    }

    /**
     * Test for evaluating incorrect expression operand use for non booleans
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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test16.txt"));

        semanticAnalysis.analyse(node);
    }

    /**
     * Test for evaluating incorrect expression operand use for bool type
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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test17.txt"));

        semanticAnalysis.analyse(node);
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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test19.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test20.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test21.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test22.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test23.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test32.txt"));

        String expected = ("1").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test33.txt"));

        String expected = ("1667.9874").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
    }

    /**
     * Testing the plus func
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testFunc3Params() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test34.txt"));

        String expected = ("6").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test24.txt"));


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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test25.txt"));


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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test26.txt"));


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
        ASTArrayValue arrayValue = new ASTArrayValue();
        ASTCharacterLiteral characterLiteral = new ASTCharacterLiteral(new Token(TypeToken.CHARACTER_LITERAL, 'c'));
        ASTArrayDecl arrayDecl = new ASTArrayDecl();
        ASTArrayIdentifier arrayIdentifier = new ASTArrayIdentifier("id");
        ASTAbstractIdentifier abstractIdentifier = new ASTAbstractIdentifier("try");
        //for coverage
        ASTVariableDecl variableDecl = new ASTVariableDecl();

        expression.accept(semanticAnalysis);
        statement.accept(semanticAnalysis);
        expression.accept(interpreter);
        statement.accept(interpreter);
        actualParams.accept(interpreter);
        formalParams.accept(interpreter);
        arrayValue.accept(semanticAnalysis);
        arrayValue.accept(interpreter);
        arrayValue.accept(xml);
        characterLiteral.accept(semanticAnalysis);
        characterLiteral.accept(interpreter);
        characterLiteral.accept(xml);
        arrayDecl.accept(semanticAnalysis);
        arrayDecl.accept(interpreter);
        arrayDecl.accept(xml);
        arrayIdentifier.accept(semanticAnalysis);
        arrayIdentifier.accept(interpreter);
        arrayIdentifier.accept(xml);
        abstractIdentifier.accept(semanticAnalysis);
        abstractIdentifier.accept(interpreter);
        abstractIdentifier.accept(xml);

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test27.txt"));


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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test28.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test29.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test30.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/test31.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid11.txt"));

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
        ASTProgram node = SmallLangParserHelper.getProgramContext(getAbsolutePath("integration/testinvalid12.txt"));

        semanticAnalysis.analyse(node);
        interpreter.interpret(node);
    }




}
