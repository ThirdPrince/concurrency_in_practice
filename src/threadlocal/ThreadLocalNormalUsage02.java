package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalNormalUsage02 {
     static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public String date(int seconds){
        Date date = new Date(1000 *seconds);
       SimpleDateFormat simpleDateFormat =  null;
               //ThreadSafeFormatter.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0;i < 300;i++){
            final int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage02().date(finalI);
                    System.out.println(date);
                }
            });
       }
        executorService.shutdown();
    }

}

class ThreadSafeFormatter{
//    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal  = new ThreadLocal<>(){
//        @Override
//        protected SimpleDateFormat initialValue() {
//            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        }
//    };

}
