package com.example.csastudent2015.morseme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TIM on 4/14/16.
 */

public class MorseCode {


    private String phrase;  //text taken from user input
    private String morsePhrase = "";    //converts phrase into dashes and dots
    private final static int LONG = 100;   //dash time length
    private final static int SHORT = 1;   //dot time length
    private final static int SPACE = 250;   //space pause length
    private ArrayList<Integer> timer = new ArrayList<Integer>();    //timer to count time elapsed

    public static final Map<String, String> MORSE_MAP = new HashMap<>();    //map used to help convert a character into dashes and dots

    static {
        MORSE_MAP.put("a", ".-");
        MORSE_MAP.put("b", "-...");
        MORSE_MAP.put("c", "-.-.");
        MORSE_MAP.put("d", "-..");
        MORSE_MAP.put("e", ".");
        MORSE_MAP.put("f", "..-.");
        MORSE_MAP.put("g", "--.");
        MORSE_MAP.put("h", "....");
        MORSE_MAP.put("i", "..");
        MORSE_MAP.put("j", ".---");
        MORSE_MAP.put("k", "-.-");
        MORSE_MAP.put("l", ".-..");
        MORSE_MAP.put("m", "--");
        MORSE_MAP.put("n", "-.");
        MORSE_MAP.put("o", "---");
        MORSE_MAP.put("p", ".--.");
        MORSE_MAP.put("q", "--.-");
        MORSE_MAP.put("r", ".-.");
        MORSE_MAP.put("s", "...");
        MORSE_MAP.put("t", "-");
        MORSE_MAP.put("u", "..-");
        MORSE_MAP.put("v", "...-");
        MORSE_MAP.put("w", ".--");
        MORSE_MAP.put("x", "-..-");
        MORSE_MAP.put("y", "--.-");
        MORSE_MAP.put("z", "--..");
        MORSE_MAP.put("0", "-----");
        MORSE_MAP.put("1", ".----");
        MORSE_MAP.put("2", "..---");
        MORSE_MAP.put("3", "...--");
        MORSE_MAP.put("4", "....-");
        MORSE_MAP.put("5", ".....");
        MORSE_MAP.put("6", "-....");
        MORSE_MAP.put("7", "--...");
        MORSE_MAP.put("8", "---..");
        MORSE_MAP.put("9", "----.");
        MORSE_MAP.put(".", ".-.-.-");
        MORSE_MAP.put(",", "--..--");
        MORSE_MAP.put(":", "---...");
        MORSE_MAP.put("?", "..--..");
        MORSE_MAP.put("'", ".----.");
        MORSE_MAP.put("-", "-....-");
        MORSE_MAP.put("/", "-..-.");
        MORSE_MAP.put(" ", " ");

    }

    public MorseCode(String phrase) {   //constructor
        this.phrase = phrase.toLowerCase();
    }

    public String phraseToMorsePhrase() {    //turn phrase into morsePhrase

        for (int i = 0; i < phrase.length(); i++) {  //loops through phrase and converts to morsePhrase using values from MORSE_MAP
            morsePhrase += MORSE_MAP.get(phrase.substring(i, i + 1)) + " ";
        }

        return morsePhrase;
    }

    public void morsePhraseToFlash() {

        for (int i = 0; i < morsePhrase.length(); i++) {


            if (morsePhrase.charAt(i) == '-') {

                    timer.add(LONG);

            }

            else if (morsePhrase.charAt(i) == '.') {

                    timer.add(SHORT);

            }

            else {

                timer.add(SPACE);

            }

        }

    }


    public ArrayList<Integer> getArray() {

        morsePhraseToFlash();
        return timer;

    }
}
