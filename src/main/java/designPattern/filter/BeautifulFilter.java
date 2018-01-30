package designPattern.filter;

import java.util.ArrayList;
import java.util.List;

public class BeautifulFilter implements FilterInterface{
	private String beautiful;
	public BeautifulFilter(){
		
	}
	public BeautifulFilter(String bu){
		this.beautiful = bu;
	}
	@Override
	public List<Person> choose(List<Person> list) {
		List<Person> rlist = new ArrayList<Person>(); 
		for(Person p : list){
			if(beautiful.equals(p.getBeautiful())){
				rlist.add(p);
			}
		}
		return rlist;
	}
}
