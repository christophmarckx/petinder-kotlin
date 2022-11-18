package com.switchfully.pettinder.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

// This is the same handling for the Exception we have in our PetController, only this time it would span every controller in this codebase instead of just the PetController
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  protected void illegalArgumentException(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
    response.sendError(BAD_REQUEST.value(), ex.getMessage());
  }
}
