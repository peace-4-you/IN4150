package com.broadcast;


public class TimeStamp{
    /* Stores the timestamps*/
    private int sync_time;

    /* Constructor */
    public TimeStamp(){
    }

    /* Get timestamp
    *  Input: none
    *  Output: timestamp at index
    * */
    public int get_timeStamp(){
        return sync_time;
    }

    /* Set timestamp
    *  Input: time  - set the timestamp to this value
    *  Output: none
    * */
    public void set_timeStamp(int time){
        sync_time = time;
    }

    /* Saves the max value of either the sender or the receiver process
    *  Input: sender_time - scalar clock of the sender
    *         receiver_time - scalar clock of the receiver
    *  Output: none
     */
    public void synchronize(int sender_time, int receiver_time){
        if (receiver_time < sender_time) {
            set_timeStamp(sender_time);
        }
        else if(receiver_time == sender_time){
            // tie-breaking with process_id
        }
    }

    /* Increments the timestamp by one
    *  Input: none
    *  Output: none
     */
    public void increment(){
        sync_time++;
    }
}
