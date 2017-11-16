package com.broadcast;

public class Message {
    public String message;
    public TimeStamp timestamp;

    /* Constructor */
    public Message(String m, TimeStamp t){
        message = m;
        timestamp = t;
    }
}
