package com.csuf.exceptions;

public class BadCountException extends DbException {

    public BadCountException(String operation, int expected, int actual) {
        this("Wrong number of records " + operation + ". Expected " + expected + ", was " + actual + ".");
    }

    public BadCountException(String msg) {
        super(msg);
    }
}