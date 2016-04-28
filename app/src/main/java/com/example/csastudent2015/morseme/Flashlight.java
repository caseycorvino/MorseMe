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

    /*public boolean hasFlash() {

        // cam.open();



            if (cam == null) {
                Log.e(TAG, "cam == null");
                return false;
            }

            //p = cam.getParameters();

            if (p.getFlashMode() == null) {
                Log.e(TAG, "p.getFlashMode() == null");
                return false;
            }

            List<String> supportedFlashModes = p.getSupportedFlashModes();
            if (supportedFlashModes == null || supportedFlashModes.isEmpty() || supportedFlashModes.size() == 1 && supportedFlashModes.get(0).equals(Camera.Parameters.FLASH_MODE_OFF)) {
                Log.e(TAG, "array == null");
                return false;
            }

        return true;
    }*/
}
