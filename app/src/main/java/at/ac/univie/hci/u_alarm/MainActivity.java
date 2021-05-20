package at.ac.univie.hci.u_alarm;



import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

import at.ac.univie.hci.u_alarm.databinding.ActivityMainBinding;
import at.ac.univie.hci.u_alarm.ui.AlarmPage.AlarmActivity;
import at.ac.univie.hci.u_alarm.ui.configuration.ConfigurationFragment;
import at.ac.univie.hci.u_alarm.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SharedPreferences preferences = null;
    public static String language = "Deutsch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //This boolean stores "true" if the App runs for the first time.
        preferences = MainActivity.this.getSharedPreferences("preferences",0);
        boolean firstRun = preferences.getBoolean("firstRun",true);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_alarmlist, R.id.navigation_configuration, R.id.navigation_map)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        //If the App runs for the first time, the default screen will be the settings one
        //because the screen page is a fragment, we need a framgentmanager+transaction.
        if(firstRun || preferences == null){
            Log.i("onCreate firstRun: ", "First time running the app.");
            preferences.edit().putBoolean("firstRun",false).commit();
            Fragment fragment = new ConfigurationFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();
            tr.replace(R.id.fragment_home_view,fragment);
            tr.commit();

        } else{
            Log.i("onCreate firstRun: ", "Not first time running the app.");
            Fragment fragment = new HomeFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();
            tr.replace(R.id.fragment_home_view,fragment);
            tr.commit();
        }


        

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

