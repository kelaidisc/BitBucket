package org.tamasenco.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;

public class HttpVerticle extends AbstractVerticle {

  private HttpServer server;
  private static final int httpPort = Integer.parseInt
      (System.getenv()
          .getOrDefault("HTTP_PORT", "8080"));


  @Override
  public void start(Promise<Void> startPromise) {


    RouterBuilder.create(this.vertx, "openapi.yaml")
        .onFailure(Throwable::printStackTrace) // In case the contract loading failed print the stacktrace
        .onSuccess(routerBuilder -> {
          // Before router creation you can enable/disable various router factory behaviours


          routerBuilder.operation("helloWorld")
              .handler(this::getHelloWorld);
          routerBuilder.operation("createMessage")
              .handler(this::createMessage);
          routerBuilder.operation("getMessages")
              .handler(this::getMessages);

          Router router = routerBuilder.createRouter();

          server = vertx
              .createHttpServer()
              .requestHandler(router);
          server.listen(httpPort)
              .onSuccess(server -> System.out.println("Server started on port " + server.actualPort()))
              .onFailure(Throwable::printStackTrace);
        });
  }

  private void getHelloWorld(RoutingContext context) {

  }

  private void createMessage(RoutingContext context) {

  }

  private void getMessages(RoutingContext context) {

  }
}
