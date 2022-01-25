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
    static final double MIN_BALL_X_DIRECTION = 0.2;
    static final int PADDLE_WIDTH = 25;
    static  final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
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
        // set position of ball
        int ballX = getMiddleX() - (BALL_DIAMETER / 2);
        int ballY = getMiddleY() - (BALL_DIAMETER / 2);

        // set ball direction
        double randomAngle = Math.random() * Math.PI;
        double directionX = Math.cos(randomAngle);
        double directionY = Math.sin(randomAngle);

        // assert that ball is going to the paddles
        if (Math.abs(directionX) < MIN_BALL_X_DIRECTION) {
            if (directionX >= 0) {
                directionX = MIN_BALL_X_DIRECTION;
            }
            if (directionX < 0) {
                directionX = -MIN_BALL_X_DIRECTION;
            }
        }

        // create ball
        ball = new Ball(ballX, ballY, BALL_DIAMETER, directionX, directionY);
    }
    public void newPaddles(){
        // set position of paddles
        int paddleXOffset = 10;
        int paddle1X = paddleXOffset;
        int paddle2X = GAME_WIDTH - PADDLE_WIDTH - paddleXOffset;
        int paddlesY = (GAME_HEIGHT/2) - PADDLE_HEIGHT / 2;

        // create paddles
        paddle1 = new Paddle(paddle1X, paddlesY, PADDLE_WIDTH, PADDLE_HEIGHT, KeyEvent.VK_W, KeyEvent.VK_S);
        paddle2 = new Paddle(paddle2X, paddlesY, PADDLE_WIDTH, PADDLE_HEIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
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
        ball.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollisions(){
        correctPaddlePosition(paddle1);
        correctPaddlePosition(paddle2);

    }

    public void correctPaddlePosition(Paddle paddle){
        if (paddle.y <= 0){
            paddle.setLocation(paddle.x, 0);
        }
        if (paddle.y + paddle.height >= GAME_HEIGHT) {
            paddle.setLocation(paddle.x, GAME_HEIGHT - paddle.height);
        }
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
                checkCollisions();
                repaint();
                lastTime = now;
            }
        }
    }
    public int getMiddleX(){
        return GAME_WIDTH / 2;
    }

    public int getMiddleY(){
        return GAME_HEIGHT / 2;
    }


    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
