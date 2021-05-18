package at.ac.univie.hci.u_alarm.ui.configuration;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.IntentCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import org.jetbrains.annotations.NotNull;

import at.ac.univie.hci.u_alarm.MainActivity;
import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentConfigurationBinding;
import at.ac.univie.hci.u_alarm.ui.home.HomeFragment;

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
        Switch switchLicht = (Switch) getView().findViewById(R.id.switchLicht);
        Switch switchFlash = (Switch) getView().findViewById(R.id.switchFlash);
        Switch switchDisplay = (Switch) getView().findViewById(R.id.switchDisplay);

        //Variable for the alarm test
        Button buttonTest = (Button) getView().findViewById(R.id.buttonTestAlarm);

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
                //Will do something.
            }
        });


        //Buttons right and left to change the language.
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView languageText = getActivity().findViewById(R.id.textViewLanguage);
                languageText.setText("English");
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView languageText = getActivity().findViewById(R.id.textViewLanguage);
                languageText.setText("Deutsch");
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}