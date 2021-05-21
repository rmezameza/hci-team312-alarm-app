package at.ac.univie.hci.u_alarm.ui.AlarmPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import at.ac.univie.hci.u_alarm.Alarmer;
import at.ac.univie.hci.u_alarm.MainActivity;
import at.ac.univie.hci.u_alarm.R;

public class AlarmActivityTest extends AppCompatActivity {

    private static final String ALARM_NAME = "Feuer Alarm";
    private static final String ALARM_PLACE = "Erdgeschoss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_test);

        Button stopButton;
        TextView tvAlarmName ;
        TextView tvAlarmPlace;
        ImageButton mapButton;
        TextView alarmTest;

        stopButton = findViewById(R.id.stop_button);
        mapButton = findViewById(R.id.map_button);
        tvAlarmName = findViewById(R.id.alarm_name);
        tvAlarmPlace = findViewById(R.id.alarm_place_name);
        alarmTest = findViewById(R.id.alarm_testText);

        if (MainActivity.language.compareTo("English") == 0) {
            alarmTest.setText("THIS IS AN ALARM TEST");
        }


        // Wäre wsl eleganter wenn die Klasse selbst einen Alarmer als Feld hätte, dann könnte
        // die stop_alarm() Funktion auch noch im onPause/onDestroyed aufgerufen werden. Derzeit
        // leicht buggy wenn sich 2 oder mehr Alarme überschneiden, dann vibriert das ganze auch
        // weiter nachdem der Stop-BUtton gedrückt wurde. Wird in der Praxis wahrscheinlich keinem
        // auffallen.
        Alarmer alarmTester = new Alarmer(AlarmActivityTest.this.getApplicationContext(),
                500,
                255,
                10,
                500,
                10);

        // Geht vllt auch indem ein Runnable an den Thread gepasst wird statt nur Thread,
        // sollte aber egal sein. Wenns ohne extra Thread gestartet wird läuft zuerst die Vibration
        // komplett durch bevor das User Interface gezeichnet wurde.
        // Wenn Zeit ist noch mittels Threadhandlern statt "nackt" machen.
        Thread thread = new Thread(alarmTester::startAlarm);
        thread.start();


        stopButton.setOnClickListener(v -> {
            alarmTester.stopAlarm();
            Intent intent = new Intent(AlarmActivityTest.this, MainActivity.class);
            startActivity(intent);
        });


        tvAlarmName.setText(ALARM_NAME);
        tvAlarmPlace.setText(ALARM_PLACE);
    }
}