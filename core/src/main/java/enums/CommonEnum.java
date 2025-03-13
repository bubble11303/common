package enums;

import exception.BaseErrorInfoInterface;
import lombok.Getter;

/**
 * @Author: 热心村民王富贵
 * @Date: 2025/03/13
 * @Description:
 */

@Getter
public enum CommonEnum implements BaseErrorInfoInterface {

    //系统相关(常用)
    success(0, "success"),

    system_error(1000, "前方太拥挤，请稍后重试 !"),
    not_found(1004, "Not found!"),
    request_params_error(1005, "Request params error"),

    // 未登录
    user_token_error(2000, "Token error"),
    not_login(2001, "登录信息已失效，请重新登录"),

    password_rules(2002, "Your password does not comply with the rules"),

    file_too_large(2003, "文件大小不能超过 10 MB"),

    token_ex(2005, "登录信息已失效，请重新登录"),
    account_not_exist(2006, "This account doesn't exist"),
    bank_not_exist(2007, "This bank card doesn't exist"),

    unknown(9999, "Unknown"),
    ;


    /**
     * 错误码
     */
    private final int resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    CommonEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

}

