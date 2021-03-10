/**
 * @author: Dev Patel
 * @description: Converter activity of roman numeral calculator for quick and easy conversions between roman and decimal using android built-in keyboard.
 * @date: 01/29/2021
 */

package com.example.romancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Converter extends AppCompatActivity {
    Button b_convertToRoman, b_convertToNumber, decimalButton1, romanButton1, button_info_c;
    EditText et_number, et_romanInput;
    TextView tv_romanOutput, tv_numberOutput;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        b_convertToNumber = (Button) findViewById(R.id.b_convertToNumber);
        b_convertToRoman = (Button) findViewById(R.id.b_convertToRoman);
        et_number = (EditText)findViewById(R.id.et_number);
        et_romanInput = (EditText)findViewById(R.id.et_romanInput);
        tv_romanOutput = (TextView)findViewById(R.id.tv_romanOutput);
        tv_numberOutput = (TextView)findViewById(R.id.tv_numberOutput);
        decimalButton1 = (Button)findViewById(R.id.decimalButton1);
        romanButton1 = (Button)findViewById(R.id.romanButton1);
        button_info_c = (Button)findViewById(R.id.button_info_c);

        /**
         * Converts inputted roman numeral to integer when convertToNumber button is clicked.
         */
        b_convertToNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Roman r = new Roman();
                int theNumber;
                String theRoman;

                //Get the roman from the EditText input.
                theRoman = et_romanInput.getText().toString();

                //Try converting the roman to integer. If it is not valid, throw an exception and output a message.
                try {
                    theNumber = r.romanToInt(theRoman);
                    tv_numberOutput.setText(Integer.toString(theNumber));
                } catch (RomanNumeralException e) {
                    e.printStackTrace();
                    tv_numberOutput.setText("Invalid Roman Numeral!");
                }
            }
        });

        /**
         * Converts inputted integer value to roman numeral.
         */
        b_convertToRoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Roman nc = new Roman();
                int theNumber;
                String theRoman;

                //Try converting the int to a roman numeral. If the int is invalid, throw an exception and output a message.
                try {
                    //Parse the integer from the input EditText field within the activity.
                    theNumber = Integer.parseInt(et_number.getText().toString());
                    theRoman = nc.intToRoman(theNumber);
                    tv_romanOutput.setText(theRoman);
                } catch (Exception e) {
                    tv_romanOutput.setText("Invalid Integer! (Must be from 1-4999.)");
                }
            }
        });

        /**
         * If the decimal button at the bottom of the activity is clicked, switch to the decimal calculator activity.
         */
        decimalButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent (Converter.this, MainActivity.class);
                startActivity(i);
            }
        });

        /**
         * If the roman button at the bottom of the activity is clicked, switch to the roman calculator activity.
         */
        romanButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent(Converter.this, RomanInput.class);
                startActivity(i);
            }
        });

        /**
         * If the info button at the bottom of the activity is clicked, switch to the basic conversion principles / information activity.
         */
        button_info_c.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent(Converter.this, BasicConversionPrinciples.class);
                startActivity(i);
            }
        });
    }
}
