package designPattern.decorator;

public class DecoratorTest {
	public static void main(String[] args){
		Hero hero = new Monsen("Monsen");
		Skills q = new Q_skill(hero,"Qbiu");
		Skills w = new W_skill(q,"Wbiu");
		w.learnSkills();
	}
}
