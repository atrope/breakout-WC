package com.project.shenkar.breakout_wc;

import android.graphics.Color;

public class Countries{

    public enum CountriesEnum {
        JAPAN,NIGERIA;
    }
    private int[][][] levelmaps;

    Countries(){
        levelmaps = new int[2][4][12];
    }

    private void generateMap(CountriesEnum country){
        if (country.equals(CountriesEnum.JAPAN)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelmaps[country.ordinal()][row][column] = ( (row == 1 || row == 2) && (column >= 4 && column <= 7)) ? Color.RED : Color.WHITE;
        }
        else if (country.equals(CountriesEnum.NIGERIA)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelmaps[country.ordinal()][row][column] = (column >= 4 && column <=7) ? Color.WHITE : Color.rgb(0,100,0) ;
        }
    }
    /*
    /* Return Level Colors so we can build it
   */
    public int [][] getLevel(CountriesEnum country){
        generateMap(country);
        return levelmaps[country.ordinal()];
    }
}
