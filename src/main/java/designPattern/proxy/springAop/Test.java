package designPattern.proxy.springAop;

public class Test {
    public static void main(String[] args) {
        // 绑定代理，这种方式会在所有的方法都加上切面方法
    	ITalk iTalk = (ITalk) new DynamicProxy().bind(new properTalk());
        iTalk.talk("业务说明");
        
        //cglib
        properTalk peopleTalk = (properTalk) new CglibProxy().getInstance(new properTalk());
        peopleTalk.talk("业务方法");
    }
}
