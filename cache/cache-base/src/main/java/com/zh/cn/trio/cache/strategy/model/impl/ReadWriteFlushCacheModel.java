package com.zh.cn.trio.cache.strategy.model.impl;


import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.zh.cn.trio.cache.strategy.CacheStrategy;
import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;
import com.zh.cn.trio.cache.strategy.model.CacheModel;

@Component
public class ReadWriteFlushCacheModel implements CacheModel {

	@Override
	public String getModel() {
		return READ_WRITE_FLUSH;
	}

	@Override
	public Object exec(ProceedingJoinPoint proceedingJoinPoint, CacheStrategy cacheStrategy,
			CacheStrategyInfo cacheStrategyInfo) throws Throwable {
		Object rs = proceedingJoinPoint.proceed();
		cacheStrategyInfo.setResultObject(rs);
		cacheStrategy.setCache(cacheStrategyInfo);
		return rs;
	}

}
