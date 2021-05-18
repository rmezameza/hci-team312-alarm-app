package at.ac.univie.hci.u_alarm.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import at.ac.univie.hci.u_alarm.R;

public class GoogleMapFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * (Comment by Android Studio)
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            // Pre-defined latitude and longitude values (Main building of the University of Vienna
            LatLng mainBuildingUniversityVienna = new LatLng(48.21319, 16.36010);

            // Add a marker with the pre-defined coordinates but hides the marker
            googleMap.addMarker(new MarkerOptions().position(mainBuildingUniversityVienna)
                    .title("Main Building of the University of Vienna").visible(false));

            // Zooms in by the value (17.0f)
            googleMap.moveCamera(CameraUpdateFactory
                    .newLatLngZoom(mainBuildingUniversityVienna, 17.0f));

            // Values to enable indoor building map view of Google Maps
            googleMap.setIndoorEnabled(true);
            googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this child fragment
        return inflater.inflate(R.layout.fragment_google_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment)getChildFragmentManager()
                        .findFragmentById(R.id.childFragment_google_map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}