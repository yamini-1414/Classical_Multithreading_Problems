package basics;
public class ThreadTermination {
	public static void main(String[] args) {
		Thread thread = new Thread(new BlockingThread());
		thread.setDaemon(true);
		thread.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//thread.interrupt();
	}

	public static class BlockingThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(500000000);
			} catch (InterruptedException e) {
				System.out.println("Exiting from the blocking thread");
				e.printStackTrace();
			}
		}
		
	}
}
