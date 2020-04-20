package com.cricket;

public class CricketAnalyserException extends Exception{
    public static ExceptionType type;
    public static enum ExceptionType{
        FILE_ERROR,NO_IPL_DATA,FILE_NOT_FOUND,IPL_CSVFILE_PROBLEM,SOME_FILE_ISSUE
    }

    public CricketAnalyserException(String message,ExceptionType type) {
        super(message);
        this.type=type;
    }

    public CricketAnalyserException(ExceptionType type,String message) {
        super(message);
        this.type=type;
    }
}
