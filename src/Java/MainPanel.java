package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class MainPanel extends JPanel implements ActionListener {

    public static int SCREEN_WIDTH = 500;
    public static int SCREEN_HEIGHT = 500;
    public static int DELAY = 150;
    public static int SCREEN_UNIT = 10;

    private Timer timer;
    private boolean running;
    private Cell[][] cells;
    private Random r;

    public MainPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        this.addKeyListener(new MyKeyAdapter());

        startGame();
    }

    public void startGame(){
        timer = new Timer(DELAY,this);
        cells = new Cell[SCREEN_WIDTH/SCREEN_UNIT][SCREEN_HEIGHT/SCREEN_UNIT];
        r = new Random();
        for(int i =0; i<cells.length;i++){
            for(int j = 0; j < cells[0].length;j++){
                if(r.nextInt(100) < 10) {
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

        //Draw grid

        for(int i =0; i<cells.length;i++){
            for(int j = 0; j < cells[0].length;j++){
                g.setColor(cells[i][j].getColor());
                g.fillRect(j*SCREEN_UNIT,i*SCREEN_UNIT,SCREEN_UNIT-1,SCREEN_UNIT-1);
            }
        }
    }

    public void restart(){
        for(int i =0; i<cells.length;i++){
            for(int j = 0; j < cells[0].length;j++){
                if(r.nextInt(100) < 10) {
                    cells[i][j] = new Cell(true);
                } else {
                    cells[i][j] = new Cell(false);
                }
            }
        }
    }

    public void nextGeneration(){
        int aliveNeighbours;
        for(int i =0; i<cells.length;i++){
            for(int j = 0; j < cells[0].length;j++){
                aliveNeighbours = 0;
                for(int k = -1; k<2;k++){
                    if(i-1 >= 0 && j+k >= 0 && j+k < SCREEN_WIDTH/SCREEN_UNIT)
                        if(cells[i-1][j+k].getColor() == Color.white) {
                            aliveNeighbours++;
                        }
                    if(i+1 < SCREEN_HEIGHT/SCREEN_UNIT && j+k >= 0 && j+k < SCREEN_WIDTH/SCREEN_UNIT)
                        if(cells[i+1][j+k].getColor() == Color.white) {
                            aliveNeighbours++;
                        }
                }
                if(j-1 >= 0 && j+1 < SCREEN_WIDTH/SCREEN_UNIT) {
                    if (cells[i][j - 1].getColor() == Color.white) {
                        aliveNeighbours++;
                    }
                    if(cells[i][j+1].getColor() == Color.white) {
                        aliveNeighbours++;
                    }
                }
                cells[i][j].getNextState(aliveNeighbours);
            }
        }

        for(int i =0; i<cells.length;i++)
            for(int j = 0; j < cells[0].length;j++)
                cells[i][j].changeState();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            nextGeneration();
        }
        repaint();
    }

    class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
                running = !running;
            if(e.getKeyCode() == KeyEvent.VK_W)
                restart();
        }
    }
}
