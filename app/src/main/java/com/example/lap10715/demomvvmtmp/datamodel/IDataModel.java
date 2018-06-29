package com.example.lap10715.demomvvmtmp.datamodel;

import android.support.annotation.NonNull;

import com.example.lap10715.demomvvmtmp.datamodel.model.Language;

import java.util.List;

import io.reactivex.Observable;

public interface IDataModel {
    @NonNull
    Observable<List<Language>> getAllLanguages();
    @NonNull
    Observable<String> getHelloByLanguageCode(@NonNull Language.LanguageCode code);


}
