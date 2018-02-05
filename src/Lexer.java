import java.io.*;
import java.util.*;

public class Lexer {
    public static List<Token> lex(Scanner in) throws IOException {
        List<Token> toks = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            for (int i = 0; i < line.length(); ) {
                char ch = line.charAt(i);
                if (ch == '(') {
                    toks.add(new Token(TokenKind.LPAREN));
                    i++;
                } else if (ch == ')') {
                    toks.add(new Token(TokenKind.RPAREN));
                    i++;
                } else if (ch == '+') {
                    toks.add(new Token(TokenKind.PLUS));
                    i++;
                } else if (Character.isDigit(ch)) {
                    String num = "" + ch;
                    i++;
                    while (i < line.length()) {
                        ch = line.charAt(i++);
                        if (!Character.isDigit(ch)) {
                            i--;
                            break;      // NOTE: SUPER disgusting
                        }
                        num += ch;
                    }
                    toks.add(new Token(TokenKind.INT,
                                Integer.parseInt(num)));
                } else if (Character.isWhitespace(ch)) {
                    i++;
                } else {
                    Util.failWith(
                        String.format("Unexpected char found: %c", ch));
                }
            }
        }
        return toks;
    }
}
