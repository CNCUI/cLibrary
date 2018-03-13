下载源码：
wget http://download.redis.io/releases/redis-3.2.8.tar.gz
解压源码：
tar -zxvf redis-3.2.8.tar.gz
进入到解压目录：
cd redis-3.2.8
执行make编译Redis：
make MALLOC=libc
注意：make命令执行完成编译后，会在src目录下生成6个可执行文件，分别是redis-server、redis-cli、redis-benchmark、redis-check-aof、redis-check-rdb、redis-sentinel。
安装Redis：
make install 
配置Redis能启动:
./utils/install_server.sh 一直回车

Redis服务查看、开启、关闭:
a.通过ps -ef|grep redis命令查看Redis进程
b.开启Redis服务操作通过/etc/init.d/redis_6379 start命令，也可通过（service redis_6379 start）
c.关闭Redis服务操作通过/etc/init.d/redis_6379 stop命令，也可通过（service redis_6379 stop）