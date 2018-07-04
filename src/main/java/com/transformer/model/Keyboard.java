package com.transformer.model;

import java.util.Arrays;

public class Keyboard {
    private static final String[][] KEYS = new String[][] {
            { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" },
            { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p" },
            { "a", "s", "d", "f", "g", "h", "j", "k", "l", ";" },
            { "z", "x", "c", "v", "b", "n", "m", ",", ".", "/" }
    };

    public static final String[] ALL_KEYS = Arrays.stream(KEYS)
            .flatMap(Arrays::stream)
            .toArray(String[]::new);

    public static final int ROW_COUNT = KEYS.length;
    public static final int COLUMN_COUNT = KEYS[0].length;
    public static final int KEY_COUNT = ALL_KEYS.length;

    public static String[] getRow(int index) {
        return KEYS[index];
    }

    public static String[] getColumn(int index){
        String[] column = new String[KEYS.length];
        for(int i=0; i < column.length; i++){
            column[i] = KEYS[i][index];
        }
        return column;
    }

}
