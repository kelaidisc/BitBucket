package org.tamasenco.verticle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import java.util.List;
import org.tamasenco.dto.HelloWorldDto;
import org.tamasenco.dto.MessageDto;
import org.tamasenco.repository.MessageRepository;

public class HttpVerticle extends AbstractVerticle {

  private final ObjectMapper mapper = new ObjectMapper();

  private final MessageRepository messageRepository = new MessageRepository();

  private HttpServer server;
  private static final int httpPort = Integer.parseInt
      (System.getenv()
          .getOrDefault("HTTP_PORT", "8080"));


  @Override
  public void start(Promise<Void> startPromise) {


    RouterBuilder.create(this.vertx, "openapi.yaml")
        .onFailure(Throwable::printStackTrace)
        .onSuccess(routerBuilder -> {

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
    HelloWorldDto helloWorldDto = HelloWorldDto.helloWorldDtoProvider();
    String json;

    try {
      json = mapper.writeValueAsString(helloWorldDto);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    context.json(json);
  }

  private void createMessage(RoutingContext context) {

    MessageDto messageDto = context.getBodyAsJson().mapTo(MessageDto.class);
    messageRepository.createMessage("16-char long", messageDto);

    context.response()
        .setStatusCode(201)
        .end();
  }

  private void getMessages(RoutingContext context) {

    List<String> messages = messageRepository.getMessages("16-char long");

    context.json(messages);
  }
}
