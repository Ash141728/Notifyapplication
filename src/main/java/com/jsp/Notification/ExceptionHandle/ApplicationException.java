package com.jsp.Notification.ExceptionHandle;

import com.jsp.Notification.Helper.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationException extends Exception {


    @ExceptionHandler(value = NoStudentFoundException.class)
    public ResponseStructure<NoStudentFoundException> exceptionFinder(NoStudentFoundException noStudentFoundException) {
        ResponseStructure<NoStudentFoundException> responseStructure = new ResponseStructure<>();
        responseStructure.setMessage(noStudentFoundException.getMessage());
        responseStructure.setData(noStudentFoundException);
        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
        return responseStructure;
    }

}
