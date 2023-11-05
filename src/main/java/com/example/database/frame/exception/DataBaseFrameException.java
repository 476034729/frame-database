
package com.example.database.frame.exception;


/**
 * @author Clinton Begin
 */
public class DataBaseFrameException extends RuntimeException {

  private static final long serialVersionUID = -353418993628421873L;

  public DataBaseFrameException() {
    super();
  }

  public DataBaseFrameException(String message) {
    super(message);
  }

  public DataBaseFrameException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataBaseFrameException(Throwable cause) {
    super(cause);
  }
}
