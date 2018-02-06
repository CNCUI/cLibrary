package designPattern.decorator;

public class Skills implements Hero{
	private Hero hero;
	public Skills(Hero he){
		hero = he;
	}
	@Override
	public void learnSkills() {
		hero.learnSkills();
	}

}
