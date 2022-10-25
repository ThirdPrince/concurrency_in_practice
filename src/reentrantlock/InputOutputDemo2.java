package reentrantlock;

import threadshare.Input;
import threadshare.Output;
import threadshare.Res;

public class InputOutputDemo2 {
    public static void main(String[] args) {
        Res res = new Res();
        threadshare.Input input = new Input(res);
        Output output = new Output(res);
        new Thread(input).start();
        new Thread(output).start();
    }
}
