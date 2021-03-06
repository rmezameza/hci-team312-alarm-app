package at.ac.univie.hci.u_alarm.ui.alarmlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import at.ac.univie.hci.u_alarm.MainActivity;
import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentAlarmlistBinding;


public class AlarmListFragment extends Fragment {

    private FragmentAlarmlistBinding binding;
    public static AlarmListView adapter;

    public static ArrayList<String> alarmTypesDescOrder = MainActivity.alarmTypes;
    public static ArrayList<String> alarmPlacesDescOrder = MainActivity.alarmPlaces;
    public static ArrayList<String> alarmDatesDescOrder = MainActivity.alarmDates;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAlarmlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView tv_title = (TextView) root.findViewById(R.id.title_alarmlist);

        String language = MainActivity.language;

        //Change the Language in List View
        if (language.equals("English")) {
            tv_title.setText("List of past alarms");
            MainActivity.alarmTypes = (ArrayList<String>)
                    alarmTypesDescOrder.stream().map(e ->  "Fire Alarm").collect(Collectors.toList());
            alarmTypesDescOrder = MainActivity.alarmTypes;
            MainActivity.alarmPlaces = (ArrayList<String>)
                    alarmPlacesDescOrder.stream().map(e -> "Ground Floor").collect(Collectors.toList());
            alarmPlacesDescOrder = MainActivity.alarmPlaces;
        } else if (language.equals("Deutsch")) {
            tv_title.setText("Liste vergangener Alarme");
            MainActivity.alarmTypes = (ArrayList<String>) alarmTypesDescOrder.stream().map(e ->  "Feuer Alarm ").collect(Collectors.toList());
            alarmTypesDescOrder = MainActivity.alarmTypes;
            MainActivity.alarmPlaces = (ArrayList<String>)  alarmPlacesDescOrder.stream().map(e -> "Erdgeschoss").collect(Collectors.toList());
            alarmPlacesDescOrder = MainActivity.alarmPlaces;
        }


        Collections.reverse(alarmTypesDescOrder);
        Collections.reverse(alarmPlacesDescOrder);
        Collections.reverse(alarmDatesDescOrder);

         adapter = null;


        if(alarmTypesDescOrder.isEmpty()) {
            if (language.equals("English")){
                adapter = new AlarmListView(
                    (Activity)this.getContext(),
                    new ArrayList<>(Arrays.asList("List is currently empty.")),
                    new ArrayList<>(Arrays.asList("")),
                    new ArrayList<>(Arrays.asList("")));
            } else {
                adapter = new AlarmListView(
                        (Activity)this.getContext(),
                        new ArrayList<>(Arrays.asList("Liste ist momentan leer.")),
                        new ArrayList<>(Arrays.asList("")),
                        new ArrayList<>(Arrays.asList("")));}

        } else {
            adapter = new AlarmListView(
                    (Activity)this.getContext(),
                    alarmTypesDescOrder,
                    alarmPlacesDescOrder,
                    alarmDatesDescOrder);
        }

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