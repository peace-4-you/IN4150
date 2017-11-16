package com.broadcast;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObject extends Remote {
    void receive(Message m) throws RemoteException;
    void acknowledge(TimeStamp t) throws RemoteException;
}
