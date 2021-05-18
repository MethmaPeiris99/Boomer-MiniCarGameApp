package com.example.w1761353;

import android.graphics.Color;
import android.text.Html;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class BoomerService {

    //------------- Initializing constant variables -------------//
    private static final int NUMBER_OF_CAR_IMAGES = 30;

    //------------- Declaring class variables -------------//
    static int randomNumber;

    //---------- Method used to generate a random number between the range of 0-29 ----------//
    public static int getRandomNumber(){
        Random generateRandomNumber = new Random(); //Creating an instance of Random class

        //Assigning a random Integer in the range of 0-29 exclusive of specified bound
        int inputOfRandomNumber = generateRandomNumber.nextInt(NUMBER_OF_CAR_IMAGES);
        randomNumber = inputOfRandomNumber;
        return randomNumber;
    }

    //-------- Method used to display a message using a Snack bar to inform whether the answer is correct or not --------//
    public static void displayMessage(String textAction, String texColor , String text , String correctAnswer , View view){
        Snackbar snackbar = Snackbar
                .make(view, Html.fromHtml("<h7><font color=\"" + texColor + "\">" + text + "</font><h7>"  +
                        "<br>" + "<font color=\"#ffe100\">" + correctAnswer + "</font>"), Snackbar.LENGTH_LONG)
                .setAction(textAction , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

}

