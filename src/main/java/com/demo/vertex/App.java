package com.demo.vertex;

import com.demo.vertex.db.MainVerticle;

import io.vertx.core.Vertx;

/**
 * Hello world!
 *Run below class to deploy app
 */

public class App {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();

		vertx.deployVerticle(new MainVerticle());
	}
}
