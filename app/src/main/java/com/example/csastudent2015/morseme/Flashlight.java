package com.example.csastudent2015.morseme;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;


/**
 * Created by TIM on 4/18/16.
 */
public class Flashlight {

    private static final String TAG = "Flashlight";
    Camera cam;
    Parameters p;


    public void flashlightOn() {


        cam = Camera.open();
        p = cam.getParameters();
        p.setFlashMode(Parameters.FLASH_MODE_TORCH);

        cam.setParameters(p);
        cam.startPreview();


    }

    public void flashlightOff() {

        cam.stopPreview();
        cam.release();

    }

}
