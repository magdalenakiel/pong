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
    static final int PADDLE_HEIGHT = 100;
    static final int SCORE_FONT_SIZE = 120;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    public GamePanel(){
        newGame();
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newGame(){
        newPaddles();
        newBall();
        score = new Score(SCORE_FONT_SIZE, getMiddleX(), getMiddleY());
    }

    public void newBall(){
        // set position of ball
        int ballX = getMiddleX() - (BALL_DIAMETER / 2);
        int ballY = getMiddleY() - (BALL_DIAMETER / 2);

        // set ball direction
        double randomAngle = Math.random() * Math.PI * 2;

        // create ball
        ball = new Ball(ballX, ballY, BALL_DIAMETER, randomAngle);
    }
    public void newPaddles(){
        // set position of paddles
        int paddleXOffset = 10;

        int paddle1X = paddleXOffset;
        int paddle2X = GAME_WIDTH - PADDLE_WIDTH - paddleXOffset;
        int paddlesY = getMiddleY() - PADDLE_HEIGHT / 2;

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
        score.draw(g);
        g.setColor(Color.WHITE);
        g.drawLine(getMiddleX(), 0, getMiddleX(), GAME_HEIGHT);
        ball.draw(g);
        paddle1.draw(g);
        paddle2.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollisions(){
        correctPaddlePosition(paddle1);
        correctPaddlePosition(paddle2);
        bounceBall();
        setScore();
    }
    public void correctPaddlePosition(Paddle paddle){
        if (paddle.y <= 0){
            paddle.setLocation(paddle.x, 0);
        }
        if (paddle.y + paddle.height >= GAME_HEIGHT) {
            paddle.setLocation(paddle.x, GAME_HEIGHT - paddle.height);
        }
    }

    public void bounceBall() {
        if (ball.y <= 0 || ball.y + ball.height >= GAME_HEIGHT) {
            ball.bounceY();
        }

        if (ball.intersects(paddle1) || ball.intersects(paddle2)) {
            ball.bounceX();
        }
    }

    public void setScore() {
        if (ball.x < 0){
            score.addScoreToPlayer2();
            newBall();
        }
        if (ball.x > GAME_WIDTH){
            score.addScoreToPlayer1();
            newBall();
        }
    }

    public void run(){
        // game loop
        long lastTime = System.nanoTime();
        double ticksPerSecond = 60;
        // refresh period in nanoseconds
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

    public void keyPressedPanel(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_R) {
            newGame();
        }
    }

    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
            keyPressedPanel(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
