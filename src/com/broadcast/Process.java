package com.broadcast;

import com.broadcast.*;

import java.util.LinkedList;

public class Process {

    // ID of this process (0 is the first ID)
    int id;
    // Asynchronously stores the timestamp of this process
    TimeStamp time;
    // Message queue to store all messages
    LinkedList<Message> message_queue;
    // Total amount of processes in the system
    int processCount;

    /* Constructor
    * Input: process_id - the ID of this process
    *        total_process - total amount of processes in the system
    */
    public Process(int process_id, int total_process){
       id = process_id;
       time = new TimeStamp();
       processCount = total_process;
    }

    public void send(String m){
        time.increment();
        Message message = new Message(m, time);
    }

    public void receive(){
        time.increment();
    }

    public void acknowledge(){
        time.increment();
    }
}
