package com.zh.cn.trio.cache.redis.serialization.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.zh.cn.trio.cache.redis.serialization.Serialization;

@Repository
public class SerializationImpl implements Serialization {

	@Override
	public String serialization(Object object) {
		return JSON.toJSONString(object);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object forSerialization(String val, Type genericReturnType) {
		Type type = genericReturnType;
		if (type instanceof ParameterizedType) {
			Type[] typesto = ((ParameterizedType) type).getActualTypeArguments();
			Class tempClass=(Class) typesto[0];
			return JSON.parseArray(val, tempClass);
		}else{
			return JSON.parseObject(val, genericReturnType);
		}
	}

}
