package designPattern.filter;

import java.util.ArrayList;
import java.util.List;

public class SkinFilter implements FilterInterface{
	private String skin;
	public SkinFilter(){
		
	}
	public SkinFilter(String sk){
		this.skin = sk;
	}
	@Override
	public List<Person> choose(List<Person> list) {
		List<Person> rlist = new ArrayList<Person>(); 
		for(Person p : list){
			if(skin.equals(p.getSkin())){
				rlist.add(p);
			}
		}
		return rlist;
	}

}
