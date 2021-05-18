package com.example.w1761353;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class AdvancedLevelGameActivity extends AppCompatActivity {
    private static final int NUMBER_OF_CAR_IMAGES = 30;
    private static final int NUMBER_OF_CAR_MAKES = 6;
    private static final String LOG_TAG = AdvancedLevelGameActivity.class.getSimpleName();

    TypedArray carImages;
    TextView textView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    TextView correctAnswerTextView1;
    TextView correctAnswerTextView2;
    TextView correctAnswerTextView3;
    Button submitAndNext;

    int generatedRandomNumberToDisplayCarMake;
    int generatedRandomNumberToDisplayImage1;
    int generatedRandomNumberToDisplayImage2;
    int generatedRandomNumberToDisplayImage3;
    int[] listOfGeneratedRandomNumbersToDisplayRandomImage = new int[3];

    String[] listOfCorrectCarMakeNames;
    String selectedCarMakeName;

    boolean[] checkAnswers = {false,false,false};
    int score;
    int numberOfAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level_game);

        displayThreeRandomCarImages();
        checkCarMakeAnswers();
    }

    public void displayThreeRandomCarImages(){
        carImages = getResources().obtainTypedArray(R.array.carImages);
        imageView1 = findViewById(R.id.advance_imageView1);
        imageView2 = findViewById(R.id.advance_imageView2);
        imageView3 = findViewById(R.id.advance_imageView3);

        generatedRandomNumberToDisplayImage1 = (int) (Math.random() * 10);
        imageView1.setBackgroundResource(carImages.getResourceId(generatedRandomNumberToDisplayImage1, 1));
        listOfGeneratedRandomNumbersToDisplayRandomImage[0] = generatedRandomNumberToDisplayImage1;

        generatedRandomNumberToDisplayImage2 = (int) (Math.random() * (19-10)+1)+10;
        imageView2.setBackgroundResource(carImages.getResourceId(generatedRandomNumberToDisplayImage2, 1));
        listOfGeneratedRandomNumbersToDisplayRandomImage[1] = generatedRandomNumberToDisplayImage2;

        generatedRandomNumberToDisplayImage3 = (int) (Math.random() * (29-20)+1)+20;
        imageView3.setBackgroundResource(carImages.getResourceId(generatedRandomNumberToDisplayImage3, 1));
        listOfGeneratedRandomNumbersToDisplayRandomImage[2] = generatedRandomNumberToDisplayImage3;

        Log.d(LOG_TAG,"GENERATED RANDOM NUMBERS: "+ Arrays.toString(listOfGeneratedRandomNumbersToDisplayRandomImage));

        carImages.recycle();

        listOfCorrectCarMakeNames = new String[listOfGeneratedRandomNumbersToDisplayRandomImage.length];
        String[] listOfCarMakeNamesToDisplay = new String[]{"AUDI", "BENZ", "BMW", "LAMBORGHINI", "MINI", "PORSCHE"};

        for(int i=0; i<listOfGeneratedRandomNumbersToDisplayRandomImage.length; i++) {
            for (int j = 0; j<NUMBER_OF_CAR_IMAGES; j++) {
                if (listOfGeneratedRandomNumbersToDisplayRandomImage[i] <= 4) {
                    listOfCorrectCarMakeNames[i] = listOfCarMakeNamesToDisplay[0];
                } else if (listOfGeneratedRandomNumbersToDisplayRandomImage[i] == 5 || listOfGeneratedRandomNumbersToDisplayRandomImage[i] <= 9) {
                    listOfCorrectCarMakeNames[i] = listOfCarMakeNamesToDisplay[1];
                } else if (listOfGeneratedRandomNumbersToDisplayRandomImage[i] == 10 || listOfGeneratedRandomNumbersToDisplayRandomImage[i] <= 14) {
                    listOfCorrectCarMakeNames[i] = listOfCarMakeNamesToDisplay[2];
                } else if (listOfGeneratedRandomNumbersToDisplayRandomImage[i] == 15 || listOfGeneratedRandomNumbersToDisplayRandomImage[i] <= 19) {
                    listOfCorrectCarMakeNames[i] = listOfCarMakeNamesToDisplay[3];
                } else if (listOfGeneratedRandomNumbersToDisplayRandomImage[i] == 20 || listOfGeneratedRandomNumbersToDisplayRandomImage[i] <= 24) {
                    listOfCorrectCarMakeNames[i] = listOfCarMakeNamesToDisplay[4];
                } else if (listOfGeneratedRandomNumbersToDisplayRandomImage[i] == 25 || listOfGeneratedRandomNumbersToDisplayRandomImage[i] <= 29) {
                    listOfCorrectCarMakeNames[i] = listOfCarMakeNamesToDisplay[5];
                }
            }
        }
        Log.d(LOG_TAG,"SELECTED CAR MAKES: "+Arrays.toString(listOfCorrectCarMakeNames));

    }

    public void checkCarMakeAnswers(){
        submitAndNext = findViewById(R.id.advanceSubmit_button);
        editText1 = findViewById(R.id.advance_editText1);
        editText2 = findViewById(R.id.advance_editText2);
        editText3 = findViewById(R.id.advance_editText3);
        textView = findViewById(R.id.advanceScore_textView);

        correctAnswerTextView1 = findViewById(R.id.advance_textView1);
        correctAnswerTextView2 = findViewById(R.id.advance_textView2);
        correctAnswerTextView3 = findViewById(R.id.advance_textView3);

        score = 0;

        submitAndNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitAndNext.getText().toString().equals("Submit")) {
                    numberOfAttempts++;

                    if (editText1.getText().toString().trim().toLowerCase().equals(listOfCorrectCarMakeNames[0].toLowerCase()) && !checkAnswers[0]) {
                        checkAnswers[0] = true;
                        editText1.setTextColor(0xff00ff00);
                        editText1.setEnabled(false);
                        score++;
                    } else if(!checkAnswers[0]) {
                        editText1.setTextColor(0xffff0000);
                    }
                    if (editText2.getText().toString().trim().toLowerCase().equals(listOfCorrectCarMakeNames[1].toLowerCase()) && !checkAnswers[1]) {
                        checkAnswers[1] = true;
                        editText2.setTextColor(0xff00ff00);
                        editText2.setEnabled(false);
                        score++;
                    } else if(!checkAnswers[1]){
                        editText2.setTextColor(0xffff0000);
                    }
                    if (editText3.getText().toString().trim().toLowerCase().equals(listOfCorrectCarMakeNames[2].toLowerCase()) && !checkAnswers[2]) {
                        checkAnswers[2] = true;
                        editText3.setTextColor(0xff00ff00);
                        editText3.setEnabled(false);
                        score++;
                    } else if(!checkAnswers[2]){
                        editText3.setTextColor(0xffff0000);
                    }
                    textView.setText("SCORE: "+score);
                    if(score == 3 && numberOfAttempts!=3){
                        BoomerService.displayMessage("OK", "#47d147", "CORRECT !", "", v);
                        submitAndNext.setText("Next");
                    }
                    if(numberOfAttempts == 3){
                        BoomerService.displayMessage("OK", "#C54024", "WRONG !", "", v);
                        if(!checkAnswers[0]){
                            correctAnswerTextView1.setVisibility(View.VISIBLE);
                            correctAnswerTextView1.setText(listOfCorrectCarMakeNames[0]);
                        }
                        if(!checkAnswers[1]){
                            correctAnswerTextView2.setVisibility(View.VISIBLE);
                            correctAnswerTextView2.setText(listOfCorrectCarMakeNames[1]);
                        }
                        if(!checkAnswers[2]){
                            correctAnswerTextView3.setVisibility(View.VISIBLE);
                            correctAnswerTextView3.setText(listOfCorrectCarMakeNames[2]);
                        }
                        submitAndNext.setText("Next");
                    }
                }
                else{
                    refresh();
                }
            }
        });
    }

    public void refresh(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}