package producer_consumer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedSolution {
	public static void main(String[] args) throws InterruptedException{
	  final BlockingQueue<Integer> q = new BlockingQueue<Integer>(100);
	  
	  Thread thread1 = new Thread(new Runnable() {

		@Override
		public void run() {
			for(int i=0;i<20;i++) {
				try {
					q.enqueue(new Integer(i));
					System.out.println(i +" element enqueued");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		  
	  });
	  Thread thread2 = new Thread(new Runnable() {

		@Override
		public void run() {
			for(int i=0;i<10;i++) {
				try {
					System.out.println("The item dequeued is "+ (Integer)q.dequeue());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	  });
	  
	  Thread thread3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					try {
						System.out.println("The item dequeued is "+ (Integer)q.dequeue());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		  });
	  Thread threadLock1 = new Thread(new Runnable() {

		@Override
		public void run() {
			for(int i=0;i<20;i++) {
				q.enqueueLock((Integer)(i+1));
				System.out.println( (i+1) + " element inserted");
			}
		}
		  
	  });
	  
	  Thread threadLock2 = new Thread(new Runnable() {

		@Override
		public void run() {
			for(int i=0;i<10;i++) {
				System.out.println("The element dequeued from the queue "+q.dequeueLock());
			}
		}		  
	  });
	  Thread threadLock3 = new Thread(new Runnable() {

		@Override
		public void run() {
			for(int i=0;i<10;i++) {
				System.out.println("The element dequeued from the queue "+q.dequeueLock());
			}
		}		  
	  });
	  
	  threadLock1.start();
	  Thread.sleep(4000);
	  threadLock2.start();
	  threadLock2.join();
	  threadLock3.start();
	  threadLock1.join();
	  threadLock3.join();
	  
	  
	}
	static class BlockingQueue<T>{
		T[] array;
		int size = 0;
		int capacity;
		int head = 0;
		int tail = 0;
		Lock lock  = new ReentrantLock();
		@SuppressWarnings("unchecked")
		public BlockingQueue(int capacity) {
			array = (T[]) new Object[capacity];
			this.capacity = capacity;
		}
		
		public synchronized void enqueue(T item) throws InterruptedException {
			while(size==capacity) {
				wait();
			}
			
			if(tail==capacity) {
				tail = 0;
			}
			
			array[tail] = item;
			tail++;
			size++;
			
			notifyAll();
		}
		public void enqueueLock(T item) {
			lock.lock();
			while(size==capacity) {
				//Unlock as the condition is checked and the allow the condition to change;
				lock.unlock();
				// Lock again to check the condition
				lock.lock();
			}
			
			if(tail==capacity) {
				tail = 0;
			}
			array[tail] = item;
			tail++;
			size++;
			
			lock.unlock();
		}
		
		public T dequeueLock() {
			T item = null;
			lock.lock();
			while(size==0) {
				lock.unlock();
				
				lock.lock();
			}
			
			if(head==capacity) {
				head = 0;
			}
			item = array[head];
			head++;
			size--;
			
			lock.unlock();
			return item;	 
		}
		
		public synchronized T dequeue() throws InterruptedException{
			T item = null;
			while(size==0) {
				wait();
			}
			
			if(head==capacity) {
				head = 0;
			}
			item = array[head];
			head++;
			size--;
			
			notifyAll();
			return item;
		}
		
	}

}
