package com.climasync.common.exceptionhandler;

import com.climasync.weather.exception.CountryNotFoundException;
import com.climasync.weather.exception.LocationNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                              HttpStatusCode status, WebRequest request) {
        //Initializing the HttpServletRequest object to get the path of the request that caused the error.
        HttpServletRequest requestServlet = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String path = requestServlet.getRequestURI();

        //Creating the body of the response
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.toString())
                .message("The request contains invalid data, probably left some field empty or null.")
                .path(path)
                .build();

                return ResponseEntity.badRequest().body(customErrorMessage);

    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleLocationNotFound() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String path = request.getRequestURI();

        //Creating the body of the response
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .error("Not Found")
                .message("The requested location doesn't exist, please try again.")
                .path(path)
                .build();

        return ResponseEntity.status(404).body(customErrorMessage);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleCountryNotFound() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String path = request.getRequestURI();

        //Creating the body of the response
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .error("Not Found")
                .message("This country doesn't exist, please try again.")
                .path(path)
                .build();

        return ResponseEntity.status(404).body(customErrorMessage);
    }
}
