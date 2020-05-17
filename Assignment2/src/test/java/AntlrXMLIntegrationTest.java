import antlrSrc.SmallLangParserHelper;
import exceptions.*;
import lexer.Lexer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.node.ASTProgram;
import parser.node.expression.ASTExpression;
import parser.node.statement.ASTStatement;
import visitor.VisitorInterpreter;
import visitor.VisitorXMLGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

public class AntlrXMLIntegrationTest {

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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest1.txt");

        xml.generate(node);
        String expected = ("<Program>\n" +
                "\t<VarDecl>\n" +
                "\t\t<Identifier Type=\"INT\">i</Identifier>\n" +
                "\t\t<IntegerLiteral>0</IntegerLiteral>\n" +
                "\t</VarDecl>\n" +
                "\t<VarDecl>\n" +
                "\t\t<Identifier Type=\"BOOL\">b</Identifier>\n" +
                "\t\t<BooleanLiteral>true</BooleanLiteral>\n" +
                "\t</VarDecl>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest2.txt");

        String expected = ("<Program>\n" +
                "\t<Print>\n" +
                "\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t</Print>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);

        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest3.txt");

        String expected = ("<Program>\n" +
                "\t<VarDecl>\n" +
                "\t\t<Identifier Type=\"FLOAT\">x</Identifier>\n" +
                "\t\t<FloatLiteral>3.2</FloatLiteral>\n" +
                "\t</VarDecl>\n" +
                "\t<Assignment>\n" +
                "\t\t<Identifier>x</Identifier>\n" +
                "\t\t<FloatLiteral>5.0</FloatLiteral>\n" +
                "\t</Assignment>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest4.txt");

        String expected = ("<Program>\n" +
                "\t<If>\n" +
                "\t\t<BinaryExpr Op=\"==\">\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t</BinaryExpr>\n" +
                "\t\t<Block>\n" +
                "\t\t\t<Print>\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t</Print>\n" +
                "\t\t</Block>\n" +
                "\t\t<Block>\n" +
                "\t\t\t<Print>\n" +
                "\t\t\t\t<IntegerLiteral>0</IntegerLiteral>\n" +
                "\t\t\t</Print>\n" +
                "\t\t</Block>\n" +
                "\t</If>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest5.txt");

        String expected = ("<Program>\n" +
                "\t<If>\n" +
                "\t\t<BinaryExpr Op=\"==\">\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t</BinaryExpr>\n" +
                "\t\t<Block>Empty</Block>\n" +
                "\t\t<Block>Empty</Block>\n" +
                "\t</If>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest6.txt");

        String expected = ("<Program>\n" +
                "\t<While>\n" +
                "\t\t<BinaryExpr Op=\"<\">\n" +
                "\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t<IntegerLiteral>3</IntegerLiteral>\n" +
                "\t\t</BinaryExpr>\n" +
                "\t\t<Block>\n" +
                "\t\t\t<Print>\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t</Print>\n" +
                "\t\t</Block>\n" +
                "\t</While>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest7.txt");

        String expected = ("<Program>\n" +
                "\t<FuncDecl>\n" +
                "\t\t<Identifier Type=\"INT\">square</Identifier>\n" +
                "\t\t<FormalParams>\n" +
                "\t\t\t<FormalParam>\n" +
                "\t\t\t\t<Identifier Type=\"INT\">x</Identifier>\n" +
                "\t\t\t</FormalParam>\n" +
                "\t\t</FormalParams>\n" +
                "\t\t<Block>\n" +
                "\t\t\t<Return>\n" +
                "\t\t\t\t<BinaryExpr Op=\"*\">\n" +
                "\t\t\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t\t</BinaryExpr>\n" +
                "\t\t\t</Return>\n" +
                "\t\t</Block>\n" +
                "\t</FuncDecl>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest10.txt");

        String expected = ("<Program>\n" +
                "\t<FuncDecl>\n" +
                "\t\t<Identifier Type=\"INT\">square</Identifier>\n" +
                "\t\t<FormalParams>Empty</FormalParams>\n" +
                "\t\t<Block>\n" +
                "\t\t\t<Return>\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t</Return>\n" +
                "\t\t</Block>\n" +
                "\t</FuncDecl>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest8.txt");

        String expected = ("<Program>\n" +
                "\t<For>\n" +
                "\t\t<VarDecl>\n" +
                "\t\t\t<Identifier Type=\"INT\">x</Identifier>\n" +
                "\t\t\t<IntegerLiteral>0</IntegerLiteral>\n" +
                "\t\t</VarDecl>\n" +
                "\t\t<BinaryExpr Op=\"<\">\n" +
                "\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t<IntegerLiteral>10</IntegerLiteral>\n" +
                "\t\t</BinaryExpr>\n" +
                "\t\t<Assignment>\n" +
                "\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t<BinaryExpr Op=\"+\">\n" +
                "\t\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t</BinaryExpr>\n" +
                "\t\t</Assignment>\n" +
                "\t\t<Block>\n" +
                "\t\t\t<Print>\n" +
                "\t\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t</Print>\n" +
                "\t\t</Block>\n" +
                "\t</For>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output",expected , output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest9.txt");

        String expected = ("<Program>\n" +
                "\t<VarDecl>\n" +
                "\t\t<Identifier Type=\"INT\">x</Identifier>\n" +
                "\t\t<IntegerLiteral>9</IntegerLiteral>\n" +
                "\t</VarDecl>\n" +
                "\t<For>\n" +
                "\t\t<VarDecl>Empty</VarDecl>\n" +
                "\t\t<BinaryExpr Op=\"<\">\n" +
                "\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t<IntegerLiteral>10</IntegerLiteral>\n" +
                "\t\t</BinaryExpr>\n" +
                "\t\t<Assignment>Empty</Assignment>\n" +
                "\t\t<Block>\n" +
                "\t\t\t<Print>\n" +
                "\t\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t</Print>\n" +
                "\t\t\t<Assignment>\n" +
                "\t\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t\t<BinaryExpr Op=\"+\">\n" +
                "\t\t\t\t\t<Identifier>x</Identifier>\n" +
                "\t\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t\t</BinaryExpr>\n" +
                "\t\t\t</Assignment>\n" +
                "\t\t</Block>\n" +
                "\t</For>\n" +
                "</Program>\n" +
                "9").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        interpreter.interpret(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest11.txt");

        String expected = ("<Program>\n" +
                "\t<VarDecl>\n" +
                "\t\t<Identifier Type=\"INT\">x</Identifier>\n" +
                "\t\t<FunctionCall>\n" +
                "\t\t\t<Identifier>square</Identifier>\n" +
                "\t\t\t<ActualParams>\n" +
                "\t\t\t\t<IntegerLiteral>1</IntegerLiteral>\n" +
                "\t\t\t</ActualParams>\n" +
                "\t\t</FunctionCall>\n" +
                "\t</VarDecl>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest12.txt");

        String expected = ("<Program>\n" +
                "\t<VarDecl>\n" +
                "\t\t<Identifier Type=\"INT\">x</Identifier>\n" +
                "\t\t<FunctionCall>\n" +
                "\t\t\t<Identifier>func</Identifier>\n" +
                "\t\t\t<ActualParams>Empty</ActualParams>\n" +
                "\t\t</FunctionCall>\n" +
                "\t</VarDecl>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
        ASTProgram node = SmallLangParserHelper.getProgramContext("xml/xmltest13.txt");

        String expected = ("<Program>\n" +
                "\t<VarDecl>\n" +
                "\t\t<Identifier Type=\"INT\">x</Identifier>\n" +
                "\t\t<Unary Type=\"-\">\n" +
                "\t\t\t<IntegerLiteral>2</IntegerLiteral>\n" +
                "\t\t</Unary>\n" +
                "\t</VarDecl>\n" +
                "</Program>").replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));

        xml.generate(node);
        Assert.assertEquals("Asserting console output", expected, output.toString().trim());
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
