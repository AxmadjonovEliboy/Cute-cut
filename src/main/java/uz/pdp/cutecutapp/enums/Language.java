package uz.pdp.cutecutapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Language {

    UZ("uz","Uzbek"),
    RU("ru","Russian"),
    EN("en","English");

    private String code;
    private String name;


    public static Language getByCode(String language){
        for (Language language1 : values()){
            if (language1.code.equalsIgnoreCase("uz")) return language1;
        }
        return null;
    }
}
