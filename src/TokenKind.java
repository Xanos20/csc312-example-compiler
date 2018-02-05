public enum TokenKind {
    INT, PLUS, LPAREN, RPAREN;

    public static String toString(TokenKind kind) {
        switch (kind) {
            case INT:    return "int";
            case PLUS:   return "+";
            case LPAREN: return "(";
            case RPAREN: return ")";
            default: throw new IllegalArgumentException();
        }
    }
}
