
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BackgroundThread {
   static class Cache {
	  private String data;
	  Cache(String data){
		  this.setData(data);
	  }
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	  
  }
   public static void main(String[] args) throws InterruptedException {
	   Cache mainCache = new Cache("Cat");
	   Cache slaveCache1 = new Cache("Cat");
	   Cache slaveCache2 = new Cache("Cat");
	   
	   Runnable task1 = new Runnable() {

		@Override
		public void run() {
			update(slaveCache1,"Dog");
			System.out.println("Cache1 updated by task1");
		}
		   
	   };
	   Runnable task2 = new Runnable() {

			@Override
			public void run() {
				update(slaveCache2,"Dog");
				System.out.println("Cache2 updated by task2");
			}
			   
		   };
	   mainCache.setData("Dog");
	   ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
	   executor.execute(task1);
	   executor.execute(task2);
	   System.out.println("The data in the cache "+mainCache.data +" and in cache1 "+ slaveCache1.data +
			   " and in cache2 "+ slaveCache2.data);
	   executor.shutdown();
   }
   static void update(Cache cache, String data) {
	   cache.setData(data);
   }
   
}
