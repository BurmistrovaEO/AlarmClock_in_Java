package alarms;

public class AlarmFactory {
    public Alarm setAlarm(){
        Alarm ret = new AlarmNote();
        return ret;
    }
}
