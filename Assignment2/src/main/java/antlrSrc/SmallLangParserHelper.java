package antlrSrc;

import exceptions.InvalidSyntaxException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import parser.node.ASTProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.regex.PatternSyntaxException;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class SmallLangParserHelper {
    /**
     * Method to get program context tree from filename
     * @param filename path for file to parse
     * @return program tree
     * @throws IOException
     * @throws URISyntaxException
     */
    public static ASTProgram getProgramContext(String filename) throws IOException, URISyntaxException, InvalidSyntaxException {

        try
        {
            //get absolute path
            ErrorThrowListener errorThrowListener = new ErrorThrowListener();

            //get char stream
            CharStream cs = fromFileName(filename);
            //init lexer
            SmallLangLexer lexer  = new SmallLangLexer(cs);
            //add error listener
            lexer.removeErrorListeners();
            lexer.addErrorListener(errorThrowListener);
            //get tokens
            CommonTokenStream token = new CommonTokenStream(lexer);
            //init parser
            SmallLangParser parser = new SmallLangParser(token);
            //add error listener
            parser.removeErrorListeners();
            parser.addErrorListener(errorThrowListener);

            //get tree
            SmallLangParser.ProgramContext programContext = parser.program();

            //get our AST tree
            TransformerVisitor transformer = new TransformerVisitor(lexer);
            return transformer.visitProgram(programContext);
        }
        catch (ParseCancellationException e)
        {
            throw new InvalidSyntaxException(e.getMessage());
        }


    }
}
