package com.example.api.exception;

import com.example.api.error.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends AppException {
  @Override
  public String getCode() {
    return ErrorCodes.POST_NOT_FOUND;
  }

  public PostNotFoundException() {
    super();
  }

  public PostNotFoundException(String message) {
    super(message);
  }

  public PostNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public PostNotFoundException(Throwable cause) {
    super(cause);
  }

  public PostNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
