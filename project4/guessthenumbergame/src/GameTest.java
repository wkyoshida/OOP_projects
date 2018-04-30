/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 4
 * Guess the Number game test
 */
package guessthenumber;

import javax.swing.JFrame;

public class GameTest {
    public static void main(String[] args){
        GuesstheNumber gameFrame = new GuesstheNumber();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(700, 300);
        gameFrame.setVisible(true);
    }
}
