package exception;

import enums.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import response.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @Author: 热心村民王富贵
 * @Date: 2025/03/13
 * @Description:
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理RunTimeException异常
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResult runtimeExceptionHandler(RuntimeException e) {
        log.error("runtime：", e);
        return BaseResult.error("");
    }

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public BaseResult bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常！请求地址:{}原因是：  {}", req.getRequestURI(), e.getErrorMsg(), e);
        return BaseResult.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public BaseResult exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return BaseResult.error(CommonEnum.unknown);
    }

    @ExceptionHandler(value = ClassCastException.class)
    @ResponseBody
    public BaseResult resourceHttpRequestHandler(HttpServletRequest req, NullPointerException e) {
        log.error("404！原因是:", e);
        return BaseResult.error(CommonEnum.unknown);
    }

    /**
     * 请求参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws UnsupportedEncodingException {
        // 从异常对象中拿到ObjectError对象
        String result = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("请求参数异常:", e);
        log.error("错误原因:{}", result);
        return BaseResult.error(400, result);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public BaseResult MethodArgumentNotValidExceptionHandler(HttpMessageNotReadableException e) {
        return BaseResult.error("Request params error");
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return BaseResult.error(CommonEnum.system_error);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public BaseResult exceptionHandler(MissingServletRequestParameterException e) {
        log.error("请求参数缺失！原因是:", e);
        return BaseResult.error(e.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public BaseResult exceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.error("不支持的请求方式！原因是:", e);
        return BaseResult.error(e.getMessage());
    }
}


