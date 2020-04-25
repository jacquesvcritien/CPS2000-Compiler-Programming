import exceptions.*;
import lexer.Lexer;
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
        Lexer lexer = new Lexer("xmltest13.txt");
        VisitorXMLGenerator xml = new VisitorXMLGenerator();
        VisitorSemanticAnalysis semanticAnalysis = new VisitorSemanticAnalysis();
        VisitorInterpreter interpreter = new VisitorInterpreter();
//        Token nextToken;
//        int counter = 1;
//        do{
//            nextToken = lexer.nextToken();
//            System.out.println(counter+": "+nextToken.getType()+", "+nextToken.getAttribute());
//            counter++;
//        }while(nextToken.getType() != TypeToken.EOF && nextToken.getType() != TypeToken.INVALID);
        Parser parser = new Parser(lexer);
        ASTProgram node = parser.parse();

        xml.visit(node);
        semanticAnalysis.visit(node);

        interpreter.visit(node);

    }
}
