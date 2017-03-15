package com.zh.cn.trio.cache.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.zh.cn.trio.cache.strategy.CacheStrategy;
import com.zh.cn.trio.cache.strategy.bean.CacheStrategyInfo;
import com.zh.cn.trio.cache.strategy.model.CacheModel;
import com.zh.cn.trio.utils.spring.SpringUtils;

public abstract class AbstractTrioCacheAspect {

	private Map<String, CacheStrategy> strategyMap;

	private Map<String, CacheModel> cacheModelMap;

	private Map<String, CacheStrategy> getStrategyMap() {
		if (strategyMap == null) {
			synchronized (this) {
				String[] beans = SpringUtils.getBeanNamesForType(CacheStrategy.class);
				strategyMap = new HashMap<>();
				for (String beanName : beans) {
					CacheStrategy temp = SpringUtils.getBean(beanName, CacheStrategy.class);
					strategyMap.put(temp.getName(), temp);
				}
			}
		}
		return strategyMap;
	}

	private Map<String, CacheModel> getCacheModelMap() {
		if (cacheModelMap == null) {
			synchronized (this) {
				String[] beans = SpringUtils.getBeanNamesForType(CacheModel.class);
				cacheModelMap = new HashMap<>();
				for (String beanName : beans) {
					CacheModel temp = SpringUtils.getBean(beanName, CacheModel.class);
					cacheModelMap.put(temp.getModel(), temp);
				}
			}
		}
		return cacheModelMap;
	}

	public void setStrategyMap(Map<String, CacheStrategy> strategyMap) {
		this.strategyMap = strategyMap;
	}

	public void setCacheModelMap(Map<String, CacheModel> cacheModelMap) {
		this.cacheModelMap = cacheModelMap;
	}

	/**
	 * 提取目标方法
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 */
	public Method getTargetMethod(ProceedingJoinPoint proceedingJoinPoint) {
		MethodSignature joinPointObject = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = joinPointObject.getMethod();
		return method;
	}

	public Object proxy(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		CacheStrategyInfo cacheStrategyInfo = craeteInfo(proceedingJoinPoint);
		return proxyMethod(proceedingJoinPoint, cacheStrategyInfo);
	}

	public abstract CacheStrategyInfo craeteInfo(ProceedingJoinPoint proceedingJoinPoint);

	public Object proxyMethod(ProceedingJoinPoint proceedingJoinPoint, CacheStrategyInfo cacheStrategyInfo)
			throws Throwable {
		// 读缓存模式
		CacheModel cacheModel = getCacheModelMap().get(cacheStrategyInfo.getTrioCacheBean().getCacheModel());
		if (cacheModel == null) {
			cacheModel = getCacheModelMap().get(CacheModel.READ_WRITE);
		}

		// 读缓存策略
		CacheStrategy cacheStrategy = getStrategyMap().get(cacheStrategyInfo.getTrioCacheBean().getCacheStrategy());
		// 没有配置缓存策略 并且 只存在一个缓存策略
		if (cacheStrategy == null || getStrategyMap().values().size() == 1) {
			// 切换默认
			for (CacheStrategy temp : getStrategyMap().values()) {
				cacheStrategy = temp;
			}
		}
		// 代理执行
		Object rs = cacheModel.exec(proceedingJoinPoint, cacheStrategy, cacheStrategyInfo);
		return rs;
	}

}
