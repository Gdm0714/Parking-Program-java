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
    private JButton enter, exit;

    public JLabel[] parking = new JLabel[63];
    public JLabel charge2;


    BevelBorder border;

    JComboBox<String> field_c = new JComboBox<String>();



    Surprise_Db db;

    //Index_info info[] = new Index_info[12];
    //전역 변수
    public LocalDateTime entertime;

    LocalDateTime[] d2 = new LocalDateTime[63];
    JButton bt_floor[] = new JButton[3];
    String button_image_url[] = {
            "1","2","3" // 추가 예정
    };
    String text_floor[] = { "1층","2층","3층"};
    long d3;

    String c_num;

    private ImageIcon car = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\car.png");

    private ImageIcon logo1 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\logo1.jpg");

    private ImageIcon logo2 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\logo2.png");

    private ImageIcon logo3 = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\logo.png");

    private ImageIcon place = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\images.png");

    private ImageIcon conBack = new ImageIcon("C:\\Users\\고동민\\Desktop\\자바팀플 20192601 고동민\\Parking-Program-java\\untitled\\images\\img.png");
//12/3 수정
    private  ImageIcon bt_floor_1 = new ImageIcon("C:\\Users\\user\\Desktop\\bt_floor_1.png");
    private  ImageIcon bt_floor_2 = new ImageIcon("C:\\Users\\user\\Desktop\\bt_floor_2.png");
    private  ImageIcon bt_floor_3 = new ImageIcon("C:\\Users\\user\\Desktop\\bt_floor_3.png");
    private  ImageIcon bt_floor_1_click = new ImageIcon("C:\\Users\\user\\Desktop\\bt_floor_1_click.png");
    private  ImageIcon bt_floor_2_click = new ImageIcon("C:\\Users\\user\\Desktop\\bt_floor_2_click.png");
    private  ImageIcon bt_floor_3_click = new ImageIcon("C:\\Users\\user\\Desktop\\bt_floor_3_click.png");
//12/3 수정 완

    private showThread st;

    private JLabel enterNotice = new JLabel();

    private JLabel exitNotice = new JLabel();
    JPanel logo = new JPanel() {
        Image img = logo1.getImage();

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, this);
        }
    };


    JPanel header = new JPanel();

    Chart_Circle side = new Chart_Circle();

    JPanel content = new JPanel();

    JPanel charge_section = new JPanel();

    Color pinky = new Color(236, 64, 122);

    Color shinysky = new Color(0, 188, 212);

    Color teal = new Color(220, 231, 117);

    Color purplebubble = new Color(103, 58, 183);

    Color[] colors = { pinky, shinysky, teal, purplebubble };
    String[] floorsgraph = { "1st", "2nd", "3rd", "rest" };

    public int cnt_whole = 63;

    public int cnt_graph[] = new int[4];


    int[] angle = new int[4];

    int[] n = new int[4];
    private JLabel logoLabel = new JLabel();

    private Container c;

    private JLabel space;

    String[] floorsGraph = { "1st", "2nd", "3rd", "rest" };


    int firstNum = 0;

    int secondNum = 0;

    int thirdNum = 0;

    boolean check_bt[] ={true,false,false};

    public Parking(String table_name,String lc_text) {
        db= new Surprise_Db(lc_text);
        db.connect();
        //db.default_create();// 1번만 쓰고 지워요 테이블 디폴트값 설정 용도
        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = getContentPane();
        c.setLayout(new GridBagLayout());
        /*깔끔한 코드를 위해 레이아웃을 그리드백레이아웃이라는 녀석으로 제가 했는데
          레이아웃 만지고 싶은데 어케 해야 할지 모르겠으면 검색 혹은 저에게 물어봐주세용
          간단히 설명 하자면 155줄 부터 레이아웃 상세 설정 해놓은 건데
          비율 설정해서 화면 분할 한 거에여
          분할된 화면은 내용물 크기에 따라 x축이 자동으로 설정되고 높이는 고정
        */


        content.setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
        side.setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
        logo.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 5)));
        header.setBorder(new TitledBorder(new LineBorder(Color.ORANGE, 5)));

//12/3 수정

        bt_floor[0] = new JButton(bt_floor_1_click);

        bt_floor[1] = new JButton(bt_floor_2);

        bt_floor[2] = new JButton(bt_floor_3);

        JPanel page_f[] = new JPanel[3];
        for(int i =0; i<3; i++){
            cnt_graph[i] = 0;
            page_f [i] = new JPanel();
            bt_floor[i].setSize(10,10);
            bt_floor[i].setName(i+"");
            bt_floor[i].setBackground(Color.gray);
            bt_floor[i].setBorder(null);

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
            bt_floor[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    JButton field = (JButton)e.getSource();
                    int index = Integer.parseInt(field.getName());
                    if(index == 0){
                        bt_floor[0].setIcon(bt_floor_1_click);
                        bt_floor[1].setIcon(bt_floor_2);
                        bt_floor[2].setIcon(bt_floor_3);
                    }
                    if(index == 1){
                        bt_floor[1].setIcon(bt_floor_2_click);
                        bt_floor[0].setIcon(bt_floor_1);
                        bt_floor[2].setIcon(bt_floor_3);
                    }
                    if(index == 2){
                        bt_floor[2].setIcon(bt_floor_3_click);
                        bt_floor[1].setIcon(bt_floor_2);
                        bt_floor[0].setIcon(bt_floor_1);
                    }
                }
            });
        }
        //12/3 수정완
        cnt_graph[3] = 63;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        enter = new JButton("입차");
        exit = new JButton("출차");
        charge2 = new JLabel("정산");
        charge_section.add(enter);
        charge_section.add(exit);
        charge_section.add(charge2);
        Color color = new Color(204,204,255);
        content.setBorder(new TitledBorder(new LineBorder(Color.red,5)));
        side.setBorder(new TitledBorder(new LineBorder(Color.red,5)));
        side.setLayout(null);
        charge_section.setBorder(new TitledBorder(new LineBorder(color,5)));



        int a = 0; // 주차공간 id에 접근하기위해서 임의로 값 수정 하기위해 사용한 변수
        int stack = 0;// 주차장의 층 확인용 변수

        for (int i = 0; i < 22; i++) {
            if (i == 21 && stack == 2) break;

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
            System.out.printf("" + stack);

            if (db.check_parking(i + a + 1) && stack + 1 == db.get_car_floor(i + a + 1)) {
                entertime = db.get_Init_Time(i + a + 1);

                System.out.println(i + a + 1);

                //+ "시 " + entertime.getMinute() + "분"
                c_num = db.get_Car_Num(i + a + 1);
                parking[i + a] = new JLabel();
                parking[i + a].setName(i+a+1+"");
                parking[i + a].setIcon(car);
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
                parking[i + a].setForeground(Color.WHITE);
                parking[i + a].setPreferredSize(new Dimension(150, 170));
                parking[i + a].setName(i+a+1+"");
                page_f[stack].add(parking[i + a]);
                parking[i + a].setBackground(Color.BLACK);
                parking[i + a].setBorder(new LineBorder(Color.WHITE, 5));
                parking[i + a].setFont(new Font("Gothic", Font.PLAIN, 20));
                parking[i + a].setHorizontalAlignment(parking[i + a].CENTER);

                parking[i + a].setOpaque(true);
                cnt_graph[3]++;
            }
        }

        for (int i = 0; i < 3; i++) {
            page_f[i].setLayout(new GridLayout(3, 7, 0, 50));
            page_f[i].setBorder(new TitledBorder(new LineBorder(Color.red, 5)));
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

        //c.add(field_c);
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

        setSize(1432, 922);
        setVisible(true);

        logo.add(logoLabel);
        for (int i = 0; i < 62; i++) {
            parking[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();

                    int ed = Integer.parseInt(label.getName());
                    if(db.check_parking(ed) == true) {
                        c_num = db.get_Car_Num(ed);
                        entertime = db.get_Init_Time(ed);
                        label.setIcon(null);
                        label.setText("<html><body><center>"
                                + c_num + "<br><br>" + entertime.getHour()
                                + "시 " + entertime.getMinute() + "분" + "</center></body></html>");
                        label.setBackground(Color.pink);
                    }

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
        content.setBackground(Color.BLACK);
        side.setBackground(Color.GRAY);
        header.setBackground(Color.GRAY);


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

        private JTextField floor = new JTextField("floor");

        public Enter() {
            setTitle("입차");
            Container c = getContentPane();
            c.setLayout(new GridLayout(2, 2));
            en.setBackground(Color.gray);
            carnum.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (carnum.getText().equals("car_num"))
                        carnum.setText("");
                }
            });
            num.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (num.getText().equals("index_num"))
                        num.setText("");
                }
            });

            floor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (floor.getText().equals("floor"))
                        floor.setText("");
                }
            });
            en.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    entertime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    c_num = carnum.getText();
                    int fl = Integer.parseInt(floor.getText());
                    if (fl == 2) {
                        select_index += 21;
                        cnt_graph[1]++;
                        cnt_graph[3]--;
                        secondNum++;
                    } else if (fl == 3) {
                        select_index += 42;
                        thirdNum++;
                        cnt_graph[2]++;
                        cnt_graph[3]--;
                    } else if (fl == 1) {
                        firstNum++;
                        cnt_graph[0]++;
                        cnt_graph[3]--;
                    }
                    System.out.printf(select_index + "");
                    parking[select_index - 1].setText("");
                    parking[select_index - 1].setIcon(car);
                    parking[select_index - 1].setVerticalTextPosition(SwingConstants.BOTTOM);

                    System.out.println(select_index - 1 + c_num);
                    db.init_car(select_index - 1, c_num, entertime, fl);
                    side.repaint();
                    setVisible(false);//입력 후 창 닫기

                }
            });

            en.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    entertime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    c_num = carnum.getText();
                    int fl = Integer.parseInt(floor.getText());
                    if (fl == 2) {
                        select_index += 20;
                    } else if (fl == 3) {
                        select_index += 41;
                    }
                    enterNotice.setFont(new Font("Serif", Font.BOLD, 30));
                    enterNotice.setForeground(Color.BLUE);
                    enterNotice.setText("<html><body><center>" + +entertime.getHour() + "시" + entertime.getMinute() + "분<br>"
                            + Integer.toString(fl) + "층" + Integer.toString(select_index) + "번 자리에 " + c_num + "차량 입차되었습니다</center></body></html>");
                    st = new showThread(enterNotice, header, 0, e.getY());
                    st.start();
                    revalidate();
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

        private JTextField num = new JTextField("index_num");

        private JTextField select_floor = new JTextField("floor");

        public LocalDateTime exittime;
        String s;

        public Exit() {

            setTitle("출차");

            Container c = getContentPane();

            c.setLayout(new GridLayout(2, 2));
            ex.setBackground(Color.gray);
            back.setBackground(Color.gray);
            num.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (num.getText().equals("index_num"))
                        num.setText("");
                }
            });

            select_floor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (select_floor.getText().equals("floor"))
                        select_floor.setText("");
                }
            });

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
                    /*if (d3 > 60) {
                        charge2.setText(Long.toString(d3 / 60) + "분" + Long.toString(d3 % 60) + "초");
                    } else {
                        charge2.setText(s + "초");
                    }*/
                    int fl = Integer.parseInt(select_floor.getText());
                    int value = Integer.parseInt(num.getText());
                    if (fl == 2) {
                        value += 20;
                        cnt_graph[1]--;
                        cnt_graph[3]++;
                    } else if (fl == 3) {
                        value += 41;
                        cnt_graph[2]--;
                        cnt_graph[3]++;
                    }
                    else {
                        cnt_graph[0]--;
                        cnt_graph[3]++;
                    }
                    if (d3 > 1800) {
                        d3 -= 1800;
                        d3 = d3 / 600 * 500 + 1500;
                    }
                    else if(d3 <= 1800 || d3 > 0) {
                        d3 = 1500;
                    }
                    s = Long.toString(d3);
                    charge2.setFont(new Font("Gothic", Font.PLAIN, 15));
                    charge2.setText(db.get_Car_Num(value) + "차량의 요금은 " + s + "원 입니다");

                    db.Out_car(value);
                    parking[value - 1].setIcon(null);
                    parking[value - 1].setForeground(Color.WHITE);
                    parking[value - 1].setBackground(Color.BLACK);
                    parking[value - 1].setBorder(new LineBorder(Color.WHITE, 5));
                    parking[value - 1].setText(num.getText());
                    side.repaint();
                }

            });

            ex.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    exittime = LocalDateTime.now();
                    int select_index = Integer.parseInt(num.getText());
                    int fl = Integer.parseInt(select_floor.getText());
                    if (fl == 2) {
                        select_index += 20;
                    } else if (fl == 3) {
                        select_index += 41;
                    }
                    exitNotice.setFont(new Font("Serif", Font.BOLD, 30));
                    exitNotice.setForeground(Color.BLUE);
                    exitNotice.setText("<html><body><center>" + Integer.toString(fl) + "층 " + Integer.toString(select_index) + "번 자리에 " + exittime.getHour() + "시 " + exittime.getMinute() + "분 출차되었습니다</center></body></html>");
                    st = new showThread(exitNotice, header, 0, e.getY());
                    st.start();
                    revalidate();
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

    }
}
