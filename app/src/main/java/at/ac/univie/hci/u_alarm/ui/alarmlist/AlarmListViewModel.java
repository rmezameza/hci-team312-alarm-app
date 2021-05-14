package at.ac.univie.hci.u_alarm.ui.alarmlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlarmListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AlarmListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is alarm list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}