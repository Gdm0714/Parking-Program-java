package mFrame;

import Cs_Db.DB_Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class base extends JFrame {
    private JTextField pw = new JTextField("pw 입력");
    private JButton log = new JButton("로그인");
    private ImageIcon parking = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\logo2.png");
    DB_Connection db = new DB_Connection();
    private JLabel notice = new JLabel();
    public base() {
        db.connect();
        setTitle("로그인 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        pw.setBounds(220, 330, 160, 30);
        notice.setBounds(100, 100, 400 ,100);
        c.add(pw);
        c.add(notice);
        //pi = new JLabel(parking);
        //pi.setBounds(200, 100, 300, 300);
        //c.add(pi);

        pw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pw.setText("");
            }
        });
        log.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(db.login(pw.getText())){
                    new Parking();
                    setVisible(false);
                }
                else{
                    notice.setForeground(Color.RED);

                    notice.setText("관리자 비밀번호가 틀렸습니다!");
                }
            }
        });
        log.setBounds(200, 360, 100, 50);
        c.add(log);
        setSize(600, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new base();
    }
}
