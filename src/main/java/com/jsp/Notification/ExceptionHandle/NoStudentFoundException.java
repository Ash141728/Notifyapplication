package com.jsp.Notification.ExceptionHandle;

public class NoStudentFoundException extends Exception {

    private String message;
    public NoStudentFoundException(String message){
        this.message=message;
    }
}
