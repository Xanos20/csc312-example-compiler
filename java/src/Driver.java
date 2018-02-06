import java.util.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File(args[0]));
        List<Token> tokens = Lexer.lex(in);
        Parser parser = new Parser(tokens);
        Exp e = parser.parse();
        System.out.println(e.interpret());
    }
}
