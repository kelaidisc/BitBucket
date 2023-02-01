package org.tamasenco;

import io.vertx.core.Vertx;
import org.tamasenco.verticle.HttpVerticle;

public class Main {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HttpVerticle());
  }
}