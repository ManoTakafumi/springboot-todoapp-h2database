package com.example.todo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //全体例外処理
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {

        model.addAttribute("message", "エラーが発生しました");

        return "error";
    }
}
