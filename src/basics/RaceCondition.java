package basics;
import java.util.Random;

public class RaceCondition {
	static class BadCounter{
		int count=0;
		public void increment() {
			count++;
		}
		public void decrement() {
			count--;
		}
		public void printFinalValue() {
			System.out.println("The final value of count "+ count);
		}
	}
	public static void sleepThread() {
		try {
			Thread.sleep(new Random().nextInt(10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		BadCounter b = new BadCounter();
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					b.increment();
					sleepThread();
				}
				
			}
			
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					b.decrement();
					sleepThread();
				}
				
			}
			
		});
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
		
		
		b.printFinalValue();
	
	}
}

