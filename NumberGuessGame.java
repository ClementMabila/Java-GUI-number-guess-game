
package za.ac.tut.numberguess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.util.Random;

public class NumberGuessGame extends JFrame{
    
   private int score;
   private int gamesPlayed;
   private int gamesWon;
   private ArrayList<Integer> numbers = new ArrayList<>();
   private ArrayList<Integer> Usernumbers = new ArrayList<>();
   
   private JPanel titlePnl;
   private JPanel gamePnl;
   private JPanel scoreAreaPnl;
   private JPanel buttonsPnl;
   private JPanel instructionsPnl;
   private JPanel mainPnl;
   private JPanel guesLblPnl;
   private JPanel siriAreaPnl;
   private JPanel userAreaPnl;
   private JPanel setupPanel;
   
   private JLabel titleLbl;
   private JLabel guessLbl;
   private JLabel instructionLbl;
   private JLabel siriLbl; 
   
   private JTextArea summaryArea;
   private JTextArea siriArea;
   private JTextArea userArea;
   
   private JButton submitGuessBtn;
   private JButton giveUpBtn;
   private JButton endGameBtn;
   private JButton exitButton;
   
   public NumberGuessGame(){
       
       setSize(606,470);
       setLayout(new BorderLayout());
       setTitle("NUMBER GUESS GAME");
       setResizable(false);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       titlePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
       titlePnl.setBackground(Color.pink);
       gamePnl = new JPanel(new BorderLayout());
       scoreAreaPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
       buttonsPnl = new JPanel(new GridLayout(4,1));
       instructionsPnl = new JPanel(new GridLayout(2,1));
       mainPnl = new JPanel(new BorderLayout());
       siriAreaPnl = new JPanel(new GridLayout(2, 1));
       userAreaPnl = new JPanel(new GridLayout(2, 1));
       setupPanel = new JPanel(new GridLayout(1,3));
       
       titleLbl = new JLabel("NUMBER GUESS GAME");
       titleLbl.setForeground(Color.white);
       titleLbl.setFont(new Font(Font.SERIF ,Font.BOLD ,20));
       
       guessLbl = new JLabel("         GUESS ME??");
       guessLbl.setForeground(Color.white);
       guessLbl.setBorder(new LineBorder(Color.orange,3));
       siriLbl = new JLabel("               SIRI");
       siriLbl.setForeground(Color.white);
       siriLbl.setBorder(new LineBorder(Color.pink,3));
       instructionLbl = new JLabel("This is a number guess game\nThe cpu SIRI will generate a random number\nYou should guess the correct number to win");
       instructionLbl.setFont(new Font(Font.SANS_SERIF , Font.BOLD + Font.ITALIC ,10));
       instructionsPnl.add(instructionLbl);
       instructionsPnl.setBackground(Color.pink);
       instructionLbl.setForeground(Color.white);
       instructionsPnl.setSize(10,10);
       
       summaryArea = new JTextArea(4,8);
       summaryArea.setBackground(Color.pink);
       summaryArea.setBorder(new TitledBorder(new LineBorder(Color.BLACK),"GAME SUMMARY"));
       siriArea = new JTextArea(20,20);
       siriArea.setEditable(false);
       siriArea.setBackground(Color.pink);
       siriArea.setBorder(new LineBorder(Color.orange,2));
       
       instructionLbl.setBorder(new TitledBorder(new LineBorder(Color.BLACK),"INSTRUCTIONS"));
       
       userArea = new JTextArea(20,10);
       userArea.setBackground(Color.green);
       userArea.setBorder(new LineBorder(Color.orange,2));
       
       submitGuessBtn = new JButton("GUESS");
       submitGuessBtn.setBackground(Color.pink);
       submitGuessBtn.setForeground(Color.white);
       giveUpBtn = new JButton("Give Up");
       giveUpBtn.setForeground(Color.white);
       giveUpBtn.setBackground(Color.green);
       
       endGameBtn = new JButton("END GAME");
       endGameBtn.setForeground(Color.pink);
       endGameBtn.setBackground(Color.white);
       
       exitButton = new JButton("EXIT");
       exitButton.setForeground(Color.white);
       exitButton.setBackground(Color.black);
       
       submitGuessBtn.addActionListener(new SubmitBtnActionListener());
       endGameBtn.addActionListener(new EndGameBtnActionListener());
       exitButton.addActionListener(new ExitActionListener());
       
       buttonsPnl.add(submitGuessBtn);
       buttonsPnl.add(giveUpBtn);
       buttonsPnl.add(endGameBtn);
       buttonsPnl.add(exitButton);
       
       titlePnl.add(titleLbl);
       siriAreaPnl.add(siriLbl);
       siriAreaPnl.add(siriArea);
       siriAreaPnl.setBorder(new LineBorder(Color.pink,2));
       siriAreaPnl.setBackground(Color.pink);
       
       userAreaPnl.add(guessLbl);
       userAreaPnl.add(userArea);
       userAreaPnl.setBorder(new LineBorder(Color.green,2));
       userAreaPnl.setBackground(Color.green);
       
       setupPanel.add(siriAreaPnl);
       setupPanel.add(userAreaPnl);
       setupPanel.add(buttonsPnl);
       setupPanel.setBorder(new TitledBorder(new LineBorder(Color.black,3),"--PLAY--"));
       setupPanel.setBackground(Color.white);
       titlePnl.setBorder(new LineBorder(Color.black));
       instructionsPnl.add(summaryArea);
       instructionsPnl.setBorder(new LineBorder(Color.BLACK,3));
       gamePnl.add(setupPanel , BorderLayout.CENTER);
       gamePnl.add(titlePnl, BorderLayout.NORTH);
       gamePnl.add(instructionsPnl, BorderLayout.SOUTH);
       
       add(gamePnl);
       setVisible(true);
   }
    
   private class SubmitBtnActionListener implements ActionListener {
        
        Random gen = new Random();
        @Override
        public void actionPerformed(ActionEvent e) {
            
           int randomNum = 1+gen.nextInt(5 - 1)+1;
           int userGuess = Integer.parseInt(userArea.getText());
           Usernumbers.add(userGuess);
           if(userGuess == randomNum)
           {
               score++;
               summaryArea.setText("\t\tCORRECT GUESS\n"+"\t\tYOUR CURRENT POINTS :  "+score);
               numbers.add(randomNum);
               gamesWon++;
           }
           else
           {
               if(score == 0){
                summaryArea.setText("\t\tINCORRECT GUESS\n\n"+"\t\tYOUR CURRENT POINTS :  "+score);
                numbers.add(randomNum);
               }
               else{
               score--;
               summaryArea.setText("\t\tINCORRECT GUESS\n\n"+"\t\tYOUR CURRENT POINTS :  "+score);
               numbers.add(randomNum);
               }
           }
          gamesPlayed++;
          userArea.setText("");
          userArea.setFocusable(true);
       }

   }
   
   private class EndGameBtnActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            double scoreInperc = (gamesWon*100 / gamesPlayed);
            String summaryList = "SIRI number's : "+numbers+"\nYOUR number's : "+Usernumbers+"\nGames Played : "+gamesPlayed+"\nWON GAMES : "+gamesWon+"\nSCORE : "+scoreInperc;
            summaryArea.setText(summaryList);
            gamesPlayed = 0;
            gamesWon = 0;
            score = 0;
            Usernumbers.clear();
            numbers.clear();
        }
    }
   
   private class ExitActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(1);
        }
   }
}
