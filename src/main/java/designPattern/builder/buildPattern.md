1.首先创建实体类person,有title,body,ege等属性
2.创建接口PersonBuilder,有setTitle等方法
3.创建Builder类，实现PersonBuilder接口，在这里组装person
4.创建BuilderFactory类，继承Builder，通过调用父类方法在这里封装组装过程
5.测试类BuildTest实例化BuilderFactory，按需组装person对象