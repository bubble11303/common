package config;

import annotation.DirectReturn;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import response.BaseResult;

import java.util.Objects;

/**
 * @author: 你先别说话
 * @since: 2025/03/13
 * @Description:
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }


    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 是否需要跳过包装
        DirectReturn directReturn = methodParameter.getExecutable().getAnnotation(DirectReturn.class);
        if (!Objects.isNull(directReturn)) {
            return obj;
        }

        // 如果Controller直接返回String的话，SpringBoot会直接返回，手动转换为json。
        if (obj instanceof String) {
            return objectMapper.writeValueAsString(BaseResult.success(obj));
        }
        // 全局异常处理器已经封装好了返回格式，直接返回即可。
        if (obj instanceof BaseResult) {
            return obj;
        }
        return BaseResult.success(obj);
    }
}

