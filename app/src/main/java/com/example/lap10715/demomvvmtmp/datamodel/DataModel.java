package com.example.lap10715.demomvvmtmp.datamodel;

import android.support.annotation.NonNull;

import com.example.lap10715.demomvvmtmp.datamodel.model.Language;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class DataModel implements IDataModel {
    @NonNull
    @Override
    public Observable<List<Language>> getAllLanguages() {
        return Observable.fromCallable(this::getSupportLanguage);
    }

    @NonNull
    @Override
    public Observable<String> getHelloByLanguageCode(@NonNull Language.LanguageCode code) {
        switch (code){
            case EN:
                return Observable.just("Hello");
            case VN:
               return Observable.just("Xin chao");
            case CN:
                return Observable.just("Ola");
                default:
                    return Observable.just("");
        }
    }

    private List<Language> getSupportLanguage(){
        List<Language> languageList = new ArrayList<>();
        languageList.add(new Language("Tiếng Anh", Language.LanguageCode.EN));
        languageList.add(new Language("Việt Nam", Language.LanguageCode.VN));
        languageList.add(new Language("Bồ Đào Nha", Language.LanguageCode.CN));

        return languageList;
    }
}
