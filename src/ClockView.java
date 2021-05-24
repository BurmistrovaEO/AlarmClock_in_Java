import alarms.AlarmNote;
import clocks.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.JOptionPane.*;

public class ClockView implements ItemListener {
    private JPanel cards;
    Thread st_th, st_th_2, b_th1, b_th2;
    Basic_Clock basic_clock;
    Font font1 = new Font("SansSerif", Font.BOLD, 40);
    public final AtomicBoolean alive = new AtomicBoolean(false);
    public final AtomicBoolean paused = new AtomicBoolean(false);
    public final AtomicBoolean alive_2 = new AtomicBoolean(false);
    public final AtomicBoolean paused_2 = new AtomicBoolean(false);
    public final AtomicBoolean alarmRing = new AtomicBoolean(false);
    public final AtomicBoolean b_th1_alive = new AtomicBoolean(false);
    public final AtomicBoolean b_th2_alive = new AtomicBoolean(false);
    //int alive = 0;
    With_Seconds_Clock with_seconds_clock;
    /*public JPanel createPopUpPanel(){
        JPanel popPanel = new JPanel();

        JTextField field1 = new JTextField();
        field1.setPreferredSize(new Dimension(20, 20));
        field1.setEditable(true);
        JLabel tw_d = new JLabel(":");
        tw_d.setPreferredSize(new Dimension(20,20));
        JTextField field2 = new JTextField();
        field2.setPreferredSize(new Dimension(20, 20));
        field2.setEditable(true);
        popPanel.add(field1);
        popPanel.add(tw_d);
        popPanel.add(field2);
        return popPanel;
    }*/

    final   static String SIMPLECLOCKPANEL = "Панель с простыми часами"       ;
    final   static String WITHSECONDSCLOCKPANEL   = "Панель с часами с секундной стрелкой";

    public void createPanelUI(Container container){
        JComboBox<String> combobox = new JComboBox<String>(
                new String[] {SIMPLECLOCKPANEL, WITHSECONDSCLOCKPANEL});
        combobox.setEditable    (false);
        combobox.addItemListener(this);
        JPanel cbPanel = new JPanel();
        cbPanel.setPreferredSize(new Dimension(200, 50));
        cbPanel.add(combobox);
        //JPanel PopUpPanel = createPopUpPanel();
        JPanel card1 = new JPanel();
        GroupLayout layout1 = new GroupLayout(card1);

        JTextField time_hours = new JTextField();
        time_hours.setFont(font1);
        time_hours.setHorizontalAlignment(JTextField.CENTER);
        time_hours.setEditable(false);
        time_hours.setPreferredSize(new Dimension(130,130));
        JTextField time_minutes = new JTextField();
        time_minutes.setFont(font1);
        time_minutes.setHorizontalAlignment(JTextField.CENTER);
        time_minutes.setEditable(false);
        time_minutes.setPreferredSize(new Dimension(130,130));
        JLabel dots = new JLabel(":");
        dots.setPreferredSize(new Dimension(16,104));
        JButton start = new JButton("Start");

        JPanel popPanel = new JPanel(new GridLayout(0,2,1,1));
        popPanel.add(new JLabel("What time to set an alarm to?"));
        JTextField field1 = new JTextField();
        field1.setPreferredSize(new Dimension(20, 20));
        field1.setEditable(true);
        JLabel tw_d = new JLabel(":");
        tw_d.setPreferredSize(new Dimension(20,20));
        JTextField field2 = new JTextField();
        field2.setPreferredSize(new Dimension(20, 20));
        field2.setEditable(true);
        popPanel.add(field1);
        popPanel.add(tw_d);
        popPanel.add(field2);

        final int[] tmp1 = new int[1];
        final int[] tmp2 = new int[1];

        final int[] tmp12 = new int[1];
        final int[] tmp22 = new int[1];

        JOptionPane popMess = new JOptionPane(new GridLayout(0,2,1,1));

        start.setPreferredSize(new Dimension(140,47));
        JButton stop = new JButton("Stop");
        stop.setPreferredSize(new Dimension(140,47));
        JButton pause = new JButton("Pause" );
        pause.setPreferredSize(new Dimension(140,47));
        JButton continuee = new JButton("Continue");
        continuee.setPreferredSize(new Dimension(140,47));
        JButton add_alarm = new JButton("+ add alarm");
        //
        add_alarm.setPreferredSize(new Dimension(339,47));
        JLabel alarm = new JLabel("Alarms:");
        alarm.setPreferredSize(new Dimension(339,16));
        DefaultListModel<String> listModel = new DefaultListModel();
        JList<String> alarm_list = new JList(listModel);
        //JList<String> alarm_list = new JList<String>();
        alarm_list.setPreferredSize(new Dimension(339,381));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(alive.get()){ //бежит
                    //System.out.println("I'm Alive!!!");
                    //System.out.println(alarmRing.get())
                    // if(alarmRing.get()){
                        //System.out.println("I'm Alive!!!");
                        //alarmRing.set(false);
                        //showMessageDialog(getRootFrame(),"Alarm!");
                    //}
                          throw new ArithmeticException("thread_is_active!");
                }
                else {

                    basic_clock=new Basic_Clock(time_hours, time_minutes,alive,paused);
                    st_th = new Thread(basic_clock);
                    //java.awt.EventQueue.invokeLater(st_th.start());
                    alive.set(true);
                    st_th.start();
                    //time_hours.setText(Integer.toString(basic_clock.get_hours()));
                    //time_minutes.setText(Integer.toString(basic_clock.get_minutes()));
                }
            }

        });
        stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alive.get()){ //бежит
                    alive.set(false);
                    //st_th.join();
                    //assertFalse(st_th.isAlive());
                    paused.set(false);
                    ////for(Iterator<AlarmNote> alar = basic_clock.alarms.iterator(); alar.hasNext();) {
                    ////    System.out.print(alar.get_hour());
                    ////    System.out.print("     ");
                    ////    System.out.println(alar.next().get_minutes());
                   ////}
                    //st_th.interrupt();
                    //time_hours.setText(Integer.toString(0));
                    //time_minutes.setText(Integer.toString(0));
                }
                else {
                    if (paused.get()) {
                        System.out.println("Stop after pause");
                        time_hours.setText(Integer.toString(0));
                        time_minutes.setText(Integer.toString(0));
                        paused.set(false);
                        alive.set(false);
                        System.out.println(paused);
                        System.out.println(alive);
                    } else {
                        throw new ArithmeticException("thread_is_not_active!");
                    }

                }
            }
        });
        pause.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alive.get()){ //бежит
                    alive.set(false);
                    paused.set(true);
                    //st_th.interrupt();
                    //time_hours.setText(Integer.toString(0));
                    //time_minutes.setText(Integer.toString(0));
                }
                else {
                    throw new ArithmeticException("thread_is_not_active!");

                }
            }
        });
        continuee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!alive.get() && paused.get()){
                    st_th = new Thread(basic_clock);
                    //java.awt.EventQueue.invokeLater(st_th.start());
                    alive.set(true);
                    paused.set(false);
                    st_th.start();
                }
                else{
                    throw new ArithmeticException("thread_is_active or dead!");
                }
            }
        });
        add_alarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_th1 = new Thread();
                //java.awt.EventQueue.invokeLater(st_th.start());
                b_th1_alive.set(true);
                b_th1.start();
                int option = showConfirmDialog(null, popPanel,
                        "Please fill all fields",
                        OK_OPTION, INFORMATION_MESSAGE);
                        if (option == OK_OPTION) {

                            String hoursInput = field1.getText();
                            String minutesInput = field2.getText();
                            String add_h="",add_m="";
                            if ((Integer.parseInt(hoursInput))/10 == 0) {
                                add_h = "0";
                            }
                            if((Integer.parseInt(minutesInput))/10 == 0){
                                add_m = "0";
                            }
                            listModel.addElement(add_h+hoursInput+":"+add_m+minutesInput);
                            basic_clock.addToSet(Integer.parseInt(hoursInput),Integer.parseInt(minutesInput));
                            try {
                            //days = Integer.parseInt(daysInput);
                            //assignments = Integer.parseInt(assignmentsInput);
                            tmp1[0] = Integer.parseInt(field1.getText());
                            tmp2[0] = Integer.parseInt(field2.getText());
                            field1.setText(Integer.toString(0));
                            field2.setText(Integer.toString(0));
                            System.out.println(tmp1[0] + tmp2[0]);
                        } catch (NumberFormatException nfe) {
                            //nfe.printStackTrace();
                        }

                }
                        b_th1.stop();
                //b_th1_alive.set(false);
            }
        });

        card1.setLayout(layout1);
        layout1.setHorizontalGroup(layout1.createSequentialGroup()
                .addGroup(layout1.createParallelGroup(CENTER)
                        .addGroup(layout1.createSequentialGroup()
                                .addComponent(time_hours)
                                .addComponent(dots)
                                .addComponent(time_minutes)
                        )
                        .addGroup(layout1.createSequentialGroup()
                                .addComponent(start)
                                .addComponent(pause))
                        .addGroup(layout1.createSequentialGroup()
                                .addComponent(stop)
                                .addComponent(continuee)
                        )
                )     //clock
                .addGroup(layout1.createParallelGroup(LEADING)
                        .addComponent(alarm)
                        .addComponent(alarm_list)
                        .addComponent(add_alarm)
                )     //alarms
        );

        layout1.setVerticalGroup(layout1.createParallelGroup(LEADING)
                .addGroup(layout1.createSequentialGroup()
                    .addComponent(time_hours)
                    .addComponent(start)
                    .addComponent(stop))
                .addGroup(layout1.createSequentialGroup()
                    .addComponent(dots))
                .addGroup(layout1.createSequentialGroup()
                    .addComponent(time_minutes)
                    .addComponent(pause)
                    .addComponent(continuee))
                .addGroup(layout1.createSequentialGroup()
                    .addComponent(alarm)
                    .addComponent(alarm_list)
                    .addComponent(add_alarm))
        );

        layout1.setAutoCreateGaps(true);
        layout1.setAutoCreateContainerGaps(true);


        JPanel card2 = new JPanel();
        GroupLayout layout2 = new GroupLayout(card2);

        JTextField time_hours_s = new JTextField();
        time_hours_s.setFont(font1);
        time_hours_s.setHorizontalAlignment(JTextField.CENTER);
        time_hours_s.setEditable(false);
        time_hours_s.setPreferredSize(new Dimension(130,130));
        JTextField time_minutes_s = new JTextField();
        time_minutes_s.setFont(font1);
        time_minutes_s.setHorizontalAlignment(JTextField.CENTER);
        time_minutes_s.setEditable(false);
        time_minutes_s.setPreferredSize(new Dimension(130,130));
        JTextField time_seconds_s = new JTextField();
        time_seconds_s.setFont(font1);
        time_seconds_s.setHorizontalAlignment(JTextField.CENTER);
        time_seconds_s.setPreferredSize(new Dimension(130,130));
        time_seconds_s.setEditable(false);
        JLabel dots1 = new JLabel(":");
        dots1.setPreferredSize(new Dimension(16,104));
        JLabel dots2 = new JLabel(":");
        dots2.setPreferredSize(new Dimension(16,104));
        JButton start2 = new JButton("Start");
        start2.setPreferredSize(new Dimension(140,47));
        JButton stop2 = new JButton("Stop");
        stop2.setPreferredSize(new Dimension(140,47));
        JButton pause2 = new JButton("Pause" );
        pause2.setPreferredSize(new Dimension(140,47));
        JButton continuee2 = new JButton("Continue");
        continuee2.setPreferredSize(new Dimension(140,47));
        JButton add_alarm2 = new JButton("+ add alarm");
        add_alarm2.setPreferredSize(new Dimension(150,47));
        JLabel alarm2 = new JLabel("Alarms:");
        alarm2.setPreferredSize(new Dimension(150,16));
        DefaultListModel<String> listModel2 = new DefaultListModel();
        JList<String> alarm_list2 = new JList(listModel2);
        alarm_list2.setPreferredSize(new Dimension(150,381));

        start2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(alive_2.get()){ //бежит
                    throw new ArithmeticException("thread_is_active!");
                }
                else {

                    with_seconds_clock = new With_Seconds_Clock(time_hours_s, time_minutes_s,time_seconds_s,
                            alive_2,paused_2);
                    st_th_2 = new Thread(with_seconds_clock);
                    //java.awt.EventQueue.invokeLater(st_th.start());
                    alive_2.set(true);
                    st_th_2.start();
                    //time_hours.setText(Integer.toString(basic_clock.get_hours()));
                    //time_minutes.setText(Integer.toString(basic_clock.get_minutes()));
                }
            }

        });
        stop2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alive_2.get()){ //бежит
                    alive_2.set(false);
                    paused_2.set(false);
                    //st_th.interrupt();
                    //time_hours.setText(Integer.toString(0));
                    //time_minutes.setText(Integer.toString(0));
                }
                else {
                    if(paused_2.get()) {
                        System.out.println("Stop after pause");
                        time_hours_s.setText(Integer.toString(0));
                        time_minutes_s.setText(Integer.toString(0));
                        time_seconds_s.setText(Integer.toString(0));
                        paused_2.set(false);
                        alive_2.set(false);
                        System.out.println(paused);
                        System.out.println(alive);
                    }
                    else {
                        throw new ArithmeticException("thread_is_not_active!");
                    }

                }
            }
        });
        pause2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alive_2.get()){ //бежит
                    alive_2.set(false);
                    paused_2.set(true);
                    st_th_2.interrupt();
                    //time_hours.setText(Integer.toString(0));
                    //time_minutes.setText(Integer.toString(0));
                }
                else {
                    throw new ArithmeticException("thread_is_not_active!");

                }
            }
        });
        continuee2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!alive_2.get() && paused_2.get()){
                    st_th_2 = new Thread(with_seconds_clock);
                    //java.awt.EventQueue.invokeLater(st_th.start());
                    alive_2.set(true);
                    paused_2.set(false);
                    st_th_2.start();
                }
                else{
                    throw new ArithmeticException("thread_is_active or dead!");
                }
            }
        });

        add_alarm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_th2 = new Thread();
                //java.awt.EventQueue.invokeLater(st_th.start());
                b_th2_alive.set(true);
                b_th2.start();
                int option = showConfirmDialog(null, popPanel,
                        "Please fill all fields",
                        OK_OPTION, INFORMATION_MESSAGE);
                if (option == OK_OPTION) {

                    String hoursInput = field1.getText();
                    String minutesInput = field2.getText();
                    String add_h="",add_m="";
                    if ((Integer.parseInt(hoursInput))/10 == 0) {
                        add_h = "0";
                    }
                    if((Integer.parseInt(minutesInput))/10 == 0){
                        add_m = "0";
                    }
                    listModel2.addElement(add_h+hoursInput+":"+add_m+minutesInput);
                    with_seconds_clock.addToSet(Integer.parseInt(hoursInput),Integer.parseInt(minutesInput));
                    try {
                        //days = Integer.parseInt(daysInput);
                        //assignments = Integer.parseInt(assignmentsInput);
                        tmp12[0] = Integer.parseInt(field1.getText());
                        tmp22[0] = Integer.parseInt(field2.getText());
                        field1.setText(Integer.toString(0));
                        field2.setText(Integer.toString(0));
                        System.out.println(tmp12[0] + tmp22[0]);
                    } catch (NumberFormatException nfe) {
                        //nfe.printStackTrace();
                    }

                }
                b_th2.stop();
                //b_th1_alive.set(false);
            }
            /*@Override
            public void actionPerformed(ActionEvent e) {
                int option = showConfirmDialog(null, popPanel,
                        "Please fill all the fields",
                        OK_OPTION, INFORMATION_MESSAGE);
                if (option == OK_OPTION) {

                    String hoursInput = field1.getText();
                    String minutesInput = field2.getText();

                    try {
                        //days = Integer.parseInt(daysInput);
                        //assignments = Integer.parseInt(assignmentsInput);
                        tmp1[0] = Integer.parseInt(field1.getText());
                        tmp2[0] = Integer.parseInt(field2.getText());
                        field1.setText(Integer.toString(0));
                        field2.setText(Integer.toString(0));
                        System.out.println(tmp1[0] + tmp2[0]);
                    } catch (NumberFormatException nfe) {
                        //nfe.printStackTrace();
                    }

                }
            }*/
        });

        card2.setLayout(layout2);
        layout2.setHorizontalGroup(layout2.createSequentialGroup()
                .addGroup(layout2.createParallelGroup(CENTER)
                        .addGroup(layout2.createSequentialGroup()
                                .addComponent(time_hours_s)
                                .addComponent(dots1)
                                .addComponent(time_minutes_s)
                                .addComponent(dots2)
                                .addComponent(time_seconds_s)
                        )
                        .addGroup(layout2.createSequentialGroup()
                                .addComponent(start2)
                                .addComponent(pause2))
                        .addGroup(layout2.createSequentialGroup()
                                .addComponent(stop2)
                                .addComponent(continuee2)
                        )
                )     //clock
                .addGroup(layout2.createParallelGroup(LEADING)
                        .addComponent(alarm2)
                        .addComponent(alarm_list2)
                        .addComponent(add_alarm2)
                )     //alarms
        );

        layout2.setVerticalGroup(layout2.createParallelGroup(LEADING)
                .addGroup(layout2.createSequentialGroup()
                        .addGroup(layout2.createParallelGroup(BASELINE)
                            .addComponent(time_hours_s)
                            .addComponent(dots1)
                            .addComponent(time_minutes_s)
                            .addComponent(dots2)
                            .addComponent(time_seconds_s))
                        .addGroup(layout2.createParallelGroup(CENTER)
                            .addComponent(start2)
                            .addComponent(pause2))
                        .addGroup(layout2.createParallelGroup(CENTER)
                            .addComponent(stop2)
                            .addComponent(continuee2))
                )

                .addGroup(layout2.createSequentialGroup()
                        .addComponent(alarm2)
                        .addComponent(alarm_list2)
                        .addComponent(add_alarm2))
        );

        layout2.setAutoCreateGaps(true);
        layout2.setAutoCreateContainerGaps(true);

        // Создаем панель с менеджером расположения CardLayout
        cards = new JPanel(new CardLayout());

        // Добавляем вкладки
        cards.add(card1, SIMPLECLOCKPANEL);
        cards.add(card2, WITHSECONDSCLOCKPANEL);

        container.add(cbPanel, BorderLayout.PAGE_START);
        container.add(cards  , BorderLayout.CENTER    );
    }
    // Обработчик события
    public void itemStateChanged(ItemEvent event)
    {
        CardLayout layout = (CardLayout)(cards.getLayout());
        layout.show(cards, (String)event.getItem());
    }
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException, InterruptedException
    {
        // Создание и настройка окна
        final JFrame frame = new JFrame("ClockView ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setMaximumSize(new Dimension(1000, 600));
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new ClockView ()).createPanelUI(frame.getContentPane());

                // Открытие окна
                frame.pack();
                frame.setVisible(true);
            }
        });

    }
}
