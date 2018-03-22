package designPattern.proxy.staticc;

public class ProxyMan implements Italk{
	private Italk italk;
	public ProxyMan(Italk ta){
		italk = ta;
	}
	@Override
	public void talk(String say) {
		italk.talk(say);
	}

}
