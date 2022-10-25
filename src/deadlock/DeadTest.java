package deadlock;

public class DeadTest {
    public static void main(String[] args) {
        Dead dead = new Dead(true);
        Dead dead2 = new Dead(false);
        new Thread(dead).start();
        new Thread(dead2).start();

    }
}
