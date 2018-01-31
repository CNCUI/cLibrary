package designPattern.composite;

public abstract class Component {
	public String name;
	public Component(){
	}
	public Component(String name){
		this.name = name;
	}
	public void add(Component c){};
	public void remove(Component c){};
	public abstract void display();
	
}
