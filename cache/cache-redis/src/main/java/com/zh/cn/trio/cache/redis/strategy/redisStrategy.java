package com.zh.cn.trio.cache.redis.strategy;

import javax.annotation.Resource;

import com.zh.cn.trio.cache.redis.operation.RedisStringOperation;
import com.zh.cn.trio.cache.redis.serialization.Serialization;
import com.zh.cn.trio.cache.strategy.CacheStrategy;
import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;

public class redisStrategy implements CacheStrategy {

	@Override
	public String getName() {
		return "redisStrategy";
	}

	@Resource
	private RedisStringOperation redisStringOperation;

	@Resource
	private Serialization serialization;

	@Override
	public boolean hasCache(CacheStrategyInfo cacheStrategyInfo) {
		String key = cacheStrategyInfo.getRootKey();
		return redisStringOperation.exists(key);
	}

	@Override
	public Object getCache(CacheStrategyInfo cacheStrategyInfo) {
		String key = cacheStrategyInfo.getRootKey();
		String val = redisStringOperation.get(key);
		Object vals = serialization.forSerialization(val, cacheStrategyInfo.getGenericReturnType());
		return vals;
	}

	@Override
	public boolean setCache(CacheStrategyInfo cacheStrategyInfo) {
		String key = cacheStrategyInfo.getRootKey();
		Object object = cacheStrategyInfo.getResultObject();
		String val = serialization.serialization(object);
		return redisStringOperation.expireat(key, val, cacheStrategyInfo.getCacheTime());
	}

	@Override
	public boolean removeCache(CacheStrategyInfo cacheStrategyInfo) {
		String key = cacheStrategyInfo.getRootKey();
		boolean b = redisStringOperation.del(key);
		return b;
	}

}
