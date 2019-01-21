package com.busycount.common;

import java.util.regex.Pattern;

/**
 * StringUtil
 * <p>
 * 2019-01-21 | Count.C | Created
 */
public class StringUtil {

    public static final String REG_PHONE = "^(1)\\d{10}$";

    public static boolean checkPhone(String str) {
        if (str == null || str.length() < 11) {
            return false;
        }
        Pattern p = Pattern.compile(REG_PHONE);
        return p.matcher(str).matches();
    }
}
