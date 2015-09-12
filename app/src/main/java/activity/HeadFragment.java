package activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.act.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadFragment extends Fragment {

    TextView dayOfWeek, monthOfYear, dayOfMonth, year;


    public HeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_head, container, false);
        final Calendar myCalander = Calendar.getInstance();

        dayOfWeek = (TextView) rootView.findViewById(R.id.day_of_week);
        monthOfYear = (TextView) rootView.findViewById(R.id.month_of_year);
        dayOfMonth = (TextView) rootView.findViewById(R.id.day_of_month);
        year = (TextView) rootView.findViewById(R.id.year);

        String[] days = new String[]{" ", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        String day = days[myCalander.get(Calendar.DAY_OF_WEEK)];
        dayOfWeek.setText(day);

        String[] months = new String[] {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST","SEPTEMBER" , "OCTOBER", "NOVEMBER"};
        String month = months[myCalander.get(Calendar.MONTH)];

        int dayNumber = myCalander.get(Calendar.DAY_OF_MONTH);
        dayOfMonth.setText(new StringBuilder().append(dayNumber) + ",");

        int yearNumber = myCalander.get(Calendar.YEAR);
        year.setText(new StringBuilder().append(yearNumber));


        return rootView;
    }


}
