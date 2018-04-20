package designPattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PrototypeTest {
	/**
	 * 浅拷贝知识拷贝了对象的引用，操作的还是同一个对象 深拷贝才是复制为新的对象
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Prototype t1 = new Prototype();
		Prototype t2 = t1.clone();
		t1.str = "t1str";
		t1.list.add("t1");

		t2.str = "t2str";
		t2.list.add("t2");
		co c = new co("nnn");
		t2.list.add(c);

		System.out.println(t1.list == t2.list);
		t1.print();
		t2.print();
	}

	/**
	 * 深克隆
	 */
	public Object deepClone() throws IOException, ClassNotFoundException {
		// 将对象写到流里
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		// 从流里读回来
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
}
