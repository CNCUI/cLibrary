package redis;

import java.util.Arrays;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

/**
 * redis插入十万数据性能比较
 * @author Cao
 *
 */
public class RedisDemo {

	private static Jedis jedis;
	private static ShardedJedis sharding;
	static ShardedJedisPool pool;

	public static void init() throws Exception {
		JedisShardInfo jc1 = new JedisShardInfo("127.0.0.1", 6379);
		jc1.setPassword("111111");
		JedisShardInfo jc2 = new JedisShardInfo("127.0.0.1", 6379);
		jc2.setPassword("111111");
		List<JedisShardInfo> shards = Arrays.asList(jc1,jc2); // 使用相同的ip:port,仅作测试

		jedis = new Jedis("127.0.0.1");
		jedis.auth("111111");
		sharding = new ShardedJedis(shards);
		JedisPoolConfig config = new JedisPoolConfig();// Jedis池配置
		pool = new ShardedJedisPool(config, shards);
	}

	public static void CleanUp() throws Exception {
		jedis.disconnect();
		sharding.disconnect();
		pool.destroy();
	}

	// 测试普通同步方式， 设置10w个key，value，看用时多少。
	public void test1Normal() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String result = jedis.set("1n" + i, "1n" + i);
		}
		long end = System.currentTimeMillis();
		System.out.println("普通同步方式::Simple SET: " + ((end - start) / 1000.0) + " seconds");
	}

	// 测试事务方式Transactions， 设置10w个key，value，看用时多少。
	public void test2Trans() {
		long start = System.currentTimeMillis();
		Transaction tx = jedis.multi();
		for (int i = 0; i < 100000; i++) {
			tx.set("1t" + i, "1t" + i);
		}
		// System.out.println(tx.get("t1000").get());

		List<Object> results = tx.exec();
		long end = System.currentTimeMillis();
		System.out.println("事务方式::Transaction SET: " + ((end - start) / 1000.0) + " seconds");
	}

	// 采用管道方式，异步方式，一次发送多个指令，
	public void test3Pipelined() {
		Pipeline pipeline = jedis.pipelined();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			pipeline.set("p" + i, "p" + i);
		}
		// System.out.println(pipeline.get("p1000").get());
		List<Object> results = pipeline.syncAndReturnAll();
		long end = System.currentTimeMillis();
		System.out.println("管道方式异步：：Pipelined SET: " + ((end - start) / 1000.0) + " seconds");
	}

	// 管道中调用事务,
	public void test4combPipelineTrans() {
		long start = System.currentTimeMillis();
		Pipeline pipeline = jedis.pipelined();
		pipeline.multi();
		for (int i = 0; i < 100000; i++) {
			pipeline.set("" + i, "" + i);
		}
		// 与管道的区别在这里
		pipeline.exec();
		List<Object> results = pipeline.syncAndReturnAll();
		long end = System.currentTimeMillis();
		System.out.println("管道中调用事务::Pipelined transaction: " + ((end - start) / 1000.0) + " seconds");
	}

	// 分布式直连同步调用,用到了分片
	public void test5shardNormal() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String result = sharding.set("sn" + i, "n" + i);
		}
		long end = System.currentTimeMillis();
		System.out.println("分布式直连同步::Simple@Sharing SET: " + ((end - start) / 1000.0) + " seconds");
	}

	// 分布式直连异步调用
	public void test6shardpipelined() {
		// 采用sharding 对象
		ShardedJedisPipeline pipeline = sharding.pipelined();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			pipeline.set("sp" + i, "p" + i);
		}
		List<Object> results = pipeline.syncAndReturnAll();
		long end = System.currentTimeMillis();
		System.out.println("分布式直连异步::Pipelined@Sharing SET: " + ((end - start) / 1000.0) + " seconds");
	}

	// 分布式连接池同步调用, 线程安全
	public void test7shardSimplePool() {
		ShardedJedis one = pool.getResource();

		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			String result = one.set("spn" + i, "n" + i);
		}
		long end = System.currentTimeMillis();
		pool.returnResource(one);
		System.out.println("分布式连接池同步调用::Simple@Pool SET: " + ((end - start) / 1000.0) + " seconds");
	}

	// 分布式连接池异步调用
	public void test8shardPipelinedPool() {
		ShardedJedis one = pool.getResource();

		ShardedJedisPipeline pipeline = one.pipelined();

		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			pipeline.set("sppn" + i, "n" + i);
		}
		List<Object> results = pipeline.syncAndReturnAll();
		long end = System.currentTimeMillis();
		pool.returnResource(one);
		System.out.println("分布式连接池异步调用::Pipelined@Pool SET: " + ((end - start) / 1000.0) + " seconds");
	}

	public static void main(String[] args) {

		RedisDemo testDemo = new RedisDemo();
		try {
			testDemo.init();
		} catch (Exception Exc) {

			Exc.printStackTrace();
			System.exit(0);
		}

		System.out.println("init complete sucessfully!!!");

		// 测试普通同步方式
		testDemo.test1Normal();
		// 测试事务方式Transactions
		testDemo.test2Trans();
		// 采用管道方式，异步方式
		testDemo.test3Pipelined();
		// 管道中调用事务,
		testDemo.test4combPipelineTrans();
		// 分布式直连同步调用,用到了分片
		testDemo.test5shardNormal();
		// 分布式直连异步调用
		testDemo.test6shardpipelined();
		// 分布式连接池同步调用, 线程安全
		testDemo.test7shardSimplePool();
		// 分布式连接池异步调用, 线程安全
		testDemo.test8shardPipelinedPool();

		try {
			testDemo.CleanUp();
		} catch (Exception Exc) {
			Exc.printStackTrace();
			System.exit(0);
		}

	}

}
