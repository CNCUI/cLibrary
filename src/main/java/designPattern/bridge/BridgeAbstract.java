package designPattern.bridge;

/**
 * 抽象类封装
 * @author Cao
 *
 */
public abstract class BridgeAbstract{
	private BridgeInterface bridgeInterface = null;
	
	public BridgeInterface getBridgeInterface() {
		return bridgeInterface;
	}
	public void setBridgeInterface(BridgeInterface bridgeInterface) {
		this.bridgeInterface = bridgeInterface;
	}

}
