import alarms.*;
import clocks.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Handler;

import static javax.swing.GroupLayout.Alignment.*;

public class ClockView implements ItemListener {
    private JPanel cards;
    Thread st_th, t1_th,t2_th;
    Basic_Clock basic_clock;
    private final AtomicBoolean alive = new AtomicBoolean(false);
    //int alive = 0;
    int alive_2 = 0;
    With_Seconds_Clock with_seconds_clock;


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

        JPanel card1 = new JPanel();
        GroupLayout layout1 = new GroupLayout(card1);

        JTextField time_hours = new JTextField();


        time_hours.setEditable(false);
        time_hours.setPreferredSize(new Dimension(130,130));
        JTextField time_minutes = new JTextField();
        time_minutes.setEditable(false);
        time_minutes.setPreferredSize(new Dimension(130,130));
        JLabel dots = new JLabel(":");
        dots.setPreferredSize(new Dimension(16,104));
        JButton start = new JButton("Start");
       // start.addActionListener(new ActionListener() {

       ///     @Override
        //    public void actionPerformed(ActionEvent e) {
       //         if(alive == 1){ //бежит
         //           throw new ArithmeticException("Not a valid hours value!");
        //        }
         //       else {
        //            basic_clock=new Basic_Clock();
        //            time_hours.setText(Integer.toString(basic_clock.get_hours()));
        //            time_minutes.setText(Integer.toString(basic_clock.get_minutes()));
        //            alive = 1;
        //            basic_clock.run();
        //        }
        //    }
        //});


        start.setPreferredSize(new Dimension(140,47));
        JButton stop = new JButton("Stop");
        stop.setPreferredSize(new Dimension(140,47));
        JButton pause = new JButton("Pause" );
        pause.setPreferredSize(new Dimension(140,47));
        JButton continuee = new JButton("Continue");
        continuee.setPreferredSize(new Dimension(140,47));
        JButton add_alarm = new JButton("+ add alarm");
        add_alarm.setPreferredSize(new Dimension(339,47));
        JLabel alarm = new JLabel("Alarms:");
        alarm.setPreferredSize(new Dimension(339,16));
        JList<String> alarm_list = new JList<String>();
        alarm_list.setPreferredSize(new Dimension(339,381));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(alive == true){ //бежит
                          throw new ArithmeticException("thread_is_active!");
                       }
                       else {

                            basic_clock=new Basic_Clock(time_hours, time_minutes);
                            st_th = new Thread(basic_clock);
                            //java.awt.EventQueue.invokeLater(st_th.start());
                            alive = true;
                            st_th.start();
                            //time_hours.setText(Integer.toString(basic_clock.get_hours()));
                            //time_minutes.setText(Integer.toString(basic_clock.get_minutes()));
                        }
            }

        });
        stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alive == 1){ //бежит
                    st_th = new Thread(basic_clock);
                    //java.awt.EventQueue.invokeLater(st_th.start());
                    alive = 1;
                    st_th.start();
                    //time_hours.setText(Integer.toString(basic_clock.get_hours()));
                    //time_minutes.setText(Integer.toString(basic_clock.get_minutes()));
                }
                else {
                    throw new ArithmeticException("thread_is_not_active!");
                    basic_clock=new Basic_Clock(time_hours, time_minutes);

                }
            }
        });
        //pause.addActionListener((ActionListener) handler);
        //continuee.addActionListener((ActionListener) handler);
        //add_alarm.addActionListener((ActionListener) handler);

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
        time_hours_s.setEditable(false);
        time_hours_s.setPreferredSize(new Dimension(130,130));
        JTextField time_minutes_s = new JTextField();
        time_minutes_s.setEditable(false);
        time_minutes_s.setPreferredSize(new Dimension(130,130));
        JTextField time_seconds_s = new JTextField();
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
        JList<String> alarm_list2 = new JList<String>();
        alarm_list2.setPreferredSize(new Dimension(150,381));

        //start2.addActionListener((ActionListener) handler);
        //stop2.addActionListener((ActionListener) handler);
        //pause2.addActionListener((ActionListener) handler);
        //continuee2.addActionListener((ActionListener) handler);
        //add_alarm2.addActionListener((ActionListener) handler);

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
            IllegalAccessException, UnsupportedLookAndFeelException
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
