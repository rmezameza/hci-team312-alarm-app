package at.ac.univie.hci.u_alarm.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        // Inflate the layout of this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Background image
        final ImageView backgroundImage = binding.backgroundImage;
        Picasso.get().load(R.drawable.u_alarm_logo).into(backgroundImage);


        // Text for the home fragment
        // Content of TextViews are taken from the HomeViewModel
        final TextView tvActualPlaceTitle = binding.textActualPlaceTitle;
        homeViewModel.getActualPlaceTitle()
                .observe(getViewLifecycleOwner(), tvActualPlaceTitle::setText);


        final TextView tvActualPlaceContent = binding.textActualPlaceContent;
        homeViewModel.getActualPlaceContent()
                .observe(getViewLifecycleOwner(), tvActualPlaceContent::setText);


        final TextView tvTelephoneTitle = binding.textTelephoneTitle;
        homeViewModel.getTelephoneTitle()
                .observe(getViewLifecycleOwner(), tvTelephoneTitle::setText);


        final TextView tvTelephoneContent = binding.textTelephoneContent;
        homeViewModel.getTelephoneContent()
                .observe(getViewLifecycleOwner(), tvTelephoneContent::setText);


        final TextView tvEmailTitle = binding.textEmailTitle;
        homeViewModel.getEmailTitle()
                .observe(getViewLifecycleOwner(), tvEmailTitle::setText);


        final TextView tvEmailContent = binding.textEmailContent;
        homeViewModel.getEmailContent()
                .observe(getViewLifecycleOwner(), tvEmailContent::setText);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
/*
package at.ac.univie.hci.u_alarm.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Timer;
import java.util.TimerTask;

import at.ac.univie.hci.u_alarm.Alarmer;
import at.ac.univie.hci.u_alarm.MainActivity;
import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentHomeBinding;
import at.ac.univie.hci.u_alarm.ui.AlarmPage.AlarmActivity;
import at.ac.univie.hci.u_alarm.ui.map.GoogleMapFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button AlarmtestButton = (Button) view.findViewById(R.id.StopButton);
        AlarmtestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmTestButtonClicked(v);
            }
        });

    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} */