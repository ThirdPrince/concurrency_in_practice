package threadshare;

public class InputOutputDemo {
    public static void main(String[] args) {
        Res res = new Res();
        Input input = new Input(res);
        Output output = new Output(res);
        new Thread(input).start();
        new Thread(output).start();
    }
}
