import exceptions.*;
import lexer.Lexer;
import lexer.Token;
import lexer.TypeToken;
import parser.Parser;
import parser.node.ASTNode;
import parser.node.ASTProgram;
import visitor.VisitorInterpreter;
import visitor.VisitorSemanticAnalysis;
import visitor.VisitorXMLGenerator;

import java.io.IOException;
import java.net.URISyntaxException;


public class SmallLangV2Executor {
    public static void main(String args[]) throws IOException, InvalidSyntaxException, IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException, URISyntaxException {
        Lexer lexer = new Lexer(args[0]);
        VisitorXMLGenerator xml = new VisitorXMLGenerator();

        Parser parser = new Parser(lexer);
        parser.parse();
        System.out.println("Program successfully parsed");
    }
}
