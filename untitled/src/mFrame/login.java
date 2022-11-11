package mFrame;

import Cs_Db.DB_Connection;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class login extends JFrame {
    private JTextField id = new JTextField("id 입력");
    private JTextField pw = new JTextField("pw 입력");
    private JButton log = new JButton("로그인");
    private JButton jb = new JButton("회원가입");
    DB_Connection db = new DB_Connection();
    public login() {
        db.connect();
        setTitle("로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new GridLayout(2, 2));
        c.add(id);
        c.add(pw);
        c.add(log);
        c.add(jb);
        log.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                db.login(id.getText(), pw.getText());
            }
        });
        jb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new sign_Dialog();
            }
        });
        setSize(500, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new login();
    }
}