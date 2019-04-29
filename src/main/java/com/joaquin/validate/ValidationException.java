package com.joaquin.validate;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException {

  private String errMsg;

  public ValidationException() { }

  public ValidationException(String errMsg) {
    this.errMsg = errMsg;
  }
}
