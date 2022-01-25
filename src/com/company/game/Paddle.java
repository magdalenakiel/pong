package com.company.game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Paddle extends Rectangle{
    int up_event;
    int down_event;
    int paddleYVelocity = 0;
    int PADDLE_SPEED = 10;

    public Paddle(int x, int y, int width, int height, int up_event, int down_event){
        super(x, y, width, height);
        this.up_event = up_event;
        this.down_event = down_event;
    }
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == up_event) {
            setPaddleYVelocity(-PADDLE_SPEED);
        }
        if (e.getKeyCode() == down_event) {
            setPaddleYVelocity(PADDLE_SPEED);
        }
    }

    public void keyReleased(KeyEvent e){
        // stop the paddle if released key is responsible for current direction
        if (e.getKeyCode() == up_event && paddleYVelocity < 0 || e.getKeyCode() == down_event && paddleYVelocity > 0){
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
