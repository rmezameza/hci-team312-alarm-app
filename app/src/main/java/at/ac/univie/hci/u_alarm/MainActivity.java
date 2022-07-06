package at.ac.univie.hci.u_alarm;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import at.ac.univie.hci.u_alarm.databinding.ActivityMainBinding;
import at.ac.univie.hci.u_alarm.ui.configuration.ConfigurationFragment;
import at.ac.univie.hci.u_alarm.ui.home.HomeFragment;
import at.ac.univie.hci.u_alarm.ui.map.MapFragment;

public class
MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SharedPreferences preferences = null;
    public static String language = "Deutsch";


    // For alarm - global variables with alarm values for AlarmListView
    public static ArrayList<String> alarmTypes = new ArrayList<>();
    public static ArrayList<String> alarmPlaces = new ArrayList<>();
    public static ArrayList<String> alarmDates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Overwrite the background color of the app bar. Because we want the same color both
        // for light and night mode.
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0063A6"));
        actionBar.setBackgroundDrawable(colorDrawable);

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

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            Fragment fragment = new MapFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();
            tr.add(R.id.nav_host_fragment_activity_main,fragment);
            tr.commit();
        }



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

        } else {
            Log.i("onCreate firstRun: ", "Not first time running the app.");
            Fragment fragment = new HomeFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();
            tr.replace(R.id.fragment_home_view, fragment);
            tr.commit();
        }
    }
}