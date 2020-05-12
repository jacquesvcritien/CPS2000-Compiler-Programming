import antlrSrc.ErrorThrowListener;
import antlrSrc.SmallLangLexer;
import antlrSrc.SmallLangParser;
import antlrSrc.TransformerVisitor;
import exceptions.AlreadyDeclaredException;
import exceptions.IncorrectTypeException;
import exceptions.ReturnTypeMismatchException;
import exceptions.UndeclaredException;
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
    public static void main(String args[]) throws IOException, URISyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        VisitorXMLGenerator xmlGenerator = new VisitorXMLGenerator();

        String filename = "sourcecode.txt";
        URL url = AntlrTester.class.getClassLoader().getResource(filename);
        if(url == null)
            throw new FileNotFoundException(filename+" not found");
        File file = Paths.get(url.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        CharStream cs = fromFileName(absolutePath);
        SmallLangLexer lexer  = new SmallLangLexer(cs);
        CommonTokenStream token = new CommonTokenStream(lexer);
        SmallLangParser parser = new SmallLangParser(token);
        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorThrowListener());
        SmallLangParser.ProgramContext programContext = parser.program();

        TransformerVisitor transformer = new TransformerVisitor(lexer);
        ASTProgram program = transformer.visitProgram(programContext);
        xmlGenerator.generate(program);


    }
}
