package memory.hospitalisation_management;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GridAdapter extends ArrayAdapter {
    List<Date> dates;
    Calendar currentDate;
    List<RDV> rdv;
    LayoutInflater inflater;

    public GridAdapter(@NonNull Context context, List<Date> dates, Calendar currentDate, List<RDV> rdv) {
        super(context, R.layout.cellule_calendrier_layout);

        this.dates=dates;
        this.currentDate=currentDate;
        this.rdv=rdv;
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date monthDate = dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int DayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH)+1;
        int currentYear = currentDate.get(Calendar.YEAR);



        View view = convertView;
        if (view ==null){
            view = inflater.inflate(R.layout.cellule_calendrier_layout,parent,false);

        }

       if(displayMonth == currentMonth && displayYear == currentYear){
            view.setBackgroundColor(Color.WHITE);

        }
        else
        {

            view.setBackgroundColor(getContext().getResources().getColor(R.color.mois));
        }

        TextView Day_Number = view.findViewById(R.id.calendar_day);
        TextView RDV_num = view.findViewById(R.id.id_rdv);
        Day_Number.setText(String.valueOf(DayNo));
        Calendar eventCalendar = Calendar.getInstance();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0;i < rdv.size();i++){
            eventCalendar.setTime(ConvertStringToDate(rdv.get(i).getDate()));
            if(DayNo == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH)+1
                    && displayYear == eventCalendar.get(Calendar.YEAR)){
                arrayList.add(rdv.get(i).getRdv());
                RDV_num.setText(arrayList.size()+" rdv");


            }

        }



        return view;
    }


    private Date ConvertStringToDate(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


    @Override
    public int getCount() {
        return dates.size();
    }


    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }


    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }
}
