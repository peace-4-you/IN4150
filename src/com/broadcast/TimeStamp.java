package com.broadcast;


public class TimeStamp{
    /* Stores the timestamps*/
    private int[] sync_time;

    /* Constructor
    * Input: entryCount - total number of processes in the system
    */
    public TimeStamp(int entryCount){
        sync_time = new int[entryCount];
    }

    /* Get timestamp
    *  Input: index - get timestamp at this index
    *  Output: timestamp at index
    * */
    public int get_timeStamp(int index){
        return sync_time[index];
    }

    /* Set timestamp
    *  Input: index - set timestamp at this index
    *         time  - set the timestamp to this value
    *  Output: void
    * */
    public void set_timeStamp(int index, int time){
        sync_time[index] = time;
    }

    /* Saves the max value of either the sender or the receiver process
    *  Input: index - process ID of the receiver
    *         sender_time - TimeStamp object of the sender
    *  Output: void
     */
    public void synchronize(int index, TimeStamp sender_time){
        for(int i = 0; i < sync_time.length; i++){
            int send_syncTime = sender_time.get_timeStamp(i);
            int recv_syncTime = sync_time[i];

            if(i == index){
                recv_syncTime++;
            }

            if (recv_syncTime < send_syncTime) {
                set_timeStamp(i, send_syncTime);
            }
        }
    }
}
