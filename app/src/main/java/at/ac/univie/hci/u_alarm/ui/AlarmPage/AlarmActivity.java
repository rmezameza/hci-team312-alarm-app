package at.ac.univie.hci.u_alarm.ui.AlarmPage;

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
import at.ac.univie.hci.u_alarm.ui.alarmlist.Alarm;

public class AlarmActivity extends AppCompatActivity {

    public static  ArrayList<String> alarmtypeGlobal =new ArrayList<>();
    public static  ArrayList<String>  alarmortGlobal =new ArrayList<>();
    public static  ArrayList alarmdateGlobal = new ArrayList<String>();

    Button stop_button;
    TextView alarmname ;
    TextView ort ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //Wäre wsl eleganter wenn die Klasse selbst einen Alarmer als Feld hätte, dann könnte die stop_alarm() Funktion auch noch im onPause/onDestroyed aufgerufen werden. Derzeit leicht buggy wenn sich 2 oder mehr Alarme überschneiden, dann vibriert das ganze auch weiter nachdem der Stop-BUtton gedrückt wurde. Wird in der Praxis wahrscheinlich keinem auffallen.
        Alarmer alarmtester=new Alarmer(AlarmActivity.this.getApplicationContext(),500,255,10,500,10);

        //Geht vllt auch indem ein Runnable an den Thread gepasst wird statt nur Thread, sollte aber egal sein. Wenns ohne extra Thread gestartet wird läuft zuerst die Vibration komplett durch bevor das User Interface gezeichnet wurde.
        //Wenn Zeit ist noch mittels Threadhandlern statt "nackt" machen.
        Thread thread = new Thread() {
            @Override
            public void run() {
                alarmtester.start_alarm();
            }
        };

        thread.start();


        stop_button = (Button) findViewById(R.id.stop_button);
        alarmname = findViewById(R.id.alarm_name);
        ort = findViewById(R.id.alarm_place_name);

        stop_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alarmtester.stop_alarm();
                Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Random random = new Random();

        ArrayList<String>  alarmtype =new ArrayList<>(Arrays.asList(
                "Feuer Alarm","Probe Alarm",
                "Alarm1","Alarm2"
        ));
        ArrayList<String>  alarmort =new ArrayList<>(Arrays.asList(
                "Erdgeschoss","zweite Stock ",
                "erste Stock","Garten"
        ));

        //Alarmtype und Ort wird gewählt
        int random_Number_Alarmtype= random.nextInt(alarmtype.size());
        int random_Number_Alarmort= random.nextInt(alarmort.size());

        alarmname.setText(alarmtype.get(random_Number_Alarmtype));
        ort.setText(alarmort.get(random_Number_Alarmort));

        //Datum wird geholt und in einem List einfügen
        DateTimeFormatter jahr_monat_tag = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter zeit = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        String date = jahr_monat_tag.format(localDate) +" " + zeit.format(localTime) ;

        Alarm list = new Alarm(alarmname.getText().toString(),ort.getText().toString(), date);

        alarmtypeGlobal.add(alarmname.getText().toString());
        alarmortGlobal.add(ort.getText().toString());
        alarmdateGlobal.add(date);


    }
}