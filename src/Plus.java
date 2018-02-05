public class Plus implements Exp {
    private Exp e1, e2;
    public Plus(Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public int interpret() {
        return
            e1.interpret() +
            e2.interpret();
    }
}
