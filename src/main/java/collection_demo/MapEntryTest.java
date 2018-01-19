package collection_demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapEntryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>() ; 
		map.put("1", "1v");
		map.put("2", "2v");
		map.put("3", "3v");
		for (Map.Entry<String,String> entry : (Set<Map.Entry<String,String>>)map.entrySet()) {
		     entry.getKey() ;   
		     entry.getValue() ;   
		     
		     System.out.println(entry.getKey());
		     System.out.println(entry.getValue());
		} 

	}

}
