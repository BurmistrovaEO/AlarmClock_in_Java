package alarms;
import java.util.Comparator;

public class Alarm_Compare implements Comparator<AlarmNote> {
    @Override
    public int compare(AlarmNote a, AlarmNote b){
        if(a.get_hour()>b.get_hour())
            return 1;
        else if(a.get_hour()<b.get_hour())
            return -1;
        else
            return 0;
    }
}


