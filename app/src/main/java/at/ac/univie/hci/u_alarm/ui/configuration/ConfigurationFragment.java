package at.ac.univie.hci.u_alarm.ui.configuration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Timer;
import java.util.TimerTask;

import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentConfigurationBinding;
import at.ac.univie.hci.u_alarm.ui.AlarmPage.AlarmActivity;

public class ConfigurationFragment extends Fragment {

    private ConfigurationViewModel configurationViewModel;
    private FragmentConfigurationBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configurationViewModel =
                new ViewModelProvider(this).get(ConfigurationViewModel.class);

        binding = FragmentConfigurationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*
        final TextView textView = binding.textConfiguration;
       configurationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        }); */



        return root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // All switches assigned to a variable.
        Switch switchVibration = (Switch) getView().findViewById(R.id.switchVibration);
        Switch switchLicht = (Switch) getView().findViewById(R.id.switchLight);
        Switch switchFlash = (Switch) getView().findViewById(R.id.switchFlash);
        Switch switchDisplay = (Switch) getView().findViewById(R.id.switchDisplay);

        //Variable for the alarm test
        Button buttonTest = (Button)view.findViewById(R.id.buttonTestAlarm);

        //Variables for buttons to switch the language
        ImageView leftArrow = (ImageView) getActivity().findViewById(R.id.imageViewLeft);
        ImageView rightArrow = (ImageView) getActivity().findViewById(R.id.imageViewRight);

        //Listener for Vibration Switch
        switchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Log.i("TEST ON SWITCH:","IT WORKS.");

                } else {

                }
            }
        });

        //Listener for Light Switch
        switchLicht.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){

                } else {

                }
            }
        });

        //Listener for Flash Switch
        switchFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){

                } else {

                }
            }
        });

        //Listener for Display Switch
        switchDisplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){

                } else {

                }
            }
        });

        //Listener for Test Alarm button
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmTestButtonClicked(view);
            }
        });


        //Buttons right and left to change the language.
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView languageText = getActivity().findViewById(R.id.textViewLanguage);
                languageText.setText("English");

                TextView languageTitle = getActivity().findViewById(R.id.languageText);
                languageTitle.setText("Language");

                TextView colorsText = getActivity().findViewById(R.id.colorsText);
                colorsText.setText("Color combination");

                TextView functionsTitleText = getActivity().findViewById(R.id.textFunctions);
                functionsTitleText.setText("Tests");

                TextView textVibration = getActivity().findViewById(R.id.textViewVibration);
                textVibration.setText("Vibration");

                TextView textLight = getActivity().findViewById(R.id.textViewLight);
                textLight.setText("Light");

                TextView textFlash = getActivity().findViewById(R.id.textViewFlash);
                textFlash.setText("Flash");

                TextView textDisplay = getActivity().findViewById(R.id.textViewDisplay);
                textDisplay.setText("Display");

                buttonTest.setText("Alarm Test");


            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView languageText = getActivity().findViewById(R.id.textViewLanguage);
                languageText.setText("Deutsch");

                TextView languageTitle = getActivity().findViewById(R.id.languageText);
                languageTitle.setText("Sprache");

                TextView colorsText = getActivity().findViewById(R.id.colorsText);
                colorsText.setText("Farbkombination");

                TextView functionsTitleText = getActivity().findViewById(R.id.textFunctions);
                functionsTitleText.setText("Funktionstest");

                TextView textVibration = getActivity().findViewById(R.id.textViewVibration);
                textVibration.setText("Vibrationtest");

                TextView textLight = getActivity().findViewById(R.id.textViewLight);
                textLight.setText("Lighttest");

                TextView textFlash = getActivity().findViewById(R.id.textViewFlash);
                textFlash.setText("Flashtest");

                TextView textDisplay = getActivity().findViewById(R.id.textViewDisplay);
                textDisplay.setText("Displaytest");

                buttonTest.setText("Alarmprobe");

            }
        });



    }

    public void AlarmTestButtonClicked(View v) {
        new Timer().schedule(new TimerTask(){
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(getActivity(), AlarmActivity.class));
                    }
                });
            }
        }, 5000);
        //Alarmer alarmtester=new Alarmer(this.getActivity().getApplicationContext(),500,255,1,500,10);
        //alarmtester.start_alarm();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}