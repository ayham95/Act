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

import com.software.shell.fab.ActionButton;
import com.team.act.R;


public class HomeFragment extends Fragment {
    ActionButton fab;

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


        Fragment headFragment = new HeadFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container1, headFragment);
        fragmentTransaction.commit();



        //second fragment..
        Fragment listFragment = new ListFragment();
        FragmentManager fragmentManager1 = getFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.add(R.id.fragment_container2, listFragment);
        fragmentTransaction1.commit();


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