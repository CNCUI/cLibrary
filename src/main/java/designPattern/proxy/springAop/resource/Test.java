package designPattern.proxy.springAop.resource;

import org.springframework.aop.framework.ProxyFactory;

public class Test {
	public static void main(String[] args) {

        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new RetryAdvice());
        factory.setExposeProxy(true);
        Pojo pojo = (Pojo) factory.getProxy();

        // this is a method call on the proxy!
        pojo.fo();
    }
}
