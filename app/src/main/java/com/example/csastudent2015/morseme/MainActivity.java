package com.example.csastudent2015.morseme;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by TIM on 4/14/16.
 */

public class MainActivity extends AppCompatActivity {

    Flashlight f = new Flashlight();
    boolean hasFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        hasFlash = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);


        ImageView imv = (ImageView) findViewById(R.id.morseButton);


        imv.setOnClickListener(new View.OnClickListener() {  //implements the morse button
            @Override
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.morseText);
                String phrase = text.getText().toString();

                MorseCode morseCode = new MorseCode(phrase);
                String string = morseCode.phraseToMorsePhrase();

                ArrayList<Integer> morseCodeArray = morseCode.getArray();
                int arrayLength = morseCodeArray.size();

                TextView text2 = (TextView) findViewById(R.id.morsePhrase); //displays morsePhrase as morse code at the bottom
                text2.setText(string);



                if (hasFlash) {

                 int counter = 0;

                    while(counter < arrayLength)
                    {
                        if(morseCodeArray.get(counter) == 1) {
                            shortMorse();
                            counter++;
                            //Toast.makeText(MainActivity.this, "short flash", Toast.LENGTH_SHORT).show();
                        }
                        else if(morseCodeArray.get(counter) == 100) {
                            longMorse();
                            counter++;
                            //Toast.makeText(MainActivity.this, "long flash", Toast.LENGTH_SHORT).show();
                        }
                        else if(morseCodeArray.get(counter) == 250) {
                            spaceMorse();
                            counter++;
                        }
                    else {
                            break;
                        }
                    }
                }
                else {

                    Toast.makeText(MainActivity.this, "Your device does not support flash.", Toast.LENGTH_SHORT).show();

                }
                }


        });

        ImageView imv1 = (ImageView) findViewById(R.id.clearButton);

        imv1.setOnClickListener(new View.OnClickListener() { //implements the clear button
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.morseText)).setText("");
                ((TextView) findViewById(R.id.morsePhrase)).setText("");

                /*if (hasFlash) {
                    f.flashlightOff();
                }*/
            }
        });

        //to do
        //limit is 40 characters
        //have the morsePhrase build itself as time goes on
        //have the app work as intended
    }

    public void shortMorse() {

        f.flashlightOn();

        sleep(1, hasFlash);

        f.flashlightOff();
    }

    public void longMorse() {

        f.flashlightOn();

        sleep(100, hasFlash);

        f.flashlightOff();
    }

    public void spaceMorse() {

        sleep(250, hasFlash);

    }

    public void sleep(int milliseconds, boolean hasFlash) {

        if(hasFlash) {
            try {
                Thread.currentThread().sleep(milliseconds);
            }
            catch(InterruptedException e) {
                f.flashlightOff();
                Thread.currentThread().interrupt();
            }
        }
        else {
            try {
                Thread.currentThread().sleep(milliseconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
