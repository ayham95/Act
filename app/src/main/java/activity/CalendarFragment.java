package activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.*;
import com.team.act.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Alaa Shammaa on 9/3/2015.
 */
public class CalendarFragment extends Fragment implements OnDateChangedListener {
    MaterialCalendarView calendarView;
    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = (MaterialCalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setTopbarVisible(false);
        calendarView.setOnDateChangedListener(this);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDateChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {

    }

}
