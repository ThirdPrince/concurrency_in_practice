package threadlocal;

/**
 * 演示 ThreadLocal 用法2 避免传递参数的麻烦
 */
public class ThreadLocalNormalUsage03 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Service1().process(Thread.currentThread().getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Service1().process(Thread.currentThread().getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Service1().process(Thread.currentThread().getName());
            }
        }).start();

    }

}
class Service1{
    public void process(String name){
         User user = new User(name);
         UserContextHolder.holder.set(user);
         new Service2().process();

    }
}

class Service2{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service2 user name:"+user.name);
        new Service3().process();
    }
}

class Service3{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service3 user name:"+user.name);

    }
}
class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();

}
class User{
    String name ;
    public User(String name){
        this.name = name;
    }
}
