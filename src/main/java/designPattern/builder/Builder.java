package designPattern.builder;

public class Builder implements PersonBuilder{
	public Person p = new Person();

	@Override
	public void setTile() {
		p.setTitle("Tile");
	}

	@Override
	public void setBody() {
		p.setBody("Body");
	}

	@Override
	public void setEge() {
		p.setEge("Ege");
	}
}
