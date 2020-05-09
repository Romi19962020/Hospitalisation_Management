package memory.hospitalisation_management;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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

import static memory.hospitalisation_management.Constantns.MEN;
import static memory.hospitalisation_management.Constantns.SERVER_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationFragment extends Fragment  {


    public ConsultationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_consultation, container, false);
        final EditText dateCons=view.findViewById(R.id.dateCons);
        final EditText nomCons= view.findViewById(R.id.nomCons);
        EditText prenomCons=view.findViewById(R.id.prenomCons);
        EditText dateNaisCons=view.findViewById(R.id.datenaissCons);
        EditText ageCons=view.findViewById(R.id.ageCons);
        RadioButton homme=view.findViewById(R.id.homme);
        RadioButton femme=view.findViewById(R.id.femme);
        final EditText motifCons=view.findViewById(R.id.motifCons);
        final EditText diagCons=view.findViewById(R.id.diagCons);
        CheckBox consDecision= view.findViewById(R.id.consDecision);
        final CheckBox hospDecision=view.findViewById(R.id.hospDecision);
        CheckBox hjDecision=view.findViewById(R.id.hjDecision);
        CheckBox hadDecision =view.findViewById(R.id.hadDecision);
        Button enregistrerCons=view.findViewById(R.id.enregistrerCons);

enregistrerCons.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
if(hospDecision.isChecked()){
 /* Intent intent = new Intent(getActivity(),HommeHospitalisationFragment.class);
    intent.putExtra("Nom",nomCons.getText().toString());
    startActivity(intent);*/
  // HommeHospitalisationFragment hommeHospitalisationFragment = new HommeHospitalisationFragment ();
   // Bundle bundle = new Bundle();
//    Date dateConsultation= Date.valueOf(dateCons.getText().toString());
    String nomConsultation= nomCons.getText().toString();
    String motifConsultation= motifCons.getText().toString();
    String diagConsultation= diagCons.getText().toString();
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

        Patient patient = new Patient(0,"Flan","Ben Flan",MEN);
        List<Consultation>consultations = new ArrayList<>();
        consultations.add(new Consultation(0,motifConsultation,diagConsultation,new Date()));//
        patient.setConsultations(consultations);
        //att
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    JsonObject data = gson.toJsonTree(patient).getAsJsonObject();
    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
    progressDialog.setMessage("Wait please ...");
    progressDialog.setCancelable(false);
    Ion.with(getActivity())
            .load("POST",SERVER_URL+"/Patient")
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


    // bundle.putString("YourKey", nomConsultation);
  //  hommeHospitalisationFragment.setArguments(bundle);
  //  getFragmentManager().beginTransaction().add(R.id.contentFl, hommeHospitalisationFragment).commit();*/

   /* getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.contentFl, hommeHospitalisationFragment, "YourKey")
            .addToBackStack(null)
            .commit();*/

}
    }
});// awdi e

            return view;
    }




}


