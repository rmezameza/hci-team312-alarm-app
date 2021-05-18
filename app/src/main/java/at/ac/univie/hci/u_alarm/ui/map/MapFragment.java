package at.ac.univie.hci.u_alarm.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {

    private MapViewModel mapViewModel;
    private FragmentMapBinding binding;

    // This variable checks the current Fragment IDs of the child fragments
    private String currentChildMapFragment;

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
        mapViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Sets the default values: Google Map
        setCurrentChildMapFragment("1");
        // Methode to set the title text (ViewModels' field)
        setMapViewModelText();

        return root;
    }



    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Variables needed for nested fragments
        FragmentManager childFragment = this.getChildFragmentManager();
        Fragment googleMapFragment = new GoogleMapFragment();
        Fragment buildingMapFragment = new BuildingMapFragment();

        // Sets the default content of the fragment container: Google Maps
        childFragment.beginTransaction().replace(R.id.map_fragment_container,
                googleMapFragment).commit();

        // Buttons for switching between Google and building map
        ImageButton imageButtonLeft = (ImageButton) view.findViewById(R.id.leftMapChangeButton);
        imageButtonLeft.setOnClickListener(viewLeftButton -> {
            // Checks the current child fragment (class field "currentChildMapFragment")
            if(currentChildMapFragment.equals("1")) {
                // Sets the field variable to the new fragment id.
                setCurrentChildMapFragment("2");

                // Then it loads the other fragment into the container and sets the text for
                childFragment.beginTransaction().replace(R.id.map_fragment_container,
                        buildingMapFragment).commit();
            } else {
                setCurrentChildMapFragment("1");
                childFragment.beginTransaction().replace(R.id.map_fragment_container,
                        googleMapFragment).commit();
            }
            // Sets the new text
            setMapViewModelText();
        });

        // Same procedure as imageButtonLeft
        ImageButton imageButtonRight = (ImageButton) view.findViewById(R.id.rightMapChangeButton);
        imageButtonRight.setOnClickListener(viewRightButton -> {
            if(currentChildMapFragment.equals("1")) {
                setCurrentChildMapFragment("2");
                childFragment.beginTransaction().replace(R.id.map_fragment_container,
                        buildingMapFragment).commit();
            }
            else {
                setCurrentChildMapFragment("1");
                childFragment.beginTransaction().replace(R.id.map_fragment_container,
                        googleMapFragment).commit();
            }
            setMapViewModelText();
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
                this.mapViewModel.setMapTextView("Google Maps");
                break;
            case "2":
                this.mapViewModel.setMapTextView("Gebäude Karte");
                break;
        }
    }

    // Method to set the current child fragment
    private void setCurrentChildMapFragment(String fragmentId) {
        this.currentChildMapFragment = fragmentId;
    }
}