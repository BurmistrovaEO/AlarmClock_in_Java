package alarms;

import clocks.Clock;

public interface Alarm {
    void init(int hr, int mins, Clock cl);
    void CheckTime();
}
