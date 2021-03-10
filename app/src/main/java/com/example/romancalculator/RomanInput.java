package com.example.romancalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class RomanInput extends AppCompatActivity {
    private int count_i, count_v, count_x, count_l, count_c, count_d, count_m;
    private boolean entered_sign, entered_plus, entered_minus, entered_multiply, entered_equal, disabledI, disabledX;
    Roman r = new Roman();

    public RomanInput() {
        this.count_i = 0;
        this.count_v = 0;
        this.count_x = 0;
        this.count_l = 0;
        this.count_c = 0;
        this.count_d = 0;
        this.count_m = 0;

        this.entered_sign = false;
        this.entered_plus = false;
        this.entered_minus = false;
        this.entered_multiply = false;
        this.entered_equal = false;
        this.disabledI = false;
        this.disabledX = false;
    }

    Button decimalButton, convertButton2, button_info_r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roman_input);

        decimalButton = (Button)findViewById(R.id.button2);
        convertButton2 = (Button)findViewById(R.id.convertButton2);
        button_info_r = (Button)findViewById(R.id.button_info_r);

        //Initially disable the button that convert the output text since there is no output to convert yet..
        ((Button)findViewById(R.id.decimalConvert)).setClickable(false);
        ((Button)findViewById(R.id.decimalConvert)).setAlpha(0.5f);

        /**
         * If the decimal button at the bottom of the screen is clicked, switch to the decimal calculator activity.
         */
        decimalButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent (RomanInput.this, MainActivity.class);
                startActivity(i);
            }
        });

        /**
         * If the convert button at the bottom of the screen is clicked, switch to the converter activity.
         */
        convertButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent(RomanInput.this, Converter.class);
                startActivity(i);
            }
        });

        /**
         * If the info button at the bottom of the screen is clicked, switch to the info activity.
         */
        button_info_r.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Intent i = new Intent (RomanInput.this, BasicConversionPrinciples.class);
                startActivity(i);
            }
        });
    }

    /**
     * Disables a button by making it unclickable and slightly transparent to signify that it is disabled.
     * @param i The button to be disabled.
     */
    public void disableButton (int i) {
        ((Button)findViewById(i)).setClickable(false);
        ((Button)findViewById(i)).setAlpha(0.5f);
    }

    /**
     * Enables a button by making it clickable and removes transparency to signify it is enabled.
     * @param i The button to be enabled.
     */
    public void enableButton (int i) {
        ((Button)findViewById(i)).setClickable(true);
        ((Button)findViewById(i)).setAlpha(1.0f);
    }

    /**
     * Resets the counters of all the roman numeral chars.
     */
    public void resetCounters () {
        this.count_i = 0;
        this.count_v = 0;
        this.count_x = 0;
        this.count_l = 0;
        this.count_c = 0;
        this.count_d = 0;
        this.count_m = 0;
    }

    /**
     * Disables the buttons for the plus, minus, and multiplication operators.
     */
    public void disableOperators() {
        disableButton(R.id.button_plus_r);
        disableButton(R.id.button_minus_r);
        disableButton(R.id.button_multiply_r);
    }

    /**
     * Enables the buttons for all the roman numeral chars.
     */
    public void enableNumerals() {
        enableButton(R.id.button_I);
        enableButton(R.id.button_V);
        enableButton(R.id.button_X);
        enableButton(R.id.button_L);
        enableButton(R.id.button_C);
        enableButton(R.id.button_D);
        enableButton(R.id.button_M);
    }

    /**
     * Checks if an addition is too big for a predictive added char to the second roman numeral input. Used to disable buttons.
     * @param numeral1 The first addition input roman numeral.
     * @param numeral2 The second addition input roman numeral.
     * @param addedLetter The letter to be added to the second input roman numeral to test if the addition would be valid with it being added.
     * @return If the return value is negative, the addition is invalid (too big to be displayed as a roman numeral).
     * @throws RomanNumeralException If numerals are invalid.
     */
    public int addValid(String numeral1, String numeral2, String addedLetter) throws RomanNumeralException {
        return 4999 - (r.romanToInt(numeral1) + r.romanToInt(numeral2 + addedLetter)) + 1;
    }

    /**
     * Checks if a subtraction is too small for a predictive added char to the second roman numeral input. Used to disable buttons.
     * @param numeral1 The first roman numeral that is subtracted from.
     * @param numeral2 The second roman numeral representing the difference value.
     * @param addedLetter The letter to be added to the second roman numeral to determine if the subtraction result would be valid.
     * @return If the return value is negative, the subtraction is invalid (too small to be displayed as a roman numeral).
     * @throws RomanNumeralException If numerals are invalid.
     */
    public int subtractValid(String numeral1, String numeral2, String addedLetter) throws RomanNumeralException {
        return (r.romanToInt(numeral1) - r.romanToInt(numeral2 + addedLetter));
    }

    /**
     * Checks if a multiplication is too large for a predictive added char to the second roman numeral input. Used to disable buttons.
     * @param numeral1 The first roman numeral that is multiplied.
     * @param numeral2 The second roman numeral that is multiplied.
     * @param addedLetter The predictive letter to be added to the second numeral to check for validity.
     * @return If the return value is negative, the subtraction is invalid (too large to be displayed as a roman numeral).
     * @throws RomanNumeralException If numerals are invalid.
     */
    public int multiplyValid(String numeral1, String numeral2, String addedLetter) throws RomanNumeralException {
        return 4999 - (r.romanToInt(numeral1) * r.romanToInt(numeral2 + addedLetter));
    }

    public void onButtonPush (View view) throws RomanNumeralException {
        TextView input = ((TextView)findViewById(R.id.romanInput1));

        //If an operator (sign) has been used, we move onto the second input field to input the second number.
        if (this.entered_sign) {
            input = ((TextView) findViewById(R.id.romanInput2));

            //Disable all operators to prevent them from being used again.
            disableOperators();
        }

        if (view.getId() == R.id.button_I) {
            if (!this.disabledI) {
                input.append("I");

                disableButton(R.id.button_L);
                disableButton(R.id.button_C);
                disableButton(R.id.button_D);
                disableButton(R.id.button_M);

                this.count_i++;

                //I cannot be used more than three times.
                if (this.count_i >= 3) disableButton(R.id.button_I);
                if (this.count_i >= 2) {
                    disableButton(R.id.button_V);
                    disableButton(R.id.button_X);
                }
            }

            String text = input.getText().toString();
            if (text.length() >= 3) {
                //Cannot use another I if the last three chars were XIX, since XIXI is invalid.
                if (text.substring(text.length() - 3).equals("XIX")) {
                    disableButton(R.id.button_I);
                    disableButton(R.id.button_X);
                    disableButton(R.id.button_V);
                    this.disabledI = true;
                }
            }

        } else if (view.getId() == R.id.button_V) {
            input.append("V");

            this.count_v++;

            disableButton(R.id.button_V);
            disableButton(R.id.button_X);
            disableButton(R.id.button_L);
            disableButton(R.id.button_C);
            disableButton(R.id.button_D);
            disableButton(R.id.button_M);

        } else if (view.getId() == R.id.button_X) {
            String text = input.getText().toString();
            if (text.length() >= 1) {
                //Cannot use an X after a V.
                if (text.substring(text.length() - 1).equals("V")) {
                    disableButton(R.id.button_X);
                    this.disabledX = true;
                }
            }

            if (!this.disabledX) {
                input.append("X");

                disableButton(R.id.button_D);
                disableButton(R.id.button_M);

                this.count_x++;

                //X cannot be used more than three times.
                if (this.count_x >= 3) disableButton(R.id.button_X);
                if (this.count_x >= 2) {
                    disableButton(R.id.button_C);
                    disableButton(R.id.button_L);
                }
            }

        } else if (view.getId() == R.id.button_L) {
            input.append("L");

            this.count_l++;

            disableButton(R.id.button_L);
            disableButton(R.id.button_C);
            disableButton(R.id.button_D);
            disableButton(R.id.button_M);

        } else if (view.getId() == R.id.button_C) {
            input.append("C");

            this.count_c++;
            if (this.count_c >= 3) disableButton(R.id.button_C);
            if (this.count_c >= 2) {
                disableButton(R.id.button_D);
                disableButton(R.id.button_M);
            }

            //M can be used after C as long as no other char has been used. CM represents 900.
            if (this.count_m < 5 && this.count_d == 0 && this.count_l == 0 && this.count_v == 0 && this.count_x == 0 && this.count_i == 0) {
                enableButton(R.id.button_M);
            }

        } else if (view.getId() == R.id.button_D) {
            input.append("D");
            this.count_d++;

            disableButton(R.id.button_D);
            disableButton(R.id.button_M);

        } else if (view.getId() == R.id.button_M) {
            input.append("M");
            this.count_m++;
            String inputText = input.getText().toString();

            //M cannot be used more than 5 times total, or four times in a row.
            if (inputText.equals("MMMM") || this.count_m >= 5 || this.count_c != 0) disableButton(R.id.button_M);

            //If M has been used 5 times, CM must have occurred (according to patterns observed in roman numerals.
            //CM represents 900, so D (representing 500) cannot be used.
            if (this.count_m >= 5) {
                disableButton(R.id.button_D);
            }
        }

        else if (view.getId() == R.id.button_clear_r) {
            enableNumerals();
            enableButton(R.id.button_plus_r);
            enableButton(R.id.button_minus_r);
            enableButton(R.id.button_multiply_r);
            enableButton(R.id.button_equals_r);
            disableButton(R.id.decimalConvert);

            ((TextView)findViewById(R.id.romanInput1)).setText("");
            ((TextView)findViewById(R.id.romanInput2)).setText("");
            ((TextView)findViewById(R.id.sign)).setText("");
            ((TextView)findViewById(R.id.decimalOutput)).setText("");

            resetCounters();

            this.entered_sign = false;
            this.entered_plus = false;
            this.entered_minus = false;
            this.entered_multiply = false;
            this.entered_equal = false;
        }

        else if (view.getId() == R.id.button_plus_r) {
            this.entered_sign = true;
            this.entered_plus = true;
            ((TextView)findViewById(R.id.sign)).append("+");

            enableNumerals();
            disableOperators();
            resetCounters();
        }

        else if (view.getId() == R.id.button_minus_r) {
            this.entered_sign = true;
            this.entered_minus = true;
            ((TextView)findViewById(R.id.sign)).append("—");

            enableNumerals();
            disableOperators();
            resetCounters();
        }

        else if (view.getId() == R.id.button_multiply_r) {
            this.entered_sign = true;
            this.entered_multiply = true;
            ((TextView)findViewById(R.id.sign)).append("*");

            enableNumerals();
            disableOperators();
            resetCounters();
        }

        else if (view.getId() == R.id.button_equals_r) {
            entered_equal = true;
            disableButton(R.id.button_equals_r);
            enableButton(R.id.decimalConvert);

            String roman1 = ((TextView)findViewById(R.id.romanInput1)).getText().toString();
            String roman2 = ((TextView)findViewById(R.id.romanInput2)).getText().toString();

            //Based on the operator used, convert both inputted roman numerals to integers, add/multiply/subtract them,
            //then convert back to a roman numeral and output it to the output field.
            if (entered_plus) {
                int sum = r.romanToInt(roman1) + r.romanToInt(roman2);
                String sumString = r.intToRoman(sum);
                ((TextView)findViewById(R.id.decimalOutput)).setText(sumString);
            }
            else if (entered_minus) {
                int difference = r.romanToInt(roman1) - r.romanToInt(roman2);
                String differenceString = r.intToRoman(difference);
                ((TextView)findViewById(R.id.decimalOutput)).setText(differenceString);
            }
            else if (entered_multiply) {
                int product = r.romanToInt(roman1) * r.romanToInt(roman2);
                String productString = r.intToRoman(product);
                ((TextView)findViewById(R.id.decimalOutput)).setText(productString);
            }
        }

        else if (view.getId() == R.id.decimalConvert) {
            //Convert the roman numeral output value to an integer.
            if (entered_equal) {
                String output = ((TextView)findViewById(R.id.decimalOutput)).getText().toString();
                int outputValue = r.romanToInt(output);
                ((TextView)findViewById(R.id.decimalOutput)).setText(Integer.toString(outputValue) + " ");
                disableButton(R.id.decimalConvert);
            }
        }

        if (this.entered_sign) {
            String roman1 = ((TextView) findViewById(R.id.romanInput1)).getText().toString();
            String roman2 = ((TextView) findViewById(R.id.romanInput2)).getText().toString();

            int num1 = 0, num2 = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0, num7 = 0;

            //Based on the operation, check the result validity for each roman char added to the second input.
            if (entered_plus) {
                num1 = addValid(roman1, roman2, "I");
                num2 = addValid(roman1, roman2, "V");
                num3 = addValid(roman1, roman2, "X");
                num4 = addValid(roman1, roman2, "L");
                num5 = addValid(roman1, roman2, "C");
                num6 = addValid(roman1, roman2, "D");
                num7 = addValid(roman1, roman2, "M");

            } else if (entered_minus) {
                num1 = subtractValid(roman1, roman2, "I");
                num2 = subtractValid(roman1, roman2, "V");
                num3 = subtractValid(roman1, roman2, "X");
                num4 = subtractValid(roman1, roman2, "L");
                num5 = subtractValid(roman1, roman2, "C");
                num6 = subtractValid(roman1, roman2, "D");
                num7 = subtractValid(roman1, roman2, "M");

            } else if (entered_multiply) {
                num1 = multiplyValid(roman1, roman2, "I");
                num2 = multiplyValid(roman1, roman2, "V");
                num3 = multiplyValid(roman1, roman2, "X");
                num4 = multiplyValid(roman1, roman2, "L");
                num5 = multiplyValid(roman1, roman2, "C");
                num6 = multiplyValid(roman1, roman2, "D");
                num7 = multiplyValid(roman1, roman2, "M");
            }

            //Based on the digits/chars that are not valid, disable input buttons.
            if (num1 < 1) {
                disableButton(R.id.button_I);
                disableButton(R.id.button_V);
                disableButton(R.id.button_X);
                disableButton(R.id.button_L);
                disableButton(R.id.button_C);
                disableButton(R.id.button_D);
                disableButton(R.id.button_M);
            } else if (num2 < 1) {
                disableButton(R.id.button_V);
                disableButton(R.id.button_X);
                disableButton(R.id.button_L);
                disableButton(R.id.button_C);
                disableButton(R.id.button_D);
                disableButton(R.id.button_M);
            } else if (num3 < 1) {
                disableButton(R.id.button_X);
                disableButton(R.id.button_L);
                disableButton(R.id.button_C);
                disableButton(R.id.button_D);
                disableButton(R.id.button_M);
            } else if (num4 < 1) {
                disableButton(R.id.button_L);
                disableButton(R.id.button_C);
                disableButton(R.id.button_D);
                disableButton(R.id.button_M);
            } else if (num5 < 1) {
                disableButton(R.id.button_C);
                disableButton(R.id.button_D);
                disableButton(R.id.button_M);
            } else if (num6 < 1) {
                disableButton(R.id.button_D);
                disableButton(R.id.button_M);
            } else if (num7 < 1) {
                disableButton(R.id.button_M);
            }
        }
    }
}
