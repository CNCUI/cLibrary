package redis;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		jedis.auth("111111");
		System.out.println(jedis.keys("key001"));
	}
}
