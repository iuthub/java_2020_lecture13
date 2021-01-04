import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedTest {
    public static void main(String args[]) {
        Table obj = new Table();//only one object
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new MyThread(obj, 5));
        executorService.execute(new MyThread(obj, 100));

        executorService.shutdown();
    }
}

class Table {

    synchronized void printTable(int n) {
//        synchronized (this) {//synchronized block
            for (int i = 1; i <= 5; i++) {
                System.out.println(n * i);
                try {
                    Thread.sleep(400);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
//    }//end of the method
}

class MyThread implements Runnable {
    Table t;
    int num;

    MyThread(Table t, int num) {
        this.num = num;
        this.t = t;
    }

    public void run() {
        t.printTable(num);
    }

}

