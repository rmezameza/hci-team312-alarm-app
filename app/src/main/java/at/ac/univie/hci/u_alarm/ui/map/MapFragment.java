package at.ac.univie.hci.u_alarm.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {

    private MapViewModel mapViewModel;
    private FragmentMapBinding binding;

    // This variable checks the current Fragment IDs of the child fragments
    private String currentChildMapFragment;

    private ImageButton imageButtonLeft;
    private ImageButton imageButtonRight;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // ViewModel for this Fragment. This class sends the to the viewModel and
        // there it sets its variable to that particular value.
        this.mapViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);

        // Inflate the layout for this fragment
        this.binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get the Text of the ViewModel
        TextView textView = binding.textMap;
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Sets the default values: Google Map
        setCurrentChildMapFragment("1");
        // Methode to set the title text (ViewModels' field)
        setMapViewModelText();

        return root;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Variables needed for nested fragments
        FragmentManager childFragment = this.getChildFragmentManager();
        Fragment googleMapFragment = new GoogleMapFragment();
        Fragment buildingMapFragment = new BuildingMapFragment();

        // Sets the default content of the fragment container: Google Maps
        childFragment.beginTransaction().replace(R.id.map_fragment_container,
                googleMapFragment).addToBackStack(null).commit();

        // Buttons for switching between Google and building map
        this.imageButtonLeft = (ImageButton)view.findViewById(R.id.leftMapChangeButton);
        this.imageButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checks the current child fragment (class field "currentChildMapFragment")
                if(currentChildMapFragment.equals("1")) {
                    // Sets the field variable to the new fragment id.
                    setCurrentChildMapFragment("2");

                    // Then it loads the other fragment into the container and sets the text for
                    childFragment.beginTransaction().replace(R.id.map_fragment_container,
                            buildingMapFragment).addToBackStack(null).commit();
                } else {
                    setCurrentChildMapFragment("1");
                    childFragment.beginTransaction().replace(R.id.map_fragment_container,
                            googleMapFragment).addToBackStack(null).commit();
                }
                // Sets the new text
                setMapViewModelText();
            }
        });

        // Same procedure as imageButtonLeft
        this.imageButtonRight = (ImageButton)view.findViewById(R.id.rightMapChangeButton);
        this.imageButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentChildMapFragment.equals("1")) {
                    setCurrentChildMapFragment("2");
                    childFragment.beginTransaction().replace(R.id.map_fragment_container,
                            buildingMapFragment).addToBackStack(null).commit();
                }
                else {
                    setCurrentChildMapFragment("1");
                    childFragment.beginTransaction().replace(R.id.map_fragment_container,
                            googleMapFragment).addToBackStack(null).commit();
                }
                setMapViewModelText();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Method to set the field of MapViewModel (title text)
    private void setMapViewModelText() {
        switch (this.currentChildMapFragment) {
            case "1":
                this.mapViewModel.setMapTextView("Google Map");
                break;
            case "2":
                this.mapViewModel.setMapTextView("Building Map");
                break;
        }
    }

    // Method to set the current child fragment
    private void setCurrentChildMapFragment(String fragmentId) {
        this.currentChildMapFragment = fragmentId;
    }
}