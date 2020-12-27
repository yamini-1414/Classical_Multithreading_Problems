package basics;
public class ReEntrantLock {

	boolean isLocked = false;
	Thread lockedBy = null;
	int lockedCount = 0;
	public synchronized void lock() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		while(isLocked && lockedBy != callingThread) {
			wait();
		}
		this.isLocked = true;
		this.lockedCount++;
		this.lockedBy = callingThread;
	}
	
	public synchronized void unlock() {
		if(Thread.currentThread() == lockedBy) {
			this.lockedCount--;
		}
		if(this.lockedCount==0) {
			isLocked = false;
			notify();
		}
	}
}