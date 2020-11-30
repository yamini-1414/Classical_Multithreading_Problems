
import java.util.Date;
import java.util.HashMap;

public final class Immutable {
    private final int id;
    private final String name;
    private final Date date;
    private final HashMap<Integer, String> map;
    
    public Immutable(int id,String name, Date date, HashMap<Integer,String> map) {
    	this.id = id;
    	this.name = name;
    	this.date = new Date(date.getTime());
    	HashMap<Integer,String> tempMap = new HashMap<Integer,String>();
    	for(int key: map.keySet()) {
    		tempMap.put(key, map.get(key));
    	}
    	this.map = tempMap;
    }
    public int getId() {
    	return id;
    }
    public String getName(){
    	return name;
    }
    public Date getDate() {
    	return new Date(date.getTime());
    }
    public static void main(String[] args) {
    	HashMap<Integer,String> map = new HashMap<>();
    	map.put(1, "Harry");
    	map.put(2,"Daniel");
    	Immutable im = new Immutable(1,"Jack",new Date(),map);
    	for(int i=0;i<10;i++)
    	System.out.println(im.getDate()+ "   "+map);
 
    }
}
