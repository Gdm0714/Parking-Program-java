package mFrame;

import Cs_Db.Surprise_Db;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import Cs_Db.Surprise_Db;

public class frame extends JFrame {
    private JButton enter, exit;
    public JLabel[] parking = new JLabel[21];
    public JLabel charge2;
    private  JPanel panel;
    BevelBorder border;

    Surprise_Db db = new Surprise_Db();
    private JPanel center, west;

    //Index_info info[] = new Index_info[12];
    //전역 변수
    public LocalDateTime entertime;
    LocalDateTime[] d2 = new LocalDateTime[12];

    long d3;
    String c_num;
    private ImageIcon car = new ImageIcon("images/car.png");

    public frame() {
        db.connect();
   //
        setTitle("Te");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        panel = new JPanel();
        enter = new JButton("입차");
        exit = new JButton("출차");
        charge2 = new JLabel("정산");
        border = new BevelBorder(BevelBorder.RAISED);
        panel.setBounds(100, 700, 1050, 100);
        panel.setBackground(Color.cyan);
        enter.setBounds(150, 725, 100, 50);
        enter.setBackground(Color.LIGHT_GRAY);
        exit.setBounds(570, 725, 100, 50);
        exit.setBackground(Color.LIGHT_GRAY);
        charge2.setBounds(1000, 700, 100, 100);
        charge2.setBackground(Color.WHITE);
        //
        for (int i = 0; i < 21; i++) {
            parking[i] = new JLabel(Integer.toString(i + 1));
            if (i < 7) {
                parking[i].setBounds(100 + 150*i, 70, 150, 170);
                c.add(parking[i]);
                parking[i].setBackground(Color.GREEN);
                parking[i].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i].setHorizontalAlignment(parking[i].CENTER);
                parking[i].setBorder(border);
                parking[i].setOpaque(true);
            } else if (i < 14) {
                parking[i].setBounds(100 + 150 * (i - 7), 270, 150, 170);
                c.add(parking[i]);
                parking[i].setBackground(Color.GREEN);
                parking[i].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i].setHorizontalAlignment(parking[i].CENTER);
                parking[i].setBorder(border);
                parking[i].setOpaque(true);
            } else if (i < 21) {
                parking[i].setBounds(100 + 150 * (i - 14), 470, 150, 170);
                c.add(parking[i]);
                parking[i].setBackground(Color.GREEN);
                parking[i].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i].setHorizontalAlignment(parking[i].CENTER);
                parking[i].setBorder(border);
                parking[i].setOpaque(true);
            }
        }
        c.add(enter);
        c.add(exit);
        c.add(charge2);
        c.add(panel);
        setSize(1250, 900);
        setVisible(true);
    }

    public class Enter extends JFrame {
        private JButton en = new JButton("입차");
        private JTextField carnum = new JTextField("car_num");
        private JTextField num = new JTextField("index_num");

        public Enter() {
            setTitle("입차");

            Container c = getContentPane();
            c.setLayout(new GridLayout(2, 2));
            en.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub

                    entertime = LocalDateTime.now();
                    System.out.println(entertime);
                    System.out.println(entertime);
                    int select_index = Integer.parseInt(num.getText());
                    c_num = carnum.getText();
                    //
                    parking[select_index - 1].setText("<html><body><center>"
                            + c_num + "<br><br>" + entertime.getHour()
                            + "시 " + entertime.getMinute() + "분" + "</center></body></html>");
                    parking[select_index-1].setBackground(Color.CYAN);
                    parking[select_index-1].setIcon(car);
                    parking[select_index-1].setVerticalTextPosition(SwingConstants.BOTTOM);
                    System.out.println(select_index - 1 + c_num);
                    db.init_car(select_index - 1, c_num, entertime);
                    setVisible(false);//입력 후 창 닫기
                }
            });

            c.add(carnum);
            c.add(num);
            c.add(en);
            setSize(300, 300);
            setVisible(true);
        }
    }

    public class Exit extends JFrame {

        private JButton ex = new JButton("출차");

        private JButton back = new JButton("돌아가기");

        private JTextField num = new JTextField("");
        public LocalDateTime exittime;
        String s;

        public Exit() {

            setTitle("출차");

            Container c = getContentPane();

            c.setLayout(new GridLayout(2, 2));


            ex.addActionListener(new ActionListener() {

                @Override

                public void actionPerformed(ActionEvent e) {

                    // TODO Auto-generated method stub

                    exittime = LocalDateTime.now();
                    parking[Integer.parseInt(num.getText()) - 1].setText(num.getText() + "번");
                    d2[Integer.parseInt(num.getText()) - 1] = LocalDateTime.of(exittime.getYear(), exittime.getMonth(), exittime.getDayOfMonth(),
                            exittime.getHour(), exittime.getMinute(), exittime.getSecond());
                    setVisible(false);

                    d3 = ChronoUnit.SECONDS.between(db.get_Init_Time(Integer.parseInt(num.getText())), d2[Integer.parseInt(num.getText()) - 1]);
                    s = Long.toString(d3);
                    charge2.setText(s + "초");
                    db.Out_car(Integer.parseInt(num.getText()));
                    parking[Integer.parseInt(num.getText()) - 1].setIcon(null);
                    parking[Integer.parseInt(num.getText()) - 1].setBackground(Color.lightGray);
                }

            });

            back.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {

                    new Parking();

                    setVisible(false);

                    // end = false;

                }

            });

            c.add(num);

            c.add(ex);

            c.add(back);

            setSize(300, 300);

            setVisible(true);

        }

    }


    class mouselistener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            JLabel jl = (JLabel) e.getSource();
            jl.setBackground(Color.GRAY);
        }

        public void mousePressed(MouseEvent e) {
            JLabel jl = (JLabel) e.getSource();
            jl.setBackground(Color.YELLOW);
        }

        public void mouseExited(MouseEvent e) {
            JLabel jl = (JLabel) e.getSource();
            jl.setBackground(Color.GREEN);
        }
    }


    public static void main(String[] args) {
        new frame();
    }
}
