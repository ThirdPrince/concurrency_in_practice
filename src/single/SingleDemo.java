package single;

/**
 * 饿汉式
 * class Single{
 *     private static final Single s = new Single();
 *     private Single(){}
 *     public static Single getInstance(){
 *         return s;
 *     }
 * }
 *  懒汉式
 *  private
 *
 */
class Single{
    private static Single s = null;
    private Single(){}
    public static Single getInstance(){
        if (s == null){
            s = new Single();
        }
        return s;
    }
}
class SingleInstance{
    private static SingleInstance singleInstance;
    private SingleInstance(){

    }

    public static SingleInstance getInstance(){
        if(singleInstance == null){
            synchronized (SingleInstance.class){
                if(singleInstance == null){
                    singleInstance = new SingleInstance();
                }
            }
        }
        return singleInstance;
    }

}
public class SingleDemo {
    public static void main(String[] args) {

    }
}
