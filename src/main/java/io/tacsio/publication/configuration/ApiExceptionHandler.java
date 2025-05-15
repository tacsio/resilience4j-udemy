package io.tacsio.publication.configuration;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handlerException(Exception exception, HttpServletRequest request) {
        Map<String, Object> properties = Map.of("timestamp", System.currentTimeMillis());

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperties(properties);

        log.error(request.getRequestURI(), exception);

        return problemDetail;
    }

    @ExceptionHandler(FeignException.class)
    public ProblemDetail handlerException(FeignException exception) {
        Map<String, Object> properties = Map.of("timestamp", System.currentTimeMillis());

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        problemDetail.setProperties(properties);

        log.error(exception.getMessage());

        return problemDetail;
    }
}
