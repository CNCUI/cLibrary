package designPattern.builder;

/**
 * 建造者模式
 * @author Cao
 *
 */
public class BuildTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person person = BuilderFactory.getBuilderFactory().buildTile().buildBody().buildEge().build();
		System.out.println(person.toString());
		
		Person person2 = BuilderFactory.getBuilderFactory().buildTile().buildBody().build();
		System.out.println(person2.toString());
	}

}
