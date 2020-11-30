import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	private static final int MAX_LIMIT = 9999;
	public static void main(String[] args) {
		Random random = new Random();
		Vault vault = new Vault(random.nextInt(MAX_LIMIT));
		List<Thread> threads = new ArrayList<Thread>();
		threads.add(new AscendingThread(vault));
		threads.add(new DescendingThread(vault));
		threads.add(new PoliceThread());
		
		for( Thread thread: threads) {
			thread.start();
		}
		
		
	}
	public static class Vault{
		
	

		private int password;
		public Vault(int password) {
			this.password = password;
		}
		public boolean isCorrectPassword(int guess) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this.password==guess;
		}
	}
	
	public static abstract class HackerThread extends Thread{

		protected Vault vault;
		
		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(MAX_PRIORITY);
		}

		@Override
		public  void start() {
			// TODO Auto-generated method stub
			System.out.println("Starting thread: "+this.getName());
			super.start();
			
		}
	}
	
	public static class AscendingThread extends HackerThread{

	

		public AscendingThread(Vault vault) {
			super(vault);
		}
       
		@Override
		public void run() {
			 for(int i=0;i<=MAX_LIMIT;i++) {
		        	if(this.vault.isCorrectPassword(i)) {
		        		System.out.println(this.getName() +" guessed the password and the guess: "+ i);
		        		System.exit(0);
		        	}
		        }
		}	
		
	}

    public static class DescendingThread extends HackerThread{

		public DescendingThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for(int i=MAX_LIMIT;i>=0;i--) {
				if(this.vault.isCorrectPassword(i)) {
					System.out.println(this.getName() + " guessed the password and the guess: "+i);
					System.exit(0);
				}
			}
		}
    	
    }
    
    public static class PoliceThread extends Thread{

		@Override
		public void run() {
			for(int i=10;i>=0;i--) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Police caught the hackerthreads....Game over!!");
			System.exit(0);
		}
    	 
    }
}
