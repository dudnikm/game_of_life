package Java;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainPanel mainPanel;

    public MainFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("The Game of Life");

        mainPanel = new MainPanel();
        this.add(mainPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
