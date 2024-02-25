package com.hishab.io.ext.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Dice game exception advice controller.
 */
@ControllerAdvice
@Slf4j
public class DiceGameGlobalExceptionController {
    /**
     * Handle api exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleApiException(Exception e) {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
        return ResponseEntity.internalServerError().build();
    }

    /**
     * Handle method not supported response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ResponseEntity<String> handleMethodNotSupported(Exception e) {
        log.warn(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), e);
        return ResponseEntity.badRequest().body(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    /**
     * Handle content type supported response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public ResponseEntity<String> handleContentTypeSupported(Exception e) {
        log.warn(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), e);
        return ResponseEntity.badRequest().body(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
    }

    /**
     * Handle content type supported response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(javax.validation.UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleContentTypeSupported(javax.validation.UnexpectedTypeException e) {
        log.warn(HttpStatus.BAD_REQUEST.getReasonPhrase(), e);
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
