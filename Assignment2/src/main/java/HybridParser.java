import antlrSrc.*;
import exceptions.*;
import parser.node.ASTProgram;
import visitor.VisitorInterpreter;
import visitor.VisitorSemanticAnalysis;
import visitor.VisitorXMLGenerator;

import java.io.IOException;
import java.net.URISyntaxException;

public class HybridParser {
    public static void main(String args[]) throws IOException, URISyntaxException, UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException, InvalidSyntaxException {
        VisitorXMLGenerator xmlGenerator = new VisitorXMLGenerator();
        VisitorSemanticAnalysis semanticAnalysis = new VisitorSemanticAnalysis();
        VisitorInterpreter interpreter = new VisitorInterpreter();

        ASTProgram program = SmallLangParserHelper.getProgramContext(args[0]);
        xmlGenerator.generate(program);
        semanticAnalysis.visit(program);
        interpreter.visit(program);
    }
}
