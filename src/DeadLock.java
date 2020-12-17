
public class DeadLock {
	
	public static void main(String[] args) throws Exception {
		DeadLock d = new DeadLock();
		d.runProgram();
	}

	private int counter = 0;
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	Runnable task1 = new Runnable() {

		@Override
		public void run() {
			for(int i=0;i<100;i++) {
				try {
				incrementCounter();
                Thread.sleep(100);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
		}
	  }
	};
	
	Runnable task2 = new Runnable() {

		@Override
		public void run() {
		   for(int i=0;i<100;i++) {
			   try {
				   decrementCounter();
				   Thread.sleep(100);
			   }catch(InterruptedException e) {
				   e.printStackTrace();
			   }
		   }
		}
	};
	
	public void runProgram() throws Exception{
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		
		thread1.start();
	    Thread.sleep(100);
	    thread2.start();
	    
	    thread1.join();
	    thread2.join();
	    
	    System.out.println(counter);
	}
	
	
	public void incrementCounter() throws InterruptedException {
		synchronized(lock1) {
			System.out.println("Acquired Lock1 by increment thread");
			Thread.sleep(100);
			
			synchronized(lock2) {
				System.out.println("Acquired lock2 by increment thread");
				counter++;
				System.out.println("Released Lock2 by increment thread");
			}
			System.out.println("Released Lock1 by increment thread");
		}
	}
	
	public void decrementCounter() throws InterruptedException {
		synchronized(lock2) {
			System.out.println("Acquired Lock2 by decrement thread");
			Thread.sleep(100);
			
			synchronized(lock1) {
				System.out.println("Acquired lock1 by decrement thread");
				counter--;
				System.out.println("Released Lock1 by decrement thread");
			}
			System.out.println("Released Lock2 by decrement thread");
		}
	}
	
}
