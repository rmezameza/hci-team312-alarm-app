package at.ac.univie.hci.u_alarm.ui.alarmlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.ac.univie.hci.u_alarm.databinding.FragmentAlarmlistBinding;
import at.ac.univie.hci.u_alarm.ui.AlarmPage.AlarmActivity;


public class AlarmListFragment extends Fragment {

    private AlarmListViewModel alarmlistViewModel;
    private FragmentAlarmlistBinding binding;
    //private List<Alarm> list = all_details_of_the_alarm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        alarmlistViewModel =
                new ViewModelProvider(this).get(AlarmListViewModel.class);

        binding = FragmentAlarmlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AlarmListView adapter = new AlarmListView((Activity) this.getContext(), AlarmActivity.alarmtypeGlobal, AlarmActivity.alarmortGlobal , AlarmActivity.alarmdateGlobal);
        ListView list = binding.alarmListviewId;
        list.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}