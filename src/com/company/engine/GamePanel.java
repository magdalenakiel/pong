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
        // set position of paddles
        int paddleXOffset = 10;
        int paddle1X = paddleXOffset;
        int paddle2X = GAME_WIDTH - PADDLE_WIDTH - paddleXOffset;
        int paddlesY = (GAME_HEIGHT/2) - PADDLE_HEIGHT / 2;

        // create paddles
        paddle1 = new Paddle(paddle1X, paddlesY, PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(paddle2X, paddlesY, PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
    }
    public void move(){

    }
    public void checkCollision(){

    }
    public void run(){
        // game loop
        long lastTime = System.nanoTime();
        double ticksPerSecond = 60.;
        double refreshPeriod = Math.pow(10, 9) / ticksPerSecond;

        while (true) {
            long now = System.nanoTime();

            // if the last time the screen was refreshed was earlier that refresh period time ago
            if (now - lastTime >= refreshPeriod) {
                move();
                checkCollision();
                repaint();
                lastTime = now;
            }
        }
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
