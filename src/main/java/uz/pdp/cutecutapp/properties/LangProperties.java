package uz.pdp.cutecutapp.properties;

import org.springframework.stereotype.Component;
import uz.pdp.cutecutapp.enums.Language;
import uz.pdp.cutecutapp.session.SessionUser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class LangProperties {
    private final SessionUser sessionUser;

    public static Properties uz;
    public static Properties ru;
    public static Properties en;
    public static String pathPre = "src/main/resources/i18n/";


    static {
        load();
    }

    public LangProperties(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    private static void load() {
        try (
                FileReader uzReader = new FileReader(pathPre + "uz.properties");
                FileReader ruReader = new FileReader(pathPre + "ru.properties");
                FileReader enReader = new FileReader(pathPre + "en.properties");
        ) {
            uz = new Properties();
            ru = new Properties();
            en = new Properties();
            uz.load(uzReader);
            ru.load(ruReader);
            en.load(enReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


//    public String getKey(String key) {
//        Language language = sessionUser.getLanguage();
//        return getKey(key, language);
//    }

    private String getKey(String key, Language language) {
        String lang = language.getCode();
        if (lang.equalsIgnoreCase("uz")) {
            return uz.getProperty(key);
        } else if (lang.equalsIgnoreCase("ru")) {
            return ru.getProperty(key);
        } else {
            return en.getProperty(key);
        }

    }


}
