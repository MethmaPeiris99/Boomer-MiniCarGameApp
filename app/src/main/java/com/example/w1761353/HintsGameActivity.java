package com.example.w1761353;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import android.content.res.TypedArray;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class HintsGameActivity extends AppCompatActivity {

    //---------------- Initializing constant variables ----------------//
    private static final String LOG_TAG = HintsGameActivity.class.getSimpleName();

    //---------------- Declaring instance variables ----------------//
    private EditText editText;
    private Button submitButton;
    private int randomNumber;

    private String correctCarMakeName; //Correct car make according to the displayed random image
    private String[] dashes; //Array of dashes corresponding to the car make
    private String currentCorrectGuess; //Correct guesses made by user
    private int incorrectAttempts; //Incorrect guess count made by the user

    public int time = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints_game);

        Intent intent = getIntent();
        boolean switchTimer = intent.getBooleanExtra(MainActivity.EXTRA_MESSAGE,false);

        if(switchTimer){
            timer();
        }

        submitButton = findViewById(R.id.submitAnswerAndNextbutton);

        displayRandomCarImage();
        guessCarMake();

    }

    public void displayRandomCarImage(){
        TypedArray carImages = getResources().obtainTypedArray(R.array.carImages);
        ImageView imageView = findViewById(R.id.advance_imageView1);
        randomNumber = BoomerService.getRandomNumber();

        imageView.setBackgroundResource(carImages.getResourceId(randomNumber, 1));
        Log.d(LOG_TAG,"GENERATED RANDOM NUMBER: "+randomNumber);

        carImages.recycle();
    }

    //------------ Method used to display the dashes corresponding to the correct car make ------------//
    public void guessCarMake(){
        editText = findViewById(R.id.hints_editText);
        TextView textView = findViewById(R.id.hints_textView);

        String[] listOfCarMakeNamesToGuess = new String[]{"AUDI", "BENZ", "BMW", "LAMBORGHINI", "MINI", "PORSCHE"};
        correctCarMakeName = null;

        //Finding the correct car make according to the generated random number relevant to the index position range of the image array//
        for (int index = 0; index< listOfCarMakeNamesToGuess.length; index++) {
            if(randomNumber<=4){
                correctCarMakeName = listOfCarMakeNamesToGuess[0];
            }
            else if(randomNumber==5 || randomNumber<=9){
                correctCarMakeName = listOfCarMakeNamesToGuess[1];
            }
            else if(randomNumber==10 || randomNumber<=14){
                correctCarMakeName = listOfCarMakeNamesToGuess[2];
            }
            else if(randomNumber==15 || randomNumber<=19){
                correctCarMakeName = listOfCarMakeNamesToGuess[3];
            }
            else if(randomNumber==20 || randomNumber<=24){
                correctCarMakeName = listOfCarMakeNamesToGuess[4];
            }
            else if(randomNumber==25 || randomNumber<=29) {
                correctCarMakeName = listOfCarMakeNamesToGuess[5];
            }
        }

        Log.d(LOG_TAG,"CORRECT CAR MAKE: "+correctCarMakeName);

        dashes = new String[correctCarMakeName.length()];
        Arrays.fill(dashes, " - "); //Filing the dash array with dash symbols

        //-------------- Setting the content of the text view to display the dashes relevant to the car make --------------//
        textView.setText(Arrays.toString(dashes).replace(","," ").replace("[", "") .replace("]", ""));
    }

    //-------------------- Method used to check the guesses made by the user is correct or not --------------------//
    public void check_guessed_answer(View view) {
        ArrayList<String> listOfGuesses = new ArrayList<>();
        String guessedLetter = editText.getText().toString().toUpperCase(); //Initializing the guessed letter of the car make entered by the user
        listOfGuesses.add(guessedLetter);

        Log.d(LOG_TAG,"GUESSED LETTER: "+ guessedLetter);
        Log.d(LOG_TAG,"LIST OF GUESSES: "+listOfGuesses);
        Log.d(LOG_TAG,"CORRECT CAR MAKE: "+correctCarMakeName);

        if(correctCarMakeName.contains(guessedLetter)){
            currentCorrectGuess = "";

            for(int index=0; index<correctCarMakeName.length(); index++){
                if(correctCarMakeName.charAt(index) == guessedLetter.charAt(0)){
                    dashes[index] = String.valueOf(guessedLetter.charAt(0));
                    TextView textViewCorrect = findViewById(R.id.hints_textView);
                    textViewCorrect.setText(Arrays.toString(dashes).replace(","," ").replace("[", "") .replace("]", ""));
                }
                currentCorrectGuess += dashes[index];
            }
            Log.d(LOG_TAG,"CORRECT GUESSES: "+currentCorrectGuess);
        }
        else if(!(correctCarMakeName.contains(guessedLetter))){
            incorrectAttempts++;
            Log.d(LOG_TAG,"INCORRECT ATTEMPTS: "+incorrectAttempts);
        }
        else if(currentCorrectGuess.contains(guessedLetter)){
            Toast toast = Toast.makeText(getApplicationContext(), "You already guessed that letter !", Toast.LENGTH_LONG);
            toast.show();
            Log.d(LOG_TAG,"CORRECT GUESSES: "+currentCorrectGuess);
        }
        if(currentCorrectGuess != null && incorrectAttempts!=3) {
            if (currentCorrectGuess.equals(correctCarMakeName)) {
                BoomerService.displayMessage("OK", "#47d147", "CORRECT !", "", view);
                submitButton.setText(R.string.change_button_text_next);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh();
                    }
                });
            }
        }
        else if(incorrectAttempts >= 3){
            BoomerService.displayMessage("OK", "#C54024", "WRONG !", correctCarMakeName, view);
            submitButton.setText(R.string.change_button_text_next);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refresh();
                }
            });
        }
    }

    public void timer(){
        TextView textTimer = (TextView)findViewById(R.id.hints_timer);
        new CountDownTimer(20000, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                textTimer.setText(checkDigit(time));
                time--;
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                textTimer.setText("00");
                check_guessed_answer(findViewById(R.id.submitAnswerAndNextbutton).getRootView());
            }

        }.start();
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    public void refresh(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}