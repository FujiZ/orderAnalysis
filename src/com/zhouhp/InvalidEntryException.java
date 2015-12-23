package com.zhouhp;

/**
 * Created by fuji on 15-12-21.
 */
public class InvalidEntryException extends Exception{
    InvalidEntryException(){
        super();
    }
    InvalidEntryException(String string){
        super(string);
    }
}
