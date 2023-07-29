package com.nosferatu.Sebereuapi.utils;

import java.util.List;
import java.util.Objects;

public class Utils {
    public static String stringListToString(List<String> list){
        return String.join(";", list);
    }

    public static List<String> stringToStringList(String string) {

        if(string.isEmpty()) return List.of();

        String[] stringList = string.split(";");

        return List.of(stringList);

    }
}
