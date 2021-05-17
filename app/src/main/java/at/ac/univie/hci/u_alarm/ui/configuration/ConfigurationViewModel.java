package at.ac.univie.hci.u_alarm.ui.configuration;

import android.widget.Switch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import at.ac.univie.hci.u_alarm.R;

public class ConfigurationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConfigurationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is configuration fragment");
    }

    public boolean switchToTrue(){
        return true;
    }
    public LiveData<String> getText() {
        return mText;
    }
}