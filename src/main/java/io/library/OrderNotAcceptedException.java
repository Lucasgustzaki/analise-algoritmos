package io.library;

public class OrderNotAcceptedException extends RuntimeException {

  public OrderNotAcceptedException(String message) {
      super(message);
  }
}
