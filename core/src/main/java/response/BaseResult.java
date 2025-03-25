package response;

import com.alibaba.fastjson.JSONObject;
import constant.HeaderConstant;
import enums.CommonEnum;
import exception.BaseErrorInfoInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @author: 你先别说话
 * @since: 2025/03/13
 * @Description:
 */
@Data
@AllArgsConstructor
public class BaseResult<T> implements Serializable {

    private int code;

    private T data;

    private String msg;

    private Boolean result;

    private String traceId;

    private long timestamp;

    public BaseResult() {
    }

    public BaseResult(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.msg = errorInfo.getResultMsg();
        this.timestamp = System.currentTimeMillis();
        this.traceId = MDC.get(HeaderConstant.traceId);
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> BaseResult success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> BaseResult success(Object data) {
        BaseResult rb = new BaseResult();
        rb.setCode(CommonEnum.success.getResultCode());
        rb.setMsg(CommonEnum.success.getResultMsg());
        rb.setData(data);
        rb.setResult(true);
        rb.setTraceId(MDC.get(HeaderConstant.traceId));
        rb.setTimestamp(System.currentTimeMillis());
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResult error(BaseErrorInfoInterface errorInfo) {
        BaseResult rb = new BaseResult();
        rb.setCode(errorInfo.getResultCode());
        rb.setMsg(errorInfo.getResultMsg());
        rb.setTraceId(MDC.get(HeaderConstant.traceId));
        rb.setData(null);
        rb.setResult(false);
        rb.setTimestamp(System.currentTimeMillis());
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResult error(int code, String message) {
        BaseResult rb = new BaseResult();
        rb.setCode(code);
        rb.setMsg(message);
        rb.setTraceId(MDC.get(HeaderConstant.traceId));
        rb.setTimestamp(System.currentTimeMillis());
        rb.setData(null);
        rb.setResult(false);
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResult error(String message) {
        BaseResult rb = new BaseResult();
        rb.setCode(-1);
        rb.setMsg(message);
        rb.setTraceId(MDC.get(HeaderConstant.traceId));
        rb.setTimestamp(System.currentTimeMillis());
        rb.setData(null);
        rb.setResult(false);
        return rb;
    }

    /**
     * 其他异常处理方法返回的结果
     */
    public static BaseResult error(CommonEnum errorEnum) {
        BaseResult result = new BaseResult();
        result.setMsg(errorEnum.getResultMsg());
        result.setCode(errorEnum.getResultCode());
        result.setTimestamp(System.currentTimeMillis());
        result.setTraceId(MDC.get(HeaderConstant.traceId));
        result.setResult(false);
        return result;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}

