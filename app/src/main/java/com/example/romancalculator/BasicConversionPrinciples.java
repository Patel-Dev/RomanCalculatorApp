/**
 * @author: Dev Patel
 * @description: BasicConversionPrinciples Activity of calculator app to give more info about roman numerals to users.
 * @date: 01/29/2021
 */

package com.example.romancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BasicConversionPrinciples extends AppCompatActivity {
    Button button_convert_p, button_decimal_p, button_roman_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principles);
        button_convert_p = (Button)findViewById(R.id.button_convert_p);
        button_decimal_p = (Button)findViewById(R.id.button_decimal_p);
        button_roman_p = (Button)findViewById(R.id.button_roman_p);

        TextView titleView = (TextView)findViewById(R.id.titleView);
        TextView descriptionView = (TextView)findViewById(R.id.descriptionView);

        //Use the text from the Roman Numerals assignment as the text in the descriptionView to display to the user if they need more info.
        descriptionView.setText("In general, Roman numerals can be converted mathematically by simply assigning a numerical value to each letter, according to the chart below, and calculating a total:\n\n" +
                "M=1000 | D=500 | C=100 | L=50 | X=10 | V=5 | I=1\n\n" +
                "Although the historical practice has varied, the modern convention has been to arrange the letters from left to right in order of decreasing value; the total is then calculated by adding the numerical values of all the letters in the sequence.\n" +
                " \n" +
                "For example, MDCLXVI (one of each valid letter) = 1000 + 500 + 100 + 50 + 10 + 5 + 1 = 1666.\n" +
                " \n" +
                "A well-known, but still often confusing feature of modern Roman numerals is the subtraction principle, which requires that a lower numeral appearing before a higher one be subtracted from the higher value, not added to the total. For example, IX is the Roman numeral for 9 (that is, 10 - 1). In the same way XIX represents the number 19 (X + IX, or 10 + 9) rather than 21, which is written as XXI (10 + 10 + 1). Likewise the Roman numeral for the year 1995 is usually written as MCMXCV (M + CM + XC + V, or 1000 + 900 + 90 + 5). Many other similar examples can be found which strictly follows this subtraction convention.\n" +
                " \n" +
                "Another present-day convention is the avoidance of more than three consecutive occurrences of the same letter in favor of the more succinct forms achieved by using the subtraction principle -- for example, IV for IIII (4), XL for XXXX (40), and CD for CCCC (400). An exception is the numeral M, or 1000, which is used 4 times to represent our number 4000, since the Romans had no single-letter numeral representing a higher value than M. It is now also customary not to repeat the values V, L, or D (5, 50, or 500) in the same numeral.\n");

        /**
         * @description Changes to decimal calculator activity when bottom decimal button is pressed.
         */
        button_decimal_p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent (BasicConversionPrinciples.this, MainActivity.class);
                startActivity(i);
            }
        });

        /**
         * @description Changes to roman calculator activity when bottom roman button is pressed.
         */
        button_roman_p.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent(BasicConversionPrinciples.this, RomanInput.class);
                startActivity(i);
            }
        });

        /**
         * @description Changes to converter activity when bottom convert button is pressed.
         */
        button_convert_p.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent(BasicConversionPrinciples.this, Converter.class);
                startActivity(i);
            }
        });
    }
}
