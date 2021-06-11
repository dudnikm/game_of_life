package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainPanel extends JPanel implements ActionListener {

    public static int SCREEN_WIDTH = 500;
    public static int SCREEN_HEIGHT = 500;
    public static int DELAY = 150;
    public static int SCREEN_UNIT = 20;

    private Timer timer;
    private boolean running;
    private Cell[][] cells;
    private Random r;

    public MainPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        startGame();
    }

    public void startGame(){
        timer = new Timer(DELAY,this);
        cells = new Cell[SCREEN_WIDTH/SCREEN_UNIT][SCREEN_HEIGHT/SCREEN_UNIT];
        r = new Random();
        for(int i =0; i<cells.length;i++){
            for(int j = 0; j < cells[0].length;j++){
                if(r.nextInt(100) < 20) {
                    cells[i][j] = new Cell(true);
                } else {
                    cells[i][j] = new Cell(false);
                }
            }
        }
        running = true;

        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        for(int i =0; i<cells.length;i++){
            for(int j = 0; j < cells[0].length;j++){
                g.setColor(cells[i][j].getColor());
                g.fillRect(j*SCREEN_UNIT,i*SCREEN_UNIT,SCREEN_UNIT,SCREEN_UNIT);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){

        }
        repaint();
    }
}
