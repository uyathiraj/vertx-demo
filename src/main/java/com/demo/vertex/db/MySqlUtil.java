package com.demo.vertex.db;

import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.reactivex.ext.sql.SQLClient;

public class MySqlUtil {

	public static Single<ResultSet> findById(SQLClient client, String sql, JsonArray params) {

		return client.rxGetConnection().flatMap(con -> con.rxQueryWithParams(sql, params).doAfterTerminate(con::close));
	}
}
