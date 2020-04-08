import exceptions.InvalidSyntaxException;
import lexer.Lexer;
import lexer.Token;
import lexer.TypeToken;
import parser.Parser;
import parser.node.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Tester {
    public static void main(String args[]) throws IOException, InvalidSyntaxException {
        Lexer lexer = new Lexer("sourcecode.txt");
//        Token nextToken;
//        int counter = 1;
//        do{
//            nextToken = lexer.nextToken();
//            System.out.println(counter+": "+nextToken.getType()+", "+nextToken.getAttribute());
//            counter++;
//        }while(nextToken.getType() != TypeToken.EOF && nextToken.getType() != TypeToken.INVALID);
        Parser parser = new Parser(lexer);
        Node node = parser.parse();
    }
}
