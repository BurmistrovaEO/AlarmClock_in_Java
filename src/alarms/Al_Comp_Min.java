package alarms;
import java.util.Comparator;

public class Al_Comp_Min implements Comparator<AlarmNote>{
    @Override
    public int compare(AlarmNote a, AlarmNote b){
        if(a.get_minutes()>b.get_minutes())
            return 1;
        else if(a.get_minutes()<b.get_minutes())
            return -1;
        else
            return 0;
    }
}
