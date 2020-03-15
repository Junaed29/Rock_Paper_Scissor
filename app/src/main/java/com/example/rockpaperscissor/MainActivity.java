package com.example.rockpaperscissor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView userSelectionTextView,compSelectionTextView,resultTextView,scoreTextView;

    Random random;

    LottieAnimationView lottieAnimationView;

    int userScore = 0, compScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userSelectionTextView = findViewById(R.id.meResultTextViewId);
        compSelectionTextView = findViewById(R.id.compResultTextViewId);
        resultTextView = findViewById(R.id.lastResultTextViewId);
        scoreTextView = findViewById(R.id.scoreTextViewId);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);

        random = new Random();

        resultTextView.setText("");
        userSelectionTextView.setText("");
        compSelectionTextView.setText("");
    }

    public void resetClick(View view) {
        resultTextView.setText("");
        userSelectionTextView.setText("");
        compSelectionTextView.setText("");
        lottieAnimationView.setVisibility(View.INVISIBLE);
        userScore = 0;
        compScore = 0;
        setScoreTextView();
    }

    public void rpsButtonselected(View view) {
        // User Selection Value
        int userSelection = Integer.parseInt(view.getTag().toString());
        //Log.d(TAG, "rpsButtonselected: "+userSelection);

        // Computer Selection Value by using Random Function
        int low = 1;
        int high = 3;
        //random.nextInt(high) will produce 0,1,2
        int compSelection = random.nextInt(high) + low ; // will produce 1,2,3

        makeResult(userSelection,compSelection);
    }

    public void makeResult(int userSelection, int compSelection){
        lottieAnimationView.setVisibility(View.VISIBLE);
        Log.d(TAG, "User : "+userSelection);
        Log.d(TAG, "Computer : "+compSelection);
        Log.d(TAG, "userSelection-compSelection)%3 : "+(modulo((userSelection-compSelection),3)));
        if (userSelection==compSelection){
            // Tie
            resultTextView.setText("Tie");
            lottieAnimationView.setAnimation(R.raw.tie);
        }else if ((modulo((userSelection-compSelection),3))==1){
            //User Win
            userScore++;
            resultTextView.setText("Yah, you won!");
            lottieAnimationView.setAnimation(R.raw.won);
        }else {
            //User Lost
            compScore++;
            resultTextView.setText("Oops, you lost!");
            lottieAnimationView.setAnimation(R.raw.lost);
        }

        lottieAnimationView.playAnimation();

        switch (userSelection){
            case 1:
                userSelectionTextView.setText("Rock");
                break;
            case 2:
                userSelectionTextView.setText("Paper");
                break;
            case 3:
                userSelectionTextView.setText("Scissor");
                break;
        }

        switch (compSelection){
            case 1:
                compSelectionTextView.setText("Rock");
                break;
            case 2:
                compSelectionTextView.setText("Paper");
                break;
            case 3:
                compSelectionTextView.setText("Scissor");
                break;
        }

        setScoreTextView();
    }

    public void setScoreTextView(){
        scoreTextView.setText(String.valueOf(userScore)+" : "+String.valueOf(compScore));
    }

    public int modulo( int m, int n ){
        int mod =  m % n ;
        return ( mod < 0 ) ? mod + n : mod;
        /// that's mean  if ( mod < 0 ) then  return (mod + n) else return mod
    }
}
