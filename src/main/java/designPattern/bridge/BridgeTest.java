package designPattern.bridge;

public class BridgeTest {
	public static void main(String[] args) {
		BridgeMain bm = new BridgeMain();
		bm.setBridgeInterface(new BridgeA());
		bm.operation();
		
		BridgeMain bm2 = new BridgeMain();
		bm2.setBridgeInterface(new BridgeB());
		bm2.operation();
		
	}
}
