package designPattern.filter;

import java.util.ArrayList;
import java.util.List;

public class SexFilter implements FilterInterface{
	private String sex;
	public SexFilter(){
		
	}
	public SexFilter(String se){
		this.sex = se;
	}
	@Override
	public List<Person> choose(List<Person> list) {
		List<Person> rlist = new ArrayList<Person>(); 
		for(Person p : list){
			if(sex.equals(p.getSex())){
				rlist.add(p);
			}
		}
		return rlist;
	}
}
