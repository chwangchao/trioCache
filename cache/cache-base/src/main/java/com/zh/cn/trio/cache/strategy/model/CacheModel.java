package com.zh.cn.trio.cache.strategy.model;


import org.aspectj.lang.ProceedingJoinPoint;

import com.zh.cn.trio.cache.strategy.CacheStrategy;
import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;

/**
 * 缓存读写模式的接口
 * 
 * @author wuzl
 *
 */
public interface CacheModel  {

	/**
	 * 只读 如果有缓存则读缓存 没有缓存则执行目标方法 但是不缓存本次结果
	 */
	public static final String READ_ONLY = "READ_ONLY";

	/**
	 * 读写 如果有缓存则读缓存 没有缓存则执行目标方法 然后缓存结果（推荐）
	 */
	public static final String READ_WRITE = "READ_WRITE";

	/**
	 * 强制读写 不管有没有缓存 都执行目标方法 然后刷新缓存结果
	 */
	public static final String READ_WRITE_FLUSH = "READ_WRITE_FLUSH";

	/**
	 * 删除 如果有缓存则删除缓存再执行
	 */
	public static final String REMOVE = "REMOVE";

	/**
	 * 根据模板执行缓存策略
	 * 
	 * @param proceedingJoinPoint
	 * @param cacheStrategy
	 * @param cacheStrategyInfo
	 * @return
	 * @throws Throwable
	 */
	Object exec(ProceedingJoinPoint proceedingJoinPoint, CacheStrategy cacheStrategy,
			CacheStrategyInfo cacheStrategyInfo) throws Throwable;

	String getModel();
}
