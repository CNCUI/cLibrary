1.使用命令：mysql -u root -padmin 登陆mysql时出现ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: YES)
2.重置mysql密码
	cd /etc
	vim my.cnf   //在mysqld下面增加一行skip-grant-tables，保存退出
	/etc/init.d/mysqld restart   //重启mysql
	mysql -u root -p
		mysql> use mysql;
		mysql> update user set password=password("admin") where user="root";
		mysql> flush privileges;
		mysql> quit
	vim my.cnf   //在mysqld去掉skip-grant-tables，保存退出
	/etc/init.d/mysqld restart  //重启mysql
	mysql -u root -padmin   //成功登陆
	
3.本地远程连接mysql
	报错，ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using passwor)
	service iptables status //查看端口状态
	vim /etc/sysconfig/iptables	//打开防火墙配置文件
	-A INPUT -m state --state NEW -m tcp -p tcp --dport 3306 -j ACCEPT   开启3306端口
	service iptables restart //重启防火墙
	mysql -u root -padmin //登陆mysql
	update user set host="%" where user="root"; //报错别管，继续下面操作
	flush privileges; 
	/etc/init.d/mysqld restart  //重启mysql
	
	