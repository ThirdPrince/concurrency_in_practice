package reentrantlock;



/**
 *
 */
public class Input implements Runnable {
    private Res res;

    public Input(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            synchronized (res) {
                if(res.flag) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (x == 0) {
                    res.name = "mike";
                    res.sex = "man";
                } else {
                    res.name = "丽丽";
                    res.sex = "女女女女女";
                }
                x = (x + 1) % 2;
                res.flag = true;
                res.notify();
            }


        }

    }
}
