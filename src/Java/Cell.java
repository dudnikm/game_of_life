package Java;

import java.awt.*;

public class Cell {
    private boolean alive;
    private Color color;
    private int numberOfAliveNeighbours;

    public Cell(boolean initialState){
        if(initialState) {
            this.alive = true;
            this.color = Color.white;
        } else {
            this.alive = false;
            this.color = Color.black;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumberOfAliveNeighbours() {
        return numberOfAliveNeighbours;
    }

    public void setNumberOfAliveNeighbours(int numberOfAliveNeighbours) {
        this.numberOfAliveNeighbours = numberOfAliveNeighbours;
    }
}
