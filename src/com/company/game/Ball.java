package com.company.game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Ball extends Rectangle{

    double yVelocity;
    double xVelocity;
    int SPEED = 3;

    public Ball(int x, int y, int diameter, double xUnitVelocity, double yUnitVelocity){
        super(x, y, diameter, diameter);
        xVelocity = xUnitVelocity * SPEED;
        yVelocity = yUnitVelocity * SPEED;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);

    }
}
