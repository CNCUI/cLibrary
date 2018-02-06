package designPattern.decorator;

public class Q_skill extends Skills{
	private String skillName;
	public Q_skill(Hero he,String sName) {
		super(he);
		skillName = sName;
	}
	
	@Override
    public void learnSkills() {
        System.out.println("学习了技能Q:" +skillName);
        super.learnSkills();
    }
}
