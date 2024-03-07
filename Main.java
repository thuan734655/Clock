public class Main {
    public static void main(String[] args) {
            Clock b = new Clock("7");
            Thread t1 = new Thread(b);
            t1.start();
    }
}
