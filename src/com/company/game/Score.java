package com.company.game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Score extends Rectangle{

    int SCORE_OFFSET = 20;
    int font_size;
    int x;
    int y;
    int player1 = 0;
    int player2 = 0;

    public Score(int font_size, int x, int y) {
        this.font_size = font_size;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, font_size));
        g.drawString(String.valueOf(player1), x - (font_size / 2) - SCORE_OFFSET - 15, y);
        g.drawString(String.valueOf(player2), x + SCORE_OFFSET, y);
    }

    public void addScoreToPlayer1() {
        this.player1 += 1;
    }

    public void addScoreToPlayer2() {
        this.player2 += 1;
    }
}
