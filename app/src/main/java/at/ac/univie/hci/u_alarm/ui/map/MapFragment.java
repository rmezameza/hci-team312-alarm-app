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

    private String currentChildMapFragment;
    private ImageButton imageButtonLeft = null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.mapViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);

        this.binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        TextView textView = binding.textMap;
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        setCurrentChildMapFragment("1");
        setMapViewModelText();


        return root;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager childFragment = this.getChildFragmentManager();
        Fragment googleMapFragment = new GoogleMapFragment();
        Fragment buildingMapFragment = new BuildingMapFragment();

        childFragment.beginTransaction().replace(R.id.map_fragment_container,
                googleMapFragment).addToBackStack(null).commit();


        this.imageButtonLeft = (ImageButton)view.findViewById(R.id.leftMapChangeButton);
        this.imageButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentChildMapFragment.equals("1")) {
                    setCurrentChildMapFragment("2");
                    childFragment.beginTransaction().replace(R.id.map_fragment_container,
                            buildingMapFragment).addToBackStack(null).commit();
                    setMapViewModelText();
                }
                else {
                    setCurrentChildMapFragment("1");
                    childFragment.beginTransaction().replace(R.id.map_fragment_container,
                            googleMapFragment).addToBackStack(null).commit();
                    setMapViewModelText();
                }
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

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

    private void setCurrentChildMapFragment(String fragmentId) {
        this.currentChildMapFragment = fragmentId;
    }
}