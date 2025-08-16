package io.library;

public class OrderNotAcceptedException extends RuntimeException {

  public OrderNotAcceptedException(final String message) {
      super(message);
  }
}
