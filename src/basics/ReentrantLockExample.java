package basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReentrantLockExample  implements Runnable{
    private ReEntrantLock lock;
    private static int counter = 0;
    public static final int threadSize = 5;
    
    public ReentrantLockExample(ReEntrantLock lock) {
    	this.lock = lock;
    }
    
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(threadSize);
		for(int i=0;i<threadSize;i++) {
			executor.submit(new ReentrantLockExample(new ReEntrantLock()));
		}
		executor.shutdown();
	}

	@Override
	public void run() {
		while(counter<100) {
			incrementCounter();
		}
	}
	public void incrementCounter() {
		try {
			lock.lock();
			counter++;
			System.out.println(Thread.currentThread().getName()+" : "+counter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
}
