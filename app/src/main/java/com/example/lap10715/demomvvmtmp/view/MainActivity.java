package com.example.lap10715.demomvvmtmp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lap10715.demomvvmtmp.HelloApplication;
import com.example.lap10715.demomvvmtmp.R;
import com.example.lap10715.demomvvmtmp.datamodel.model.Language;
import com.example.lap10715.demomvvmtmp.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mMainViewModel;
    private CompositeDisposable mcompositeDisposable ;
    private TextView mTvHello;
    private Spinner mSpnLanguages;
    private List<Language> mLanguageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvHello = findViewById(R.id.tv_hello);
        mSpnLanguages = findViewById(R.id.spn_list_language);

        HelloApplication helloApplication = (HelloApplication) getApplication();
        mMainViewModel = helloApplication.getMainViewModel();

        mSpnLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Language language = mLanguageList.get(position);
                mMainViewModel.languageSelected(language);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbind();
    }

    private void bind(){
        mcompositeDisposable = new CompositeDisposable();

        mcompositeDisposable.add(mMainViewModel.getSupportedLanguages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(languages -> {
                    this.mLanguageList = languages;
                    List<String> listString = new ArrayList<>();
                    for(Language elem : languages){
                        listString.add(elem.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_spinner_dropdown_item, listString);

                    mSpnLanguages.setAdapter(adapter);
                }));

        mcompositeDisposable.add(mMainViewModel.getHello()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> mTvHello.setText(s)));
    }

    private void unbind() {
        mcompositeDisposable.clear();
    }

}
