public class Token {
    public TokenKind kind;
    // Note: GROSS
    public int data;

    public Token(TokenKind kind) {
        this(kind, -1);
    }

    public Token(TokenKind kind,
                 int data) {
        this.kind = kind;
        this.data = data;
    }
    public String toString() {
        if (data == -1) {
            return String.format("%s", TokenKind.toString(kind));
        } else {
            return String.format("%s(%d)", TokenKind.toString(kind), data);
        }
    }
}
