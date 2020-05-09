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
import java.util.List;

import static memory.hospitalisation_management.Constantns.SERVER_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class HommeHospitalisationFragment extends Fragment implements AdapterView.OnItemSelectedListener {
//ArrayAdapter<String> arrayAdapter;
/*Intent  intent;

    public Intent getIntent() {
        return intent;
    }
    String nomConsultation=intent.getExtras().getString("Nom");*/
private ListView lsthosp;
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


        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.salle_hosp,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        lsthosp=view.findViewById(R.id.listHosp);
        getPatientsFromServer();
        final List<DetailHospitalisation> list = getListData();
        lst.setAdapter(new CustomListAdapter(getActivity(),list));



        Button btn= view.findViewById(R.id.ajoutPatient);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerte = new AlertDialog.Builder(getActivity());
                View alerteView = getLayoutInflater().inflate(R.layout.dialog_ajout_patient_hosp, null);
               ListView listePatient= alerteView.findViewById(R.id.listePatient);
                alerte.setView(alerteView);
                final AlertDialog dialog = alerte.create();
                dialog.setTitle("Ajout Patient");


                dialog.show();


            }
        });
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
/*    private  List<Patient_Hospitalisaion> getListPatient() {
       // Bundle bundle= getArguments()

        List<Patient_Hospitalisaion> listhosp = new ArrayList<Patient_Hospitalisaion>();
        Patient_Hospitalisaion pat1 = new Patient_Hospitalisaion("Patient 1",null,null);
        Patient_Hospitalisaion pat2 = new Patient_Hospitalisaion("Patient 2",null,null);

        listhosp.add(pat1);
        listhosp.add(pat2);

        return listhosp;
    }*/


private void getPatientsFromServer(){
    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
    progressDialog.setMessage("Wait please ...");
    progressDialog.setCancelable(false);
    Ion.with(getActivity())
            .load("GET",SERVER_URL+"/Patients")
            .asJsonArray()
            .setCallback(new FutureCallback<JsonArray>() {
                @Override
                public void onCompleted(Exception e, JsonArray result) {
                    // do stuff with the result or error
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    Type typeOfT = new TypeToken<List<Patient>>(){}.getType();
                    List<Patient>patients = gson.fromJson(result, typeOfT);
                    lsthosp.setAdapter(new PatientHospAdapter(getActivity(),patients));
                    progressDialog.dismiss();
                }
            });
}
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
