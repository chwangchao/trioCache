package com.zh.cn.trio.cache.strategy.model.impl;


import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.zh.cn.trio.cache.strategy.CacheStrategy;
import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;
import com.zh.cn.trio.cache.strategy.model.CacheModel;

@Component
public class ReadWriteCacheModel implements CacheModel{

	@Override
	public String getModel() {
		return READ_WRITE;
	}

	@Override
	public Object exec(ProceedingJoinPoint proceedingJoinPoint, CacheStrategy cacheStrategy,
			CacheStrategyInfo cacheStrategyInfo) throws Throwable {
		Object rs = null;
		boolean b = cacheStrategy.hasCache(cacheStrategyInfo);
		if (b) {
			rs = cacheStrategy.getCache(cacheStrategyInfo);
		} else {
			rs = proceedingJoinPoint.proceed();
			cacheStrategyInfo.setResultObject(rs);
			cacheStrategy.setCache(cacheStrategyInfo);
		}
		return rs;
	}

}
