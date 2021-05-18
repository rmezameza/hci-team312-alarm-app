package at.ac.univie.hci.u_alarm.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;

import at.ac.univie.hci.u_alarm.R;
import at.ac.univie.hci.u_alarm.databinding.FragmentBuildingMapBinding;


public class BuildingMapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ID = "2";

    private FragmentBuildingMapBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding = FragmentBuildingMapBinding.inflate(inflater, container, false);
        View rootBuldingMap = binding.getRoot();

        // Inflate the layout for this fragment
        PDFView pdfView = (PDFView)rootBuldingMap.findViewById(R.id.childFragment_building_map);
        //Uri buildingMap = Uri.parse("https://www.univie.ac.at/fileadmin/user_upload/startseite/Fotos/Plaene/plan-hauptgebaeude-universitaet-wien-2020-v1.pdf");
        pdfView.fromAsset("plan-hauptgebaeude-universitaet-wien-2020-v1.pdf").load();
        return rootBuldingMap;
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