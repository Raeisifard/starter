package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.handler.sse.SSEHandler;
import io.vertx.ext.web.handler.StaticHandler;
public class MainVerticle extends AbstractVerticle {
  public final static int PORT = 9001;

  private final static Logger LOG = LoggerFactory.getLogger(MainVerticle.class.getName());
  private final static String EB_ADDRESS = "iss-position";

  private HttpServer server;
  private final StaticHandler staticFiles = StaticHandler.create();
  private final SSEHandler sse = SSEHandler.create();
  private Long timerId;
  private HttpClient client;
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
