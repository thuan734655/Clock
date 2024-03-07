import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

public class Clock  implements Runnable{

    Timer timer = new Timer();
    JLabel time = new JLabel("");
    JTextField enterHour = new JTextField();
    JButton button = new JButton("Click");
    SpringLayout springLayout = new SpringLayout();

    int muiGio;
    public Clock(String muiGio) {
        this.muiGio = Integer.parseInt(muiGio);
    }

    public void updateTime() {
      try {
          ZoneId customZone = ZoneId.of("GMT" + (muiGio >= 0 ? "+" : "") + muiGio);
          LocalTime currentTime = LocalTime.now(customZone);
          time.setText(String.format("%02d:%02d:%02d", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond()));
      }
      catch (Exception e) {
          JOptionPane.showMessageDialog(null,"mui gio khong hop le");

      }
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
                if(Integer.parseInt(enterHour.getText()) > 18 || Integer.parseInt(enterHour.getText()) < -18 ) {
                    JOptionPane.showMessageDialog(null,"mui gio khong hop le");
                }
                else {
                    Clock sub = new Clock(enterHour.getText());
                    Thread t1 = new Thread(sub);
                    t1.start();
                }
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
