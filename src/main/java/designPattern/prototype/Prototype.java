package designPattern.prototype;

import java.util.ArrayList;
import java.util.List;

public class Prototype implements Cloneable{
	public String str = "111";
	public List<String> list = new ArrayList<String>();
	public Prototype(){
		list.add("1");
		list.add("2");
		list.add("3");
		System.out.println("构造方法");
	}
	public Prototype clone(){
		Prototype pro = null;
		try {
			pro = (Prototype)super.clone();
			//由于ArrayList不是基本类型，所以成员变量list，不会被拷贝，需要我们自己实现深拷贝，
			//幸运的是java提供的大部分的容器类都实现了Cloneable接口。所以实现深拷贝并不是特别困难。
			pro.list = (List<String>) ((ArrayList) this.list).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return pro;
	}
	
	public void print(){
		System.out.println("print==");
		System.out.println("string:"+this.str);
		System.out.println("list:"+this.list);
	}

}

