package com.zh.cn.trio.cache.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.zh.cn.trio.cache.annotations.TrioCache;
import com.zh.cn.trio.cache.bean.TrioCacheBean;
import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;

@Aspect
public class TrioCacheAnnotationAspect extends AbstractTrioCacheAspect {

	@Around("@annotation(com.zh.cn.trio.cache.annotations.TrioCache)")
	@Override
	public Object proxy(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return super.proxy(proceedingJoinPoint);
	}
	
	@Override
	public CacheStrategyInfo craeteInfo(ProceedingJoinPoint proceedingJoinPoint) {
		Method method = getTargetMethod(proceedingJoinPoint);
		TrioCache trioCache = method.getAnnotation(TrioCache.class);
		
		TrioCacheBean trioCacheBean = new TrioCacheBean(trioCache.cacheStrategy(), trioCache.cacheModel(),
				trioCache.cacheTime(), trioCache.key(), trioCache.keyFormat());
	
		CacheStrategyInfo cacheStrategyInfo = new CacheStrategyInfo(trioCacheBean, null, proceedingJoinPoint.getArgs(),
				method);
		return cacheStrategyInfo;
	}

}
