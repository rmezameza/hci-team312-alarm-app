package at.ac.univie.hci.u_alarm.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import at.ac.univie.hci.u_alarm.MainActivity;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> actualPlaceTitle;
    private final MutableLiveData<String> actualPlaceContent;
    private final MutableLiveData<String> telephoneTitle;
    private final MutableLiveData<String> telephoneContent;
    private final MutableLiveData<String> emailTitle;
    private final MutableLiveData<String> emailContent;

    public HomeViewModel() {
        this.actualPlaceTitle = new MutableLiveData<>();
        this.actualPlaceContent = new MutableLiveData<>();
        this.telephoneTitle = new MutableLiveData<>();
        this.telephoneContent = new MutableLiveData<>();
        this.emailTitle = new MutableLiveData<>();
        this.emailContent = new MutableLiveData<>();



        if (MainActivity.language.compareTo("English") == 0) {

            this.actualPlaceTitle.setValue("Current location");
            this.actualPlaceContent.setValue("University of Vienna Main Building");

            this.telephoneTitle.setValue("Phone numbers");
            this.telephoneContent.setValue("+43-1-4277-128 81\n" +
                    "+43-1-4277-128 82");

            this.emailTitle.setValue("E-Mail");
            this.emailContent.setValue("siwachthg1(at)univie.ac.at\n" +
                    "siwachthg2(at)univie.ac.at");


        } else{

            this.actualPlaceTitle.setValue("Aktueller Ort");
            this.actualPlaceContent.setValue("Hauptgebäude der Universtität Wien");

            this.telephoneTitle.setValue("Telefon");
            this.telephoneContent.setValue("+43-1-4277-128 81\n" +
                    "+43-1-4277-128 82");

            this.emailTitle.setValue("E-Mail");
            this.emailContent.setValue("siwachthg1(at)univie.ac.at\n" +
                    "siwachthg2(at)univie.ac.at");

        }

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