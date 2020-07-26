package memory.hospitalisation_management;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static memory.hospitalisation_management.Constantns.HJ;
import static memory.hospitalisation_management.Constantns.HOSP;
import static memory.hospitalisation_management.Constantns.MEN;
import static memory.hospitalisation_management.Constantns.SERVER_URL;
import static memory.hospitalisation_management.Constantns.WOMEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationFragment extends Fragment  {
EditText nomCons,prenomCons,motifCons,diagCons;
RadioGroup sexe;
RadioButton homme,femme,radioButton;
CheckBox consDecision, hospDecision,hjDecision,hadDecision;
    String choice;
    public ConsultationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_consultation, container, false);
        final EditText dateCons=view.findViewById(R.id.dateCons);
         nomCons= view.findViewById(R.id.nomCons);
         prenomCons=view.findViewById(R.id.prenomCons);
        EditText dateNaisCons=view.findViewById(R.id.datenaissCons);
        EditText ageCons=view.findViewById(R.id.ageCons);
        sexe=view.findViewById(R.id.radioSexe);
        homme=view.findViewById(R.id.homme);
        femme=view.findViewById(R.id.femme);
        motifCons=view.findViewById(R.id.motifCons);
         diagCons=view.findViewById(R.id.diagCons);
        consDecision= view.findViewById(R.id.consDecision);
         hospDecision=view.findViewById(R.id.hospDecision);
         hjDecision=view.findViewById(R.id.hjDecision);
        hadDecision =view.findViewById(R.id.hadDecision);
        Button enregistrerCons=view.findViewById(R.id.enregistrerCons);
       /*  int radioId= sexe.getCheckedRadioButtonId();
         radioButton=view.findViewById(radioId);
         choice= (String) radioButton.getText();*/
       hospDecision.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               AlertDialog.Builder alerte = new AlertDialog.Builder(getActivity());
               View alerteView = getLayoutInflater().inflate(R.layout.dialog_ajout_patient_hosp, null);
               ListView listelit= alerteView.findViewById(R.id.listelit);
               Button ajout=alerteView.findViewById(R.id.ajoutpatlit);

               alerte.setView(alerteView);
               final AlertDialog dialog = alerte.create();
               dialog.setTitle("Ajout Patient");
               dialog.setMessage("Veuillez choisir un des lits");
 /* List<LitHospitalisation> list = getListLit();
       final   LitHospAdapter adapter = new LitHospAdapter(getActivity(), list);
        listelit.setAdapter(adapter);*/

               dialog.show();
           }
       });

enregistrerCons.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (hospDecision.isChecked()) {
          AddConsultationPatientHospHomme();
        }
        else
            if (hjDecision.isChecked()) {
            AddConsultationPatientHJHomme();
        }

       // }
    }
});
return view;
    }
public List<LitHospitalisation>getListLit(){
    List<LitHospitalisation> list = new ArrayList<>();
   LitHospitalisation info1 = new LitHospitalisation("Lit 1", null);
   LitHospitalisation info2 = new LitHospitalisation("Lit 2",null);
    LitHospitalisation info3 = new LitHospitalisation("Lit 3",null);

    list.add(info1);
    list.add(info2);
    list.add(info3);

    return list;

}

   /* public int getSexe(){
        if(choice=="homme"){
            return 1;
        }
        else return 0;
    }*/
public void AddConsultationPatientHospHomme(){
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
    Patient patient = new Patient(getId(), nomCons.getText().toString(), prenomCons.getText().toString(), MEN);

    List<Consultation> consultations = new ArrayList<>();
    consultations.add(new Consultation(getId(), motifCons.getText().toString(), diagCons.getText().toString(), new Date()));
    patient.setConsultations(consultations);

   /* List<Hospitalisation> hospitalisations=new ArrayList<>();
    hospitalisations.add(new Hospitalisation((long) getId(),HOSP,new Date(),new String()));
    patient.setHospitalisations(hospitalisations);*/

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    JsonObject data = gson.toJsonTree(patient).getAsJsonObject();
    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
    progressDialog.setMessage("Wait please ...");
    progressDialog.setCancelable(false);
    Ion.with(getActivity())
            .load("POST", SERVER_URL + "/Patient")
            .setJsonObjectBody(data)
            .asJsonObject()
            .setCallback(new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {
                    // do stuff with the result or error
                    if(e==null)
                        Toast.makeText(getActivity(),"error" +e.getMessage(),Toast.LENGTH_LONG).show();
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "with success!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

}
    public void AddConsultationPatientHospFemme(){
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        Patient patient = new Patient(getId(), nomCons.getText().toString(), prenomCons.getText().toString(), WOMEN);

        List<Consultation> consultations = new ArrayList<>();
        consultations.add(new Consultation(getId(), motifCons.getText().toString(), diagCons.getText().toString(), new Date()));//
        patient.setConsultations(consultations);
       /* List<Hospitalisation> hospitalisations=new ArrayList<>();
        hospitalisations.add(new Hospitalisation((long) getId(),HOSP,new Date(),new String()));
        patient.setHospitalisations(hospitalisations);*/

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        JsonObject data = gson.toJsonTree(patient).getAsJsonObject();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        Ion.with(getActivity())
                .load("POST", SERVER_URL + "/Patient")
                .setJsonObjectBody(data)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "with success!", Toast.LENGTH_SHORT).show();


                    }
                });

    }
    public void AddConsultationPatientHJHomme(){
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        Patient patient = new Patient(getId(), nomCons.getText().toString(), prenomCons.getText().toString(), MEN);

        List<Consultation> consultations = new ArrayList<>();
        consultations.add(new Consultation(getId(), motifCons.getText().toString(), diagCons.getText().toString(), new Date()));//
        patient.setConsultations(consultations);
       /* List<Hospitalisation> hospitalisations=new ArrayList<>();
        hospitalisations.add(new Hospitalisation((long) getId(),HJ,new Date(),new String()));
        patient.setHospitalisations(hospitalisations);*/

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        JsonObject data = gson.toJsonTree(patient).getAsJsonObject();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        Ion.with(getActivity())
                .load("POST", SERVER_URL + "/Patient")
                .setJsonObjectBody(data)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        if(e==null)
                            Toast.makeText(getActivity(),"error" +e.getMessage(),Toast.LENGTH_LONG).show();
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "with success!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
    public void AddConsultationPatientHJFemme(){
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        Patient patient = new Patient(getId(), nomCons.getText().toString(), prenomCons.getText().toString(), MEN);

        List<Consultation> consultations = new ArrayList<>();
        consultations.add(new Consultation(getId(), motifCons.getText().toString(), diagCons.getText().toString(), new Date()));//
        patient.setConsultations(consultations);

       /* List<Hospitalisation> hospitalisations=new ArrayList<>();
        hospitalisations.add(new Hospitalisation((long) getId(),HJ,new Date(),new String()));
        patient.setHospitalisations(hospitalisations);*/

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        JsonObject data = gson.toJsonTree(patient).getAsJsonObject();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        Ion.with(getActivity())
                .load("POST", SERVER_URL + "/Patient")
                .setJsonObjectBody(data)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        if(e==null)
                            Toast.makeText(getActivity(),"error" +e.getMessage(),Toast.LENGTH_LONG).show();
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "with success!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}



