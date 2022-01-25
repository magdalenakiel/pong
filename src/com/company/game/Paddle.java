package com.company.game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Paddle extends Rectangle{
    int up_code;
    int down_code;
    int paddleYVelocity = 0;
    int PADDLE_SPEED = 10;

    public Paddle(int x, int y, int width, int height, int up_code, int down_code){
        super(x, y, width, height);
        this.up_code = up_code;
        this.down_code = down_code;
    }
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == up_code) {
            setPaddleYVelocity(-PADDLE_SPEED);
        }
        if (e.getKeyCode() == down_code) {
            setPaddleYVelocity(PADDLE_SPEED);
        }
    }

    public void keyReleased(KeyEvent e){
        // stop the paddle if released key is responsible for current direction
        if (e.getKeyCode() == up_code && paddleYVelocity < 0 || e.getKeyCode() == down_code && paddleYVelocity > 0){
            setPaddleYVelocity(0);
        }
    }
    public void move(){
        y += paddleYVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void setPaddleYVelocity(int paddleYVelocity) {
        this.paddleYVelocity = paddleYVelocity;
    }
}
