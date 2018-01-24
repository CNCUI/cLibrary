package designPattern.builder;

public class BuilderFactory extends Builder{
	private BuilderFactory(){};
	public BuilderFactory buildTile() {
		setTile();
		return this;
	}

	public BuilderFactory buildBody() {
		setBody();
		return this;
	}

	public BuilderFactory buildEge() {
		setEge();
		return this;
	}

	public Person build(){
		return p;
	}
	
	public static BuilderFactory getBuilderFactory(){
		return new BuilderFactory();
	}
}
