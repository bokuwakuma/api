package jp.alhinc.orchestra.domain.model;

import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Gender {
    UNKNOWN("0"), MEN("1"), WOMEN("2");

    private static final Map<String, Gender> genderMap;

    static {
        Map<String, Gender> map = new HashMap<>();
        for (Gender gender : values()) {
            map.put(gender.code, gender);
        }
        // TODO 調べる
        genderMap = Collections.unmodifiableMap(map);
    }

    private final String code;
    Gender(String code) {
        this.code = code;
    }

    public static Gender getByCode(String code)  {
        Gender gender = genderMap.get(code);
        Assert.notNull(gender, "gender code is invalid. code : " + code);
        return gender;
    }

    public String getCode() {
        return code;
    }
}
