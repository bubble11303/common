package config.i18n;

import static java.util.Locale.ENGLISH;

import java.util.Locale;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.HeaderConstant;
import enums.Language;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyLocaleResolver implements LocaleResolver {

    @Resource
    private HttpServletRequest httpServletRequest;

    public Locale getLocal() {
        return resolveLocale(httpServletRequest);
    }

    /**
     * 从HttpServletRequest中获取Locale
     *
     * @param httpServletRequest httpServletRequest
     * @return 语言Local
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String headerLang = httpServletRequest.getHeader(HeaderConstant.language);
        String language = StringUtils.isBlank(headerLang) ? Language.ENU.localCode() : headerLang;
        Locale locale = ENGLISH;
        if (!StringUtils.isEmpty(language)) {
            try {
                locale = new Locale(Objects.requireNonNull(Language.of(language)).localCode());
            } catch (Exception e) {
                log.error(" 获取国际化配置失败 : {}", e.getMessage(), e);
            }
        }
        return locale;
    }

    /**
     * 用于实现Locale的切换。比如SessionLocaleResolver获取Locale的方式是从session中读取，但如果
     * 用户想要切换其展示的样式(由英文切换为中文)，那么这里的setLocale()方法就提供了这样一种可能
     *
     * @param request HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param locale locale
     */
    @Override
    public void setLocale(@NonNull HttpServletRequest request, @Nullable HttpServletResponse httpServletResponse,
        @Nullable Locale locale) {

    }

}
