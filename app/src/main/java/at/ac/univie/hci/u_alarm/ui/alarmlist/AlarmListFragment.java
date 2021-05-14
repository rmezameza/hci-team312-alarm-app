package at.ac.univie.hci.u_alarm.ui.alarmlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import at.ac.univie.hci.u_alarm.databinding.FragmentAlarmlistBinding;

public class AlarmListFragment extends Fragment {

    private AlarmListViewModel alarmlistViewModel;
    private FragmentAlarmlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alarmlistViewModel =
                new ViewModelProvider(this).get(AlarmListViewModel.class);

        binding = FragmentAlarmlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAlarmlist;
        alarmlistViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}