package designPattern.filter;

public class Person {
	private String name;
	private String skin;
	private String sex;
	private String beautiful;
	public Person(){
		
	}
	public Person(String name, String skin, String sex, String beautiful) {
		super();
		this.name = name;
		this.skin = skin;
		this.sex = sex;
		this.beautiful = beautiful;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBeautiful() {
		return beautiful;
	}
	public void setBeautiful(String beautiful) {
		this.beautiful = beautiful;
	}
	
}
