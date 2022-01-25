package com.company.engine;
import com.company.game.Ball;
import com.company.game.Paddle;
import com.company.game.Score;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (5./9)); // 5./9, because 5/9=0, because the result will be int
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static  final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    public GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBall(){

    }
    public void newPaddles(){

    }
    public void paint(Graphics g){

    }
    public void draw(Graphics g){

    }
    public void move(){

    }
    public void checkCollision(){

    }
    public void run(){

    }
    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {

        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
