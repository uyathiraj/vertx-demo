package com.demo.vertex.redis;

import io.reactivex.Single;
import io.vertx.reactivex.redis.client.Redis;

public interface RedisService {
	
	public void pushToRedis(Redis client,String key,String value);
	
	public Single<String> read(Redis client,String key);
}
