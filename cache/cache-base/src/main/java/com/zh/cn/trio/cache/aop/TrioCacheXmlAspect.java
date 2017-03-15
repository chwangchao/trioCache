package com.zh.cn.trio.cache.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import com.zh.cn.trio.cache.bean.TrioCacheBean;
import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;

public class TrioCacheXmlAspect extends AbstractTrioCacheAspect {

	private TrioCacheBean trioCacheBean;

	public TrioCacheBean getTrioCacheBean() {
		return trioCacheBean;
	}

	public void setTrioCacheBean(TrioCacheBean trioCacheBean) {
		this.trioCacheBean = trioCacheBean;
	}

	@Override
	public CacheStrategyInfo craeteInfo(ProceedingJoinPoint proceedingJoinPoint) {
		Method method = getTargetMethod(proceedingJoinPoint);
		CacheStrategyInfo cacheStrategyInfo = new CacheStrategyInfo(trioCacheBean, null, proceedingJoinPoint.getArgs(),
				method);
		return cacheStrategyInfo;
	}
}
