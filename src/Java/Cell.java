package Java;

import java.awt.*;

public class Cell {
    private Color color;

    public Cell(boolean initialState){
        if(initialState) {
            this.color = Color.white;
        } else {
            this.color = Color.black;
        }
    }


    public Color getColor() {
        return color;
    }

    public void getNextState(int aliveNeighbours){
        if(color == Color.black) {
            if (aliveNeighbours == 3)
                changeState();
        } else {
            if(aliveNeighbours < 2 || aliveNeighbours > 3)
                changeState();
        }
    }

    public void changeState(){
        color = (color == Color.black ? Color.white : Color.black);
    }
}
