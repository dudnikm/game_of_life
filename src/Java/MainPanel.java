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

        //Displaying the cells
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

    /**
     * Method calculates the number of white neighbours for each cell on the screen
     */
    public void nextGeneration(){
        int aliveNeighbours;
        for(int i =0; i<cells.length;i++){
            for(int j = 0; j < cells[0].length;j++){
                aliveNeighbours = 0;
                //Checking upper and lower rows around the cell
                for(int k = -1; k<2;k++){
                    //Checking if cell has neighbours above it and their colors
                    if(i-1 >= 0 && j+k >= 0 && j+k < SCREEN_WIDTH/SCREEN_UNIT)
                        if(cells[i-1][j+k].getColor() == Color.white)
                            aliveNeighbours++;
                    //Checking if cell has neighbours under it and their colors
                    if(i+1 < SCREEN_HEIGHT/SCREEN_UNIT && j+k >= 0 && j+k < SCREEN_WIDTH/SCREEN_UNIT)
                        if(cells[i+1][j+k].getColor() == Color.white)
                            aliveNeighbours++;
                }
                //Checking neighbours on the sides of the cell
                if(j-1 >= 0 && j+1 < SCREEN_WIDTH/SCREEN_UNIT) {
                    if (cells[i][j - 1].getColor() == Color.white) {
                        aliveNeighbours++;
                    }
                    if(cells[i][j+1].getColor() == Color.white) {
                        aliveNeighbours++;
                    }
                }
                //Passing number of alive cells around to getNextState method of Cell class to determine its next state
                cells[i][j].getNextState(aliveNeighbours);
            }
        }
        //Changing the state of each cell to a new one
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
            //Press space to pause simulation
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
                running = !running;
            //Press R to restart simulation
            if(e.getKeyCode() == KeyEvent.VK_R)
                restart();
        }
    }
}
