package basics;
public class ThreadCreation {
  public static void main(String[] args) {
	Thread thread1 = new Thread(new Runnable() {

		@Override
		public void run() {
			System.out.println("From the runnable thread");
			
		}
		
	}); 
	ThreadExample thread2 = new ThreadExample();
	thread1.start();
	thread2.start();
	System.out.println("Main thread");  
	}
  public static class ThreadExample extends Thread{
	  public void run() {
		  System.out.println("From the thread extended the class Thread");
	  }
  }
}
