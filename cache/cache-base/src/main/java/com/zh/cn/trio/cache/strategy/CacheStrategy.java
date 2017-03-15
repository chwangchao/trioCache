package com.zh.cn.trio.cache.strategy;

import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;

/**
 * 缓存策略接口
 * 
 * @author wuzl
 *
 */
public interface CacheStrategy {
	
	String getName();

	/**
	 * 是否含有缓存信息
	 * @param cacheStrategyInfo
	 * @return
	 */
	boolean hasCache(CacheStrategyInfo cacheStrategyInfo);

	/**
	 * 读取缓存信息
	 * @param cacheStrategyInfo
	 * @return
	 */
	Object getCache(CacheStrategyInfo cacheStrategyInfo);

	/**
	 *	写入缓存信息
	 * @param cacheStrategyInfo
	 * @return
	 */
	boolean setCache(CacheStrategyInfo cacheStrategyInfo);

	/**
	 * 删除缓存信息
	 * @param cacheStrategyInfo
	 */
	boolean removeCache(CacheStrategyInfo cacheStrategyInfo);
}
