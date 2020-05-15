import antlrSrc.*;
import exceptions.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.node.ASTProgram;
import visitor.VisitorXMLGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class AntlrTester {
    public static void main(String args[]) throws IOException, URISyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException, InvalidSyntaxException {
        VisitorXMLGenerator xmlGenerator = new VisitorXMLGenerator();

        String filename = "sourcecode.txt";
        ASTProgram program = SmallLangParserHelper.getProgramContext(filename);
        xmlGenerator.generate(program);


    }
}
