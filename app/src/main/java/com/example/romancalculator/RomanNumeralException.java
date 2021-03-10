/**
 * @author: Dev Patel
 * @description: A roman numeral exception that is thrown whenever an invalid roman numeral is inputted and processed.
 * @date: 01/29/2021
 */

package com.example.romancalculator;

public class RomanNumeralException extends Exception {
    public RomanNumeralException() {
        super("Invalid Roman Numeral!");
    }
}
