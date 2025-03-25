package exception;

/**
 * @author: 你先别说话
 * @since: 2025/03/13
 * @Description:
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     * @return
     */
    int getResultCode();

    /**
     * 错误描述
     * @return
     */
    String getResultMsg();
}
