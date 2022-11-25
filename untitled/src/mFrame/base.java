package mFrame;

import Cs_Db.DB_Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
class MyLabel extends JLabel{
    private int barSize = 0;
    private int maxBarSize;
    public MyLabel(int maxBarSize){
        this.barSize = maxBarSize;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.MAGENTA);
        int width = (int) (((double)(this.getWidth()))/maxBarSize*barSize);
        if(width == 0) return ;
        g.fillRect(0,0,width,this.getHeight());
    }

    synchronized public void fill(){
        if(barSize == maxBarSize){
            try{
                new Parking();
            }
            catch (Exception e){return;}
        }
        barSize++;
        repaint();
        notify();
    }
}
class loading extends Thread{
    private MyLabel bar;
    public loading(MyLabel bar){
        this.bar = bar;
    }
    public void run(){
        while(true){
            try{
                sleep(1000);
                bar.fill();
            }catch (Exception e){return;}
        }
    }
}
public class base extends JFrame {
    private JTextField id = new JTextField("id 입력");
    private JTextField pw = new JTextField("pw 입력");
    private JButton log = new JButton("로그인");
    private JButton jb = new JButton("회원가입");
    private JLabel pi;
    private ImageIcon parking = new ImageIcon("images/parkingicon.png");
    DB_Connection db = new DB_Connection();
    private  MyLabel bar = new MyLabel(100);
    public base() {
       // db.connect();
        setTitle("메인 화면");
        loading l = new loading(bar);
        l.start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        id.setBounds(220, 300, 160, 30);
        pw.setBounds(220, 330, 160, 30);
        bar.setVisible(true);
        bar.setBackground(Color.yellow);
        bar.setSize(300,20);
        c.add(bar);
        c.add(id);
        c.add(pw);
        pi = new JLabel(parking);
        pi.setBounds(200, 100, 200, 200);
        c.add(pi);
        log.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               // if(db.login(id.getText(), pw.getText())){
                   Parking p =new Parking();

              // };
            }
        });
        jb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new sign_Dialog();
            }
        });
        log.setBounds(200, 360, 100, 50);
        jb.setBounds(300, 360, 100, 50);
        c.add(log);
        c.add(jb);
        setSize(600, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        new base();
    }
}
