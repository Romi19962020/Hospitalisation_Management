package memory.hospitalisation_management;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaFragment extends Fragment {
    Calendrier_rdv calendrier_rdv;


    public AgendaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_agenda, container, false);
    calendrier_rdv=view.findViewById(R.id.calendrier_rdv);


    return view;
    }

}
