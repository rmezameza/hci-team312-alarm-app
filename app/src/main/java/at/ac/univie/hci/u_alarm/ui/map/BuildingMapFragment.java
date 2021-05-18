package at.ac.univie.hci.u_alarm.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import at.ac.univie.hci.u_alarm.R;


public class BuildingMapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ID = "2";


    public BuildingMapFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        WebView pdfView = (WebView)getView().findViewById(R.id.childFragment_building_map);
        pdfView.loadUrl("https://www.univie.ac.at/fileadmin/user_upload/startseite/Fotos/Plaene/plan-hauptgebaeude-universitaet-wien-2020-v1.pdf");

        return inflater.inflate(R.layout.fragment_building_map, container, false);
    }

    /*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }*/

    public String getID() {
        return ID;
    }
}