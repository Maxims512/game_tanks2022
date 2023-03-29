package word_of_tanks;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setTitle("World of Tanks");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(782, 806);
        add(new GameField());
        setLocation(394, 10);
        setVisible(true);
    }

    public static void main(String[] args){
        Main mv = new Main();
    }



}
