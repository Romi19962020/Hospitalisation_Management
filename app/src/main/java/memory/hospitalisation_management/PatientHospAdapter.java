package memory.hospitalisation_management;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PatientHospAdapter extends BaseAdapter {
    private List<Patient> patients;
    private LayoutInflater layoutInflater;
    private Context context;

    public PatientHospAdapter(Context aContext,  List<Patient> patients) {
        this.context = aContext;
        this.patients = patients;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return patients.size();
    }

    @Override
    public Object getItem(int position) {
        return patients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.patients_hospitalise, null);
            holder = new PatientHospAdapter.ViewHolder();
            holder.nomPatHosp =  convertView.findViewById(R.id.nomPatHosp);
            holder.admission = convertView.findViewById(R.id.admission);
            holder.dm=convertView.findViewById(R.id.dm);
            convertView.setTag(holder);
        }
        holder= (ViewHolder) convertView.getTag();
        holder.admission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,AdmissionActivity.class);
                intent.putExtra("admission","admission");
               context. startActivity(intent);
            }
        });

        /*else {
            holder = (PatientHospAdapter.ViewHolder) convertView.getTag();
        }
*/
        Patient patient = patients.get(position);
        holder.nomPatHosp.setText("" + patient.getFirstName()+" "+patient.getLastName());
//            holder.admission.setTag(pat.admission);
//            holder.dm.setTag(pat.dm);


        return convertView;
    }


    static class ViewHolder {
        TextView nomPatHosp;
        Button admission;
        Button  dm;
    }
}

/* class LitHospAdapter extends ArrayAdapter<Bed> {
    private int layout;
    private List<Bed>bed;
     public LitHospAdapter(@NonNull Context context, int resource, @NonNull List<Bed> objects) {
         super(context, resource, objects);
         bed=objects;
         layout=resource;
     }

     @NonNull
     @Override
     public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

         ViewHolder mainViewholder = null;
         if(convertView == null) {
             LayoutInflater inflater = LayoutInflater.from(getContext());
             convertView = inflater.inflate(layout, parent, false);
             ViewHolder viewHolder = new ViewHolder();
             viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
             viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
             convertView.setTag(viewHolder);
         }
         mainViewholder = (ViewHolder) convertView.getTag();
         mainViewholder.button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
             }
         });
         mainViewholder.title.setText(getItem(position));

         return convertView;
     }
 }*/
/*public class ViewHolder {

    ImageView thumbnail;
    TextView title;
    Button button;
     }
} */