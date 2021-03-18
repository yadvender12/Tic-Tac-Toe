package com.mock.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    String activePlayer="yellow";
    String[] gameState={"","","","","","","","",""};
    int[][] winningstate={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6}};
    public void dropIn(View view){
        ImageView counter= (ImageView) view;
        Log.i("Tag", counter.getTag().toString());
        int getState=Integer.parseInt(counter.getTag().toString());
        gameState[getState]=activePlayer;
        counter.setTranslationY(-1500);
        if(activePlayer=="yellow")
        {
            counter.setImageResource(R.drawable.yellow);
            activePlayer="red";
        }
        else
        {
            counter.setImageResource(R.drawable.red);
            activePlayer="yellow";
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}