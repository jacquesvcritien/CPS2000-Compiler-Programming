import exceptions.*;
import lexer.Lexer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.node.ASTActualParams;
import parser.node.ASTExpression;
import parser.node.ASTProgram;
import parser.node.ASTStatement;
import visitor.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class XMLIntegrationTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream original = System.out;

    Lexer lexer;
    Parser parser;
    VisitorXMLGenerator xml = new VisitorXMLGenerator();
    VisitorInterpreter interpreter = new VisitorInterpreter();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void restoreStreams() {
        System.setOut(original);
    }

    /**
     * Test XML for declaration variable
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testDeclVariable() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest1.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<VarDecl>\r\n" +
                "\t\t<Identifier Type=\"int\">i</Identifier>\r\n" +
                "\t\t<IntegerLiteral>0</IntegerLiteral>\r\n" +
                "\t</VarDecl>\r\n" +
                "\t<VarDecl>\r\n" +
                "\t\t<Identifier Type=\"bool\">b</Identifier>\r\n" +
                "\t\t<BooleanLiteral>true</BooleanLiteral>\r\n" +
                "\t</VarDecl>\r\n" +
                "</Program>\r\n", output.toString());
    }

    /**
     * Test XML for print
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testPrint() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest2.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<Print>\r\n" +
                "\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t</Print>\r\n" +
                "</Program>\r\n", output.toString());
    }

    /**
     * Test XML for assignment
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testAssignment() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest3.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<VarDecl>\r\n" +
                "\t\t<Identifier Type=\"float\">x</Identifier>\r\n" +
                "\t\t<FloatLiteral>3.2</FloatLiteral>\r\n" +
                "\t</VarDecl>\r\n" +
                "\t<Assignment>\r\n" +
                "\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t<FloatLiteral>5.0</FloatLiteral>\r\n" +
                "\t</Assignment>\r\n" +
                "</Program>\r\n", output.toString());
    }

    /**
     * Test XML for If statement
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testIFStatement() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest4.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<If>\r\n" +
                "\t\t<BinaryExpr Op=\"==\">\r\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t</BinaryExpr>\r\n" +
                "\t\t<Block>\r\n" +
                "\t\t\t<Print>\r\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t</Print>\r\n" +
                "\t\t</Block>\r\n" +
                "\t\t<Block>\r\n" +
                "\t\t\t<Print>\r\n" +
                "\t\t\t\t<IntegerLiteral>0</IntegerLiteral>\r\n" +
                "\t\t\t</Print>\r\n" +
                "\t\t</Block>\r\n" +
                "\t</If>\r\n" +
                "</Program>\r\n", output.toString());
    }
    
    /** Test XML for empty block
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testEmptyBlock() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest5.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<If>\r\n" +
                "\t\t<BinaryExpr Op=\"==\">\r\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t</BinaryExpr>\r\n" +
                "\t\t<Block>Empty</Block>\r\n" +
                "\t\t<Block>Empty</Block>\r\n" +
                "\t</If>\r\n" +
                "</Program>\r\n", output.toString());
    }

    /** Test XML for while statement
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testWhile() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest6.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<While>\r\n" +
                "\t\t<BinaryExpr Op=\"<\">\r\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t<IntegerLiteral>3</IntegerLiteral>\r\n" +
                "\t\t</BinaryExpr>\r\n" +
                "\t\t<Block>\r\n" +
                "\t\t\t<Print>\r\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t</Print>\r\n" +
                "\t\t</Block>\r\n" +
                "\t</While>\r\n" +
                "</Program>\r\n", output.toString());
    }

    /** Test XML for function declaration including formal params and return
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testFunctionDeclFormalParamsReturn() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest7.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<FuncDecl>\r\n" +
                "\t\t<Identifier Type=\"int\">square</Identifier>\r\n" +
                "\t\t<FormalParams>\r\n" +
                "\t\t\t<FormalParam>\r\n" +
                "\t\t\t\t<Identifier Type=\"int\">x</Identifier>\r\n" +
                "\t\t\t</FormalParam>\r\n" +
                "\t\t</FormalParams>\r\n" +
                "\t\t<Block>\r\n" +
                "\t\t\t<Return>\r\n" +
                "\t\t\t\t<BinaryExpr Op=\"*\">\r\n" +
                "\t\t\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t\t</BinaryExpr>\r\n" +
                "\t\t\t</Return>\r\n" +
                "\t\t</Block>\r\n" +
                "\t</FuncDecl>\r\n" +
                "</Program>\r\n", output.toString());
    }


    /** Test XML for function declaration with no formal params
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testFunctionDeclNoParams() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest10.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<FuncDecl>\r\n" +
                "\t\t<Identifier Type=\"int\">square</Identifier>\r\n" +
                "\t\t<FormalParams>Empty</FormalParams>\r\n" +
                "\t\t<Block>\r\n" +
                "\t\t\t<Return>\r\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t</Return>\r\n" +
                "\t\t</Block>\r\n" +
                "\t</FuncDecl>\r\n" +
                "</Program>\r\n", output.toString());
    }

    
    /** Test XML for loop
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testForLoop() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest8.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<For>\r\n" +
                "\t\t<VarDecl>\r\n" +
                "\t\t\t<Identifier Type=\"int\">x</Identifier>\r\n" +
                "\t\t\t<IntegerLiteral>0</IntegerLiteral>\r\n" +
                "\t\t</VarDecl>\r\n" +
                "\t\t<BinaryExpr Op=\"<\">\r\n" +
                "\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t<IntegerLiteral>10</IntegerLiteral>\r\n" +
                "\t\t</BinaryExpr>\r\n" +
                "\t\t<Assignment>\r\n" +
                "\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t<BinaryExpr Op=\"+\">\r\n" +
                "\t\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t</BinaryExpr>\r\n" +
                "\t\t</Assignment>\r\n" +
                "\t\t<Block>\r\n" +
                "\t\t\t<Print>\r\n" +
                "\t\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t</Print>\r\n" +
                "\t\t</Block>\r\n" +
                "\t</For>\r\n" +
                "</Program>\r\n", output.toString());
    }

    /** Test XML for loop with no declaration and assignment
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testForLoopNoDeclarationNoAssignment() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest9.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<VarDecl>\r\n" +
                "\t\t<Identifier Type=\"int\">x</Identifier>\r\n" +
                "\t\t<IntegerLiteral>9</IntegerLiteral>\r\n" +
                "\t</VarDecl>\r\n" +
                "\t<For>\r\n" +
                "\t\t<VarDecl>Empty</VarDecl>\r\n" +
                "\t\t<BinaryExpr Op=\"<\">\r\n" +
                "\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t<IntegerLiteral>10</IntegerLiteral>\r\n" +
                "\t\t</BinaryExpr>\r\n" +
                "\t\t<Assignment>Empty</Assignment>\r\n" +
                "\t\t<Block>\r\n" +
                "\t\t\t<Print>\r\n" +
                "\t\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t</Print>\r\n" +
                "\t\t\t<Assignment>\r\n" +
                "\t\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t\t<BinaryExpr Op=\"+\">\r\n" +
                "\t\t\t\t\t<Identifier>x</Identifier>\r\n" +
                "\t\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t\t</BinaryExpr>\r\n" +
                "\t\t\t</Assignment>\r\n" +
                "\t\t</Block>\r\n" +
                "\t</For>\r\n" +
                "</Program>\r\n" +
                "9\r\n", output.toString());
    }

    /** Test XML for function call
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
        lexer = new Lexer("xmltest11.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<VarDecl>\r\n" +
                "\t\t<Identifier Type=\"int\">x</Identifier>\r\n" +
                "\t\t<FunctionCall>\r\n" +
                "\t\t\t<Identifier>square</Identifier>\r\n" +
                "\t\t\t<ActualParams>\r\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\r\n" +
                "\t\t\t</ActualParams>\r\n" +
                "\t\t</FunctionCall>\r\n" +
                "\t</VarDecl>\r\n" +
                "</Program>\r\n", output.toString());
    }

    /** Test XML for function call with no params
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testFunctionCallNoParams() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest12.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<VarDecl>\r\n" +
                "\t\t<Identifier Type=\"int\">x</Identifier>\r\n" +
                "\t\t<FunctionCall>\r\n" +
                "\t\t\t<Identifier>func</Identifier>\r\n" +
                "\t\t\t<ActualParams>Empty</ActualParams>\r\n" +
                "\t\t</FunctionCall>\r\n" +
                "\t</VarDecl>\r\n" +
                "</Program>\r\n", output.toString());
    }

    /** Test XML for unary
     * @throws IOException
     * @throws URISyntaxException
     * @throws InvalidSyntaxException
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    @Test
    public void testUnary() throws IOException, URISyntaxException, InvalidSyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        lexer = new Lexer("xmltest13.txt");
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.generate(node);
        Assert.assertEquals("Asserting console output", "<Program>\r\n" +
                "\t<VarDecl>\r\n" +
                "\t\t<Identifier Type=\"int\">x</Identifier>\r\n" +
                "\t\t<Unary Type=\"-\">\r\n" +
                "\t\t\t<IntegerLiteral>2</IntegerLiteral>\r\n" +
                "\t\t</Unary>\r\n" +
                "\t</VarDecl>\r\n" +
                "</Program>\r\n", output.toString());
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
        expression.accept(xml);
        statement.accept(xml);
    }


}
