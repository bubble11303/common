package annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.*;

/**
 * 是否不经过拦截 直接返回
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DirectReturn {

    /**
     * 是否包装返回
     */
    boolean wrapper() default false;

    /**
     * 正常返回httpcode码
     */
    HttpStatus status() default HttpStatus.OK;

}