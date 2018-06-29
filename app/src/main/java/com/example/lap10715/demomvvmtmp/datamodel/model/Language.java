package com.example.lap10715.demomvvmtmp.datamodel.model;

import android.support.annotation.NonNull;

public class Language {
    public enum LanguageCode{
        EN, VN, CN
    }
    @NonNull
    private String name;
    @NonNull
    private LanguageCode code;

    public Language(@NonNull String name, @NonNull LanguageCode code) {
        this.name = name;
        this.code = code;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public LanguageCode getCode() {
        return code;
    }
}
