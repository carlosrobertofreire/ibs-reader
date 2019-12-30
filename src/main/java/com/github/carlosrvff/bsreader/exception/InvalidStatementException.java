package com.github.carlosrvff.bsreader.exception;

public class InvalidStatementException extends Exception {

  public InvalidStatementException(String reason, String statement) {
    super(reason + ": " + statement);
  }

  public InvalidStatementException(String reason, String statement, Throwable cause) {
    super(reason + ": " + statement, cause);
  }
}
