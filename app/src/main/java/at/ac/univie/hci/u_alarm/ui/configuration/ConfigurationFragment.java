package at.ac.univie.hci.u_alarm.ui.configuration;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Timer;
import java.util.TimerTask;

import at.ac.univie.hci.u_alarm.MainActivity;
import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentConfigurationBinding;
import at.ac.univie.hci.u_alarm.ui.alarmpage.AlarmActivity;

public class ConfigurationFragment extends Fragment {

    private ConfigurationViewModel configurationViewModel;
    private FragmentConfigurationBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configurationViewModel =
                new ViewModelProvider(this).get(ConfigurationViewModel.class);

        binding = FragmentConfigurationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // All switches assigned to a variable.

        //Variable for the alarm test
        Button buttonMock = view.findViewById(R.id.buttonMockAlarm);

        //Variables for buttons to switch the language
        ImageView leftArrow = getActivity().findViewById(R.id.imageViewLeft);
        ImageView rightArrow = getActivity().findViewById(R.id.imageViewRight);

        if (MainActivity.language.compareTo("English") == 0) {
            Log.i("String in LANGUAGE variable:", MainActivity.language);
            setLanguageEnglish();
        } else {
            Log.i("String in LANGUAGE variable", MainActivity.language);
            setLanguageGerman();
        }


        //Listener for Test Alarm button
        buttonMock.setOnClickListener(view12 -> {
            MockTestButtonClicked(view12);
            buttonMock.setEnabled(false);

            //Handler preferable over Timer/TimerTask for performance and reliability reasons.
            Handler buttonDisabler=new Handler();

            // Time set does not really matter (as long as it's longer than the delay until
            // the alarm), as the Button's view will be created anew after stopping the alarm
            // as far as I know.
            buttonDisabler.postDelayed(() -> buttonMock.setEnabled(true),5001);
        });


        //Buttons right and left to change the language.
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView languageText = getActivity().findViewById(R.id.textViewLanguage);
                if(languageText.getText() == "English") {
                    setLanguageGerman();

                    Toast.makeText(view.getContext() , "Sprache auf Deutsch geändert",Toast.LENGTH_SHORT)
                            .show();

                } else {
                    setLanguageEnglish();
                    Toast.makeText(view.getContext() , "Language changed to English",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        leftArrow.setOnClickListener(view1 -> {
            TextView languageText = getActivity().findViewById(R.id.textViewLanguage);
            if(languageText.getText() == "English"){
                setLanguageGerman();
                Toast.makeText(view1.getContext() , "Sprache auf Deutsch geändert",Toast.LENGTH_SHORT).show();

            } else{
                setLanguageEnglish();
                Toast.makeText(view1.getContext() , "Language changed to English",Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void MockTestButtonClicked(View v) {
        new Timer().schedule(new TimerTask(){
            public void run() {
                getActivity().runOnUiThread(() -> startActivity(new Intent(getActivity(), AlarmActivity.class)));
            }
        }, 5000);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Method sets every texView' information to German
    private void setLanguageGerman(){

        TextView languageText = getActivity().findViewById(R.id.textViewLanguage);
        languageText.setText("Deutsch");

        TextView languageTitle = getActivity().findViewById(R.id.languageText);
        languageTitle.setText("Sprache");

        TextView colorsText = getActivity().findViewById(R.id.colorsText);
        colorsText.setText("Farbkombination");

        TextView functionsTitleText = getActivity().findViewById(R.id.textFunctions);
        functionsTitleText.setText("Funktionstest");

        TextView functionsSubtitle = getActivity().findViewById(R.id.textFunctions2);
        functionsSubtitle.setText("Ein Testalarm zur Prüfung der Funktionalitäten.");

        configurationViewModel.setLanguage("Deutsch");
        Log.i("LANGUAGE CHANGED:","Deutsch");

        Button blue = getActivity().findViewById(R.id.BlueButton);
        blue.setText("Blau");
        Button Grenn = getActivity().findViewById(R.id.GreenButton);
        Grenn.setText("Grün");

        // Change the Language in Alarm Page and View List
        MainActivity.language = "Deutsch";
        //AlarmListFragment.language = "Deutsch";
        AlarmActivity.ALARM_NAME = "Feuer Alarm";
        AlarmActivity.ALARM_PLACE = "Erdgeschoss";
        AlarmActivity.ALARM_INFO = "Sammelpunkt : Haupteingang";

    }


    // Method sets every texView' information to English
    private void setLanguageEnglish(){

        TextView languageText = getActivity().findViewById(R.id.textViewLanguage);
        languageText.setText("English");

        TextView languageTitle = getActivity().findViewById(R.id.languageText);
        languageTitle.setText("Language");

        TextView colorsText = getActivity().findViewById(R.id.colorsText);
        colorsText.setText("Color Combination");

        TextView functionsTitleText = getActivity().findViewById(R.id.textFunctions);
        functionsTitleText.setText("Functionality Test");

        TextView functionsSubtitle = getActivity().findViewById(R.id.textFunctions2);
        functionsSubtitle.setText("This is a mock alarm for testing the functionalities.");

        Button blue = getActivity().findViewById(R.id.BlueButton);
        blue.setText("Blue");
        Button Grenn = getActivity().findViewById(R.id.GreenButton);
        Grenn.setText("Green");

        configurationViewModel.setLanguage("English");
        Log.i("LANGUAGE CHANGED:","English");

        // Change the Language in Alarm Page and View List
        //AlarmListFragment.language = "English";
        MainActivity.language = "English";
        AlarmActivity.ALARM_NAME = "Fire Alarm ";
        AlarmActivity.ALARM_PLACE = "Ground Floor";
        AlarmActivity.ALARM_INFO = "Gathering Point : Main Entrance";
    }
}