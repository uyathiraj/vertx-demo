package com.demo.vertex.db;

import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.UpdateResult;

public class MessageServiceImpl implements MessageModelService {

	@Override
	public Single<UpdateResult> create(MessageModel model, Observable<MessageModel> observable) {
		return create(model);
	}

	@Override
	public Single<UpdateResult> create(MessageModel model) {
		MessageModel saved = new MessageModel();
		saved.setId(UUID.randomUUID().toString());
		saved.setMessage(model.getMessage());
		System.out.println("Inserting data");
		String sql = "insert into message(id,message)  values(?,?)";
		JsonArray params = new JsonArray().add(saved.getId()).add(saved.getMessage());

		return MySqlConfig.sqlClient.rxGetConnection()
				.flatMap(sqlConnection -> sqlConnection.rxUpdateWithParams(sql, params)
						.doAfterSuccess(res -> System.out.println("updated " + res))
						.doAfterTerminate(sqlConnection::close)
						.doOnError(error -> System.out.println("Getting error while running the query")));

	}

	@Override
	public Single<ResultSet> findById(String id) {

		String sql = "select * from message where id = ?";
		return MySqlUtil.findById(MySqlConfig.sqlClient, sql, new JsonArray().add(id));

	}

}
