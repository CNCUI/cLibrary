package tomcatListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * tomcat启动时执行
 *
 */
public class StartRun implements ServletContextListener {

	/**
	 * web.xml  添加如下
	 * <listener>
	    <listener-class>com.szjx.trig.weixin.util.autoRun</listener-class>
	   </listener>
	*/
	// 当后台被初始化，即发生了tomcat启动了事件，固定用法
	public void contextInitialized(ServletContextEvent arg0) {
		// 你要做的事儿，写在这里
		for(int i=0;i<10;i++){
			System.out.println("tomcat启动");
		}
	}

	// 当后台被销毁，即发生了tomcat关闭了事件，固定用法
	public void contextDestroyed(ServletContextEvent arg0) {
		// 执行内容写在这里
	}
}

