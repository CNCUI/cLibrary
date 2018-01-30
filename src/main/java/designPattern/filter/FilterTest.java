package designPattern.filter;

import java.util.ArrayList;
import java.util.List;
/**
 * 过滤器模式主要是从一组对象中筛选出具备某种特征的对象
 * @author Cao
 *
 */
public class FilterTest {

	public static void main(String[] args) {
		Person p1 = new Person("qwe","white","women","good");
		Person p2 = new Person("asd","black","women","good");
		Person p3 = new Person("zxc","white","women","good");
		Person p4 = new Person("qaz","white","man","good");
		Person p5 = new Person("wsx","white","women","bad");
		List<Person> list = new ArrayList<Person>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		
		SkinFilter skinFilter = new SkinFilter("white");
		List<Person> whitelist = skinFilter.choose(list);
//		System.out.println(toString(whitelist));
		
		SexFilter sexFilter = new SexFilter("women");
		List<Person> womenlist = sexFilter.choose(list);
//		System.out.println(toString(womenlist));
		
		BeautifulFilter beautifulFilter = new BeautifulFilter("good");
		List<Person> beautifulList = beautifulFilter.choose(list);
//		System.out.println(toString(beautifulList));
		
		List<Person> whiteAndwomenAndgood = beautifulFilter.choose(sexFilter.choose(skinFilter.choose(list)));
		System.out.println(toString(whiteAndwomenAndgood));
	}
	
	public static String toString(List<Person> list){
		StringBuffer bu = new StringBuffer();
		for(Person p : list){
			bu.append("Person[name:").append(p.getName());
			bu.append(",skin:").append(p.getSkin());
			bu.append(",sex:").append(p.getSex());
			bu.append(",beautiful:").append(p.getBeautiful()).append("]\n");
		}
		return bu.toString();
	}

}
