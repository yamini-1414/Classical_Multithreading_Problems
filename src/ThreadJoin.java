import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadJoin {
	public static void main(String[] args)  throws Exception{
		List<Long> inputs = Arrays.asList(0L, 345L, 24L, 6757L, 9087L, 2345L);
		List<FactorialThread> threads = new ArrayList<FactorialThread>();
		for(Long input: inputs) {
			threads.add(new FactorialThread(input));
		}
		for(Thread thread: threads) {
			thread.start();
		}
		for(Thread thread: threads) {
			thread.join(100000);
		}
		for(int i=0;i<inputs.size();i++) {
		    if(threads.get(i).isFinished) {

		    	System.out.println("Factorial of "+ inputs.get(i)+" is "+ threads.get(i).getResult());
		    }else {
		    	System.out.println("The calculation for "+ inputs.get(i)+"  is in progress");
		    }
		}
			
	}
	
	public static class FactorialThread extends Thread{
		private long inputNumber;
		private BigInteger result = BigInteger.ZERO;
		private boolean isFinished = false;
		public FactorialThread(long inputNumber) {
			this.inputNumber = inputNumber;
		}
		@Override
		public void run() {
			this.result = factorial(this.inputNumber);
			this.isFinished = true;
		}
		public BigInteger factorial(long n) {
			BigInteger tempResult = BigInteger.ONE;
			
			for(long i=n;i>=1;i--) {
				tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
			}
			return tempResult;
		}
		public BigInteger getResult() {
			return result;
		}
		public void setResult(BigInteger result) {
			this.result = result;
		}
		public boolean isFinished() {
			return isFinished;
		}
		public void setFinished(boolean isFinished) {
			this.isFinished = isFinished;
		}
		
	}

}

