package sync;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 你是如何调用 wait（）方法的？使用 if 块还是循环？为什么？
 * https://www.javazhiyin.com/93019.html
 */
public class SyncWhileIfDemo {
    public static void main(String[] args) {

        final Buf buf = new Buf();
        ExecutorService es = Executors.newFixedThreadPool(11);
        for (int i = 0;i< 1;i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            buf.put(1);
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }

                    }
                }
            });
        }

        for (int i = 0;i< 10;i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try{
                            buf.get();
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }

                    }
                }
            });
        }
        es.shutdown();
        try {
            es.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    static class Buf{
        private final  int MAX = 5;
        private final ArrayList<Integer> list = new ArrayList<>();
        //用while 不会出问题
        synchronized void put(int v){

            while (list.size() == MAX){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(v);
            notifyAll();
        }

        /**
         * 用 while 不会出问题
         * @return
         */
        synchronized int get(){
            while (list.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int v = list.remove(0);
            System.out.println(v);
            notifyAll();
            return v;
        }
        synchronized int size(){
            return list.size();
        }

    }
}
