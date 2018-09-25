package tread.treadLocal;
import java.util.Map;
import org.junit.Test;

import java.util.HashMap;

public class TreadLocalDemo {
    ThreadLocal<String> t1 = new ThreadLocal<>();

    public void treadLocalFunction() {
        t1.set("hello");
        String s = t1.get();
        System.out.println(s);
        new Thread(() -> System.out.println("我运行啦" + t1.get())).start();

    }
    public void threadLocalTheory(){
        final Map<Thread, String> map = new HashMap<>();
        map.put(Thread.currentThread(),"hello");
        System.out.println(map.get(Thread.currentThread()));
        new Thread(()-> System.out.println("我也运行啦"+map.get(Thread.currentThread()))).start();
    }

    @Test
    public void testThreadLocalAndTheory(){
        threadLocalTheory();
        treadLocalFunction();
    }
}
