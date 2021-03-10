package com.example.romancalculator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Roman {
    int value;
    String str;

    /**
     * Roman constructor that initializes the value and string.
     */
    public Roman() {
        value = 0;
        str = "";
    }

    /**
     * Converts a roman char to its corresponding integer.
     * @param c the char to be converted
     * @return the decimal integer value of the char.
     */
    public static int letterToInt(char c) {
        if (c == 'I') {
            return 1;
        } else if (c == 'V') {
            return 5;
        } else if (c == 'X') {
            return 10;
        } else if (c == 'L') {
            return 50;
        } else if (c == 'C') {
            return 100;
        } else if (c == 'D') {
            return 500;
        } else if (c == 'M') {
            return 1000;
        } else {
            return 0;
        }
    }

    /**
     * Converts integer into roman numeral.
     * @param num the integer to be converted to a roman numeral.
     * @return Roman numeral that corresponds to the inputted integer.
     */
    public static String intToRoman(int num) {
        //Integer cannot be converted to roman if its value is outside of 1-4999.
        if (num < 1 || num > 4999) {
            return "Invalid Integer! Must be 1-4999.";
        }

        String romanNumeral = "";

        int[] digits = new int[4];

        //Store the digits of the inputted integer.
        for (int i = 3; i > -1; i--) {
            digits[i] = num % 10;
            num /= 10;
        }

        //Array which has a specific order helping us determine which roman letters to add to the string to be outputted.
        char[][] letters = {{'M', 'M', 'M'}, {'M', 'D', 'C'}, {'C', 'L', 'X'}, {'X', 'V', 'I'}};

        //Iterate through array of digits and determine which roman letters to add.
        for(int i = 0; i <= 3 ; i++) {

            //If the digit is 0, no roman numerals are added (no value).
            if (digits[i] == 0) continue;

            if(digits[i] <= 3) {
                for (int j = 0; j < digits[i]; j++) {
                    romanNumeral += letters[i][2];
                }
            }

            else if (digits[i] == 4) {
                if (i == 0) {
                    romanNumeral += "MMMM";
                }
                else {
                    romanNumeral += letters[i][2] + "" + letters[i][1];
                }
            }

            else if (digits[i] <= 8) {
                romanNumeral += letters[i][1];

                for (int j = 0; j < digits[i] - 5; j++) {
                    romanNumeral += letters[i][2];
                }
            }

            else {
                romanNumeral += letters[i][2] + "" + letters[i][0];
            }
        }

        //Return a string roman numeral representation of the inputted integer.
        return romanNumeral;
    }

    /**
     * Converts from roman numeral string value to corresponding integer.
     * @param s The roman numeral (String) to be converted to an integer.
     * @return The integer representation of the inputted roman numeral string.
     * @throws RomanNumeralException if roman numeral string is invalid.
     */
    public static int romanToInt(String s) throws RomanNumeralException {
        //An empty roman numeral string is invalid.
        if (s.equals("")) {
            throw new RomanNumeralException();
        }

        String newString = s.toUpperCase();

        char[] letters = newString.toCharArray();
        String possibleLetters = "MDCLXVI";

        //If a non-roman char is found within the roman numeral string, the roman numeral is invalid.
        for (char letter : letters) {
            if (!possibleLetters.contains(String.valueOf(letter))) {
                throw new RomanNumeralException();
            }
        }

        int number = 0;

        //Iterate through the list of letters and add their corresponding values.
        //If a smaller value comes before a larger value, subtract it from the larger value and add the difference to the integer value.
        for (int i = 0; i < letters.length; i++) {
            number += letterToInt(letters[i]);

            if (letters.length != i + 1 && letterToInt(letters[i]) < letterToInt(letters[i + 1])) {
                number -= 2 * letterToInt(letters[i]);
            }
        }

        //If the resulting integer is outside of the integer range of a valid roman numeral, the inputted roman numeral is invalid!
        if (number <= 0 || number >= 5000) throw new RomanNumeralException();

        return number;
    }
}
