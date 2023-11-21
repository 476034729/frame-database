
package com.example.database.frame.util;


import com.example.database.frame.constants.FrameConstants;

import java.util.Map;

public class SqlScriptUtils {

    public static String safeParam(final String param) {
        return safeParam(param, null);
    }

    public static String safeParam(final String param, final String mapping) {
        String target = "#{" + param;
        return StringUtils.isBlank(mapping) ? target + "}" : target + "," + mapping + "}";
    }

    public static String getWhereSql(final Map<String, Object> params) {
        StringBuilder str = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, Object> m : params.entrySet()) {
            if (null != m.getValue()) {
                str.append(i == 0 ? FrameConstants.WHERE_START : FrameConstants.AND)
                        .append(String.format(FrameConstants.WHERE_EQUAL, m.getKey(), m.getValue()));
                i++;
            }
        }
        return str.toString();
    }
}
