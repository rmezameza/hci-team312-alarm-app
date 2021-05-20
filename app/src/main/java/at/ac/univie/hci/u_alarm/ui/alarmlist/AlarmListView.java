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

    private final Activity alarmart;
    private ArrayList<String> alarmname;
    private ArrayList<String> alarmort;
    private ArrayList<String> alarmdate;

    //List<Alarm> list = all_details_of_the_alarm;

    public AlarmListView(Activity alarmart, ArrayList<String>  alarmname, ArrayList<String>  alarmort , ArrayList<String> alarmdate) {
        super(alarmart, R.layout.mylist, alarmname);

        this.alarmart = alarmart;
        this.alarmname = alarmname;
        this.alarmort = alarmort;
        this.alarmdate = alarmdate;
    }

    public void additem( String alarmnamee, String alarmOrt ){
        alarmname.add(alarmnamee);
        alarmort.add(alarmOrt);
        notifyDataSetChanged();

    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater= alarmart.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView alarm_name = (TextView) rowView.findViewById(R.id.alarm_type);
        TextView alarm_ort = (TextView) rowView.findViewById(R.id.alarm_ort);
        TextView alarm_date = (TextView) rowView.findViewById(R.id.alarm_datum_id_in_ListView);

        alarm_name.setText(alarmname.get(position));
        alarm_ort.setText(alarmort.get(position));
        alarm_date.setText(alarmdate.get(position));
        return rowView;
    };
}