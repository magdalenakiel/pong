package com.company.game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{

    static final double RANDOMIZE_ANGLE = 0.2;
    double angle;
    int speed = 10;

    @Override
    public void add(Point pt) {
        super.add(pt);
    }

    public Ball(int x, int y, int diameter, double angle){
        super(x, y, diameter, diameter);
        this.angle = angle;
    }

    public void move(){
        x += calculateXVelocity();
        y += calculateYVelocity();
    }

    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x - 2, y - 2, width + 4, height + 4);
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

    public double calculateXVelocity(){
        return Math.cos(angle) * speed;
    }

    public double calculateYVelocity(){
        return Math.sin(angle) * speed;
    }

    public void bounceY(){
        double delta_angle = angle - Math.PI;
        angle -= delta_angle * 2;
        randomAngle();
    }

    public void bounceX(){
        double delta_angle = angle - (Math.PI / 2);
        angle -= delta_angle * 2;
        randomAngle();
        speed += 1;
    }

    public void randomAngle() {
        angle += (Math.random() * RANDOMIZE_ANGLE) - (RANDOMIZE_ANGLE / 2);
    }
}
