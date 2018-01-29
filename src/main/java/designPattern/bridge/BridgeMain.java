package designPattern.bridge;

/**
 * 充当桥梁
 * @author Cao
 *
 */
public class BridgeMain extends BridgeAbstract{
	
	public void operation(){
		super.getBridgeInterface().operation();
	}
}
