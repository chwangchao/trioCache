package com.zh.cn.trio.cache.strategy.bean;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.springframework.util.StringUtils;

import com.zh.cn.trio.cache.bean.TrioCacheBean;

/**
 * 缓存信息 上下文
 * 
 * @author wuzl
 *
 */
public class CacheStrategyInfo {

	/**
	 * 配置
	 */
	private TrioCacheBean trioCacheBean;
	/**
	 * 返回值
	 */
	private Object resultObject;
	/**
	 * 拦截方法入参
	 */
	private Object[] targetArgs;
	/**
	 * 拦截方法
	 */
	private Method targetMethod;

	public TrioCacheBean getTrioCacheBean() {
		return trioCacheBean;
	}

	public void setTrioCacheBean(TrioCacheBean trioCacheBean) {
		this.trioCacheBean = trioCacheBean;
	}

	public Object getResultObject() {
		return resultObject;
	}

	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

	public Object[] getTargetArgs() {
		return targetArgs;
	}

	public void setTargetArgs(Object[] targetArgs) {
		this.targetArgs = targetArgs;
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(Method targetMethod) {
		this.targetMethod = targetMethod;
	}

	public int getCacheTime() {
		return trioCacheBean.getCacheTime();
	}

	
	public Type getGenericReturnType(){
		return getTargetMethod().getGenericReturnType();
	}

	public String getRootKey() {
		if (StringUtils.isEmpty(trioCacheBean.getKey()) || StringUtils.isEmpty(trioCacheBean.getKeyFormat())) {
			return targetMethod.getName();
		}
		//TODO  根据key 和 keyFormat 组装存储的key
		return null;
	}

	public CacheStrategyInfo() {
	}

	public CacheStrategyInfo(TrioCacheBean trioCacheBean, Object resultObject, Object[] targetArgs,
			Method targetMethod) {
		super();
		this.trioCacheBean = trioCacheBean;
		this.resultObject = resultObject;
		this.targetArgs = targetArgs;
		this.targetMethod = targetMethod;
	}

}
