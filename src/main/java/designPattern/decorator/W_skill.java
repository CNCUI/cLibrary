package designPattern.decorator;

public class W_skill extends Skills{
	private String skillName;
	public W_skill(Hero he,String sName) {
		super(he);
		skillName = sName;
	}
	
	@Override
    public void learnSkills() {
        System.out.println("学习了技能W:" +skillName);
        super.learnSkills();
    }
}
