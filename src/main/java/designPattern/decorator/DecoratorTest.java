package designPattern.decorator;

/**
 * 装饰器模式
 * 装饰模式一般在下列情况使用：需要扩展一个类的功能或者给你个类增加附加责任；需要动态的给一个对象增加功能，这些功能可以再动态的撤销；需要增加有一些基本功能的排列组合而产生非常大量的功能，从而使得继承关系变得不现实。
	适配器模式一般使用的情况包括：系统需要使用现有的类，但此类已经不符合系统的需要；
	想要建立一个可以重复使用的类，用于与一些彼此之间没有太大关联的一些类，包括一些可能在将来引进的的类一起工作。适配器模式在系统升级的时候使用的频率很高，对旧系统的一些功能方法在新系统中引用。
 * @author Cao
 *	应用：
	当我们创建数据库连接池中连接时， 当我们用完我们创建的连接需要将连接放回到连接池（此处用集合模拟连接池）时，
	此时Connection类中的close方法不能够满足我们的需求， 因为他是关闭连接，不是放回到连接池， 
	所以我们就想增强一下他的close方法，将这个方法重写为当在连接池中调用close方法关闭连接时，我们将这个连接放回到连接池，
	此时我们就用到了装饰者设计模式。
 */
public class DecoratorTest {
	public static void main(String[] args){
		Hero hero = new Monsen("Monsen");
		Skills q = new Q_skill(hero,"Qbiu");
		Skills w = new W_skill(q,"Wbiu");
		w.learnSkills();
	}
}





/**
装饰者设计模式：用于数据库连接中close方法的增强

自我理解：装饰者设计模式的方法增强，就是自定义一个类去实现我们需要增强的方法的接口，并定义一个成员变量对象去存储我们之前的对象，
那么这个成员变量所有调用的方法就是我们原来接口中的方法，因为成员变量对象 是我们原来对象的副本，然后改写我们要增强的方法即可。

应用：
当我们创建数据库连接池中连接时， 当我们用完我们创建的连接需要将连接放回到连接池（此处用集合模拟连接池）时，
此时Connection类中的close方法不能够满足我们的需求， 因为他是关闭连接，不是放回到连接池， 
所以我们就想增强一下他的close方法，将这个方法重写为当在连接池中调用close方法关闭连接时，我们将这个连接放回到连接池，
此时我们就用到了装饰者设计模式。

自我理解：如果需要在增强后，真正的关闭连接 那么我们需要自定义方法去获取我们增强版的类的成员变量对象，因为这个对象就是原来的对象，再用他去调用我们增强类实现的接口里面的方法，就可达到目的（其实就是将原来对象用副本对象代替执行原有的方法）

实际代码：
Connection conn = DriverManager...
MyConnection myConn = new MyConnection(conn);
myConn.close();//此方法增强了
myConn.commit();//底层调用C类具体方法，conn.commit()
连接池中之后使用都是MyConnection , pool.add(myConn)

步骤：
1.必须实现接口A
2.提供有参构造，参数类型为接口A
3.提供成员变量，存放构造方法接口实际参数（其他实现类）
4.实现需要增强的方法
5.实现不需要增强的方法，调用成员变量对应的方法即可


/** 目的：使用装饰者设计模式，对Connection接口的close方法进行增强。
 * 装饰者设计模式固定的步骤：
 * 接口A，自定义实现B，其他实现类C
 * * 模式
 *     A a = C (工具类)
 *     B b = new B(a);
 * * 实际代码
 * Connection conn = DriverManager....
 * MyConnection myConn = new MyConnection( conn );
 * myConn.close();        //此方法增强了
 * myConn.commit() ; //底层调用C类具体方法，conn.commit();
 * **连接池中之后使用都是MyConnection  ,pool.add(myConn);
 * 
 * 1.必须实现接口A
 * 2.提供有参构造，参数类型为接口A
 * 3.提供成员变量，存放构造方法接口实际参数(其他实现类)
 * 4.实现需要增强的方法
 * 5.实现不需要增强的方法，调用成员变量对应的方法即可。
 * 

public class MyConnection2 implements Connection {
    
    private Connection conn;
    private List<Connection> pool;
//    public MyConnection2(Connection conn){
//        this.conn = conn;
//    }
    public MyConnection2(Connection conn,List<Connection> pool){
        this.conn = conn;
        this.pool = pool;
    }
    
    //增强的方法--将当前连接归还给连接池
    @Override
    public void close() throws SQLException {
        //this 当前对象，表示当前连接
        pool.add(this);
    }
    //可以真实关闭
    public Connection getConnection(){
        return this.conn;
    }
    // 不增强的方法
    @Override
    public void commit() throws SQLException {
        this.conn.commit();
    }
    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        this.conn.rollback(savepoint);
    }
    
具体实现 包括真实关闭
/** 进行方法增强，对close方法进行增强。
 * 1.继承，子类复写父类的方法。必须明确父类（可以获得父类名称）。
 *     通过工具类DriverManager获得连接，无法确定父类。
 * 2.设计模式--装饰者，必须有接口【】
 *     设计模式：就是一段固定的代码，为了解决固定的问题。
 *     装饰者目的：增强方法
 * 3.动态代理，必须有接口
 * 4.字节码增强，增强指定类，不需要接口
 *

public class MyPool4 {
    
    //1 容器 -- LinkedList --> 增删多，比较快
    private static LinkedList<Connection> pool = new LinkedList<Connection>(); 
    
    static{
        //1.1 初始化连接池，提供3个连接
        try {
            //1) 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            
            for(int i = 0 ; i < 3 ; i ++){
                //2) 获得连接
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ee30_day10", "root", "1234");
                // 将连接添加到池中
                //pool.add(conn);  //conn 提供连接的close存在缺陷，没有将连接归还给连接池
                //3)将已经增强close的MyConnection添加到连接池中
                MyConnection myConn = new MyConnection( conn , pool );
                pool.add(myConn);
            }
            
            //3) JVM 虚拟机关闭时，真实关闭连接
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                        for(Connection myConn : pool){
                            if(myConn instanceof MyConnection){    //判断前面变量是否是后面指定类型
                                MyConnection my = (MyConnection) myConn;
                                System.out.println("真实关闭" + my);
                                my.getConnection().close();
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            });
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    //2 获得连接,先判断池中是否有，如果有移除。否则等待100毫秒，递归
    public static Connection getConnection(){
        //有
        if(! pool.isEmpty()){
            return pool.removeFirst();
        }
        
        //没有
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        return getConnection();
    }
    
    //3 释放资源--将连接归还给连接池（添加到池中）
    public static void closeResource(Connection conn){
        //conn.close();  //真的关闭连接
        pool.add(conn);
    }
    

}














 **/
