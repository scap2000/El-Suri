package org.digitall.lib.dictionary;

import java.io.InputStream;

public abstract class DictionaryConfig {

    public static String ES_AR_LANG_PATH = "es_ar/lang.conf";
    public static String EN_US_LANG_PATH = "en_us/lang.conf";
    public static String DEFAULT_LANG_PATH = ES_AR_LANG_PATH;

    public static InputStream getDictionaryURL() {
        return DictionaryConfig.class.getResourceAsStream(DEFAULT_LANG_PATH);
    }

}
