package memory.hospitalisation_management;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HommeHospitalisationFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public HommeHospitalisationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_homme_hospitalisation, container, false);
        Spinner spinner= view.findViewById(R.id.spinnerHosp);
        final ListView lst=view.findViewById(R.id.lstvw);
        final ListView lsthosp=view.findViewById(R.id.listHosp);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.salle_hosp,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        List<DetailHospitalisation> list = getListData();
        lst.setAdapter(new CustomListAdapter(getActivity(),list));

        List<Patient_Hospitalisaion> listhosp = getListPatient();
        lsthosp.setAdapter(new PatientHospAdapter(getActivity(),listhosp));


        return view;

    }
    private  List<DetailHospitalisation> getListData() {
        List<DetailHospitalisation> list = new ArrayList<DetailHospitalisation>();
        DetailHospitalisation info1 = new DetailHospitalisation("Lit 1","Patient 1");
        DetailHospitalisation info2 = new DetailHospitalisation("Lit 2","Patient 2");
        DetailHospitalisation info3 = new DetailHospitalisation("Lit 3","");
        DetailHospitalisation info4 = new DetailHospitalisation("Lit 4","");



        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);

        return list;
    }
    private  List<Patient_Hospitalisaion> getListPatient() {
        List<Patient_Hospitalisaion> listhosp = new ArrayList<Patient_Hospitalisaion>();
        Patient_Hospitalisaion pat1 = new Patient_Hospitalisaion("Patient 1",null,null);
        Patient_Hospitalisaion pat2 = new Patient_Hospitalisaion("Patient 2",null,null);
        Patient_Hospitalisaion pat3 = new Patient_Hospitalisaion("Patient 3",null,null);
        Patient_Hospitalisaion pat4 = new Patient_Hospitalisaion("Patient 4",null,null);



        listhosp.add(pat1);
        listhosp.add(pat2);
        listhosp.add(pat3);
        listhosp.add(pat4);

        return listhosp;
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
