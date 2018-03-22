package designPattern.proxy.springAop.resource;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class RetryAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("sayBefor");
		method.invoke(target,args);
	}
}
