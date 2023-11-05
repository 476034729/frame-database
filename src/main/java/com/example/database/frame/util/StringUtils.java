package com.example.database.frame.util;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
public class StringUtils {

    public static String convertToCamelCase(String databaseName) {
        StringBuilder result = new StringBuilder();
        boolean toUpperCase = false;

        for (int i = 0; i < databaseName.length(); i++) {
            char ch = databaseName.charAt(i);
            if (ch == '_') {
                toUpperCase = true;
            } else {
                if (toUpperCase) {
                    result.append(Character.toUpperCase(ch));
                    toUpperCase = false;
                } else {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }

}
