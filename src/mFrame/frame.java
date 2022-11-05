package mFrame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class frame extends JFrame {
    private JButton enter, exit;
    public JLabel[] parking = new JLabel[21];
    public JLabel charge2;
    private  JPanel panel;
    BevelBorder border;

    public frame() {
        setTitle("Test");
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

    public static void main(String[] args) {
        new frame();
    }
}
