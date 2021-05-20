package at.ac.univie.hci.u_alarm.ui.configuration;

import android.util.Log;
import android.widget.Switch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import at.ac.univie.hci.u_alarm.R;

public class ConfigurationViewModel extends ViewModel {

    private static MutableLiveData<String> mText;
    private static MutableLiveData<String> language;



    public ConfigurationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is configuration fragment");
        language = new MutableLiveData<>();

    }

    public boolean switchToTrue(){
        return true;
    }
    public LiveData<String> getText() {
        return mText;
    }
    public String getLanguage(){
        if(language.getValue()==null){
            language = new MutableLiveData<>();
            language.setValue("Deutsch");
        }
        return this.language.getValue();
    }
    public void setLanguage(String language2){
       this.language.setValue(language2);
        Log.i("SET LANGUAGE:", this.language.getValue());
    }
}