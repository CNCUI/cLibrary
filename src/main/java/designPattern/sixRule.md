1.开闭原则（对扩展开发，对修改关闭）
2.里氏替换原则（基类出现的地方能用子类替换）
3.依赖倒转原则（针对接口编程，依赖抽象而不依赖具体）
4.接口隔离原则（使用多个隔离接口，降低耦合）
4.迪米特法则（最少知道原则，跟最少的类有关）
5.合成复用原则（尽量使用合成/聚合的方式，而不使用继承）


创建型模式-->对象怎么来
	工厂模式（Factory Pattern）  接口，实现类，工厂，if,调用get("XXX")
	抽象工厂模式（Abstract Factory Pattern）一组接口，实现类，抽象类，继承抽象类的工厂，if,工厂创建器，调用get("XXX")
	单例模式（Singleton Pattern）static实例，懒汉，饿汉，双检锁(if(instace==null),synchronized,if(instace==null))，枚举（构造方法只会执行一次 t=new T()）
	建造者模式（Builder Pattern）一个接口多个方法，实现类单个组装，继承实现类的建造者工厂调用父类方法return this
	原型模式（Prototype Pattern）实现Cloneable接口重写clone()，用于对象创新非常繁琐
结构型模式-->对象和谁有关
	适配器模式（Adapter Pattern）高级通用接口，接口包含各自方法，实现类，高级接口实现类，if创建不同对象，适配器if条件，
	桥接模式（Bridge Pattern）
	过滤器模式（Filter、Criteria Pattern）
	组合模式（Composite Pattern）
	装饰器模式（Decorator Pattern）
	外观模式（Facade Pattern）
	享元模式（Flyweight Pattern）
	代理模式（Proxy Pattern）
行为型模式-->对象与对象在干嘛
	责任链模式（Chain of Responsibility Pattern）
	命令模式（Command Pattern）
	解释器模式（Interpreter Pattern）
	迭代器模式（Iterator Pattern）
	中介者模式（Mediator Pattern）
	备忘录模式（Memento Pattern）
	观察者模式（Observer Pattern）
	状态模式（State Pattern）
	空对象模式（Null Object Pattern）
	策略模式（Strategy Pattern）
	模板模式（Template Pattern）
	访问者模式（Visitor Pattern）