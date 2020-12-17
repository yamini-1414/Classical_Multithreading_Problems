
public class Semaphore {
 int usedPermits = 0;
 int maxCount = 0;
 
 public Semaphore(int maxCount) {
	 this.maxCount = maxCount;
 }
 public synchronized void acquire() throws InterruptedException {
	 
	 while(usedPermits==maxCount)
		 wait();
	 usedPermits++;
	 notify();
 }
 public synchronized void release() throws InterruptedException {
	 while(usedPermits==0) {
		 wait();
	 }
	 usedPermits--;
	 notify();
 }
}
