package memory.hospitalisation_management;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
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

import static memory.hospitalisation_management.Constantns.SERVER_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListHJFragment extends Fragment {

private ListView lsthj;
    private SearchView searchView;

    public ListHJFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_list_hj, container, false);
       lsthj=view.findViewById(R.id.listHj);
       searchView=view.findViewById(R.id.searchview);
        getPatientsFromServerWithsexe();
        // getPatientsFromServerWithtype();
        return view;
    }



    private void getPatientsFromServerWithsexe(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Ion.with(getActivity())
                .load("GET",SERVER_URL+"/Patients")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        // do stuff with the result or error
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                        Type typeOfT = new TypeToken<List<Patient>>() {}.getType();
                        List<Patient> patients = gson.fromJson(result, typeOfT);
                        // List<Hospitalisation>hospitalisations ;

                        if (patients == null)
                            Toast.makeText(getActivity(), "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        else {
                      /*  ConsultationFragment cons = new ConsultationFragment();
                        if (cons.homme.isChecked()) {*/
                            //  Toast.makeText(getActivity(), patients.size()+"/", Toast.LENGTH_SHORT).show();
                          /*  int i = 0;
                            while (i < patients.size()) {
                                if(i>=patients.size())
                                    break;
                               // else if (patients.get(i).getSexe()==WOMEN) {
                                    // if(hospitalisations.get(i).getHospType()==CONS ||hospitalisations.get(i).getHospType()==HJ ||hospitalisations.get(i).getHospType()==HAD)

                                  //  patients.remove(i);

                                    i++;
                            }*/

                            lsthj.setAdapter(new PatientHospAdapter(getActivity(), patients));
                            progressDialog.dismiss();
                        }

                    }

                });
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
                        Type typeOfT = new TypeToken<List<Patient>>() {}.getType();
                        List<Patient> patients = gson.fromJson(result, typeOfT);
                        // List<Hospitalisation>hospitalisations ;
                        if (patients == null)
                            Toast.makeText(getActivity(), "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        else {
                      /*  ConsultationFragment cons = new ConsultationFragment();
                        if (cons.homme.isChecked()) {*/
                            //  Toast.makeText(getActivity(), patients.size()+"/", Toast.LENGTH_SHORT).show();
                            int i = 0;
                            while (i < patients.size()) {
                                if(i>=patients.size())
                                    break;
                                    //  else if(patients.get(i).getTypehosp()==CONS ||patients.get(i).getTypehosp()==HJ ||patients.get(i).getTypehosp()==HAD)

                                    // patients.remove(i);
                                else
                                    i++;
                            }

                            lsthj.setAdapter(new PatientHospAdapter(getActivity(), patients));
                            progressDialog.dismiss();
                        }
                    }


                });
    }


}
