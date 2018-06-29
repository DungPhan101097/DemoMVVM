package com.example.lap10715.demomvvmtmp.viewmodel;

import com.example.lap10715.demomvvmtmp.datamodel.IDataModel;
import com.example.lap10715.demomvvmtmp.datamodel.model.Language;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MainViewModel {

    private IDataModel mDataModel;
    private BehaviorSubject<Language> mBehaviorSubject = BehaviorSubject.create();

    public MainViewModel(IDataModel mDataModel) {
        this.mDataModel = mDataModel;
    }

    public Observable<String> getHello() {
        Observable<String> observableHello = mBehaviorSubject
                .observeOn(Schedulers.io())
                .map(Language::getCode)
                .flatMap(mDataModel::getHelloByLanguageCode);
        return observableHello;
    }

    public Observable<List<Language>> getSupportedLanguages() {
        return mDataModel.getAllLanguages();

    }

    public void languageSelected(Language language) {
        mBehaviorSubject.onNext(language);
    }
}
