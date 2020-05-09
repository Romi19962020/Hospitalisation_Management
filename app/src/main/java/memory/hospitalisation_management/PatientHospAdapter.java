package memory.hospitalisation_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PatientHospAdapter extends BaseAdapter{
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
            } else {
                holder = (PatientHospAdapter.ViewHolder) convertView.getTag();
            }

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

