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


public class Tester {
    public static void main(String args[]) throws IOException, InvalidSyntaxException, IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException, URISyntaxException {
        Lexer lexer = new Lexer("lexer/funcdecl.txt");
        VisitorXMLGenerator xml = new VisitorXMLGenerator();
//        for(Token token: lexer.getTokens())
//        {
//            System.out.println(token.getType()+" : "+token.getAttribute());
//        }

        Parser parser = new Parser(lexer);
        ASTProgram program = parser.parse();
        xml.generate(program);

    }
}
