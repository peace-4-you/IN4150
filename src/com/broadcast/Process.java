package com.broadcast;

import com.broadcast.*;

import java.util.LinkedList;

public class Process {

    // ID of this process (0 is the first ID)
    int id;
    // Asynchronously stores the timestamps of all processes
    TimeStamp time;
    // Message queue to store all messages
    LinkedList<Message> message_queue;

    /* Constructor
    * Input: process_id - the ID of this process
    *        total_process - total amount of processes in the system
    */
    public Process(int process_id, int total_process){
       id = process_id;
       time = new TimeStamp(total_process);
    }

    public void send(String m, int id_rec){
        time.set_timeStamp(id, time.get_timeStamp(id) + 1);
        Message message = new Message(m, time);
        int delay = Math.ceil(Math.random() * 100);
        TimeUnit.SECONDS.sleep(delay);
    }

    public void receive(){
    
    }

    public void acknowledge(){

    }
}
