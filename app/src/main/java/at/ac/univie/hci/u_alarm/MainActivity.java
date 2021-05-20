package at.ac.univie.hci.u_alarm;



import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

import at.ac.univie.hci.u_alarm.databinding.ActivityMainBinding;
import at.ac.univie.hci.u_alarm.ui.AlarmPage.AlarmActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_alarmlist, R.id.navigation_configuration, R.id.navigation_map)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        

    }
    /*
     //Testweise Funktionen die die App wieder öffnen sollen nachdem sie pausiert/gestoppt/zerstört wurde. Wird die App mit dem Homebutton minimiert werden onPause und onStop aufgerufen, wird sie mit dem Backbutton minimiert wird zusätzlich no onDestroy aufgerufen.
     //Hilfreich Log.d("Statustest", "onX() called") zum Testen.

    @Override
    public void onPause(){
        super.onPause();
        AlarmManager alarmtest_restartmanager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent( this, MainActivity.class );
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 123, intent, PendingIntent.FLAG_ONE_SHOT);
        alarmtest_restartmanager.set( AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+500,pendingIntent);
    }

    @Override
    public void onStop(){
        super.onStop();
        AlarmManager alarmtest_restartmanager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent restart_intent = new Intent( getApplicationContext(), MainActivity.class );
        PendingIntent test_restart_intent = PendingIntent.getBroadcast(getApplicationContext(), 111, restart_intent, PendingIntent.FLAG_ONE_SHOT);
        alarmtest_restartmanager.set( AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+2500,test_restart_intent);
    }
    /*
    @Override
    public void onDestroy() {
        super.onDestroy();
        AlarmManager alarmtest_restartmanager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent( this, MainActivity.class );
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 123, intent, PendingIntent.FLAG_ONE_SHOT);
        alarmtest_restartmanager.set( AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+500,pendingIntent);
    }

   */

    }

