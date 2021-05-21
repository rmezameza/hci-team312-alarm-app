package at.ac.univie.hci.u_alarm;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import at.ac.univie.hci.u_alarm.databinding.ActivityMainBinding;
import at.ac.univie.hci.u_alarm.ui.configuration.ConfigurationFragment;
import at.ac.univie.hci.u_alarm.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SharedPreferences preferences = null;
    public static String language = "Deutsch";

    // For alarm - global variables with one value for hardcoded alarm
    public static ArrayList<String> alarmType = new ArrayList<>(Arrays.asList("Feuer Alarm"));
    public static ArrayList<String> alarmPlace = new ArrayList<>(Arrays.asList("Erdgeschoss"));
    public static ArrayList<String> alarmDate = new ArrayList<>(Arrays.asList(calculateDate()));

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

