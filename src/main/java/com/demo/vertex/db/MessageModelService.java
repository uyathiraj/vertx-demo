package com.demo.vertex.db;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.UpdateResult;

public interface MessageModelService {

	public Single<UpdateResult> create(MessageModel model,Observable<MessageModel> observable);
	public  Single<UpdateResult> create(MessageModel model);
	public Single<ResultSet> findById(String id);
}
