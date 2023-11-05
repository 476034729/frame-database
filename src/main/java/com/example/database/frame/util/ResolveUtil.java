package com.example.database.frame.util;


import com.example.database.frame.exception.DataBaseFrameException;

/**
 * xml解析反射处理
 *
 * @author tz
 * @createDate 2023-11-03
 */
public class ResolveUtil {

    public static Class<?> resolveClass(String className) {
        if (className == null) {
            return null;
        }
        try {
            return Resources.getClassName(className);
        } catch (Exception e) {
            throw new DataBaseFrameException("Error resolving class. Cause: " + e, e);
        }
    }
}
