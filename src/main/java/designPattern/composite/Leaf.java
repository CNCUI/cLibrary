package designPattern.composite;

public class Leaf extends Component{
	
	public Leaf() {
		super();
	}
	public Leaf(String name) {
		super();
		this.name = name;
	}
	@Override
	public void display() {
		System.out.println(this.name);
	}
	

}
