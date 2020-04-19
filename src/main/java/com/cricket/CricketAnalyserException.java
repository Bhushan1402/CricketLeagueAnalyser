package com.cricket;

public class CricketAnalyserException extends Throwable {
    enum ExceptionType {
        CRICKET_FILE_PROBLEM,CRICKET_DATA_NOT_FOUND;
    }

    ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
