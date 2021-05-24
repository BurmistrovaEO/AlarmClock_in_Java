package clocks;

import javax.swing.*;

public interface Clock {
    void init(int pr,String m_name);
    int get_hours();
    int get_minutes();
    void addToSet(int hrs,int mins);
    int get_price();
    void print_time();
    String getModel_name();
    void sort_on_add();
    void set_time();
    void change_time();
    void tick();
}
