package com.example.romancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean entered_sign, entered_plus, entered_minus, entered_multiply, entered_equal;
    Roman r = new Roman();

    public MainActivity() {
        this.entered_sign = false;
        this.entered_plus = false;
        this.entered_minus = false;
        this.entered_multiply = false;
        this.entered_equal = false;
    }

    Button convertButton, romanButton2, button_info_m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertButton = (Button) findViewById(R.id.convertButton);
        romanButton2 = (Button) findViewById(R.id.romanButton2);
        button_info_m = (Button) findViewById(R.id.button_info_m);
        ((Button)findViewById(R.id.decimalConvert_main)).setClickable(false);
        ((Button)findViewById(R.id.decimalConvert_main)).setAlpha(0.5f);

        /**
         * @description: If the convert button at the bottom of the activity is clicked, switch to the converter activity.
         */
        convertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Converter.class);
                startActivity(i);
            }
        });

        /**
         * @description: If the roman button at the bottom of the activity is clicked, switch to the roman calculator activity.
         */
        romanButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RomanInput.class);
                startActivity(i);
            }
        });

        /**
         * @description If the info button at the bottom of the activity is clicked, switch to the basic conversion principles / information activity.
         */
        button_info_m.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent(MainActivity.this, BasicConversionPrinciples.class);
                startActivity(i);
            }
        });
    }

    /**
     * Disables a button by making it unclickable and slightly transparent to give the user a hint that it cannot be used.
     * @param i The button to be disabled.
     */
    public void disableButton (int i) {
        ((Button)findViewById(i)).setClickable(false);
        ((Button)findViewById(i)).setAlpha(0.5f);
    }

    /**
     * Enables a button by making it clickable and removing transparency to give the user a hint that it can be used.
     * @param i The button to be enabled.
     */
    public void enableButton (int i) {
        ((Button)findViewById(i)).setClickable(true);
        ((Button)findViewById(i)).setAlpha(1.0f);
    }

    /**
     * Determines if a number should be clickable for the next click by analysing the result of this addition method.
     * @param numString1 The first integer string to be parsed into an integer and added to the second integer string that is given a predictive digit to see if that digit will result in a valid operation.
     * @param numString2 The second integer string to be given another digit, parsed into an int and added to the first integer.
     * @param addedNum The digit to be added to the second integer to realize if its button should be disabled or enabled for producing an invalid or valid answer.
     * @return Numerical value that represents addedNum having to be disabled if 4999 - sum is less than 1.
     */
    public int addValid (String numString1, String numString2, String addedNum) {
        return 4999 - (Integer.parseInt(numString1) + Integer.parseInt(numString2 + addedNum)) + 1;
    }

    /**
     * Determines if a digit should be clickable for the next click by analysing the result of this subtraction method.
     * @param numString1 The integer string to be subtracted from.
     * @param numString2 The integer string that represents the subtraction amount.
     * @param addedNum The digit added to the second numString.
     * @return Numerical value that represents addedNum having to be disabled if the value is less than 1, meaning the
     * second number that is subtracted is to big for the first number to produce a positive result.
     */
    public int subtractValid (String numString1, String numString2, String addedNum) {
        return (Integer.parseInt(numString1) - Integer.parseInt(numString2 + addedNum));
    }

    /**
     * Determines if a digit should be clickable for the next click by analysing the result of this multiplication method.
     * @param numString1 The first integer string to be multiplied.
     * @param numString2 The second integer string to be given a predictive digit to see if it would be a valid multiplication or not based on roman numeral range.
     * @param addedNum The digit to be added to numString2.
     * @return Numerical value that represents addedNum having to be disabled if 4999 - product is less than 1, meaning
     * the second number is too big for multiplication.
     */
    public int multiplyValid (String numString1, String numString2, String addedNum) {
        return 4999 - (Integer.parseInt(numString1) * Integer.parseInt(numString2 + addedNum));
    }

    /**
     * Disables all digit buttons.
     */
    public void disableAllNums() {
        disableButton(R.id.button_0);
        disableButton(R.id.button_1);
        disableButton(R.id.button_2);
        disableButton(R.id.button_3);
        disableButton(R.id.button_4);
        disableButton(R.id.button_5);
        disableButton(R.id.button_6);
        disableButton(R.id.button_7);
        disableButton(R.id.button_8);
        disableButton(R.id.button_9);
    }

    /**
     * Enables all digit buttons.
     */
    public void enableAllNums() {
        enableButton(R.id.button_0);
        enableButton(R.id.button_1);
        enableButton(R.id.button_2);
        enableButton(R.id.button_3);
        enableButton(R.id.button_4);
        enableButton(R.id.button_5);
        enableButton(R.id.button_6);
        enableButton(R.id.button_7);
        enableButton(R.id.button_8);
        enableButton(R.id.button_9);
    }

    /**
     * Disables the buttons for the plus, minus, and multiplication operators.
     */
    public void disableAllOperators() {
        disableButton(R.id.button_plus_d);
        disableButton(R.id.button_minus_d);
        disableButton(R.id.button_multiply_d);
    }

    public void onButtonPush (View view) throws RomanNumeralException {
        TextView input = (TextView) findViewById(R.id.decimalInput1);

        //If an operator has been assigned, we have moved onto the second digit for which we are inputting into
        // the decimalInput2 TextView.
        if (this.entered_sign) {
            input = (TextView) findViewById(R.id.decimalInput2);
            disableAllOperators();
        }

        //For each digit button that is pressed, append its corresponding value in String form to the input field.
        if (view.getId() == R.id.button_0) {
            input.append("0");
        } else if (view.getId() == R.id.button_1) {
            input.append("1");
        } else if (view.getId() == R.id.button_2) {
            input.append("2");
        } else if (view.getId() == R.id.button_3) {
            input.append("3");
        } else if (view.getId() == R.id.button_4) {
            input.append("4");
        } else if (view.getId() == R.id.button_5) {
            input.append("5");
        } else if (view.getId() == R.id.button_6) {
            input.append("6");
        } else if (view.getId() == R.id.button_7) {
            input.append("7");
        } else if (view.getId() == R.id.button_8) {
            input.append("8");
        } else if (view.getId() == R.id.button_9) {
            input.append("9");

        //If the clear button is pressed, reset all input fields, output fields, values, and enable all numbers.
        } else if (view.getId() == R.id.button_clear_d) {
            enableAllNums();

            enableButton(R.id.button_plus_d);
            enableButton(R.id.button_minus_d);
            enableButton(R.id.button_multiply_d);
            enableButton(R.id.button_equals_d);
            disableButton(R.id.decimalConvert_main);

            ((TextView) findViewById(R.id.decimalInput1)).setText("");
            ((TextView) findViewById(R.id.decimalInput2)).setText("");
            ((TextView) findViewById(R.id.signDecimal)).setText("");
            ((TextView) findViewById(R.id.decimalOutput1)).setText("");

            this.entered_sign = false;
            this.entered_plus = false;
            this.entered_minus = false;
            this.entered_multiply = false;
            this.entered_equal = false;
        } else if (view.getId() == R.id.button_plus_d) {
            this.entered_sign = true;
            this.entered_plus = true;
            ((TextView) findViewById(R.id.signDecimal)).append("+");

            enableAllNums();
            disableAllOperators();

        } else if (view.getId() == R.id.button_minus_d) {
            this.entered_sign = true;
            this.entered_minus = true;
            ((TextView) findViewById(R.id.signDecimal)).append("—");

            enableAllNums();
            disableAllOperators();
        } else if (view.getId() == R.id.button_multiply_d) {
            this.entered_sign = true;
            this.entered_multiply = true;
            ((TextView) findViewById(R.id.signDecimal)).append("*");

            enableAllNums();
            disableAllOperators();
        } else if (view.getId() == R.id.button_equals_d) {
            this.entered_equal = true;
            disableButton(R.id.button_equals_d);
            enableButton(R.id.decimalConvert_main);

            //Parse both final integers from both text fields.
            int int1 = Integer.parseInt(((TextView) findViewById(R.id.decimalInput1)).getText().toString());
            int int2 = Integer.parseInt(((TextView) findViewById(R.id.decimalInput2)).getText().toString());

            //Add, subtract, or multiply both integer values based on which operator was pressed.
            if (entered_plus) {
                ((TextView) findViewById(R.id.decimalOutput1)).setText(r.intToRoman(int1 + int2));
            } else if (entered_minus) {
                ((TextView) findViewById(R.id.decimalOutput1)).setText(r.intToRoman(int1 - int2));
            } else if (entered_multiply) {
                ((TextView) findViewById(R.id.decimalOutput1)).setText(r.intToRoman(int1 * int2));
            }

        //Convert the string output value of the roman numeral to a decimal value if the conversion button is pressed.
        } else if (view.getId() == R.id.decimalConvert_main) {
            if (this.entered_equal) {
                String output = ((TextView) findViewById(R.id.decimalOutput1)).getText().toString();
                int outputValue = r.romanToInt(output);
                ((TextView) findViewById(R.id.decimalOutput1)).setText(Integer.toString(outputValue) + " ");
                disableButton(R.id.decimalConvert_main);
            }
        }

        //For the first number, if another digit results in too large of a result to be displayed as a roman numeral, disable all the input buttons.
        if (!this.entered_sign) {
            int number1 = Integer.parseInt(((TextView) findViewById(R.id.decimalInput1)).getText().toString() + "0");

            if (number1 > 4999) {
                disableAllNums();
            }
        }

        //For the second integer.
        if (this.entered_sign) {
            //Get the strings of integer digits representing the first and second integer inputs.
            String number1 = ((TextView) findViewById(R.id.decimalInput1)).getText().toString();
            String number2 = ((TextView) findViewById(R.id.decimalInput2)).getText().toString();

            int num1 = 0, num2 = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0, num7 = 0, num8 = 0, num9 = 0, num0 = 0;

            //Based on the operation, check the result validity (between 1-4999) for each decimal digit added.
            if (entered_plus) {
                num0 = addValid(number1, number2, "0");
                num1 = addValid(number1, number2, "1");
                num2 = addValid(number1, number2, "2");
                num3 = addValid(number1, number2, "3");
                num4 = addValid(number1, number2, "4");
                num5 = addValid(number1, number2, "5");
                num6 = addValid(number1, number2, "6");
                num7 = addValid(number1, number2, "7");
                num8 = addValid(number1, number2, "8");
                num9 = addValid(number1, number2, "9");
            } else if (entered_minus) {
                num0 = subtractValid(number1, number2, "0");
                num1 = subtractValid(number1, number2, "1");
                num2 = subtractValid(number1, number2, "2");
                num3 = subtractValid(number1, number2, "3");
                num4 = subtractValid(number1, number2, "4");
                num5 = subtractValid(number1, number2, "5");
                num6 = subtractValid(number1, number2, "6");
                num7 = subtractValid(number1, number2, "7");
                num8 = subtractValid(number1, number2, "8");
                num9 = subtractValid(number1, number2, "9");
            } else if (entered_multiply) {
                num0 = multiplyValid(number1, number2, "0");
                num1 = multiplyValid(number1, number2, "1");
                num2 = multiplyValid(number1, number2, "2");
                num3 = multiplyValid(number1, number2, "3");
                num4 = multiplyValid(number1, number2, "4");
                num5 = multiplyValid(number1, number2, "5");
                num6 = multiplyValid(number1, number2, "6");
                num7 = multiplyValid(number1, number2, "7");
                num8 = multiplyValid(number1, number2, "8");
                num9 = multiplyValid(number1, number2, "9");
            }

            //Based on which digits are not valid, disable buttons.
            if (num0 < 1) {
                disableAllNums();
            } else if (num1 < 1) {
                disableButton(R.id.button_1);
                disableButton(R.id.button_2);
                disableButton(R.id.button_3);
                disableButton(R.id.button_4);
                disableButton(R.id.button_5);
                disableButton(R.id.button_6);
                disableButton(R.id.button_7);
                disableButton(R.id.button_8);
                disableButton(R.id.button_9);
            } else if (num2 < 1) {
                disableButton(R.id.button_2);
                disableButton(R.id.button_3);
                disableButton(R.id.button_4);
                disableButton(R.id.button_5);
                disableButton(R.id.button_6);
                disableButton(R.id.button_7);
                disableButton(R.id.button_8);
                disableButton(R.id.button_9);
            } else if (num3 < 1) {
                disableButton(R.id.button_3);
                disableButton(R.id.button_4);
                disableButton(R.id.button_5);
                disableButton(R.id.button_6);
                disableButton(R.id.button_7);
                disableButton(R.id.button_8);
                disableButton(R.id.button_9);
            } else if (num4 < 1) {
                disableButton(R.id.button_4);
                disableButton(R.id.button_5);
                disableButton(R.id.button_6);
                disableButton(R.id.button_7);
                disableButton(R.id.button_8);
                disableButton(R.id.button_9);
            } else if (num5 < 1) {
                disableButton(R.id.button_5);
                disableButton(R.id.button_6);
                disableButton(R.id.button_7);
                disableButton(R.id.button_8);
                disableButton(R.id.button_9);
            } else if (num6 < 1) {
                disableButton(R.id.button_6);
                disableButton(R.id.button_7);
                disableButton(R.id.button_8);
                disableButton(R.id.button_9);
            } else if (num7 < 1) {
                disableButton(R.id.button_7);
                disableButton(R.id.button_8);
                disableButton(R.id.button_9);
            } else if (num8 < 1) {
                disableButton(R.id.button_8);
                disableButton(R.id.button_9);
            } else if (num9 < 1) {
                disableButton(R.id.button_9);
            }
        }
    }
}