package clocks;
import alarms.*;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;
import static javax.swing.JOptionPane.showMessageDialog;

public class With_Seconds_Clock extends Basic_Clock{
    int seconds = 0;
    JTextField s;
    Comparator<AlarmNote> acomp = new Alarm_Compare().thenComparing(new Al_Comp_Min());
    TreeSet<AlarmNote> alarms = new TreeSet(acomp);
    String type = "With_Seconds";


    public  With_Seconds_Clock(JTextField h, JTextField m, JTextField s, AtomicBoolean al,AtomicBoolean pau){
        this.h = h;
        this.m = m;
        this.s = s;
        this.al = al;
        this.pau = pau;
    }

    @Override
    public void addToSet(int hrs,int mins){
        alarms.add(new AlarmNote(hrs,mins));
    }

    @Override
    public void set_time(){
        int hrs,mins,s;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Input desired amount of hours: ");
        hrs = myObj.nextInt();
        System.out.println("Input desired amount of minutes: ");
        mins = myObj.nextInt();
        System.out.println("Input desired amount of seconds: ");
        s = myObj.nextInt();
        if(hrs>12 || hrs<1){
            throw new ArithmeticException("Not a valid hours value!");
        }
        else {
            this.hours = hrs;
        }
        if(mins>60 || mins<0){
            throw new ArithmeticException("Not a valid minutes value!");
        }
        else {
            this.minutes = mins;
        }
        if(s>60 || s<0){
            throw new ArithmeticException("Not a valid seconds value!");
        }
        else {
            this.seconds = s;
        }
    }
    @Override
    public int get_hours(){return this.hours;}

    @Override
    public int get_minutes(){return this.minutes;}

    @Override
    public void print_time(){
        System.out.print("Hours: ");
        System.out.println(this.hours);
        System.out.print("Minutes: ");
        System.out.println(this.minutes);
        System.out.print("Seconds: ");
        System.out.println(this.seconds);
    }
    @Override
    public void change_time() {
        int hrs,mins,s;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Input desired shift in hours: ");
        hrs = myObj.nextInt();
        System.out.println("Input desired shift in minutes: ");
        mins = myObj.nextInt();
        System.out.println("Input desired shift in seconds: ");
        s = myObj.nextInt();
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
        if(s>60 || s<0){
            throw new ArithmeticException("Too many wheel turns!");
        }
        else {
            if(this.seconds+seconds>=60) {
                this.minutes += 1;
                this.seconds = this.seconds + s - 60;
            }
            else
                this.seconds += s;
        }
    }

    @Override
    public void tick() {
        this.seconds +=1;
        if(this.seconds>=60){
            this.minutes++;
            this.seconds = 0;}
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
        System.out.println("With_seconds is running");
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
            this.s.setText(Integer.toString(seconds));
            //System.out.println(currentThread());
            //currentThread().interrupt();
            //System.out.println("ALARMMMM");
            //Thread check = new Thread(this) {
            if(!alarms.isEmpty())
                for(AlarmNote val : alarms)
                    if (val.get_hour() == this.hours && val.get_minutes() == this.minutes && !val.get_status().get()) {
                        val.get_status().set(true);
                        System.out.println("ALARMMMM");
                        Thread alarmm2 = new Thread() {
                            public void run() {
                                showMessageDialog(null, "Alarm!");
                            }
                        };
                        alarmm2.start();
                    }
            //currentThread().interrupted();
        }
        if(!this.pau.get()) {
            System.out.println("Stop the with_seconds clock");
            this.h.setText(Integer.toString(0));
            this.m.setText(Integer.toString(0));
            this.s.setText(Integer.toString(0));
        }

    }
}

