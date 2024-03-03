import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SubClock  implements Runnable{

    Timer timer = new Timer();
    JLabel time = new JLabel("tyd");
    JTextField enterHour = new JTextField();
    JButton button = new JButton("Click");
    SpringLayout springLayout = new SpringLayout();
    String hour;
    public SubClock(String hour) {
        this.hour = hour;
    }
    public void updateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        LocalTime currentTime = LocalTime.now();
        int min = currentTime.getMinute();
        int second = currentTime.getSecond();
        if(second == 59 && min == 59){
            int hourInt = Integer.parseInt(hour);
            hourInt++;
            hour = String.valueOf(hourInt);
            System.out.println(hour);
        }
        time.setText(hour +":" + simpleDateFormat.format(new Date()));
    }

    public void run(){
        JFrame f = new JFrame();
        f.setSize(300, 200);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.setVisible(true);
        f.setLayout(springLayout);
        f.add(time);
        f.add(enterHour);
        f.add(button);

        enterHour.setPreferredSize(new Dimension(170, 30));
        button.setPreferredSize(new Dimension(70, 30));

        time.setFont(new Font(Font.SERIF,Font.PLAIN,20));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubClock sub = new SubClock(enterHour.getText());
                Thread t1 = new Thread(sub);
                t1.start();
            }
        });

        springLayout.putConstraint(springLayout.SOUTH, time, 70, springLayout.NORTH, f);
        springLayout.putConstraint(springLayout.EAST, time, 150, springLayout.EAST, f);

        springLayout.putConstraint(springLayout.SOUTH, button, 130, springLayout.NORTH, f);
        springLayout.putConstraint(springLayout.WEST, button, 190, springLayout.EAST, f);

        springLayout.putConstraint(springLayout.SOUTH, enterHour, 130, springLayout.NORTH, f);
        springLayout.putConstraint(springLayout.WEST, enterHour, 10, springLayout.EAST, f);


        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                updateTime();
            }
        }, 0, 1000);

    }

}
