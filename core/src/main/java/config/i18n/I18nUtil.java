package config.i18n;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

import javax.annotation.PostConstruct;

import enums.Language;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class I18nUtil {

    private static final String basename = "static/i18n/message";

    private final MyLocaleResolver resolver;

    private static MyLocaleResolver customLocaleResolver;

    private static String path;

    public I18nUtil(MyLocaleResolver resolver) {
        this.resolver = resolver;
    }

    @PostConstruct
    public void init() {
        setBasename(basename);
        setCustomLocaleResolver(resolver);
    }

    /**
     * 获取 国际化后内容信息
     *
     * @param code 国际化key
     * @return 国际化后内容信息
     */
    public static String get(String code) {
        Locale locale = customLocaleResolver.getLocal();
        return getMessage(code, null, code, locale);
    }

    /**
     * 获取指定语言中的国际化信息，如果没有则走英文
     *
     * @param code 国际化 key
     * @param lang 语言参数
     * @return 国际化后内容信息
     */
    public static String getMessage(String code, String lang) {
        Locale locale;
        if (StringUtils.isEmpty(lang)) {
            locale = Locale.ENGLISH;
        } else {
            try {
                locale = new Locale(Objects.requireNonNull(Language.of(lang)).localCode());
            } catch (Exception e) {
                locale = Locale.ENGLISH;
            }
        }
        return getMessage(code, null, code, locale);
    }

    /**
     * 获取站内信指定语言 目前只支持 中文与英文两类 默认英文
     *
     * @param code 国际化 key
     * @param lang 语言参数
     * @return 国际化后内容信息
     */
    public static String getStationLetterMessage(String code, String lang) {
        Locale locale = Objects.equals(lang, "zh-CN") ? Locale.SIMPLIFIED_CHINESE : Locale.US;
        return getMessage(code, null, code, locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        messageSource.setBasename(path);
        String content;
        try {
            content = messageSource.getMessage(code, args, locale);
        } catch (Exception e) {
            log.error("国际化参数获取失败===>{}", e.getMessage());
            content = defaultMessage;
        }
        return content;

    }

    public static void setBasename(String basename) {
        I18nUtil.path = basename;
    }

    public static void setCustomLocaleResolver(MyLocaleResolver resolver) {
        I18nUtil.customLocaleResolver = resolver;
    }
}
