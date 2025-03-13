package utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;

import java.util.HashMap;
import java.util.Map;

public class StrUtils {

    public static Map<Character, Integer> count(String string) {
        Map<Character, Integer> map = new HashMap<>();
        if (StringUtils.isBlank(string)) {
            return null;
        }
        for (char c : string.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }

}
