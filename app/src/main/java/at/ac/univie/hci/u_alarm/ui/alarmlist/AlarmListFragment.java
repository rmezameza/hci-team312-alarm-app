package at.ac.univie.hci.u_alarm.ui.alarmlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import at.ac.univie.hci.u_alarm.MainActivity;
import at.ac.univie.hci.u_alarm.databinding.FragmentAlarmlistBinding;
import at.ac.univie.hci.u_alarm.ui.AlarmPage.AlarmActivity;


public class AlarmListFragment extends Fragment {

    //private AlarmListViewModel alarmlistViewModel;
    private FragmentAlarmlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //alarmlistViewModel =
         //       new ViewModelProvider(this).get(AlarmListViewModel.class);

        binding = FragmentAlarmlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AlarmListView adapter = new AlarmListView(
                (Activity)this.getContext(),
                MainActivity.alarmType,
                MainActivity.alarmPlace,
                MainActivity.alarmDate);

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