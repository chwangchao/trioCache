package com.zh.cn.trio.cache.strategy.model.impl;


import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.zh.cn.trio.cache.strategy.CacheStrategy;
import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;
import com.zh.cn.trio.cache.strategy.model.CacheModel;

@Component
public class RemoveCacheModel implements CacheModel {

	@Override
	public String getModel() {
		return REMOVE;
	}

	@Override
	public Object exec(ProceedingJoinPoint proceedingJoinPoint, CacheStrategy cacheStrategy,
			CacheStrategyInfo cacheStrategyInfo) throws Throwable {
		boolean b = cacheStrategy.hasCache(cacheStrategyInfo);
		if (b) {
			cacheStrategy.removeCache(cacheStrategyInfo);
		}
		Object rs = proceedingJoinPoint.proceed();
		return rs;
	}

}
