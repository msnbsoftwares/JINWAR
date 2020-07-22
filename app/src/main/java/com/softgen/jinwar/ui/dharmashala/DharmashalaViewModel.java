package com.softgen.jinwar.ui.dharmashala;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DharmashalaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DharmashalaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Dharmashala fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}