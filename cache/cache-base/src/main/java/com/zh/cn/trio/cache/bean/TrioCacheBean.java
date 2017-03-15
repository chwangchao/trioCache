package com.zh.cn.trio.cache.bean;

/**
 * 注解或者xml配置信息
 * 
 * @author wuzl
 *
 */
public class TrioCacheBean {

	/**
	 * 缓存策略
	 */
	private String cacheStrategy;
	/**
	 * 缓存模式
	 */
	private String cacheModel;
	
	/**
	 * 緩存鍵
	 */
	private String key;
	
	/**
	 * 缓存键 填充模式
	 */
	private String keyFormat;

	/**
	 * 缓存时间 根据不同的策略可以设置自己的默认时间
	 */
	private int cacheTime;

	

	

	public String getCacheStrategy() {
		return cacheStrategy;
	}

	public void setCacheStrategy(String cacheStrategy) {
		this.cacheStrategy = cacheStrategy;
	}

	public String getCacheModel() {
		return cacheModel;
	}

	public void setCacheModel(String cacheModel) {
		this.cacheModel = cacheModel;
	}

	public int getCacheTime() {
		return cacheTime;
	}

	public void setCacheTime(int cacheTime) {
		this.cacheTime = cacheTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKeyFormat() {
		return keyFormat;
	}

	public void setKeyFormat(String keyFormat) {
		this.keyFormat = keyFormat;
	}

	public TrioCacheBean() {
	}

	public TrioCacheBean(String cacheStrategy, String cacheModel, int cacheTime, String key, String keyFormat) {
		this.cacheStrategy = cacheStrategy;
		this.cacheModel = cacheModel;
		this.cacheTime = cacheTime;
		this.key = key;
		this.keyFormat = keyFormat;
	}

}
