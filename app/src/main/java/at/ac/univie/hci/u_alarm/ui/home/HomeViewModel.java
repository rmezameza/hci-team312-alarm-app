package at.ac.univie.hci.u_alarm.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import at.ac.univie.hci.u_alarm.ui.configuration.ConfigurationViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> actualPlaceTitle;
    private final MutableLiveData<String> actualPlaceContent;
    private final MutableLiveData<String> telephoneTitle;
    private final MutableLiveData<String> telephoneContent;
    private final MutableLiveData<String> emailTitle;
    private final MutableLiveData<String> emailContent;
    private ConfigurationViewModel configurationViewModel;

    public HomeViewModel() {


        this.actualPlaceTitle = new MutableLiveData<>();
        this.actualPlaceContent = new MutableLiveData<>();
        this.telephoneTitle = new MutableLiveData<>();
        this.telephoneContent = new MutableLiveData<>();
        this.emailTitle = new MutableLiveData<>();
        this.emailContent = new MutableLiveData<>();

        this.actualPlaceTitle.setValue("Aktueller Ort");
        this.actualPlaceContent.setValue("Hauptgebäude der Universtität Wien");

        this.telephoneTitle.setValue("Telefon");
        this.telephoneContent.setValue("+43-1-4277-128 81\n" +
                "+43-1-4277-128 82");

        this.emailTitle.setValue("E-Mail");
        this.emailContent.setValue("siwachthg1(at)univie.ac.at\n" +
                "siwachthg2(at)univie.ac.at");

        //configurationViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ConfigurationViewModel .class);


    }

    public LiveData<String> getActualPlaceTitle() {
        return this.actualPlaceTitle;
    }
    public LiveData<String> getActualPlaceContent() { return this.actualPlaceContent; }
    public LiveData<String> getTelephoneTitle() { return this.telephoneTitle; }
    public LiveData<String> getTelephoneContent() { return this.telephoneContent; }
    public LiveData<String> getEmailTitle() { return this.emailTitle; }
    public LiveData<String> getEmailContent() { return this.emailContent; }
}