package at.ac.univie.hci.u_alarm.ui.alarmlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import at.ac.univie.hci.u_alarm.Alarmer;
import at.ac.univie.hci.u_alarm.MainActivity;
import at.ac.univie.hci.u_alarm.R;

import static at.ac.univie.hci.u_alarm.MainActivity.alarmDate;
import static at.ac.univie.hci.u_alarm.MainActivity.alarmPlace;
import static at.ac.univie.hci.u_alarm.MainActivity.alarmType;


public class AlarmActivity extends AppCompatActivity {
     Button stopButton;
     TextView tvAlarmName ;
     TextView tvAlarmPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        stopButton = (Button)findViewById(R.id.stop_button);
        tvAlarmName = (TextView)findViewById(R.id.alarm_name);
        tvAlarmPlace = (TextView)findViewById(R.id.alarm_place_name);

        // Vibration Configuration
        Alarmer alarmTester = new Alarmer(AlarmActivity.this.getApplicationContext(),
                500,
                255,
                10,
                500,
                10);

        Thread thread = new Thread(alarmTester::startAlarm);
        thread.start();

        // With the Stop Button will be close the Alarm Pop and start the Home Page Again
        stopButton.setOnClickListener((View v) -> {
            alarmTester.stopAlarm();
            Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //Initial names for Alarms
        ArrayList<String> Alaram_initial_type =new ArrayList<>(Arrays.asList(
                "Fire Alarm","Test Alarm",
                "Earthquake alarm","unkownon Alarm"
        ));
        ArrayList<String>  Alaram_initial_Location =new ArrayList<>(Arrays.asList(
                "Ground Floor","Pc Raum ",
                "University Canteen","Garden"
        ));

        // Random Select the Type and the Location of Alarm
        Random random = new Random();
        int random_Number_Alarmtype= random.nextInt(Alaram_initial_type.size());
        int random_Number_Alarmort= random.nextInt(Alaram_initial_Location.size());

        tvAlarmName.setText(Alaram_initial_type.get(random_Number_Alarmtype));
        tvAlarmPlace.setText(Alaram_initial_Location.get(random_Number_Alarmort));

        //Save the Details of the Alarm
        alarmType.add(tvAlarmName.getText().toString());
        alarmPlace.add(tvAlarmPlace.getText().toString());
        alarmDate.add(calculateDate());

    }

    public String getAlarmName(){
        return tvAlarmName.getText().toString();
    }
    public String getAlarmPlace(){
        return tvAlarmPlace.getText().toString();
    }

    // Set date for alarm
    private static String calculateDate() {

        // Get date and time. Convert them to strings and assign it to alarmTime.
        DateTimeFormatter date = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        String dateString = date.format(localDate) + " | " + time.format(localTime) ;
        return dateString;
    }
}