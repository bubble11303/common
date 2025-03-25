package enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {

    /**
     * 英语
     */
    ENU("ENU", "en", "英语", "en-US", "English"),
    /**
     * 中文
     */
    ZHC("ZHC", "zh", "中文", "zh-CN", "中文"),
    /**
     * 越南语
     */
    VIT("VIT", "vi", "越南语", "vi-VN", "Tiếng Việt"),
    /**
     * 印地语
     */
    HIN("HIN", "hi", "印地语", "hi-IN", "हिंदी"),
    /**
     * 葡萄牙语
     */
    PTB("PTB", "pt", "葡萄牙语", "pt-BR", "Português"),
    /**
     * 泰语
     */
    THA("THA", "th", "泰语", "th-TH", "ไทย"),
    /**
     * 俄语
     */
    RUS("RUS", "ru", "俄语", "ru-RU", "Русский"),
    /**
     * 法语
     */
    FRA("FRA", "fr", "法语", "fr-FR", "Français"),
    /**
     * 韩语
     */
    KOR("KOR", "ko", "韩语", "ko-KR", "한국어"),
    /**
     * 西班牙语
     */
    ESP("ESP", "es", "西班牙语", "es-ES", "español"),
    /**
     * 意大利语
     */
    ITA("ITA", "ita", "意大利语", "it-IT", "Italiano"),
    /**
     * 繁体中文
     */
    CHT("CHT", "zh_CHT", "繁体中文", "zh-CHT", "繁体中文"),
    /**
     * 日文
     */
    JPN("JPN", "jpn", "日语", "ja-JP", "日本語"),
    /**
     * 印尼文
     */
    IND("IND", "ind", "印尼文", "id-ID", "Bahasa Indonesia");

    @JsonValue
    private final String code;
    private final String localCode;
    private final String languageName;
    private final String languageCultureName;
    private final String displayName;

    private Language(String code, String localCode, String languageName, String languageCultureName, String displayName) {
        this.code = code;
        this.localCode = localCode;
        this.languageName = languageName;
        this.languageCultureName = languageCultureName;
        this.displayName = displayName;
    }

    @JsonCreator
    public static Language of(String code) {
        for (Language language : values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        return null;
    }

    /**
     * 语言编码
     */
    public String languageCode() {
        return this.code;
    }

    public String localCode() {
        return this.localCode;
    }

    /**
     * 语言名称
     */
    public String languageName() {
        return this.languageName;
    }

    /**
     * 语言文化名称
     */
    public String languageCultureName() {
        return this.languageCultureName;
    }

    /**
     * 显示名称
     */
    public String displayName() {
        return this.displayName;
    }

}
