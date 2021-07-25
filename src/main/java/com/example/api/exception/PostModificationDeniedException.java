package com.example.api.exception;

import com.example.api.error.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PostModificationDeniedException extends AppException {
  @Override
  public String getCode() {
    return ErrorCodes.POST_MODIFICATION_DENIED;
  }

  public PostModificationDeniedException() {
    super();
  }

  public PostModificationDeniedException(String message) {
    super(message);
  }

  public PostModificationDeniedException(String message, Throwable cause) {
    super(message, cause);
  }

  public PostModificationDeniedException(Throwable cause) {
    super(cause);
  }

  public PostModificationDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
