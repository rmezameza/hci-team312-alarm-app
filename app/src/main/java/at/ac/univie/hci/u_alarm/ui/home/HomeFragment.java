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