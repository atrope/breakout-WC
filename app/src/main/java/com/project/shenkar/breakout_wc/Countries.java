package com.project.shenkar.breakout_wc;

import android.graphics.Color;

public class Countries{

    public enum CountriesEnum {
        POLAND,FRANCE,SENEGAL,MORROCO,PORTUGAL,SPAIN,URUGAY,ENGLAND,COLOMBIA,ARGENTINA,JAPAN,NIGERIA;

    }
    private int[][][] levelMaps;

    Countries(){
        levelMaps = new int[CountriesEnum.values().length][4][12];
    }

    private void generateMap(CountriesEnum country){
        if (country.equals(CountriesEnum.JAPAN)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = ( (row == 1 || row == 2) && (column >= 4 && column <= 7)) ? Color.RED : Color.WHITE;
        }
        else if (country.equals(CountriesEnum.NIGERIA)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (column >= 4 && column <=7) ? Color.WHITE : Color.rgb(0,100,0) ;
        }
        else if (country.equals(CountriesEnum.ARGENTINA)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (row == 2 || row == 1) ? Color.WHITE : Color.rgb(117, 170, 219) ;
        }
        else if (country.equals(CountriesEnum.COLOMBIA)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (row <= 1) ? Color.YELLOW : (row ==2 )? Color.rgb(0, 56, 147) :Color.rgb(206, 17, 38) ;
        }
        else if (country.equals(CountriesEnum.ENGLAND)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (column >= 5 && column <=6 || (row>=1 && row<3)) ? Color.RED : Color.WHITE;
        }
        else if (country.equals(CountriesEnum.URUGAY)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (column <=2 && row <3 || (row==0 || row==2)) ? Color.WHITE : Color.BLUE;
            levelMaps[country.ordinal()][1][1] = Color.YELLOW;
        }
        else if (country.equals(CountriesEnum.PORTUGAL)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (column <= 4) ? Color.rgb(0,100,0) :Color.rgb(255,0,0);
        }
        else if (country.equals(CountriesEnum.SPAIN)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (row==0 || row==3) ? Color.rgb(198, 11, 30) :Color.rgb(255, 196,0);
        }
        else if (country.equals(CountriesEnum.FRANCE)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (column >= 4 && column <=7) ? Color.WHITE : (column < 4 ) ? Color.rgb(0, 35, 149):Color.rgb(237, 41, 57) ;
        }
        else if (country.equals(CountriesEnum.MORROCO)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = Color.rgb(237, 41, 57) ;
        }
        else if (country.equals(CountriesEnum.SENEGAL)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (column >= 4 && column <=7) ? Color.YELLOW : (column < 4 ) ? Color.rgb(0, 100, 0):Color.rgb(237, 41, 57) ;
        }
        else if (country.equals(CountriesEnum.POLAND)){
            for(int row = 0; row < 4; row++)
                for (int column = 0; column < 12; column++)
                    levelMaps[country.ordinal()][row][column] = (row <= 1) ? Color.WHITE : Color.RED ;
        }


    }
    /*
    /* Return Level Colors so we can build it
   */
    public int [][] getLevel(CountriesEnum country){
        generateMap(country);
        return levelMaps[country.ordinal()];
    }
    public int [][] getLevel(int level){
        generateMap(CountriesEnum.values()[level]);
        return levelMaps[level];
    }
}
