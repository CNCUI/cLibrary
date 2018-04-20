1.mysql5.6更改字符集latin1为utf-8
	找到文件：C:\Program Files (x86)\MySQL\MySQL Server 5.6\my.ini
	修改文件内容：
		[client]
			port=3306
			default-character-set=utf8
		[mysql]
			default-character-set=utf8
			...
			character-set-server=utf8
			
			
2.mysql5.6配置主（本机windows）从(服务器centerOs)同步
	先配置好相同字符集