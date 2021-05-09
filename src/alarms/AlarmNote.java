package alarms;
import clocks.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class AlarmNote implements Alarm{
    int hour;
    int minutes;
    JFrame message = new JFrame("It's Time!");
    JLabel label = new JLabel("Alarm!");
    Clock used_clock = null;

    @Override
    public void init(int hr,int mins,Clock cl){
        this.hour = hr;
        this.minutes = mins;
        this.used_clock = cl;
    }

    @Override
    public void CheckTime() {
        Clock clock = this.used_clock;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                while (true) { //When this is false timer should continue at 3 second interval.
                    if(clock.get_hours()==hour && clock.get_minutes()==minutes) {
                        t.cancel();
                    }
                }
            }
        }, 0, 60000); //Trigger every 1 minutes.
    }
}
