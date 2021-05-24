package clocks;
import alarms.*;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Basic_Clock implements Clock, Runnable{
    int price;
    String model_name;
    String type = "Basic";


    JTextField h;
    JTextField m;
    AtomicBoolean al;
    AtomicBoolean pau;
    //AtomicBoolean alR;

    public  Basic_Clock(JTextField h, JTextField m, AtomicBoolean al,AtomicBoolean pau){
        this.h = h;
        this.m = m;
        this.al = al;
        this.pau = pau;
        //this.alR = alR;
    }

    int hours = 0;
    int minutes = 0;
    int tmp_seconds=0;
    int i = -1;

    Comparator<AlarmNote> acomp = new Alarm_Compare().thenComparing(new Al_Comp_Min());
    public TreeSet<AlarmNote> alarms = new TreeSet(acomp);
    //Iterator<AlarmNote> value = alarms.iterator();

    //AlarmNote an;

    public Basic_Clock() {
    }

    @Override
    public void init(int pr,String m_name){
        this.price = pr;
        this.model_name = m_name;
    }

    @Override
    public void addToSet(int hrs,int mins){
        alarms.add(new AlarmNote(hrs,mins));
    }
    @Override
    public int get_price(){
        return this.price;
    }

    @Override
    public int get_hours(){return this.hours;}

    @Override
    public int get_minutes(){return this.minutes;}
    @Override
    public String getModel_name(){
        return this.model_name;
    }

    @Override
    public void print_time(){
        System.out.print("Hours: ");
        System.out.println(this.hours);
        System.out.print("Minutes: ");
        System.out.println(this.minutes);
    }

    @Override
    public void sort_on_add(){

    }

    @Override
    public void change_time(){
        int hrs,mins;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Input desired shift in hours: ");
        hrs = myObj.nextInt();
        System.out.println("Input desired shift in minutes: ");
        mins = myObj.nextInt();
        if((hrs>12) || hrs<0){
            throw new ArithmeticException("Too many wheel turns!");
        }
        else{
            if(this.hours+hrs>=24)
                this.hours = this.hours+hrs-24;
            else
                this.hours = this.hours + hrs;
        }
        if(mins>60 || mins<0){
            throw new ArithmeticException("Too many wheel turns!");
        }
        else {
            if(this.minutes+mins>=60) {
                this.hours += 1;
                this.minutes = this.minutes + mins - 60;
            }
            else
                this.minutes = this.minutes + mins;
        }
    }

    @Override
    public void set_time() {
        int hrs,mins;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Input desired amount of hours: ");
        hrs = myObj.nextInt();
        System.out.println("Input desired amount of minutes: ");
        mins = myObj.nextInt();
        if(hrs>12 || hrs<1){
            throw new ArithmeticException("Not a valid hours value!");
        }
        else {
            this.hours = hrs;
        }
        if(mins>60 || mins<1){
            throw new ArithmeticException("Not a valid minutes value!");
        }
        else {
            this.minutes = mins;
        }
    }

    @Override
    public void tick() {
        this.tmp_seconds +=1;
        if(this.tmp_seconds>=60){
            this.minutes++;
            this.tmp_seconds = 0;}
        if(this.minutes>=60) {
            this.hours++;
            this.minutes = 0;
        }
        if(this.hours>=23) {
            this.hours = 0;
        }
    }

    @Override
    public void run() {
        System.out.println("Is running");
        while(this.al.get()) {
            this.tick();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                //System.out.println("Thread interrupted");
            }
            this.h.setText(Integer.toString(hours));
            this.m.setText(Integer.toString(minutes));
            //System.out.println(currentThread());
            //currentThread().interrupt();
            //System.out.println("ALARMMMM");
            //Thread check = new Thread(this) {
            if(!alarms.isEmpty())
                for(AlarmNote val : alarms)
                    if (val.get_hour() == this.hours && val.get_minutes() == this.minutes && !val.get_status().get()) {
                        val.get_status().set(true);
                        System.out.println("ALARMMMM");
                        Thread alarmm = new Thread() {
                            public void run() {
                                showMessageDialog(null, "Alarm!");
                            }
                        };
                        alarmm.start();
                    }
            //currentThread().interrupted();
        }
        if(!this.pau.get()) {
            System.out.println("Stop the clock");
            this.h.setText(Integer.toString(0));
            this.m.setText(Integer.toString(0));
        }

    }
}

