package com.example.w1761353;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class IdentifyCarImageGameActivity extends AppCompatActivity {
    private static final int NUMBER_OF_CAR_IMAGES = 30;
    private static final int NUMBER_OF_CAR_MAKES = 6;
    private static final String LOG_TAG = IdentifyCarImageGameActivity.class.getSimpleName();

    TypedArray carImages;
    TextView textView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    Button nextButton;

    int generatedRandomNumberToDisplayCarMake;
    int generatedRandomNumberToDisplayImage1;
    int generatedRandomNumberToDisplayImage2;
    int generatedRandomNumberToDisplayImage3;
    int[] listOfGeneratedRandomNumbersToDisplayRandomImage = new int[3];

    String[] listOfCorrectCarMakeNames;
    String selectedCarMakeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_image_game);

        displayThreeRandomCarImages();
    }
    
    public void displayThreeRandomCarImages(){
        carImages = getResources().obtainTypedArray(R.array.carImages);
        imageView1 = findViewById(R.id.advance_imageView1);
        imageView2 = findViewById(R.id.identifyCarImage_imageView2);
        imageView3 = findViewById(R.id.identifyCarImage_imageView3);

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

        displayRandomCarMake();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listOfCorrectCarMakeNames[0].equals(selectedCarMakeName)){
                    BoomerService.displayMessage("OK", "#47d147", "CORRECT !", "", v);
                }
                else{
                    BoomerService.displayMessage("OK", "#C54024", "WRONG !", "", v);
                }
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listOfCorrectCarMakeNames[1].equals(selectedCarMakeName)){
                    BoomerService.displayMessage("OK", "#47d147", "CORRECT !", "", v);
                }
                else{
                    BoomerService.displayMessage("OK", "#C54024", "WRONG !", "", v);
                }
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listOfCorrectCarMakeNames[2].equals(selectedCarMakeName)){
                    BoomerService.displayMessage("OK", "#47d147", "CORRECT !", "", v);
                }
                else{
                    BoomerService.displayMessage("OK", "#C54024", "WRONG !", "", v);
                }
            }
        });

        nextButton = findViewById(R.id.identifyCarImage_Next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }

    public void displayRandomCarMake(){
        generatedRandomNumberToDisplayCarMake = (int) (Math.random()*listOfGeneratedRandomNumbersToDisplayRandomImage.length);
        Log.d(LOG_TAG,"generatedRandomNumberToDisplayCarMake: "+generatedRandomNumberToDisplayCarMake);

        selectedCarMakeName = listOfCorrectCarMakeNames[generatedRandomNumberToDisplayCarMake];
        Log.d(LOG_TAG,"SELECTED CAR MAKE NAME: "+selectedCarMakeName);

        textView = findViewById(R.id.identifyCarImage_textView);
        textView.setText(selectedCarMakeName);
    }

    public void refresh(){
        Intent intent = getIntent(); //Receiving the data which represents to start the activity from the previous activity
        finish(); //Finishing the activity started by the previous activity
        startActivity(intent); //Starting a new activity
    }
}