package alarms;

import clocks.Clock;

import java.util.concurrent.atomic.AtomicBoolean;

public interface Alarm {
    void init(int hr, int mins);
    int get_hour();
    int get_minutes();
    Clock get_clock();
    AtomicBoolean get_status();
    void CheckTime();
}
