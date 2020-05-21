package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons;
    private Player x;
    private Player o;
    public Player currPlayer;
    private TextView game_updates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createButtons();

        game_updates = findViewById(R.id.game_updates);

        createGame();
    }

    private void createButtons(){
        buttons = new Button[]{
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9)
        };
    }

    public void onClickNewGame(View v){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }

        currPlayer = x;

        displayCurrPlayer();
    }

    public void onClick(View v){
        for(int i = 0; i < buttons.length; i++){
            if(v == buttons[i] && buttons[i].getText() == ""){
                buttons[i].setText(currPlayer.playerName);
                disableButtons(i);
            }
            else if(v == buttons[i] && buttons[i].getText() != "") {
                changePlayer();
            }
        }
        if(isWinnerFound(currPlayer.playerName)){
            gameOver();
            displayGameResults(currPlayer.playerName + " Wins!");
        }
        else if(isTieGame()){
            displayGameResults("Tie Game!");
        }
        else {
            changePlayer();
        }
    }

    private void createGame(){
        x = new Player("X");
        o = new Player("O");

        currPlayer = x;

        displayCurrPlayer();
    }

    private void displayCurrPlayer(){
        game_updates.setText("Player " + currPlayer.playerName + "'s Turn");
    }

    private void displayGameResults(String results){
        game_updates.setText(results);
    }

    private void changePlayer(){
        if(currPlayer == x){
            currPlayer = o;
        }
        else{
            currPlayer = x;
        }

        displayCurrPlayer();
    }

    private boolean isWinnerFound(String player){
        if (        // Rows
               (buttons[0].getText() == player && buttons[1].getText() == player && buttons[2].getText() == player ||
                buttons[3].getText() == player && buttons[4].getText() == player && buttons[5].getText() == player ||
                buttons[6].getText() == player && buttons[7].getText() == player && buttons[8].getText() == player) ||
                        // Columns
               (buttons[0].getText() == player && buttons[3].getText() == player && buttons[6].getText() == player ||
                buttons[1].getText() == player && buttons[4].getText() == player && buttons[7].getText() == player ||
                buttons[2].getText() == player && buttons[5].getText() == player && buttons[8].getText() == player) ||
                        // Diagonals
               (buttons[0].getText() == player && buttons[4].getText() == player && buttons[8].getText() == player ||
                buttons[2].getText() == player && buttons[4].getText() == player && buttons[6].getText() == player))
                {
                    return true;
                }

        return false;
    }

    private boolean isTieGame(){
        for(int i = 0; i < buttons.length; i++){
            if(buttons[i].getText() == ""){
                return false;
            }
        }

        return true;
    }

    private void disableButtons(int buttonNum){
        buttons[buttonNum].setEnabled(false);
    }

    private void gameOver(){
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setEnabled(false);
        }
    }
}
