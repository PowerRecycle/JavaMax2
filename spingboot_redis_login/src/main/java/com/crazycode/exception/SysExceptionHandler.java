package com.crazycode.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//当控制器方法出错的时候,抛出异常的时候,就会交给SysExceptionHandler来调用带有@ExceptionHandler的方法来进行异常处理
@ControllerAdvice
public class SysExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handlerExcepotion(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        SysException se = null;
        if (ex instanceof SysException) {
            se = (SysException) ex;
        } else {
            String message = ex.getMessage();
            se = new SysException(message);
        }
        modelAndView.addObject("errorInfo", se.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
