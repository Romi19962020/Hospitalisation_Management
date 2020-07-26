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
import java.util.List;

import static memory.hospitalisation_management.Constantns.CONS;
import static memory.hospitalisation_management.Constantns.HAD;
import static memory.hospitalisation_management.Constantns.HJ;
import static memory.hospitalisation_management.Constantns.HOSP;
import static memory.hospitalisation_management.Constantns.MEN;
import static memory.hospitalisation_management.Constantns.SERVER_URL;
import static memory.hospitalisation_management.Constantns.WOMEN;


/**
 * A simple {@link Fragment} subclass.
 */
public class FemmeHjFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private ListView lsthj;

    public FemmeHjFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_femme_hj, container, false);
        Spinner spinner= view.findViewById(R.id.spinnerHJ);
        final ListView lst=view.findViewById(R.id.lstvw);


        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.salle_hj,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        lsthj=view.findViewById(R.id.listHj);
        getPatientsFromServerWithtype();
        //getPatientsFromServerWithsexe();

        final List<DetailHospitalisation> list = getListData();
        lst.setAdapter(new CustomListAdapter(getActivity(),list));



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
    private void getPatientsFromServerWithtype(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        Ion.with(getActivity())
                .load("GET",SERVER_URL+"/Patients/Hospitalisation/{type}")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        // do stuff with the result or error
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                        Type typeOfT = new TypeToken<List<Patient>>(){}.getType();
                        List<Patient>patients = gson.fromJson(result, typeOfT);
                        //List<Hospitalisation>hospitalisations = null;
                        if (patients==null)
                            Toast.makeText(getActivity(), "error" +e.getMessage(), Toast.LENGTH_SHORT).show();
                        else {

                            int i = 0;
                            while (i<patients.size()){
                                   /* if(i>=patients.size())
                                            break;
                                        else if (patients.get(i).getSexe()==MEN) {
                                           // if(hospitalisations.get(i).getHospType()==CONS ||hospitalisations.get(i).getHospType()==HOSP ||hospitalisations.get(i).getHospType()==HAD)

                                                patients.remove(i);
                                        } else
                                            i++;
                                }*/
                            lsthj.setAdapter(new PatientHospAdapter(getActivity(), patients));
                            progressDialog.dismiss();
                            }
                        }

                    }
                });
    }
    private void getPatientsFromServerWithsexe(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        Ion.with(getActivity())
                .load("GET",SERVER_URL+"/Patients/{genre}")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        // do stuff with the result or error
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                        Type typeOfT = new TypeToken<List<Patient>>(){}.getType();
                        List<Patient>patients = gson.fromJson(result, typeOfT);
                        // List<Hospitalisation>hospitalisations = null;
                        if (patients==null)
                            Toast.makeText(getActivity(), "error" +e.getMessage(), Toast.LENGTH_SHORT).show();
                        else {
                            int i = 0;
                            while (i < patients.size()) {
                           /*         if(i>=patients.size())
                                            break;
                                        else if (patients.get(i).getSexe()==MEN) {
                                          //  if(hospitalisations.get(i).getHospType()==CONS ||hospitalisations.get(i).getHospType()==HJ ||hospitalisations.get(i).getHospType()==HAD)
                                                patients.remove(i);
                                        } else
                                            i++;
                                }*/

                                lsthj.setAdapter(new PatientHospAdapter(getActivity(), patients));
                                progressDialog.dismiss();
                            }
                        }
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
