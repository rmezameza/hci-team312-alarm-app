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
import at.ac.univie.hci.u_alarm.ui.AlarmPage.AlarmActivityTest;

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


        //Variable for the alarm test
        Button buttonTest = view.findViewById(R.id.buttonTestAlarm);
        Button buttonMock = view.findViewById(R.id.buttonMockAlarm);

        //Variables for buttons to switch the language
        ImageView leftArrow = getActivity().findViewById(R.id.imageViewLeft);
        ImageView rightArrow = getActivity().findViewById(R.id.imageViewRight);



        //Listener for Test Alarm button
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmTestButtonClicked(view);
            }
        });

        buttonMock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MockTestButtonClicked(view);
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


                buttonTest.setText("Alarm Test");

                TextView functionsSubtitle = getActivity().findViewById(R.id.textFunctions2);
                functionsSubtitle.setText("This is an alarm test that tests the App on your Smartphone");

                configurationViewModel.setLanguage("English");
                Log.i("LANGUAGE CHANGED:","English");


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


                buttonTest.setText("Alarmprobe");

                configurationViewModel.setLanguage("Deutsch");
                Log.i("LANGUAGE CHANGED:","Deutsch");

            }
        });



    }

    public void AlarmTestButtonClicked(View v) {
        new Timer().schedule(new TimerTask(){
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(getActivity(), AlarmActivityTest.class));
                    }
                });
            }
        }, 5000);
    }

    public void MockTestButtonClicked(View v) {
        new Timer().schedule(new TimerTask(){
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(getActivity(), AlarmActivity.class));
                    }
                });
            }
        }, 5000);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}