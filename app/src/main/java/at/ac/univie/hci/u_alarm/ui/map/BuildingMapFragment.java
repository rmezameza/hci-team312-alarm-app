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

    private FragmentBuildingMapBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this child fragment
        this.binding = FragmentBuildingMapBinding.inflate(inflater, container, false);
        View rootBuildingMap = binding.getRoot();

        // Creates variable with type of library PDFViewer
        PDFView pdfView = (PDFView)rootBuildingMap.findViewById(R.id.childFragment_building_map);
        // Takes the pdf from the folder "asset" in main folder and load it
        pdfView.fromAsset("plan-hauptgebaeude-universitaet-wien-2020-v1.pdf").load();

        return rootBuildingMap;
    }
}