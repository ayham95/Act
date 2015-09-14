package activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.*;
import com.team.act.R;


/**
 * Created by Alaa Shammaa on 9/13/2015.
 */
public class MainCalendar extends Fragment implements OnDateChangedListener {
    MaterialCalendarView calendarView;
    public MainCalendar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_calendar, container, false);
        calendarView = (MaterialCalendarView) rootView.findViewById(R.id.calendar);
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
