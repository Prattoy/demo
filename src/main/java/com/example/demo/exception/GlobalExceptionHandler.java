package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, Model model) {
        // Log the exception
        System.err.println("An exception occurred: " + e.getMessage());

        // Add error message to the model
        model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());

        // Return the error view
        return "error";
    }
}
