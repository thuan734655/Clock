public class Main {
    public static void main(String[] args) {
            Clock b = new Clock();
            Thread t1 = new Thread(b);
            t1.start();
    }
}
