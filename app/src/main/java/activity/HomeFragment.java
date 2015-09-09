package activity;

/**
 * Created by Alaa Shammaa on 9/3/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.software.shell.fab.ActionButton;
import com.team.act.R;


public class HomeFragment extends Fragment {
    //action button to trigger fragments replacements
    ActionButton fab;
    //store the statue of the button if it's clicked or not
    boolean isFabClicked = false;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        fab = (ActionButton) rootView.findViewById(R.id.action_button);
        fab.setButtonColor(getResources().getColor(R.color.colorPrimary));
        fab.setShadowColor(getResources().getColor(R.color.fab_material_grey_500));
        fab.setShadowRadius(5.0f);
        fab.setImageResource(R.drawable.calendar);

        //Add two fragments to the Home screen
        final Fragment headFragment = new HeadFragment();
        Fragment bottomFragment = new BottomFragment();
        final Fragment calendarFragment = new CalendarFragment();
        final FragmentManager fragmentManager = getChildFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container1, headFragment, "fragment_head");
        fragmentTransaction.replace(R.id.fragment_container2, bottomFragment, "fragment_bottom");
        fragmentTransaction.commit();
        //Set an action when the button is clicked
        // FIXME: 9/7/2015 The animation doesn't work on all screen sizes it's not dynamic
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFabClicked) {
                    isFabClicked = true;
                    FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                    fragmentTransaction1.replace(R.id.fragment_container1, calendarFragment, "fragment_calendar");
                    fragmentTransaction1.commit();
                    // change the position of the button when clicked
                    fab.animate().yBy(300).setDuration(500);
                }else{
                    isFabClicked = false;
                    FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                    fragmentTransaction1.replace(R.id.fragment_container1, headFragment, "fragment_head");
                    fragmentTransaction1.commit();
                    // change the position of the button when clicked
                    fab.animate().yBy(-300).setDuration(500);
                }
            }
        });

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
}