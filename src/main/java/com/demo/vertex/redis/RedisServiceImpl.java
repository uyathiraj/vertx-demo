package com.demo.vertex.redis;

import io.reactivex.Single;
import io.vertx.reactivex.redis.client.Redis;

public class RedisServiceImpl implements RedisService{

	@Override
	public void pushToRedis(Redis client, String key, String value) {
		client.connect(con->{
			if(con.succeeded()) {
				
			}
		});
		
	}

	@Override
	public Single<String> read(Redis client, String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
