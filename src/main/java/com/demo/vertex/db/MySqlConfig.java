package com.demo.vertex.db;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.asyncsql.MySQLClient;
import io.vertx.reactivex.ext.sql.SQLClient;

public class MySqlConfig {

	final static JsonObject mysqlConfig = new JsonObject().put("host", "localhost").put("port", 3306).put("username", "root")
			.put("password", "root").put("database", "demo");

	public static SQLClient sqlClient;

	public static void initialize(Vertx vertx) {
		sqlClient = MySQLClient.createNonShared(vertx, mysqlConfig);
	}

}
