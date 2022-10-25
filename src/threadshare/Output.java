package threadshare;

public class Output implements Runnable {
    private Res res;
    public Output(Res res){
        this.res = res;
    }
    @Override
    public void run() {

        while (true){
            synchronized (res){
                if(!res.flag) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("name = "+res.name+":sex ="+res.sex);
                res.flag = false;
                res.notify();
            }

        }
    }
}
