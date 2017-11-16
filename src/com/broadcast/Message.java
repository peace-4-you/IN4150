package com.broadcast;

public class Message {
    public String message;
    public TimeStamp timestamp;
    public int sender_processId;

    /* Constructor */
    public Message(String m, TimeStamp t, int send_id){
        message = m;
        timestamp = t;
        sender_processId = send_id;
    }
}
