package at.ac.univie.hci.u_alarm.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        // Inflate the layout of this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        // Background image
        final ImageView backgroundImage = binding.backgroundImage;
        Picasso.get().load(R.drawable.u_alarm_logo).into(backgroundImage);


        // Text for the home fragment
        // Content of TextViews are taken from the HomeViewModel
        final TextView tvActualPlaceTitle = binding.textActualPlaceTitle;
        homeViewModel.getActualPlaceTitle()
                .observe(getViewLifecycleOwner(), tvActualPlaceTitle::setText);


        final TextView tvActualPlaceContent = binding.textActualPlaceContent;
        homeViewModel.getActualPlaceContent()
                .observe(getViewLifecycleOwner(), tvActualPlaceContent::setText);


        final TextView tvTelephoneTitle = binding.textTelephoneTitle;
        homeViewModel.getTelephoneTitle()
                .observe(getViewLifecycleOwner(), tvTelephoneTitle::setText);


        final TextView tvTelephoneContent = binding.textTelephoneContent;
        homeViewModel.getTelephoneContent()
                .observe(getViewLifecycleOwner(), tvTelephoneContent::setText);


        final TextView tvEmailTitle = binding.textEmailTitle;
        homeViewModel.getEmailTitle()
                .observe(getViewLifecycleOwner(), tvEmailTitle::setText);


        final TextView tvEmailContent = binding.textEmailContent;
        homeViewModel.getEmailContent()
                .observe(getViewLifecycleOwner(), tvEmailContent::setText);


        return root;
    }

    // Hide the title in action bar, but just for the home fragment
    @Override
    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        if (supportActionBar != null)
            supportActionBar.hide();
    }

    // Reset the title in action bar for the other fragments.
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        if (!supportActionBar.isShowing())
            supportActionBar.show();
        binding = null;
    }
}