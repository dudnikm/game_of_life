package Java;

import java.awt.*;

public class Cell {
    private Color color;
    private boolean nextState;

    public Cell(boolean initialState){
        if(initialState) {
            this.color = Color.white;
        } else {
            this.color = Color.black;
        }
        nextState = false;
    }


    public Color getColor() {
        return color;
    }

    /**
     * Calculates the next state of the cell based on its color and number of alive neighbours
     * @param aliveNeighbours - number of alive neighbours around the cell
     */
    public void getNextState(int aliveNeighbours){
        if(color == Color.black) {
            if (aliveNeighbours == 3)
                nextState = true;
        } else {
            if(aliveNeighbours < 2 || aliveNeighbours > 3)
                nextState = true;
        }
    }

    /**
     * Changes the color of the cell
     */
    public void changeState(){
        if(nextState)
            color = (color == Color.black ? Color.white : Color.black);
        nextState = false;
    }
}
