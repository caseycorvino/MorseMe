package com.example.csastudent2015.morseme;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by TIM on 4/14/16.
 */

public class MainActivity extends AppCompatActivity {

    boolean hasFlash;
    EditText text;

    String phrase;
    String string;
    int arrayLength;

    MediaPlayer lm;
    MediaPlayer sm;

    FlashTimerTask t;


    Flashlight f;
    MorseCode morseCode;

    CheckBox flashBox;


    ArrayList<Integer> morseCodeArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f = new Flashlight();
        text = (EditText) findViewById(R.id.morseText);

        flashBox = (CheckBox) findViewById(R.id.flashBox);



        final Context context = this;
        hasFlash = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);



        lm = MediaPlayer.create(getApplicationContext(), R.raw.longmorse);
        sm = MediaPlayer.create(getApplicationContext(), R.raw.shortmorse);

        ImageView imv = (ImageView) findViewById(R.id.morseButton);


        imv.setOnClickListener(new View.OnClickListener() {  //implements the morse button

            @Override
            public void onClick(View v) {


                phrase = text.getText().toString();
                morseCode = new MorseCode(phrase);
                string = morseCode.phraseToMorsePhrase();
                morseCodeArray = morseCode.getArray();
                arrayLength = morseCodeArray.size();

                TextView text2 = (TextView) findViewById(R.id.morsePhrase); //displays morsePhrase as morse code at the bottom
                text2.setText(string);

                if(hasFlash && flashBox.isChecked()){
                    t = new FlashTimerTask();
                    t.execute();

                }
                else if(hasFlash && flashBox.isChecked()) {
                   t = new FlashTimerTask();
                    t.execute();
                }
                else if (!hasFlash && flashBox.isChecked()) {
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

                //cancel async task

                if(t != null && !t.isCancelled()) {
                    t.cancel(true);
                }

            }
        });




        // TODO

        //max length for morsephrase (DONE)
        //lock screen orientation (DONE)
        //close virtual keyboard on enter key (DONE)
        //have correct times for flashes (DONE)
        //async task cancel  (done)
        //import/create media player (done)
        //play sounds
        //themes!
    }



    public void sleep(int milliseconds) {

            try {
                Thread.currentThread().sleep(milliseconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


    public void shortMorse() {

        f.flashlightOn();

        //short sound plays

        sleep(1);

        f.flashlightOff();

    }

    public void longMorse() {

        f.flashlightOn();

        //long sound plays

        sleep(400);

        f.flashlightOff();

    }



    public void spaceMorse() {

        sleep(500);

    }

               public class FlashTimerTask extends AsyncTask<Void, Void, Void> {


                @Override
                protected Void doInBackground(Void... params) {

                        int counter = 0;


                        while (counter < arrayLength && !isCancelled())

                        {

                            if (morseCodeArray.get(counter) == 1) {
                                shortMorse();
                                counter++;
                                //short flash
                            } else if (morseCodeArray.get(counter) == 100) {
                                longMorse();
                                counter++;
                                //long flash
                            } else if (morseCodeArray.get(counter) == 250) {
                                spaceMorse();
                                counter++;
                                //space flash
                            } else {
                                break;
                            }
                        }

            return null;
        }

                   @Override
                   protected void onCancelled() {
                       super.onCancelled();
                   }
               }




}

