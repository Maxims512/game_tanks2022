package word_of_tanks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GameField extends JPanel implements ActionListener {
    //////////////////////////////// настройки игры |
    int umniy_bot =1;// делает бота умнее
    Boolean with_bot=true; // есть бот или нет
    int kolvo_sten=120; // количество блоков на карте

    /////////////////////////////// спавн танков |
    private int x1 = 672, y1 = 64;
    private int x2 = 64, y2 = 64;
    private int x3 = 11*32, y3 = 12*32;

    ///////////////////////////////
    int pole; // 1-рандомная карта, 2-карта Юры(52 стены)
    private int m = 0;
    private final int SIZE = 768;
    private final int DOT_SIZE = 32;
    private Boolean shots3=false;
    int t1 = 1, t2 = 1, t3=1;
    private int shotX1=x1, shotY1=y1;
    private int shotX2=x2, shotY2=y2;
    private int shotX3=x3, shotY3=y3;
    private Boolean paintRest=false;
    private int boxX1;
    private int boxY1;
    private boolean igra_idet = false;
    private final int[][] sten= new int[kolvo_sten][2];
    private final int[][] karta1=new int[kolvo_sten][2];
    private final int[][] kamni =new int[10][2];
    private final int[] sostoyan_kamni =new int[10];
    private Timer timer;
    private Boolean nach_menu=true;
    private Boolean tank1_moget_ispolz= false;
    private Boolean tank2_moget_ispolz = false;
    private Boolean tank1_rocket = false;
    private Boolean tank1_ispolz_rocket = false;
    private Boolean tank2_rocket = false;
    private Boolean tank2_ispolz_rocket = false;
    private boolean inGame3 = true;
    private boolean left1 = false, right1 = true, up1 = false, down1 = false, inGame1 = true;
    private boolean left2 = true, right2 = false, up2 = false, down2 = false, inGame2 = true;
    private String sost1 = "l", sostv1, sostvi1;
    private String sost2 = "r", sostv2, sostvi2;
    private String sost3, sostv3, sostvi3;
    String st1win = "ПЕРВЫЙ ИГРОК ПОБЕДИЛ", st2win = "ВТОРОЙ ИГРОК ПОБЕДИЛ";
    private Boolean shots1 = false, shots2 = false;
    private Boolean stroika = true, stroika1 = true, stroika2 = true;
    private Boolean box_live = false;

    Font f1 = new Font("TimesRoman", Font.BOLD, 42);
    Random random = new Random();
    private final int kolvo_cvet = random.nextInt(0, 100);
    private final int kolvo_kam = random.nextInt(0, 100);
    private final int[][] stroik_kamm = new int[kolvo_kam][2] ;
    private final int[][] stroik_cvett =new int[kolvo_cvet][2];
    private Image tank1_left, tank1_up, tank1_down, tank1_right, missile_1;
    private Image tank2_left, tank2_up, tank2_down, tank2_right, missile_2;
    private Image tank3_left, tank3_up, tank3_down, tank3_right, missile_3;
    private Image rocket_down_g, rocket_up_g, rocket_right_g, rocket_left_g;
    private Image rocket_down_y, rocket_up_y, rocket_right_y, rocket_left_y;
    private Image miss, kam2, kam1, kam0, restart1, explosion4, tank3, knopka_pole_1, knopka_pole_2;
    private Image stena, fon_graund, fon_black, icon_rocket, kamushki, flovers,knopka_with_bot,knopka_bez_bot;
    public GameField(){
        tank3=tank3_down;
        stroika_karta1();
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void stroika_karta1(){
        kamni[0][0] = 7; kamni[0][1] = 8;
        kamni[1][0] = 17; kamni[1][1] = 8;
        kamni[2][0] = 2; kamni[2][1] = 10;
        kamni[3][0] = 21; kamni[3][1] = 2;
        kamni[4][0] = 3; kamni[4][1] = 19;
        kamni[5][0] = 7; kamni[5][1] = 16;
        kamni[6][0] = 11; kamni[6][1] = 14;
        kamni[7][0] = 19; kamni[7][1] = 12;
        kamni[8][0] = 20; kamni[8][1] = 13;
        kamni[9][0] = 21; kamni[9][1] = 17;

        for (int i = 0; i < 10; i++) {
            sostoyan_kamni[i]=2;
        }

        karta1[0][0] = 21; karta1[0][1] = 1;
        karta1[1][0] = 7; karta1[1][1] = 2;
        karta1[2][0] = 	19; karta1[2][1] = 2;
        karta1[3][0] = 2; karta1[3][1] = 4;
        karta1[4][0] = 3; karta1[4][1] = 4;
        karta1[5][0] = 20; karta1[5][1] = 3;
        karta1[6][0] = 21; karta1[6][1] = 3;
        karta1[7][0] = 7; karta1[7][1] = 5;
        karta1[8][0] = 10; karta1[8][1] = 6;
        karta1[9][0] = 17; karta1[9][1] = 6;
        karta1[10][0] = 7; karta1[10][1] = 7;
        karta1[11][0] = 13; karta1[11][1] = 7;
        karta1[12][0] = 14; karta1[12][1] = 7;
        karta1[13][0] = 18; karta1[13][1] = 7;
        karta1[14][0] = 3; karta1[14][1] = 9;
        karta1[15][0] = 4; karta1[15][1] = 9;
        karta1[16][0] = 8; karta1[16][1] = 9;
        karta1[17][0] = 9; karta1[17][1] = 9;
        karta1[18][0] = 15; karta1[18][1] = 9;
        karta1[19][0] = 16; karta1[19][1] = 9;
        karta1[20][0] = 1; karta1[20][1] = 11;
        karta1[21][0] = 16; karta1[21][1] = 11;
        karta1[22][0] = 10; karta1[22][1] = 12;
        karta1[23][0] = 12; karta1[23][1] = 12;
        karta1[24][0] = 16; karta1[24][1] = 12;
        karta1[25][0] = 17; karta1[25][1] = 12;
        karta1[26][0] = 18; karta1[26][1] = 12;
        karta1[27][0] = 1; karta1[27][1] = 13;
        karta1[28][0] = 21; karta1[28][1] = 13;
        karta1[29][0] = 21; karta1[29][1] = 14;
        karta1[30][0] = 3; karta1[30][1] = 15;
        karta1[31][0] = 4; karta1[31][1] = 15;
        karta1[32][0] = 16; karta1[32][1] = 15;
        karta1[33][0] = 15; karta1[33][1] = 16;
        karta1[34][0] = 16; karta1[34][1] = 16;
        karta1[35][0] = 5; karta1[35][1] = 17;
        karta1[36][0] = 15; karta1[36][1] = 17;
        karta1[37][0] = 19; karta1[37][1] = 17;
        karta1[38][0] = 20; karta1[38][1] = 17;
        karta1[39][0] = 22; karta1[39][1] = 17;
        karta1[40][0] = 2; karta1[40][1] = 18;
        karta1[41][0] = 4; karta1[41][1] = 18;
        karta1[42][0] = 10; karta1[42][1] = 18;
        karta1[43][0] = 11; karta1[43][1] = 18;
        karta1[44][0] = 12; karta1[44][1] = 18;
        karta1[45][0] = 22; karta1[45][1] = 18;
        karta1[46][0] = 2; karta1[46][1] = 19;
        karta1[47][0] = 17; karta1[47][1] = 20;
        karta1[48][0] = 2; karta1[48][1] = 21;
        karta1[49][0] = 3; karta1[49][1] = 21;
        karta1[50][0] = 17; karta1[50][1] = 21;
        karta1[51][0] = 17; karta1[51][1] = 22;

        for (int i = 0; i < 52; i++) {
            karta1[i][0] *= 32;
            karta1[i][1] *= 32;
        }


    }
    public void chek_menu(){
        if (nach_menu){
            if(x1<SIZE/2){
                x1=SIZE/2;
            }
            if(x2>SIZE/2-32){
                x2=SIZE/2-32;
            }
            if (x1>SIZE-32){
                x1=SIZE-32;
            }
            if (x2<0){
                x2=0;
            }
            if (y1<0){
                y1=0;
            }
            if (y2<0){
                y2=0;
            }
            if (y2>SIZE-32){
                y2=SIZE-32;
            }
            if (y1>SIZE-32){
                y1=SIZE-32;
            }

            if (x1>310 && y1>150 && x1<448 && y1<288 && x2>310 && y2>150 && x2<448 && y2<288) {
                x1 = 672;
                y1 = 672;
                x2 = 64;
                y2 = 64;
                x3=11*32;
                y3=11*32;
                System.out.println("Оба танка встали на кнопку");
                nach_menu=false;
                igra_idet=true;
            }
        }
    }

    private int[][] stroik_kam() {
        if (stroika2 && igra_idet) {
            for (int i = 0; i < kolvo_kam; i++) {
                stroik_kamm[i][0] = random.nextInt(2, 23) * DOT_SIZE;
                stroik_kamm[i][1] = random.nextInt(2, 23) * DOT_SIZE;
            }
            stroika2 = false;
        }
        return stroik_kamm;
    }

    private int[][] stroik_cvet() {
        if (stroika1) {
            for (int i = 0; i < kolvo_cvet; i++) {
                stroik_cvett[i][0] = random.nextInt(2, 23) * DOT_SIZE;
                stroik_cvett[i][1] = random.nextInt(2, 23) * DOT_SIZE;
            }
            stroika1=false;
        }
        return stroik_cvett;
    }


    private int[][] stroik(){
        if (pole==2){
            kolvo_sten=52;
        }
        if (stroika && pole == 1 && igra_idet) {
            int i = 0;
            while (i < kolvo_sten) {
                int stroiX = random.nextInt(2, 22) * DOT_SIZE;
                int stroiY = random.nextInt(2, 22) * DOT_SIZE;
                if (!(stroiY == y1 && stroiX == x1) && !(stroiY == y2 && stroiX == x2) && !(stroiY == y3 && stroiX == x3)) {//переделай
                    sten[i][0] = stroiX;
                    sten[i][1] = stroiY;
                    i++;
                }
            }
            stroika = false;
        }
        if (pole==1) {
            return sten;
        } else {
            return karta1;
        }

    }

    public void sushest_pul(){ //сюда добавь про координаты камней
        for (int i = 0; i < kolvo_sten; i++) {
            if (stroik()[i][0]==shotX1 && stroik()[i][1]==shotY1){
                t1=1;
                shotX1=x1;
                shotY1=y1;
                shots1=false;
                tank1_ispolz_rocket=false;
            }

            if (stroik()[i][0]==shotX2 && stroik()[i][1]==shotY2){
                t2=1;
                shotX2=x2;
                shotY2=y2;
                shots2=false;
                tank2_ispolz_rocket=false;
            }

            if (stroik()[i][0]==shotX3 && stroik()[i][1]==shotY3){
                t3=1;
                shotX3=x3;
                shotY3=y3;
                shots3=false;
            }


        }
        for (int i = 0; i < 10; i++) {
            if (kamni[i][0]==shotX1/32 && kamni[i][1]==shotY1/32 && sostoyan_kamni[i] > 0){
                sostoyan_kamni[i]-=1;
                t1=1;
                shotX1=x1;
                shotY1=y1;
                shots1=false;
                tank1_ispolz_rocket=false;
            }

            if (kamni[i][0]==shotX2/32 && kamni[i][1]==shotY2/32 && sostoyan_kamni[i] > 0){
                sostoyan_kamni[i]-=1;
                t2=1;
                shotX2=x2;
                shotY2=y2;
                shots2=false;
                tank2_ispolz_rocket=false;
            }

            if (kamni[i][0]==shotX3/32 && kamni[i][1]==shotY3/32 && sostoyan_kamni[i] > 0){
                sostoyan_kamni[i]-=1;
                t3=1;
                shotX3=x3;
                shotY3=y3;
                shots3=false;
            }
        }
        if (!tank1_ispolz_rocket && !shots1){
            shotX1=x1;
            shotY1=y1;
        }
        if (!tank2_ispolz_rocket && !shots2){
            shotX2=x2;
            shotY2=y2;
        }
    }
    public void initGame() {
        timer = new Timer(110,this);
        timer.start();
    }

    public void loadImages(){
        ImageIcon kwb = new ImageIcon("sbotomknopka.png");
        knopka_with_bot = kwb.getImage();

        ImageIcon kwob = new ImageIcon("bezbotknopka.png");
        knopka_bez_bot = kwob.getImage();

        ImageIcon knp1 = new ImageIcon("pole1knopka.png");
        knopka_pole_1 = knp1.getImage();

        ImageIcon knp2 = new ImageIcon("pole2knopka.png");
        knopka_pole_2 = knp2.getImage();

        ImageIcon kam = new ImageIcon("grass_1b.png");
        kamushki= kam.getImage();

        ImageIcon flv = new ImageIcon("grass_1c.png");
        flovers= flv.getImage();

        ImageIcon exp4 = new ImageIcon("explosion_4.png");
        explosion4 = exp4.getImage();

        ImageIcon rest1 = new ImageIcon("Play_wait.png");
        restart1 = rest1.getImage();

        ImageIcon kam_2 = new ImageIcon("rock_a_1.png");
        kam2 = kam_2.getImage();

        ImageIcon kam_1 = new ImageIcon("rock_a_2.png");
        kam1 = kam_1.getImage();

        ImageIcon kam_0 = new ImageIcon("rock_a_3.png");
        kam0 = kam_0.getImage();

        ////////////////////////////////////////////////////////

        ImageIcon rok_d_g = new ImageIcon("rocket_g_down.png");
        rocket_down_g = rok_d_g.getImage();

        ImageIcon rok_u_g = new ImageIcon("rocket_g_up.png");
        rocket_up_g = rok_u_g.getImage();

        ImageIcon rok_l_g = new ImageIcon("rocket_g_left.png");
        rocket_left_g = rok_l_g.getImage();

        ImageIcon rok_r_g = new ImageIcon("rocket_g_right.png");
        rocket_right_g = rok_r_g.getImage();

        ImageIcon rok_d_y = new ImageIcon("rocket_y_down.png");
        rocket_down_y = rok_d_y.getImage();

        ImageIcon rok_u_y = new ImageIcon("rocket_y_up.png");
        rocket_up_y = rok_u_y.getImage();

        ImageIcon rok_l_y = new ImageIcon("rocket_y_left.png");
        rocket_left_y = rok_l_y.getImage();

        ImageIcon rok_r_y = new ImageIcon("rocket_y_right.png");
        rocket_right_y = rok_r_y.getImage();

        ///////////////////////////////////////////////////////////////

        ImageIcon fon_r = new ImageIcon("super_rocket.png");
        icon_rocket = fon_r.getImage();

        ImageIcon fon_b = new ImageIcon("Black_768x768.png");
        fon_black = fon_b.getImage();

        ImageIcon fon_g = new ImageIcon("trava.png");
        fon_graund = fon_g.getImage();

        ImageIcon sten1 = new ImageIcon("brick_32.png");
        stena = sten1.getImage();
        ////////////////////////////////////////////////////////////
        ImageIcon iia1 = new ImageIcon("missile_2_g.png");
        missile_1 = iia1.getImage();

        ImageIcon m1 = new ImageIcon("missile_2_g.png");
        miss = m1.getImage();

        ImageIcon m3 = new ImageIcon("missile_2_e.png");
        missile_3 = m3.getImage();

        ImageIcon iid_down1 = new ImageIcon("sp_IDLE_g_down_32.png");
        tank1_down = iid_down1.getImage();

        ImageIcon iid_left1 = new ImageIcon("sp_IDLE_g_left_32.png");
        tank1_left = iid_left1.getImage();

        ImageIcon iid_right1 = new ImageIcon("sp_IDLE_g_right_32.png");
        tank1_right = iid_right1.getImage();

        ImageIcon iid_up1 = new ImageIcon("sp_IDLE_g_up_32.png");
        tank1_up = iid_up1.getImage();

        /////////////////////////////////////////////////////////////////

        ImageIcon t3d = new ImageIcon("sE_IDLE_down_32.png");
        tank3_down =t3d.getImage();

        ImageIcon t3l = new ImageIcon("sE_IDLE_left_32.png");
        tank3_left = t3l.getImage();

        ImageIcon t3r = new ImageIcon("sE_IDLE_right_32.png");
        tank3_right = t3r.getImage();

        ImageIcon t3u = new ImageIcon("sE_IDLE_up_32.png");
        tank3_up = t3u.getImage();

        /////////////////////////////////////////////////////////////

        ImageIcon iia2 = new ImageIcon("missile_2_y.png");
        missile_2 = iia2.getImage();

        ImageIcon iid_down2 = new ImageIcon("sp_IDLE_y_down_32.png");
        tank2_down = iid_down2.getImage();

        ImageIcon iid_left2 = new ImageIcon("sp_IDLE_y_left_32.png");
        tank2_left = iid_left2.getImage();

        ImageIcon iid_right2 = new ImageIcon("sp_IDLE_y_right_32.png");
        tank2_right = iid_right2.getImage();

        ImageIcon iid_up2 = new ImageIcon("sp_IDLE_y_up_32.png");
        tank2_up = iid_up2.getImage();
    }



    public void sost_pole(){
        if (nach_menu) {
            if (x2>95+32 && x2<150+32 && y2<160-96) { //тут координаты кнопки с ботом
                with_bot=false;
            }
            if (x2>159+32 && x2<224+32 && y2<160-96) { //тут координаты кнопки без бота
                with_bot=true;
            }
            if (x1>575 && y1<64 && x1< 640) { // тут координаты кнопки поле 1
                pole=1;
            }
            if (x1>511 && x1<576 && y1<64) { // тут координаты кнопки поле 2
                pole=2;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        ////////////////////// РИСОВКА ПОЛЯ ///////////////////////




        if (igra_idet){
            g.drawImage(fon_graund, 0, 0, this);
        } else {
            g.drawImage(fon_black,0,0,this);
        }




        if (igra_idet) {
            for (int i = 0; i < SIZE; i += 32) {
                g.drawImage(stena, 0, i, this);
                g.drawImage(stena, SIZE - 32, i, this);
                g.drawImage(stena, i, 0, this);
                g.drawImage(stena, i, SIZE - 32, this);
            }
        }

        if (igra_idet) {
            for (int i = 0; i < kolvo_cvet; i++) {
                g.drawImage(flovers, stroik_cvet()[i][0], stroik_cvet()[i][1], this);
            }
        }

        if (igra_idet) {
            for (int i = 0; i < kolvo_kam; i++) {
                g.drawImage(kamushki, stroik_kam()[i][0], stroik_kam()[i][1], this);
            }
        }



        if (igra_idet) {
            for (int i = 0; i < kolvo_sten; i++) {
                g.drawImage(stena, stroik()[i][0], stroik()[i][1], this);
            }
        }



        if (igra_idet) {
            for (int i = 0; i < 10; i++) {
                if (sostoyan_kamni[i] == 2) {
                    g.drawImage(kam2, kamni[i][0] * 32, kamni[i][1] * 32, this);
                }

                if (sostoyan_kamni[i] == 1) {
                    g.drawImage(kam1, kamni[i][0] * 32, kamni[i][1] * 32, this);
                }

                if (sostoyan_kamni[i] == 0) {
                    g.drawImage(kam0, kamni[i][0] * 32, kamni[i][1] * 32, this);
                }
            }
        }

        if (paintRest){
            g.drawImage(restart1, 320, 5*32, this);
        }

        if (nach_menu){
            System.out.println("нач меню");
            if (with_bot){
                g.drawImage(knopka_with_bot,128,0,this);//поменяй тут координаты
            } else {
                g.drawImage(knopka_bez_bot,128,0,this);
            }
            if (pole == 1){
                g.drawImage(knopka_pole_1, SIZE-256,0, this);
            } else {
                g.drawImage(knopka_pole_2,SIZE-256,0,this);
            }

        }


        /////////////////// КОНЕЦ РИСОВКИ ПОЛЯ ////////////////////


        if (inGame3 && igra_idet && with_bot){
            g.drawImage(tank3, x3, y3, this);
        }

        if (inGame3 && igra_idet && with_bot && shots3){
            g.drawImage(missile_3, shotX3, shotY3, this);
        }


        if (igra_idet) {
            if (tank1_rocket) {
                g.drawImage(icon_rocket, 736,304, this);
            }
            if (tank2_rocket) {
                g.drawImage(icon_rocket, 0, 304, this);

            }
        }

        if (igra_idet){
            if (!box_live){
                int k = 0;
                int boxX = random.nextInt(1, 23) * DOT_SIZE;
                int boxY = random.nextInt(1, 23) * DOT_SIZE;
                for (int i = 0; i < kolvo_sten; i++) {
                    if (!(boxX ==stroik()[i][0] && boxY ==stroik()[i][1])){
                        k++;
                    }

                }
                if (k==kolvo_sten){
                    boxX1= boxX;
                    boxY1= boxY;
                    box_live=true;
                }
            }
        }

        if (box_live && igra_idet){
            g.drawImage(icon_rocket, boxX1, boxY1, this);
        }



        if (inGame1) {
            if (!shots1){
                sostv1=sostvi1;
            }

            switch (sost1){
                case "l": g.drawImage(tank1_left, x1, y1, this);
                    break;
                case "r": g.drawImage(tank1_right, x1, y1, this);
                    break;
                case "u": g.drawImage(tank1_up, x1, y1, this);
                    break;
                case "d": g.drawImage(tank1_down, x1, y1, this);
                    break;
            }

            if (shots1 && (sostv1 == "d") && !tank1_ispolz_rocket && igra_idet) {
                if (shotY1 < SIZE-64) {
                    shotY1 += DOT_SIZE;
                    g.drawImage(missile_1, shotX1, shotY1, this);
                } else {
                    shots1 = false;
                }
            }

            if (shots1 && (sostv1 == "r") && !tank1_ispolz_rocket && igra_idet) {
                if (shotX1 < SIZE-64) {
                    shotX1 += DOT_SIZE;
                    g.drawImage(missile_1, shotX1, shotY1, this);
                } else {
                    shots1 = false;
                }
            }

            if (shots1 && (sostv1 == "u") && !tank1_ispolz_rocket && igra_idet) {
                if (shotY1 > 32) {
                    shotY1 -= DOT_SIZE;
                    g.drawImage(missile_1, shotX1, shotY1, this);
                } else {
                    shots1 = false;
                }
            }

            if (shots1 && (sostv1 == "l") && !tank1_ispolz_rocket && igra_idet) {
                if (shotX1 > 32) {
                    shotX1 -= DOT_SIZE;
                    g.drawImage(missile_1, shotX1, shotY1, this);
                } else {
                    shots1 = false;
                }
            }

        } else {
            tank1_rocket=false;
            tank2_rocket=false;
            igra_idet=false;
            stroika1=true;
            stroika2=true;
            stroika=true;
            g.setFont(f1);
            g.setColor(Color.orange);
            g.drawString(st2win, 100, SIZE / 2);
        }


        if (igra_idet && tank1_ispolz_rocket) {
            if (t1==1){
                shotX1=x1;
                shotY1=y1;
                t1=0;
            }
            g.drawImage(missile_1, shotX1, shotY1, this);
        }



        //////////////////////////////////////////////////

        if (inGame2) {
            if (!shots2){
                sostv2=sostvi2;
            }

            switch (sost2){
                case "l": g.drawImage(tank2_left, x2, y2, this);
                    break;
                case "r": g.drawImage(tank2_right, x2, y2, this);
                    break;
                case "u": g.drawImage(tank2_up, x2, y2, this);
                    break;
                case "d": g.drawImage(tank2_down, x2, y2, this);
                    break;
            }

            if (shots2 && (sostv2 == "d") && !tank2_ispolz_rocket && igra_idet) {
                if (shotY2 < SIZE-64) {
                    shotY2 += DOT_SIZE;
                    g.drawImage(missile_2, shotX2, shotY2, this);
                } else {
                    shots2 = false;
                }
            }

            if (shots2 && (sostv2 == "r")  && !tank2_ispolz_rocket && igra_idet) {
                if (shotX2 < SIZE-64) {
                    shotX2 += DOT_SIZE;
                    g.drawImage(missile_2, shotX2, shotY2, this);
                } else {
                    shots2 = false;
                }
            }

            if (shots2 && (sostv2 == "u")  && !tank2_ispolz_rocket && igra_idet) {
                if (shotY2 > 32) {
                    shotY2 -= DOT_SIZE;
                    g.drawImage(missile_2, shotX2, shotY2, this);
                } else {
                    shots2 = false;
                }
            }

            if (shots2 && (sostv2 == "l")  && !tank2_ispolz_rocket && igra_idet) {
                if (shotX2 > 32) {
                    shotX2 -= DOT_SIZE;
                    g.drawImage(missile_2, shotX2, shotY2, this);
                } else {
                    shots2 = false;
                }
            }


        } else {
            tank2_rocket=false;
            tank1_rocket=false;
            igra_idet=false;
            stroika1=true;
            stroika2=true;
            stroika=true;
            g.setColor(Color.green);
            g.setFont(f1);
            g.drawString(st1win, 100, SIZE / 2);

        }
        if (igra_idet && tank2_ispolz_rocket ) {
            if (t2==1){
                shotX2=x2;
                shotY2=y2;
                t2=0;
            }
            g.drawImage(missile_2, shotX2, shotY2, this);
        }

        if (shotY1==y2 && shotX1==x2 && igra_idet){
            g.drawImage(explosion4, x2, y2, this);
        }
        if (shotY2==y1 && shotX2==x1 && igra_idet){
            g.drawImage(explosion4, x1, y1, this);
        }
        if (shotY3==y1 && shotX3==x1 && igra_idet && inGame3 && with_bot){
            g.drawImage(explosion4, x1, y1, this);
        }
        if (shotY3==y2 && shotX3==x2 && igra_idet && inGame3 && with_bot){
            g.drawImage(explosion4, x2, y2, this);
        }
        if (shotY1==y3 && shotX1==x3 && igra_idet && with_bot && inGame3){
            g.drawImage(explosion4, x3, y3, this);
        }
        if (shotY2==y3 && shotX2==x3 && igra_idet && with_bot && inGame3){
            g.drawImage(explosion4, x3, y3, this);
        }



    }

    public void move_1() {
        if (!tank1_ispolz_rocket) {
            if (left1) {
                x1 -= DOT_SIZE;
            }
            if (right1) {
                x1 += DOT_SIZE;
            }
            if (up1) {
                y1 -= DOT_SIZE;
            }
            if (down1) {
                y1 += DOT_SIZE;
            }
        }

    }

    public void move_2(){
        if (!tank2_ispolz_rocket) {
            if (left2) {
                x2 -= DOT_SIZE;
            }
            if (right2) {
                x2 += DOT_SIZE;
            }
            if (up2) {
                y2 -= DOT_SIZE;
            }
            if (down2) {
                y2 += DOT_SIZE;
            }
        }
    }
    public Boolean sushestv_stena(String l) { // рядом стена или танк
        for (int i = 0; i < kolvo_sten; i++) {
            switch (l) {
                case "r":
                    if ((x3 == stroik()[i][0] - 32 && y3 == stroik()[i][1]) || (x3 == x1 - 32 && y3 == y1) || (x3 == x2 - 32 && y3 == y2) || (x3 == 704)) { //проверка справа от 3 танк
                        return true;
                    }
                    break;
                case "l":
                    if ((x3 == stroik()[i][0] + 32 && y3 == stroik()[i][1]) || (x3 == x1 + 32 && y3 == y1) || (x3 == x2 + 32 && y3 == y2) || (x3 == DOT_SIZE)) { //проверка слева 2 танк
                        return true;
                    }
                    break;
                case "u":
                    if ((x3 == stroik()[i][0] && y3 == stroik()[i][1] + 32) || (x3 == x1 && y3 == y1 + 32) || (x3 == x2 && y3 == y2 + 32) || (y3 == 32)) { //проверка сверху 2 танк
                        return true;
                    }
                    break;
                case "d":
                    if ((x3 == stroik()[i][0] && y3 == stroik()[i][1] - 32) || (x3 == x1 && y3 == y1 - 32) || (x3 == x2 && y3 == y2 - 32) || (y3 == 704)) { //проверка снизу 2 танк
                        return true;
                    }
                    break;
            }
            if (i < 10) {
                switch (l) {
                    case "r":
                        if (x3 / 32 == kamni[i][0] - 1 && y3 / 32 == kamni[i][1] && sostoyan_kamni[i] > 0) { //проверка справа 2 танк
                            return true;
                        }
                        break;
                    case "l":
                        if  (x3 / 32 == kamni[i][0] + 1 && y3 / 32 == kamni[i][1] && sostoyan_kamni[i] > 0) { //проверка слева 2 танк
                            return true;
                        }
                        break;
                    case "u":
                        if (x3 / 32 == kamni[i][0] && y3 / 32 == kamni[i][1] + 1 && sostoyan_kamni[i] > 0) { //проверка сверху 2 танк
                            return true;
                        }
                        break;
                    case "d":
                        if (x3 / 32 == kamni[i][0] && y3 / 32 == kamni[i][1] - 1 && sostoyan_kamni[i] > 0) { //проверка снизу 2 танк
                            return true;
                        }
                        break;
                }
            }
        }
        return false;
    }

    public int rast_1(){
        return (x1-x3)*(x1-x3)+(y1-y3)*(y1-y3);
    }

    public int rast_2(){
        return (x3-x2)*(x3-x2)+(y3-y2)*(y3-y2);
    }

    public void move_3() {
        //////////////////////////// управление картинкой 3 танка

        if (sost3 == "d") {
            tank3 = tank3_down;
        }
        if (sost3 == "l") {
            tank3 = tank3_left;
        }
        if (sost3 == "r") {
            tank3 = tank3_right;
        }
        if (sost3 == "u") {
            tank3 = tank3_up;
        }

        ////////////////////////////// далее идет управление координатой 3 танка

        if (rast_1() < rast_2() && igra_idet && inGame3) { ////////////////////////////////////// ТАНК 1 БЛИЖЕЫ
            if ((Math.abs(x1 - x3) > Math.abs(y1 - y3)) && x1 > x3) { // танк 1 справа
                if (!sushestv_stena("r")) {
                    sost3 = "r";
                    x3 += DOT_SIZE;
                } else if (!sushestv_stena("d") && umniy_bot>0) {
                    sost3 = "d";
                    y3 += DOT_SIZE;
                } else if (!sushestv_stena("l") && umniy_bot>1) {
                    sost3 = "l";
                    x3 -= DOT_SIZE;
                }

            } else if ((Math.abs(x1 - x3) > Math.abs(y1 - y3)) && x1 < x3) { // танк 1 слева
                if (!sushestv_stena("l")) {
                    sost3 = "l";
                    x3 -= DOT_SIZE;
                } else if (!sushestv_stena("u") && umniy_bot>0) {
                    sost3 = "u";
                    y3 -= DOT_SIZE;
                } else if (!sushestv_stena("r") && umniy_bot>1) {
                    sost3 = "r";
                    x3 += DOT_SIZE;
                }

            } else if ((Math.abs(x1 - x3) < Math.abs(y1 - y3)) && y3 > y1) { // танк 1 сверху
                if (!sushestv_stena("u")) {
                    sost3 = "u";
                    y3 -= DOT_SIZE;
                } else if (!sushestv_stena("r") && umniy_bot>0) {
                    sost3 = "r";
                    x3 += DOT_SIZE;
                } else if (!sushestv_stena("d") && umniy_bot>1) {
                    sost3 = "d";
                    y3 += DOT_SIZE;
                }

            } else if ((Math.abs(x1 - x3) < Math.abs(y1 - y3)) && y3 < y1) { // танк 1 снизу
                if (!sushestv_stena("d")) {
                    sost3 = "d";
                    y3 += DOT_SIZE;
                } else if (!sushestv_stena("l") && umniy_bot > 0) {
                    sost3 = "l";
                    x3 -= DOT_SIZE;
                } else if (!sushestv_stena("u") && umniy_bot > 1) {
                    sost3 = "u";
                    y3 -= DOT_SIZE;
                }
            }

        } else { ///////////////////////// проверка для 2 танка
            if ((Math.abs(x2 - x3) > Math.abs(y2 - y3)) && x2 > x3 && inGame3) { // танк 1 справа
                if (!sushestv_stena("r")) {
                    sost3 = "r";
                    x3 += DOT_SIZE;
                } else if (!sushestv_stena("d") && umniy_bot>0) {
                    sost3 = "d";
                    y3 += DOT_SIZE;
                } else if (!sushestv_stena("l") && umniy_bot>1) {
                    sost3 = "l";
                    x3 -= DOT_SIZE;
                }

            } else if ((Math.abs(x2 - x3) > Math.abs(y2 - y3)) && x2 < x3) { // танк 1 слева
                if (!sushestv_stena("l")) {
                    sost3 = "l";
                    x3 -= DOT_SIZE;
                } else if (!sushestv_stena("u") && umniy_bot>0) {
                    sost3 = "u";
                    y3 -= DOT_SIZE;
                } else if (!sushestv_stena("r") && umniy_bot>1) {
                    sost3 = "r";
                    x3 += DOT_SIZE;
                }

            } else if ((Math.abs(x2 - x3) < Math.abs(y2 - y3)) && y3 > y2) { // танк 1 сверху
                if (!sushestv_stena("u")) {
                    sost3 = "u";
                    y3 -= DOT_SIZE;
                } else if (!sushestv_stena("r") && umniy_bot>0) {
                    sost3 = "r";
                    x3 += DOT_SIZE;
                } else if (!sushestv_stena("d") && umniy_bot>1) {
                    sost3 = "d";
                    y3 += DOT_SIZE;
                }

            } else if ((Math.abs(x2 - x3) < Math.abs(y2 - y3)) && y3 < y2) { // танк 1 снизу
                if (!sushestv_stena("d")) {
                    sost3 = "d";
                    y3 += DOT_SIZE;
                } else if (!sushestv_stena("l") && umniy_bot>0) {
                    sost3 = "l";
                    x3 -= DOT_SIZE;
                } else if (!sushestv_stena("u") && umniy_bot>1) {
                    sost3 = "u";
                    y3 -= DOT_SIZE;
                }
            }

        }
        if (!shots3){
            shotX3=x3;
            shotY3=y3;
        }
    }



    public void checkCollisions1() {
        for (int i = 0; i < kolvo_sten; i++){
            if ((x1 == stroik()[i][0] - 32 && y1==stroik()[i][1]) || (x1 == stroik()[i][0] - 32 && y1==stroik()[i][1]) ||
                    (x1 == x2 - 32 && y1==y2) || (x1 == x3 - 32 && y1==y3  && with_bot && inGame3) || (x1==704)) { //проверка справа 1 танк
                right1 = false;
            }

            if ((x1 == stroik()[i][0] + 32 && y1==stroik()[i][1]) || (x1 == x2 + 32 && y1==y2) ||
                    (x1 == x3 + 32 && y1==y3  && with_bot && inGame3) || (x1==DOT_SIZE)){ //проверка слева 1 танк
                left1 = false;
            }

            if ((x1 == stroik()[i][0] && y1==stroik()[i][1]+32) || (x1 == x2 && y1==y2+32) ||
                    (x1 == x3 && y1==y3+32  && with_bot && inGame3) || (y1==32)) { //проверка сверху 1 танк
                up1 = false;
            }

            if ((x1 == stroik()[i][0] && y1==stroik()[i][1]-32) || (x1 == x2 && y1==y2-32) ||
                    (x1 == x3 && y1==y3-32  && with_bot && inGame3) || (y1==704)){ //проверка снизу 1 танк
                down1 = false;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (x1/32 == kamni[i][0] - 1 && y1/32==kamni[i][1] && sostoyan_kamni[i]>0){
                right1 = false;
            }
            if (x1/32 == kamni[i][0] + 1 && y1/32==kamni[i][1] && sostoyan_kamni[i]>0){
                left1=false;
            }
            if (x1/32 == kamni[i][0] && y1/32==kamni[i][1]+1 && sostoyan_kamni[i]>0){
                up1 = false;
            }
            if (x1/32 == kamni[i][0] && y1/32==kamni[i][1]-1 && sostoyan_kamni[i]>0){
                down1=false;
            }

            if (x2/32 == kamni[i][0] - 1 && y2/32 == kamni[i][1] && sostoyan_kamni[i]>0){
                right2 = false;
            }
            if (x2/32 == kamni[i][0] + 1 && y2/32 == kamni[i][1] && sostoyan_kamni[i]>0){
                left2 = false;
            }
            if (x2/32 == kamni[i][0] && y2/32 == kamni[i][1]+1 && sostoyan_kamni[i]>0){
                up2 = false;
            }
            if (x2/32 == kamni[i][0] && y2/32 == kamni[i][1]-1 && sostoyan_kamni[i]>0){
                down2=false;
            }

        }

        if (x1 == shotX2 && y1 == shotY2 && igra_idet){
            inGame1=false;
            tank2_ispolz_rocket=false;
            x1 = 672; y1 = 672;
            x2 = 64; y2 = 64;
        }
        if (x1 == shotX3 && y1 == shotY3 && inGame3 && with_bot){
            inGame1=false;
            tank2_ispolz_rocket=false;
            x1 = 672; y1 = 672;
            x2 = 64; y2 = 64;
        }

    }

    public void checkCollisions2(){
        for (int i = 0; i < kolvo_sten; i++){
            if ((x2 == stroik()[i][0] - 32 && y2==stroik()[i][1]) || (x2 == x1 - 32 && y2==y1) ||
                    (x2 == x3 - 32 && y2==y3 && with_bot && inGame3) || (x2==704)) { //проверка справа 2 танк
                right2 = false;
            }

            if ((x2 == stroik()[i][0] + 32 && y2==stroik()[i][1]) || (x2 == x1 + 32 && y2==y1) ||
                    (x2 == x3 + 32 && y2==y3 && with_bot && inGame3) || (x2==DOT_SIZE)){ //проверка слева 2 танк
                left2 = false;
            }

            if ((x2 == stroik()[i][0] && y2==stroik()[i][1]+32) || (x2 == x1 && y2==y1+32)  ||
                    (x2 == x3 && y2==y3+32  && with_bot && inGame3) || (y2==32)) { //проверка сверху 2 танк
                up2 = false;
            }

            if ((x2 == stroik()[i][0] && y2==stroik()[i][1]-32) || (x2 == x1 && y2==y1-32) ||
                    (x2 == x3 && y2==y3-32 && with_bot && inGame3) || (y2==704)){ //проверка снизу 2 танк
                down2 = false;
            }
        }

        if (x2 == shotX1 && y2 == shotY1 && igra_idet){
            inGame2=false;
            tank1_ispolz_rocket=false;
            x1 = 672; y1 = 672;
            x2 = 64; y2 = 64;

        }
        if (x2 == shotX3 && y2 == shotY3 && inGame3 && with_bot){
            inGame2=false;
            tank1_ispolz_rocket=false;
            x1 = 672; y1 = 672;
            x2 = 64; y2 = 64;
        }

        if ((x3 == shotX1 && y3 == shotY1) || (x3 == shotX2 && y3 == shotY2)){
            tank1_ispolz_rocket=false;
            tank2_ispolz_rocket=false;
            inGame3=false;
        }

    }






    public void all_of_rocket(){
        if (box_live && !tank1_rocket) {
            if (boxX1 == x1 && boxY1 == y1) {
                tank1_rocket = true;
                box_live=false;
            }
        }
        if (box_live && !tank2_rocket) {
            if (boxX1 == x2 && boxY1 == y2){
                tank2_rocket=true;
                box_live=false;
            }
        }


        ////////////////////////////////////////

        if (igra_idet && tank1_ispolz_rocket){
            tank1_rocket=false;
            switch (sost1) {
                case "l" -> {
                    missile_1 = rocket_left_g;
                    shotX1 -= DOT_SIZE;
                }
                case "r" -> {
                    missile_1 = rocket_right_g;
                    shotX1 += DOT_SIZE;
                }
                case "u" -> {
                    missile_1 = rocket_up_g;
                    shotY1 -= DOT_SIZE;
                }
                case "d" -> {
                    missile_1 = rocket_down_g;
                    shotY1 += DOT_SIZE;
                }
            }
        }

        if (igra_idet && !tank1_ispolz_rocket){
            missile_1=miss;
        }

        ///////////////////////////////////

        if (igra_idet && tank2_ispolz_rocket){
            tank2_rocket=false;
            switch (sost2) {
                case "l" -> {
                    missile_2 = rocket_left_y;
                    shotX2 -= DOT_SIZE;
                }
                case "r" -> {
                    missile_2 = rocket_right_y;
                    shotX2 += DOT_SIZE;
                }
                case "u" -> {
                    missile_2 = rocket_up_y;
                    shotY2 -= DOT_SIZE;
                }
                case "d" -> {
                    missile_2 = rocket_down_y;
                    shotY2 += DOT_SIZE;
                }
            }
        }

        if (igra_idet && !tank2_ispolz_rocket ){
            missile_2=miss;
        }

        tank1_moget_ispolz= tank1_rocket;
        tank2_moget_ispolz= tank2_rocket;

        if (shotX1>SIZE-64 || shotY1>SIZE-64 || shotX1<32 || shotY1<32){
            tank1_ispolz_rocket=false;
            shotX1=x1;
            shotY1=y1;
        }

    }

    public void all_of_kamni() {
        for (int i = 0; i < 10; i++) {
            if ((shotX1/32 == kamni[i][0] && shotY1/32 == kamni[i][1] && (sostoyan_kamni[i] > 0)) ||
                    (shotX2/32 == kamni[i][0] && shotY2/32 == kamni[i][1]) && (sostoyan_kamni[i] > 0)) {
                sostoyan_kamni[i] -= 1;
            }
        }
    }

    public void shotTank3(){
        if (shots3 && (sostv3 == "d")) {
            if (t3==1){
                shotX3=x3;
                shotY3=y3;
                t3=0;
            }
            if (shotY3 < SIZE-64) {
                shotY3 += DOT_SIZE;
            } else {
                shots3 = false;
            }
        }
        if (shots3 && (sostv3 == "r")) {
            if (t3==1){
                shotX3=x3;
                shotY3=y3;
                t3=0;
            }
            if (shotX3 < SIZE-64) {
                shotX3 += DOT_SIZE;
            } else {
                shots3 = false;
            }
        }
        if (shots3 && (sostv3 == "u")) {
            if (t3==1){
                shotX3=x3;
                shotY3=y3;
                t3=0;
            }
            if (shotY3 > 32) {
                shotY3 -= DOT_SIZE;
            } else {
                shots3 = false;
            }
        }
        if (shots3 && (sostv3 == "l")) {
            System.out.println("Выстрел влево");
            if (t3==1){
                shotX3=x3;
                shotY3=y3;
                t3=0;
            }
            if (shotX3 > 32) {
                shotX3 -= DOT_SIZE;
            } else {
                shots3 = false;
            }
        }

    }
    public void shot_bot(){ //доделай для каждого игрока отдельно x3==x2: y2>y3 и y2<y3
        if (x3==x2 || x3==x1){
            if ((y1>y3 || y2>y3) && !shots3){
                sostv3="d";
                sost3="d";
                shots3=true;
                t3=1;
            }
            if ((y1<y3 || y2<y3) && !shots3){
                shotTank3();
                sostv3="u";
                sost3="u";
                shots3=true;
                t3=1;
            }
        }

        else if (y3==y2 || y3==y1){
            if ((x1>x3 || x2>x3) && !shots3){
                sostv3="r";
                sost3="r";
                shots3=true;
                t3=1;
            }
            if ((x1<x3 || x2<x3) && !shots3){
                sostv3="l";
                sost3="l";
                shots3=true;
                t3=1;
            }
        }
    }

    public void chekreturn(){
        if (!igra_idet){
            m=1;
            if (x1<0){
                x1=0;
            }
            if (x2<0){
                x2=0;
            }
            if (y1<0){
                y1=0;
            }
            if (y2<0){
                y2=0;
            }
            if (y2>SIZE-32){
                y2=SIZE-32;
            }
            if (y1>SIZE-32){
                y1=SIZE-32;
            }
            if (x2>SIZE-32){
                x2=SIZE-32;
            }
            if (x1>SIZE-32){
                x1=SIZE-32;
            }

            paintRest=true;

            if (((x1>310 && y1>150 && x1<448 && y1<288) || (x2>310 && y2>150 && x2<448 && y2<288)) && !nach_menu){ // меню ретурна
                nach_menu=false;
                igra_idet=true;
                inGame1=true;
                inGame2=true;
                if (with_bot) {
                    x3 = 11 * 32;
                    y3 = 12 * 32;
                    inGame3=true;
                }
                x1 = 672;
                y1 = 672;
                x2 = 64;
                y2 = 64;
            }
        } else {
            paintRest=false;
            if (m==1) {
                for (int i = 0; i < 10; i++) {
                    sostoyan_kamni[i] = 2;
                }
                m=0;
            }



        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (igra_idet) {
            checkCollisions1();
            checkCollisions2();
        } else {
            chek_menu();
        }
        sost_pole();
        move_1();
        move_2();
        if (with_bot && inGame3) {
            move_3();
            shot_bot();
            shotTank3();
        }
        right1 = false;
        up1 = false;
        down1 = false;
        left1 = false;
        right2 = false;
        up2 = false;
        down2 = false;
        left2 = false;
        sushest_pul();
        all_of_rocket();
        all_of_kamni();
        chekreturn();
        repaint();
    }



    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && ! right1){
                left1 = true;
                up1 =false;
                down1 = false;
                sost1 = "l";
                sostvi1 = "l";
            }

            if (key == KeyEvent.VK_RIGHT && ! left1){
                right1 = true;
                up1 =false;
                down1 = false;
                sost1 = "r";
                sostvi1 = "r";
            }

            if (key == KeyEvent.VK_UP && ! down1){
                up1 = true;
                left1 = false;
                right1 = false;
                sost1 = "u";
                sostvi1 = "u";

            }

            if (key == KeyEvent.VK_DOWN && ! up1){
                down1 = true;
                right1 =false;
                left1 = false;
                sost1 = "d";
                sostvi1 = "d";

            }
            if (key == KeyEvent.VK_SPACE && !shots1 && !tank1_ispolz_rocket){
                shots1 = true;
                shotY1 = y1;
                shotX1 = x1;
            }

            //////////////////////////////////////////////////////

            if (key == KeyEvent.VK_A && ! right2){
                left2 = true;
                up2 =false;
                down2 = false;
                sost2 = "l";
                sostvi2 = "l";
            }

            if (key == KeyEvent.VK_D && ! left2){
                right2 = true;
                up2 =false;
                down2 = false;
                sost2 = "r";
                sostvi2 = "r";
            }

            if (key == KeyEvent.VK_W && ! down2){
                up2 = true;
                left2 = false;
                right2 = false;
                sost2 = "u";
                sostvi2 = "u";

            }

            if (key == KeyEvent.VK_S && ! up2){
                down2 = true;
                right2 =false;
                left2 = false;
                sost2 = "d";
                sostvi2 = "d";

            }

            if (key == KeyEvent.VK_SHIFT && !shots2 && !tank2_ispolz_rocket){
                shots2 = true;
                shotY2 = y2;
                shotX2 = x2;
            }

            if (key == KeyEvent.VK_Z) {
                if (tank2_moget_ispolz) {
                    tank2_ispolz_rocket = true;
                }
            }

            if (key == KeyEvent.VK_ENTER) {
                if (tank1_moget_ispolz) {
                    tank1_ispolz_rocket = true;
                }
            }

        }
    }
}
