package test.path.redis;

import com.zh.cn.trio.cache.redis.operation.RedisStringOperation;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisStringOperationImpl implements RedisStringOperation {

	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public boolean exists(String key) {
		return jedisPool.getResource().exists(key);
	}

	@Override
	public boolean expireat(String key, String val, int cacheTime) {
		Jedis jedis = jedisPool.getResource();
		jedis.set(key, val);
		jedis.expire(key, cacheTime);
		return true;
	}

	@Override
	public String get(String key) {
		String val = jedisPool.getResource().get(key);
		return val;
	}

	@Override
	public boolean del(String key) {
		jedisPool.getResource().del(key);
		return true;
	}

}
