package memory.hospitalisation_management;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static memory.hospitalisation_management.Constantns.CONS;
import static memory.hospitalisation_management.Constantns.HAD;
import static memory.hospitalisation_management.Constantns.HJ;
import static memory.hospitalisation_management.Constantns.MEN;
import static memory.hospitalisation_management.Constantns.SERVER_URL;
import static memory.hospitalisation_management.Constantns.WOMEN;


/**
 * A simple {@link Fragment} subclass.
 */
public class FemmeHospitalisationFragment extends Fragment  {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    public FemmeHospitalisationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_femme_hospitalisation, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableList.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),expandableListTitle.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),expandableListTitle.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), expandableListTitle.get(groupPosition) + " -> " + expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        /*Spinner spinner= view.findViewById(R.id.spinnerHospH);
         lst=view.findViewById(R.id.lstvw);*/


       /* final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.salle_hosp,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/
     /*  spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,salles));
        lst.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getListData()));
spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i>=0&& i<salles.length){
            getSelectedData(i);
        }else
            Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});*/
/*
       arraysalle=new ArrayList<>();
        arraysalle.add("Salle 2");
        arraysalle.add("Salle 3");
        arraysalle.add("Salle 4");
        arraysalle.add("Salle 5");
        arraysalle.add("Salle 6");
        arraysalle.add("Salle 7");
        arraysalle.add("Salle 8");
        salleadapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,arraysalle);
        spinner.setAdapter(salleadapter);
*/

        Button btn= view.findViewById(R.id.ajoutPatient);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerte = new AlertDialog.Builder(getActivity());
                View alerteView = getLayoutInflater().inflate(R.layout.dialog_ajout_patient_hosp, null);
                ListView listePatient= alerteView.findViewById(R.id.listelit);
                alerte.setView(alerteView);
                final AlertDialog dialog = alerte.create();
                dialog.setTitle("Ajout Patient");


                dialog.show();


            }
        });
        return view;

    }
   /* private ArrayList<DetailHospitalisation> getListData(){
        ArrayList<DetailHospitalisation> data= new ArrayList<>();
        data.clear();
        data.add(0,new DetailHospitalisation("lit 1","Patient 1",0));
        data.add(0,new DetailHospitalisation("lit 2","Patient 2",0));
        data.add(0,new DetailHospitalisation("lit 3"," ",0));
        data.add(0,new DetailHospitalisation("lit 4"," ",0));
        data.add(0,new DetailHospitalisation("lit 5","Patient 2",1));
        return data;
    }*/

   /* private  List<DetailHospitalisation> getListData() {
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
    private  List<DetailHospitalisation> getListData5lits() {
        List<DetailHospitalisation> list = new ArrayList<DetailHospitalisation>();
        DetailHospitalisation info1 = new DetailHospitalisation("Lit 1","Patient 1");
        DetailHospitalisation info2 = new DetailHospitalisation("Lit 2","Patient 2");
        DetailHospitalisation info3 = new DetailHospitalisation("Lit 3","");
        DetailHospitalisation info4 = new DetailHospitalisation("Lit 4","");
        DetailHospitalisation info5 = new DetailHospitalisation("Lit 5","Patient 3");

        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        list.add(info5);

        return list;
    }
/*    private  List<Patient_Hospitalisaion> getListPatient() {
       // Bundle bundle= getArguments()

        List<Patient_Hospitalisaion> listhosp = new ArrayList<Patient_Hospitalisaion>();
        Patient_Hospitalisaion pat1 = new Patient_Hospitalisaion("Patient 1",null,null);
        Patient_Hospitalisaion pat2 = new Patient_Hospitalisaion("Patient 2",null,null);

        listhosp.add(pat1);
        listhosp.add(pat2);

        return listhosp;
    }*/


}
