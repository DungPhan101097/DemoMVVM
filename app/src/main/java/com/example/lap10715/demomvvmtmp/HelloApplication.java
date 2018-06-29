package com.example.lap10715.demomvvmtmp;

import android.app.Application;

import com.example.lap10715.demomvvmtmp.datamodel.DataModel;
import com.example.lap10715.demomvvmtmp.datamodel.IDataModel;
import com.example.lap10715.demomvvmtmp.viewmodel.MainViewModel;

public class HelloApplication extends Application {
    private final IDataModel mDataModel;

    public HelloApplication() {
        mDataModel = new DataModel();
    }

    public IDataModel getDataModel() {
        return mDataModel;
    }

    public MainViewModel getMainViewModel(){
        return new MainViewModel(getDataModel());
    }
}
