package utils;

import java.util.UUID;

/**
 * @author: 你先别说话
 * @since: 2025/03/13
 * @Description:
 */
public class IDUtil {

    /**
     * 获取随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

}
