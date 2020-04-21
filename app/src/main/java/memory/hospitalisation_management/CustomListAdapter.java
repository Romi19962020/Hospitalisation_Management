package memory.hospitalisation_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<DetailHospitalisation> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext,  List<DetailHospitalisation> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_hospitalisation, null);
            holder = new ViewHolder();
            holder.lit = (TextView) convertView.findViewById(R.id.litHosp);
            holder.nom = (TextView) convertView.findViewById(R.id.nomHosp);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DetailHospitalisation detail = this.listData.get(position);
        holder.lit.setText(detail.getLit());
        holder.nom.setText(detail.getNom());

        return convertView;
    }


    static class ViewHolder {
        TextView lit;
        TextView nom;
    }
}
