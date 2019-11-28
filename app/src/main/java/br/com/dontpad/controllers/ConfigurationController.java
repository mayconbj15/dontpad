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

    public static void changeTextColor(Object color){
        switch ((String)color){
            case "Vermelho": colorText = R.color.red; break;

            case "Laranja": colorText = R.color.orange; break;

            case "Amarelo": colorText = R.color.yellow; break;

            case "Verde": colorText = R.color.green; break;

            case "Azul": colorText = R.color.blue; break;

            case "Anil": colorText = R.color.darkBlue; break;

            case "Violeta": colorText = R.color.violet; break;
        }
    }

    public static void changeBackgroundColor(Object color){
        switch ((String)color){
            case "Vermelho": colorBackground = R.color.red; break;

            case "Laranja": colorBackground = R.color.orange; break;

            case "Amarelo": colorBackground = R.color.yellow; break;

            case "Verde": colorBackground = R.color.green; break;

            case "Azul": colorBackground = R.color.blue; break;

            case "Anil": colorBackground = R.color.darkBlue; break;

            case "Violeta": colorBackground = R.color.violet; break;
        }
    }
}
