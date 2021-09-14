package com.mycompany.transactionphaseeventlistenertest.entity;


public class MyEvent {
    String message;
    boolean exception;
    
    public MyEvent(String message, boolean exception) {
        this.message = message;
        this.exception = exception;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public boolean isException() {
        return this.exception;
    }
}
