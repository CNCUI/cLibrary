package collection_demo;

import java.util.ArrayList;
import java.util.List;

public class ListToString {
	public static void main(String[] args) {
		List list = new ArrayList();
    	list.add("1");
    	list.add("2");
    	list.add("3");
    	String[] s = (String[]) list.toArray(new String[list.size()]);
	}
}
