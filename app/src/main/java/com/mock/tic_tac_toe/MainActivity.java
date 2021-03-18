package com.mock.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    String activePlayer="yellow";
    String[] gameState={"null","null","null","null","null","null","null","null","null"};
    int[][] winningStates={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6}};
    boolean gameActive=true;
    static int yellowWins=0;
    static int redWins=0;
    int coinCount=0;
    public void dropIn(View view){
        ImageView counter= (ImageView) view;
        Log.i("Tag", counter.getTag().toString());
        int getState=Integer.parseInt(counter.getTag().toString());
        if(gameState[getState].equals("null") && gameActive) {
            coinCount++;
            gameState[getState] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer.equals("yellow")) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = "red";
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = "yellow";
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            checkWinning(counter);
        }
        if(coinCount==9 && gameActive){
            Toast.makeText(this , "Game Tied", Toast.LENGTH_SHORT).show();
            Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
            TextView gameOver=(TextView)findViewById(R.id.gameOver);
            gameOver.setText("Game Over! Its a Tie");
            playAgainButton.setVisibility(View.VISIBLE);
            gameOver.setVisibility(View.VISIBLE);
        }
    }

    private void checkWinning(ImageView counter) {
        for(int[] winningState : winningStates){
            if(gameState[winningState[0]].equals(gameState[winningState[1]])
                    && gameState[winningState[1]].equals(gameState[winningState[2]])
                    && !gameState[winningState[0]].equals("null") ){
                gameActive=false;
                String winner="";
                if(activePlayer.equals("yellow")){
                    winner="Red";
                    redWins++;
                    TextView redWinsText=(TextView)findViewById(R.id.redWins);
                    redWinsText.setText("Red Wins: "+redWins);
                }
                else{
                    winner="Yellow";
                    yellowWins++;
                    TextView yellowWinsText=(TextView)findViewById(R.id.yellowWins);
                    yellowWinsText.setText("Yellow Wins: "+yellowWins);
                }
                Toast.makeText(this , winner+" has Won", Toast.LENGTH_SHORT).show();
                Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                TextView gameOver=(TextView)findViewById(R.id.gameOver);
                gameOver.setText("Game Over! "+winner+" has Won");
                playAgainButton.setVisibility(View.VISIBLE);
                gameOver.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view){
        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
        TextView gameOver=(TextView)findViewById(R.id.gameOver);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);

        playAgainButton.setVisibility(View.INVISIBLE);
        gameOver.setVisibility(View.INVISIBLE);

        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            gameState[i]="null";
        }
        activePlayer="yellow";
        gameActive=true;
        coinCount=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}