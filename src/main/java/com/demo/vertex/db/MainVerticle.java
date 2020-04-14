package com.demo.vertex.db;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

/**
 * Main vertical is an event processing unit 
 * 
 * WE can have multiple verticles as per our vish 
 * @author yathiraj
 *
 */
public class MainVerticle extends AbstractVerticle {

	private MessageModelService messageModelService;

	/**
	 * Whenever you start verticle this will be triggered first
	 * 
	 */
	@Override
	public void start() {
		System.out.println("starting http server");
		/**
		 * Setting up my sql connection
		 */
		MySqlConfig.initialize(vertx);
		startHttpServer();
	}

	/*
	 * Sampl handler
	 */
	private void sayHiHandler(RoutingContext context) {
		context.response().end("Say Hi");
	}

	/*
	 * Http server and router configurations can also have it seperated
	 * Its a jetty server unlike tomcat in spring (embeded)
	 */
	private void startHttpServer() {
		
		/**
		 * Router is the place we define API end points
		 * 
		 */
	
		Router router = Router.router(vertx);
		router.get("/say").handler(this::sayHiHandler);
		router.get("/sayHelloWorld").handler(this::sayHelloWorld);
		router.post("/create").handler(this::create);
		router.get("/findById/:id").handler(this::findById);
		
		/**
		 * This creates Jetty server
		 */
		HttpServer server = vertx.createHttpServer();
		
		/**
		 * Server starts at port 5000
		 */
		/**
		 * Wholw vertex RUNS on JAVA 8 SO you need to be good in that as well
		 * basically the fuctional interfaces
		 */
		server.requestHandler(router).listen(5000, ar -> {
			/*
			 * ar is succeded when server is up and running
			 */
			if (ar.succeeded()) {
				messageModelService = new MessageServiceImpl();
			} else {
				/**
				 * Server failed to start
				 */
			}

		});

	}
	
	
	private void sayHelloWorld(RoutingContext context) {
		context.response().end("Hello world");
	}
	
	/*
	 * Rest API handler to handle request
	 */
	private MessageModel create(RoutingContext routingContext) {

		routingContext.request().bodyHandler(body -> {
			MessageModel model;
			try {
				model = new ObjectMapper().readValue(body.getBytes(), MessageModel.class);
				messageModelService.create(model).subscribe(item -> {
					System.out.println(item);
					// sonObject jsonObject = new JsonObject(new
					// ObjectMapper().writeValueAsString(item));
					response(HttpResponseStatus.OK, routingContext, item.toJson());
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		System.out.println("Hellohkhkh");
		return null;
	}

	private void findById(RoutingContext routingContext) {
		String id = routingContext.pathParam("id");
		System.out.println("Id " + id);
		messageModelService.findById(id).subscribe(item -> {
			if (!item.getRows().isEmpty()) {
				// String res = new ObjectMapper().writeValueAsString(item.getRows());
				response(HttpResponseStatus.OK, routingContext, item.getRows());
			} else {
				response(HttpResponseStatus.OK, routingContext, "Not found");
			}
		}, err -> response(HttpResponseStatus.BAD_REQUEST, routingContext, err.getMessage()));
	}

	/*
	 * Helper fuction to write result
	 */

	public static void response(HttpResponseStatus responseStatus, RoutingContext routingContext, Object obj) {
		routingContext.response().setStatusCode(responseStatus.code())
				.putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
				.end(new JsonObject().put("status", responseStatus.code()).put("message", responseStatus.reasonPhrase())
						.put("response", obj).encodePrettily());
	}

}
