package org.tamasenco.dto;

import lombok.Getter;

@Getter
public class HelloWorldDto {

  private final String hello;

  private HelloWorldDto() {
    this.hello = "world";
  }

  public static HelloWorldDto helloWorldDtoProvider() {
    return new HelloWorldDto();
  }
}