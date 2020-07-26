package memory.hospitalisation_management;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import static memory.hospitalisation_management.Constantns.CONS;
import static memory.hospitalisation_management.Constantns.HAD;
import static memory.hospitalisation_management.Constantns.HJ;
import static memory.hospitalisation_management.Constantns.SERVER_URL;
import static memory.hospitalisation_management.Constantns.WOMEN;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListHospFragment extends Fragment {
    private ListView lsthosp,lst;
    private SearchView searchView;

    // private ArrayList<Patient_Hospitalisaion>data= new ArrayList<Patient_Hospitalisaion>();


    public ListHospFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list_hosp, container, false);
        lsthosp=view.findViewById(R.id.listHosp);
       searchView=view.findViewById(R.id.searchview);
lsthosp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
});

       /* List<Patient_Hospitalisaion> list = getListChallenges();
        final PatientHospAdapter adapter = new PatientHospAdapter(getActivity(), list);
        lsthosp.setAdapter(adapter);*/
       getPatientsFromServerWithsexe();
      //   getPatientsFromServerWithtype();
return view;
    }
    public List<Patient_Hospitalisaion> getListChallenges(){
        List<Patient_Hospitalisaion> list = new ArrayList<>();
        Patient_Hospitalisaion info1 = new Patient_Hospitalisaion("flen", null,null);
        Patient_Hospitalisaion info2 = new Patient_Hospitalisaion("Lit 2",null,null);
        Patient_Hospitalisaion info3 = new Patient_Hospitalisaion("Lit 3",null,null);

        list.add(info1);
        list.add(info2);
        list.add(info3);

        return list;
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
                            lsthosp.setAdapter(new PatientHospAdapter(getActivity(),patients));

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
                           /* int i = 0;
                            while (i < patients.size()) {
                                if(i>=patients.size())
                                    break;
                              //  else if(patients.get(i).getTypehosp()==CONS ||patients.get(i).getTypehosp()==HJ ||patients.get(i).getTypehosp()==HAD)

                                   // patients.remove(i);
                                else
                                    i++;
                            }*/
                            Toast.makeText(getActivity(), "sucess" +patients.size() , Toast.LENGTH_SHORT).show();

                            lsthosp.setAdapter(new PatientHospAdapter(getActivity(), patients));
                            progressDialog.dismiss();
                        }
                    }


                });
    }

}
