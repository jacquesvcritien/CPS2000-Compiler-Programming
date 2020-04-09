import exceptions.InvalidNodeException;
import exceptions.InvalidSyntaxException;
import lexer.Lexer;
import parser.Parser;
import parser.node.ASTNode;
import parser.node.ASTProgram;
import visitor.VisitorXMLGenerator;

import java.io.IOException;
public class Tester {
    public static void main(String args[]) throws IOException, InvalidSyntaxException, InvalidNodeException {
        Lexer lexer = new Lexer("sourcecode.txt");
        VisitorXMLGenerator xml = new VisitorXMLGenerator();
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
    }
}
