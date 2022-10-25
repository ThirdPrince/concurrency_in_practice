package threadobjectcommommethods;

public class DaemonTest {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start();
        new Thread(you).start();
    }
}
