package com.ll.tenmindaily.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
<<<<<<< HEAD
public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataNotFoundException(String message) {
=======
public class DataNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public DataNotFoundException(String message){
>>>>>>> 3630690 (Nagiltae (#9))
        super(message);
    }
}
