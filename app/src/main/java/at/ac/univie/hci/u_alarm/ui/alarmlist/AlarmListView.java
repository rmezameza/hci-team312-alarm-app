package at.ac.univie.hci.u_alarm.ui.alarmlist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import at.ac.univie.hci.u_alarm.R;


public class AlarmListView extends ArrayAdapter<String> {

    private final Activity alarm;
    private ArrayList<String> alarmTypes;
    private ArrayList<String> alarmPlaces;
    private ArrayList<String> alarmDates;

    public AlarmListView(Activity alarm,
                         ArrayList<String> alarmType,
                         ArrayList<String> alarmPlace,
                         ArrayList<String> alarmDate) {
        super(alarm, R.layout.mylist, alarmType);

        this.alarm = alarm;
        this.alarmTypes = alarmType;
        this.alarmPlaces = alarmPlace;
        this.alarmDates = alarmDate;
    }


    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = alarm.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.mylist, null,true);

        TextView tvAlarmType = (TextView)rowView.findViewById(R.id.alarm_type);
        TextView tvAlarmPlace = (TextView)rowView.findViewById(R.id.alarm_ort);
        TextView tvAlarmDate = (TextView)rowView.findViewById(R.id.alarm_datum_id_in_ListView);

        tvAlarmType.setText(this.alarmTypes.get(position));
        tvAlarmPlace.setText(this.alarmPlaces.get(position));
        tvAlarmDate.setText(this.alarmDates.get(position));

        return rowView;
    };
}