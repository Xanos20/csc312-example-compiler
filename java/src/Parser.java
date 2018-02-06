import java.util.*;

public class Parser {

    private int pos;
    private List<Token> toks;

    public Parser(List<Token> toks) {
        this.toks = toks;
        pos = 0;
    }

    private void advance() { pos++; }
    private void consume(TokenKind t) {
        if (t.equals(toks.get(pos).kind)) {
            advance();
        } else {
            Util.failWith(
                    String.format("Expected %s, found %s", t, toks.get(pos)));
        }
    }

    public Exp parse() {
        if (pos < toks.size()) {
            Token t = toks.get(pos);
            switch (t.kind) {
                case INT:
                    advance();
                    return new Lit(t.data);
                case LPAREN:
                    consume(TokenKind.LPAREN);
                    consume(TokenKind.PLUS);
                    Exp e1 = parse();
                    Exp e2 = parse();
                    consume(TokenKind.RPAREN);
                    return new Plus(e1, e2);
                default:
                    Util.failWith(
                            String.format("Unexpected token: %s", t));
            }
        } else {
            Util.failWith("Expected a token");
        }
        return null;
    }
}
