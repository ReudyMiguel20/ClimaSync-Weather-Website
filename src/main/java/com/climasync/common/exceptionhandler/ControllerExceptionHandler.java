package com.climasync.common.exceptionhandler;

import com.climasync.user.exception.UserAlreadyExists;
import com.climasync.user.exception.UserNotFound;
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
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    // Initializing the DateTimeFormatter object to format the timestamp of the response.
    public static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        //Initializing the HttpServletRequest object to get the path of the request that caused the error.
        HttpServletRequest requestServlet = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String path = requestServlet.getRequestURI();

        //Creating the body of the response
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .timestamp(LocalDateTime.now().format(formatter))
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
                .timestamp(LocalDateTime.now().format(formatter))
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
                .timestamp(LocalDateTime.now().format(formatter))
                .status(404)
                .error("Not Found")
                .message("This country doesn't exist, please try again.")
                .path(path)
                .build();

        return ResponseEntity.status(404).body(customErrorMessage);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<CustomErrorMessage> handleUserNotFound() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String path = request.getRequestURI();

        //Creating the body of the response
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(404)
                .error("Not Found")
                .message("This user doesn't exist in our database, please try again.")
                .path(path)
                .build();

        return ResponseEntity.status(404).body(customErrorMessage);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<CustomErrorMessage> handleUserAlreadyExists() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String path = request.getRequestURI();

        //Creating the body of the response
        CustomErrorMessage customErrorMessage = CustomErrorMessage.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(409)
                .error("User already exists")
                .message("User with this email already exists.")
                .path(path)
                .build();

        return ResponseEntity.status(404).body(customErrorMessage);
    }
}
