package alarms;
import clocks.*;

import java.util.concurrent.atomic.AtomicBoolean;


public class AlarmNote implements Alarm, Comparable<AlarmNote>{
    int hour;
    int minutes;
    AtomicBoolean alR = new AtomicBoolean(false);
    //JFrame message = new JFrame("It's Time!");
    //JLabel label = new JLabel("Alarm!");
    Clock used_clock = null;

    public AlarmNote(int hrs, int mins) {
        this.hour = hrs;
        this.minutes = mins;
        //this.used_clock = clock;
    }

    @Override
    public void init(int hr,int mins){
        this.hour = hr;
        this.minutes = mins;
        //this.used_clock = cl;
    }

    @Override
    public int get_hour(){return this.hour;}

    @Override
    public int get_minutes(){return this.minutes;}

    @Override
    public Clock get_clock(){return this.used_clock;}

    @Override
    public AtomicBoolean get_status(){return this.alR;}

    @Override
    public void CheckTime() {
        //Clock clock = this.used_clock;
        //Timer t = new Timer();
        //t.schedule(new TimerTask() {
           // @Override
            //public void run() {
                //while (true) { //When this is false timer should continue at 3 second interval.
                  //  if(clock.get_hours()==hour && clock.get_minutes()==minutes) {
                   //     t.cancel();
                  //  }
               // }
           // }
      //  }, 0, 60000); //Trigger every 1 minutes.
    }

    @Override
    public int compareTo(AlarmNote o) {
        return 0;
    }
}
