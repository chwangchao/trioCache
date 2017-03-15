package com.zh.cn.trio.cache.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zh.cn.trio.cache.strategy.model.CacheModel;

/**
 * 缓存注解
 * 
 * @author wuzl
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface TrioCache {
	/**
	 * 缓存策略
	 */
	String cacheStrategy() default "";

	/**
	 * 缓存模式
	 */
	String cacheModel() default CacheModel.READ_WRITE;

	/**
	 * 緩存鍵
	 */
	String key() default "";

	/**
	 * 缓存键 填充模式
	 */
	String keyFormat() default "";

	/**
	 * 缓存时间 根据不同的策略可以设置自己的默认时间
	 */
	int cacheTime() default 0;
}
