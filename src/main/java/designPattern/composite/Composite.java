package designPattern.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component{
	List<Component> list = new ArrayList<Component>();
	public Composite() {
		super();
	}
	public Composite(String name) {
		super(name);
	}
	@Override
	public void add(Component c) {
		this.list.add(c);
	}

	@Override
	public void remove(Component c) {
		this.list.remove(c);
	}

	@Override
	public void display() {
		System.out.println(this.name);
		for(Component c : this.list){
			c.display();
		}
		
	}

}
