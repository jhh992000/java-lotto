package my.project.utils;

import my.project.messages.ExceptionMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created : 2020-11-20 오전 11:55
 * Developer : Seo
 */
public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBlank(String param) {
        return param == null || param.trim().isEmpty();
    }

    public static void isQualified(String param) {
        if (param == null || param.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STRING_IS_NULL);
        }
    }

    public static List<Integer> parseToIntList(String param) {
        List<Integer> list = new ArrayList<>();
        for (String s : param.split(",")) {
            list.add(Integer.valueOf(s));
        }
        return list;
    }

}
