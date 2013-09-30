package com.opalab.littlecolor;

public class ColorConverter {

    public static int[] fromStringToIntegers(String color){
        String color_clean = color.replaceFirst("#","");

        return new int[] {
            Integer.parseInt(color_clean.substring(0,2),16),
            Integer.parseInt(color_clean.substring(2,4),16),
            Integer.parseInt(color_clean.substring(4,6),16)
        };
    }

    private static String toIntWithPadding(String number){
        return (number.length() > 1) ? number : "0"+number;
    }

    public static String fromIntegersToString(int[] color){
        return ("#"+
            ColorConverter.toIntWithPadding(Integer.toHexString(color[0]))+
            ColorConverter.toIntWithPadding(Integer.toHexString(color[1]))+
            ColorConverter.toIntWithPadding(Integer.toHexString(color[2]))).toUpperCase();
    }

}
