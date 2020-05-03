package memory.hospitalisation_management;


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
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


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
        final EditText nomCons= view.findViewById(R.id.nomCons);
        EditText prenomCons=view.findViewById(R.id.prenomCons);
        EditText dateNaisCons=view.findViewById(R.id.datenaissCons);
        EditText ageCons=view.findViewById(R.id.ageCons);
        EditText motifCons=view.findViewById(R.id.motifCons);
        EditText diagCons=view.findViewById(R.id.diagCons);
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
    HommeHospitalisationFragment hommeHospitalisationFragment = new HommeHospitalisationFragment ();
    Bundle bundle = new Bundle();
    String nomConsultation= nomCons.getText().toString();
    bundle.putString("YourKey", nomConsultation);
    hommeHospitalisationFragment.setArguments(bundle);
    getFragmentManager().beginTransaction().add(R.id.contentFl, hommeHospitalisationFragment).commit();

   /* getActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.contentFl, hommeHospitalisationFragment, "YourKey")
            .addToBackStack(null)
            .commit();*/
}
    }
});

            return view;
    }




}


