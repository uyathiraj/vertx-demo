package com.demo.vertex.redis;

import io.vertx.core.Future;
import io.vertx.core.net.SocketAddress;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.redis.client.Redis;
import io.vertx.redis.client.RedisOptions;

public class RedisVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		RedisUtil.client = Redis.createClient(vertx,
				new RedisOptions().setEndpoint(SocketAddress.inetSocketAddress(6379, "localhost")));
	}
	
}
