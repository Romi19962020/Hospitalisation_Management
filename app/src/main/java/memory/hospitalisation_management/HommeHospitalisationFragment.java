package memory.hospitalisation_management;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static memory.hospitalisation_management.Constantns.CONS;
import static memory.hospitalisation_management.Constantns.HAD;
import static memory.hospitalisation_management.Constantns.HJ;
import static memory.hospitalisation_management.Constantns.SERVER_URL;
import static memory.hospitalisation_management.Constantns.WOMEN;


/**
 * A simple {@link Fragment} subclass.
 */
public class HommeHospitalisationFragment extends Fragment   {
 /*   ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;*/

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    //Spinner spinner;
    //ArrayAdapter<DetailHospitalisation> adapter;
   // String[] salles={"Salle 2","Salle 3","Salle 4","Salle 5","Salle 6","Salle 7","Salle 8"};
    /*ArrayList<String> arraysalle;
    ArrayAdapter<String>salleadapter;
    final List<DetailHospitalisation> list = getListData();
    final List<DetailHospitalisation> list5 = getListData5lits();
*/
    public HommeHospitalisationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_homme_hospitalisation, container, false);
        expListView = (ExpandableListView)view.findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new CustomExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


    /*
     * Preparing the list data
     */
  
       /* expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
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
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Salle 2");
        listDataHeader.add("Salle 3");
        listDataHeader.add("Salle 4");
        listDataHeader.add("Salle 5");
        listDataHeader.add("Salle 6");
        listDataHeader.add("Salle 7");
        listDataHeader.add("Salle 8");


        // Adding child data
        List<String> salle2 = new ArrayList<String>();
        salle2.add("lit1");
        salle2.add("lit2");
        salle2.add("lit3");
        salle2.add("lit4");
        

        List<String> salle3 = new ArrayList<String>();
        salle3.add("lit5");
        salle3.add("lit6");
        salle3.add("lit7");
        salle3.add("lit8");
        salle3.add("lit9");

        List<String> salle4 = new ArrayList<String>();
        salle4.add("lit10");
        salle4.add("lit11");
        salle4.add("lit12");
        salle4.add("lit13");
        salle4.add("lit14");

        List<String> salle5 = new ArrayList<String>();
        salle4.add("lit15");
        salle4.add("lit16");
        salle4.add("lit17");
        salle4.add("lit18");
        salle4.add("lit19");

        List<String> salle6 = new ArrayList<String>();
        salle4.add("lit20");
        salle4.add("lit21");
        salle4.add("lit22");
        salle4.add("lit23");
        salle4.add("lit24");

        List<String> salle7 = new ArrayList<String>();
        salle4.add("lit25");
        salle4.add("lit26");
        salle4.add("lit27");
        salle4.add("lit28");
        salle4.add("lit29");

        List<String> salle8 = new ArrayList<String>();
        salle4.add("lit30");
        salle4.add("lit31");
        salle4.add("lit32");
        salle4.add("lit33");
        salle4.add("lit34");

        listDataChild.put(listDataHeader.get(0), salle2); // Header, Child data
        listDataChild.put(listDataHeader.get(1), salle3);
        listDataChild.put(listDataHeader.get(2), salle4);
        listDataChild.put(listDataHeader.get(3), salle5);
        listDataChild.put(listDataHeader.get(4), salle6);
        listDataChild.put(listDataHeader.get(5), salle7);
        listDataChild.put(listDataHeader.get(6), salle8);


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


  /*  @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i) {
            case 0:
                lst.setAdapter(new CustomListAdapter(getActivity(),list));
                getListData();
                break;
            case 1:
                lst.setAdapter(new CustomListAdapter(getActivity(),list5));
                getListData5lits();
                break;
            case 2:
                lst.setAdapter(new CustomListAdapter(getActivity(),list5));
                getListData5lits();
                break;
            case 3:
               // getListData5lits();
                lst.setAdapter(new CustomListAdapter(getActivity(),getListData5lits()));

                break;
            case 4:
                lst.setAdapter(new CustomListAdapter(getActivity(),list5));
                getListData5lits();
                break;
            case 5:
                lst.setAdapter(new CustomListAdapter(getActivity(),list5));
                getListData5lits();
                break;
            case 6:
                lst.setAdapter(new CustomListAdapter(getActivity(),list5));
                getListData5lits();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}
