package at.ac.univie.hci.u_alarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import at.ac.univie.hci.u_alarm.databinding.ActivityMainBinding;
import at.ac.univie.hci.u_alarm.ui.configuration.ConfigurationFragment;
import at.ac.univie.hci.u_alarm.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SharedPreferences preferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This boolean stores "true" if the App runs for the first time.
        preferences = getSharedPreferences("com.at.ac.univie.hci_ualarm", MODE_PRIVATE);

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
        //
        //If the App runs for the first time, the default screen will be the settings one
        //because the screen page is a fragment, we need a framgentmanager+transaction.
        if(preferences.getBoolean("firstRun",true)){

            Fragment fragment = new ConfigurationFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();
            tr.replace(R.id.fragment_home_view,fragment);
            tr.commit();
            preferences.edit().putBoolean("firstRun",false).commit();
        } else{
            Fragment fragment = new HomeFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();
            tr.replace(R.id.fragment_home_view,fragment);
            tr.commit();
        }
    }
}