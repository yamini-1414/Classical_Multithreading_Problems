package basics;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
	
	final static int n = 10;
	static ExecutorService threadPool = Executors.newFixedThreadPool(2);
	public static void main(String[] args) throws Exception {
	     System.out.println(pollingTask(n));
	     threadPool.shutdown();
	}
	
	public static int pollingTask(int n) {
		int result = -1;
		Callable<Integer> f1_task = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				int sum = 0;
				for(int i=1;i<=n;i++)
					sum  +=  i;
				return sum;
			}
		};
		
		Callable<Void> f2_task = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				Thread.sleep(3000*100);
				return null;
			}
		};
		
		Future<Integer> f1 = threadPool.submit(f1_task);
		Future<Void> f2 = threadPool.submit(f2_task);
		try {
	    f2.cancel(true);
	    
	    while(!f1.isDone()) {
	    	System.out.println("Waiting for the first task to complete");
	    }
	   
			result = f1.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Is second task cancelled? "+ f2.isCancelled());
		
		return result;
		
		
	}

}
