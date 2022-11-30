package mFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Cs_Db.Surprise_Db;

public class Parking extends JFrame {
    Container c;
    private JButton enter, exit;
    public JLabel[] parking = new JLabel[63];
    public JLabel charge2;
    BevelBorder border;

    JButton bt_floor[] = new JButton[3];
    String button_image_url[] = {
            "1","2","3" // 추가 예정
    };
    String text_floor[] = { "1층","2층","3층"};

    private JPanel center, west;

    //Index_info info[] = new Index_info[12];
    //전역 변수
    public LocalDateTime entertime;
    LocalDateTime[] d2 = new LocalDateTime[63];

    long d3;
    String c_num;
    private ImageIcon car = new ImageIcon("C:\\Users\\user\\IdeaProjects\\untitled\\Parking-Program-java\\untitled\\images\\car.png");
    JPanel logo = new JPanel();
    JPanel header = new JPanel();
    //JPanel side = new JPanel();
    JPanel content = new JPanel();
    JPanel charge_section = new JPanel();
    private  String table_name;
    Surprise_Db db;
    //차트 생성 변수
    Color pinky = new Color(236, 64, 122);

    Color shinysky = new Color(0, 188, 212);

    Color teal = new Color(220, 231, 117);

    Color purplebubble = new Color(103, 58, 183);

    Color[] colors = { pinky, shinysky, teal, purplebubble };
    String[] floorsgraph = { "1st", "2nd", "3rd", "rest" };
//
    Chart_Circle side = new Chart_Circle();
    public int cnt_whole = 63;
    public int cnt_graph[] = new int[4];
    int[] angle = new int[4];

    int[] n = new int[4];

    public Parking(String table_name,String lc_text) {
        db= new Surprise_Db(lc_text);
        db.connect();
        //db.default_create();// 1번만 쓰고 지워요 테이블 디폴트값 설정 용도

        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c= getContentPane();

        c.setLayout(new GridBagLayout());
        /*깔끔한 코드를 위해 레이아웃을 그리드백레이아웃이라는 녀석으로 제가 했는데
          레이아웃 만지고 싶은데 어케 해야 할지 모르겠으면 검색 혹은 저에게 물어봐주세용
          간단히 설명 하자면 155줄 부터 레이아웃 상세 설정 해놓은 건데
          비율 설정해서 화면 분할 한 거에여
          분할된 화면은 내용물 크기에 따라 x축이 자동으로 설정되고 높이는 고정
        */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Color color = new Color(204,204,255);
        content.setBorder(new TitledBorder(new LineBorder(Color.red,5)));
        side.setBorder(new TitledBorder(new LineBorder(Color.red,5)));
        side.setLayout(null);
        charge_section.setBorder(new TitledBorder(new LineBorder(color,5)));


        enter = new JButton("입차");
        exit = new JButton("출차");
        charge2 = new JLabel("정산");
        charge_section.add(enter);
        charge_section.add(exit);
        charge_section.add(charge2);
        //panel.setBounds(100, 700, 1050, 100);
        //panel.setBackground(Color.cyan);


        logo.setBorder(new TitledBorder(new LineBorder(Color.BLUE,5)));

        header.setBorder(new TitledBorder(new LineBorder(Color.ORANGE,5)));
        JPanel page_f[] = new JPanel[3];
        for(int i =0; i<3; i++){
            cnt_graph[i] = 0;
            page_f [i] = new JPanel();
            bt_floor[i] = new JButton(text_floor[i]);
            bt_floor[i].setName(i+"");

            bt_floor[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton field = (JButton)e.getSource();

                    int index = Integer.parseInt(field.getName());
                    for(int i =0;i<3;i++){
                        if(i==index) {
                            page_f[i].setVisible(true);
                        }
                        else{
                            page_f[i].setVisible(false);
                        }
                    }


                }
            });
        }



        int a=0; // 주차공간 id에 접근하기위해서 임의로 값 수정 하기위해 사용한 변수
        int stack = 0;// 주차장의 층 확인용 변수

        for (int i = 0; i < 22; i++) {
            if(i == 21&&stack == 2) break;
            if (i > 20 && stack == 0) {
                stack = 1;
                i -= 21;
                a = 21;
            }
            if (i > 20 && stack == 1) {
                stack = 2;
                i -= 21;
                a = 42;
            }
            System.out.printf(""+stack);
            if (db.check_parking(i + a + 1)&& stack+1== db.get_car_floor(i+a+1)) {
                entertime = db.get_Init_Time(i + a + 1);

                System.out.println(i + a + 1);

                //+ "시 " + entertime.getMinute() + "분"
                c_num = db.get_Car_Num(i + a + 1);
                parking[i + a] = new JLabel("<html><body><center>"
                        + c_num + "<br><br>"
                        + entertime.getHour() + "시"
                        + entertime.getMinute() + "분" +
                        "</center></body></html>" + "eqweqweqweqweqweqweqw");
                parking[i + a].setName(i+a+"");
                parking[i+a] = new JLabel();
                parking[i+a].setIcon(car);
                parking[i + a].setPreferredSize(new Dimension(150, 170));
                page_f[stack].add(parking[i + a]);
                parking[i + a].setBackground(Color.pink);
                parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);
                parking[i + a].setBorder(border);
                parking[i + a].setOpaque(true);
                if (stack == 0) {

                    cnt_graph[0]++;
                } else if (stack == 1) {

                    cnt_graph[1]++;
                } else if (stack == 2) {

                    cnt_graph[2]++;
                }
            } else {
                parking[i + a] = new JLabel(Integer.toString(i + 1));
                parking[i + a].setName(i+a+1+"");
                parking[i + a].setPreferredSize(new Dimension(150, 170));
                page_f[stack].add(parking[i + a]);
                parking[i + a].setBackground(Color.GREEN);
                parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);
                parking[i + a].setBorder(border);
                parking[i + a].setOpaque(true);
                cnt_graph[3]++;
            }
        }
        //parking[0].setBorder(new TitledBorder(new LineBorder(Color.red,5)));
        for(int i =0;i<3;i++){
            page_f[i].setLayout(new GridLayout(3,7,0,50));
            page_f[i].setBorder(new TitledBorder(new LineBorder(Color.red,5)));
            content.add(page_f[i]);
            page_f[0].setVisible(true);
            page_f[1].setVisible(false);
            page_f[2].setVisible(false);
        }

        gbc.ipady =0;
        gbc.ipadx =0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        gbc.gridheight =1;
        c.add(logo,gbc);

        System.out.println(content.getSize());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth =3;
        gbc.gridheight =1;
        c.add(header,gbc);

        header.setLayout(new FlowLayout());
for(int i =0; i<3;i++){
    header.add(bt_floor[i]);
}

        header.add(new JLabel(table_name));

        gbc.weighty = 0.3;
        gbc.gridx =0;
        gbc.gridy =1;
        gbc.gridwidth =1;
        gbc.gridheight=5;
        c.add(side,gbc);


        gbc.weighty = 0.1;
        gbc.gridx =0;
        gbc.gridy =6;
        gbc.gridwidth =1;
        gbc.gridheight=3;
        c.add(charge_section,gbc);

        gbc.gridx =1;
        gbc.gridy =1;
        gbc.gridheight=9;
        gbc.gridwidth =3;
        c.add(content,gbc);

        JLabel test = new JLabel("ggg");

        setSize(1332, 722+50);
        setVisible(true);


        for (int i = 0; i < 62; i++) {
            parking[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();

                        System.out.println(label.getName());

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    if (label.getText().length() > 4) {
                        label.setIcon(car);
                        label.setText("");
                    }
                }
            });
        }
        enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                new Enter();
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                new Exit();
            }
        });

    }

    public class Enter extends JFrame {
        private JButton en = new JButton("입차");
        private JTextField carnum = new JTextField("car_num");
        private JTextField num = new JTextField("index_num");
        private JTextField floor = new JTextField("set_Floor");
        public Enter() {
            setTitle("입차");

            Container c = getContentPane();
            c.setLayout(new GridLayout(2, 2));
            en.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub

                    entertime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    c_num = carnum.getText();
                    int fl = Integer.parseInt(floor.getText());
                    //

                    if(fl == 2){
                        select_index +=21;
                        cnt_graph[1]++;
                        cnt_graph[3]--;
                    } else if (fl ==3 ) {
                        select_index +=42;
                        cnt_graph[2]++;
                        cnt_graph[3]--;
                    }
                    else if(fl == 1){
                        cnt_graph[0]++;
                        cnt_graph[3]--;
                    }

                    side.repaint();
                    System.out.printf(select_index+"qqq");
                    select_index -= 1;

                    parking[select_index].setText("<html><body><center>"
                            + c_num + "<br><br>" + entertime.getHour()
                            + "시 " + entertime.getMinute() + "분" + "</center></body></html>");
                    parking[select_index].setBackground(Color.pink);
                    parking[select_index].setIcon(car);
                    parking[select_index].setVerticalTextPosition(SwingConstants.BOTTOM);
                    System.out.println(select_index + c_num);
                    db.init_car(select_index, c_num, entertime,fl);
                    setVisible(false);//입력 후 창 닫기
                }
            });

            c.add(carnum);
            c.add(num);
            c.add(floor);
            c.add(en);
            setSize(300, 300);
            setVisible(true);
        }
    }

    public class Exit extends JFrame {

        private JButton ex = new JButton("출차");

        private JButton back = new JButton("돌아가기");

        private JTextField num = new JTextField("");
        private JTextField select_floor = new JTextField("floor");
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

                    d2[Integer.parseInt(num.getText()) - 1] = LocalDateTime.of(exittime.getYear(), exittime.getMonth(), exittime.getDayOfMonth(),
                            exittime.getHour(), exittime.getMinute(), exittime.getSecond());
                    setVisible(false);

                    d3 = ChronoUnit.SECONDS.between(db.get_Init_Time(Integer.parseInt(num.getText())), d2[Integer.parseInt(num.getText()) - 1]);
                    s = Long.toString(d3);
                    charge2.setText(s + "초");
                    int fl = Integer.parseInt(select_floor.getText());
                    int value = Integer.parseInt(num.getText());
                    if(fl == 2){
                        value +=20;
                        cnt_graph[1]--;
                        cnt_graph[3]++;
                    } else if (fl ==3 ) {
                        value +=41;
                        cnt_graph[2]--;
                        cnt_graph[3]++;
                    }
                    else {
                        cnt_graph[0]--;
                        cnt_graph[3]++;
                    }
                    //System.out.println(db.get_Car_Num(value));
                    db.Out_car(value);

                    parking[value-1].setIcon(null);
                    parking[value - 1].setBackground(Color.GREEN);
                    parking[value - 1].setText(num.getText());
                    side.repaint();
                }
            });
            back.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    setVisible(false);
                    // end = false;
                }

            });
            c.add(num);
            c.add(select_floor);
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
    class Chart_Circle extends JPanel {
        public void paintComponent(Graphics g) {
            int sum = 0;
            super.paintComponent(g);
           //setSize(c.getWidth()/5,c.getHeight()/9*5);
            setBackground(Color.white);

            int startAngle = 0;
            for (int i = 0; i < floorsgraph.length; i++) {
                g.setColor(colors[i]);
                g.drawString(floorsgraph[i] + " :" + cnt_graph[i] + "", 30, 200+i*30);
            }
            for (int i = 0; i < floorsgraph.length; i++) {
                sum += cnt_graph[i];
            }
            for (int i = 0; i < floorsgraph.length; i++) {
                angle[i] = (int) Math.round((double) cnt_graph[i] / (double) sum * 360);
                System.out.println(angle[i]);
                /*
                 * repaint();s g.setColor(colors[i]);
                 * g.fillArc(150,50,200,200,startAngle,angle[i]); startAngle += angle[i];
                 */
            }

            g.setColor(pinky);
            g.fillArc(30, 50, 100, 100, 0, angle[0]);
            g.setColor(shinysky);
            g.fillArc(30, 50, 100, 100, angle[0], angle[1]);
            g.setColor(purplebubble);
            g.fillArc(30, 50, 100, 100, angle[0] + angle[1], angle[2]);
            g.setColor(Color.GRAY);
            g.fillArc(30, 50, 100, 100, angle[0] + angle[1] + angle[2], angle[3]);

        }
    }
    public static void main(String[] args) {
        // new Parking("e");
    }
}