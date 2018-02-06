package designPattern.decorator;

public class Monsen implements Hero{
	private String name;
	public Monsen(String n){
		name = n;
	}
	@Override
	public void learnSkills() {
		 System.out.println(name + "学习了以上技能！");
	}

}
