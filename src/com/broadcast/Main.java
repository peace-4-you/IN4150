package com.broadcast;

import java.util.LinkedList;
import java.util.Iterator;

public class Main {
    public static int process_count = 5;

    public static void main(String[] args){

        /* Test program */
        LinkedList<Process> list = new LinkedList<Process>();

        System.out.println("checkpoint 1"); //Debug
        for(int i=0; i<process_count; i++){
            System.out.println("loop count " + i); //Debug
            list.add(new Process(i, process_count));
        }
        System.out.println("checkpoint 2"); //Debug
        while(true){
            Iterator it = list.iterator();
            while(it.hasNext()){
                Process ob = (Process)it.next();
                ob.start();
            }
        }
    }
}
