package com.zh.cn.trio.cache.redis.serialization;

import java.lang.reflect.Type;

public interface Serialization {

	String serialization(Object object);

	Object forSerialization(String val, Type genericReturnType);

}
