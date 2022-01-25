package com.company.engine;
import java.awt.*;
import javax.swing.*;


public class GameFrame extends JFrame{
    GamePanel panel;
    public GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Gra Pong");
        this.setResizable(false);
        this.setBackground(Color.PINK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
