package com.broadcast;

import com.broadcast.*;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Process implements RemoteObject {

    // ID of this process (0 is the first ID)
    int id;
    // Asynchronously stores the timestamp of this process
    TimeStamp time;
    // Message queue to store all messages
    LinkedList<Message> message_queue;
    // Total amount of processes in the system
    int processCount;
    // Stub of the RMI server
    RemoteObject stubServer;

    /* Constructor
    * Input: process_id - the ID of this process
    *        total_process - total amount of processes in the system
    */
    public Process(int process_id, int total_process){
       id = process_id;
       time = new TimeStamp();
       processCount = total_process;

       //Server RMI
        try{
            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            stubServer = (RemoteObject) UnicastRemoteObject.exportObject(this, 0);

            // Binding the remote object (stub) in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Server" + id, stubServer);
        } catch (Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    /* Starts the process of sending, receiving and acknowledging
    *  Input: none
    *  Output: none
     */
    public void start(){
        Message message = new Message("test", time);
        send(message);
    }

    /* Broadcasts a message to the other processes
    *  Invokes the receive() from other processes
    *  Input: m - the message to be broadcast
    *  Output: none
     */
    public void send(Message m){
        time.increment();

        //Call the receive method from other processes
        for(int i=0; i<processCount; i++) {
            try {
                // Getting the registry
                Registry registry = LocateRegistry.getRegistry(null);

                // Looking up the registry for the remote object
                RemoteObject stubClient = (RemoteObject) registry.lookup("Server" + i);

                // Calling the remote method using the obtained object
                stubClient.receive(m);
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }
}
    /* The RMI method called when other processes has send this process a message.
    *  Invokes acknowledge() of other processes.
    *  Input: m - message to receive
    *  Output: none
     */
    public void receive(Message m){
        //receiving an message takes one clock cycle
        time.increment();
        //message_queue.add(m);
        time.synchronize(m.timestamp.get_timeStamp(), time.get_timeStamp());

        // Debug: Let's see if we received something
        System.out.println(m.timestamp);

        // sending acknowledge takes one clock cycle
        time.increment();

        // Sent acknowledgement to all processes
        for(int i=0; i<processCount; i++) {
            try {
                // Getting the registry
                Registry registry = LocateRegistry.getRegistry(null);

                // Looking up the registry for the remote object
                RemoteObject stubClient = (RemoteObject) registry.lookup("Server" + i);

                // Calling the remote method using the obtained object
                stubClient.acknowledge(time);
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    /* The RMI method called when a message is acknowledged.
    *  Input: none
    *  Output: none
     */
    public void acknowledge(TimeStamp t){
        // receiving the acknowledgement takes one clock cycle
        time.increment();
        time.synchronize(t.get_timeStamp(), time.get_timeStamp());

        // TODO: allow send() to run again
    }
}
