package at.ac.univie.hci.u_alarm.ui.configuration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}