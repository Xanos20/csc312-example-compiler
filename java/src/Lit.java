public class Lit implements Exp {
    private int data;
    public Lit(int data) {
        this.data = data;
    }
    public int interpret() {
        return data;
    }
}
