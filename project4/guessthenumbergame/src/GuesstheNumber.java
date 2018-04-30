/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 4
 * Guess the Number game
 */
package guessthenumber;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GuesstheNumber extends JFrame { 

    private final JLabel instruction;
    private final JTextField textField;
    private final JButton resetButton;
    private final BorderLayout layout;
    private int answer; // random number to be guessed
    private int proximity;     // used for hinting if new answer
    private int lastProximity; // is closer than the previous

    public GuesstheNumber() { 
        super("Guess the Number!");

        layout = new BorderLayout();
        setLayout(layout);
        
        Random rand = new Random();
        answer = rand.nextInt(1000) + 1; // random value between 1 and 1000

        instruction = new JLabel(); // game prompt
        instruction.setText("<html><div style='text-align: center;'>I have a number between 1 and 1000. Can you guess my number?<br>Please enter your first guess.</div></html>");
        instruction.setFont(new Font("Algerian", Font.BOLD, 20));
        add(instruction, BorderLayout.CENTER);
        
        textField = new JTextField(""); // input guess
        textField.setFont(new Font("Algerian", Font.BOLD, 15));
        add(textField, BorderLayout.SOUTH);
        
        TextFieldHandler tHandler = new TextFieldHandler();
        textField.addActionListener(tHandler);
        
        resetButton = new JButton("New Game");
        resetButton.setFont(new Font("Algerian", Font.BOLD, 15));
        add(resetButton, BorderLayout.NORTH);
        
        ButtonHandler bHandler = new ButtonHandler();
        resetButton.addActionListener(bHandler);
        
        this.lastProximity = 1000; // inital previous proximity since 1000 is farthest one can be from the answer
        this.proximity = 0;
        
    }   
    
    // inner class for button event handling
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            Random newRand = new Random();
            answer = newRand.nextInt(1000) + 1; // new game, new random number to guess
            textField.setText("");
            textField.setEditable(true);
            instruction.setText("<html><div style='text-align: center;'>I have a number between 1 and 1000. Can you guess my number?<br>Please enter your first guess.</div></html>");
            getContentPane().setBackground(null); // return background to default color
        }
    }
    
    // inner class for text field event handling
    private class TextFieldHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            proximity = Math.abs(Integer.valueOf(event.getActionCommand()) - answer);
            
            instruction.setText("<html><div style='text-align: center;'>I have a number between 1 and 1000. Can you guess my number?<br>Try again!</div></html>");

            if (proximity < lastProximity) {
                getContentPane().setBackground(Color.RED);
            }
            else if (proximity > lastProximity) {
                getContentPane().setBackground(Color.CYAN); // change background to cyan, for better visibility, instead of blue
            }
            else {
                getContentPane().setBackground(null); // if answer is same proximity as the previous answer, change background to default
            }
            
            
            if (Integer.valueOf(event.getActionCommand()) > answer) {
                instruction.setText("<html><div style='text-align: center;'>I have a number between 1 and 1000. Can you guess my number?<br>Too high!</div></html>");                
            }
            else if (Integer.valueOf(event.getActionCommand()) < answer) {
                instruction.setText("<html><div style='text-align: center;'>I have a number between 1 and 1000. Can you guess my number?<br>Too low!</div></html>");                
            }
            else if (Integer.valueOf(event.getActionCommand()) == answer) {
                instruction.setText("<html><div style='text-align: center;'>Correct!</div></html>");
                textField.setEditable(false);
                getContentPane().setBackground(Color.YELLOW); // if win, change background to yellow
            }
            
            lastProximity = proximity;
            
        }
    }     
}