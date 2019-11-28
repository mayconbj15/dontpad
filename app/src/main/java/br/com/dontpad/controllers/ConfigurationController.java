package br.com.dontpad.controllers;

import android.media.VolumeShaper;

import br.com.dontpad.R;

public class ConfigurationController {
    public static boolean isChanged = false;
    public static boolean nightMode = false;

    public static int colorText = R.color.black;
    public static int colorBackground = R.color.white;

    public static void enableNightMode(){
        colorText = R.color.white;
        colorBackground = R.color.black;
    }

    public static void disableNightMode(){
        colorText = R.color.black;
        colorBackground = R.color.white;
    }

    public static void setConfiguration(int colorText, int colorBackground){
        ConfigurationController.colorText = colorText;
        ConfigurationController.colorBackground = colorBackground;
    }
}
