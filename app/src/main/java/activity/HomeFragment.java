package activity;

/**
 * Created by Alaa Shammaa on 9/3/2015.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.software.shell.fab.ActionButton;
import com.team.act.R;

import java.util.Calendar;


public class HomeFragment extends Fragment {
    //action button to trigger fragments replacements
    ActionButton fab;
    Animation anim, anim2, anim3;
    //store the statue of the button if it's clicked or not
    boolean isFabClicked = false;
    Animator animator, animator1;
    TextView dayOfWeek, monthOfYear, dayOfMonth, year;


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
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        fab = (ActionButton) rootView.findViewById(R.id.action_button);
        fab.setButtonColor(getResources().getColor(R.color.circular_fragment_color));
        fab.setShadowColor(getResources().getColor(R.color.fab_material_grey_500));
        fab.setRippleEffectEnabled(true);
        fab.setButtonColorRipple(getResources().getColor(R.color.circular_fragment_color_ripple));
        fab.setButtonColorPressed(getResources().getColor(R.color.fab_first_pressed));
        fab.setShadowRadius(5.0f);
        fab.setImageResource(R.drawable.calendar);
        anim = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_scale_down);
        anim.setDuration(300);
        anim2 = anim;
        anim3 = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_scale_down);

        //the Head Fragment
        final View circularFragment = rootView.findViewById(R.id.fragment_container1);


        // the Date that appeare in the home screen..............

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

        //The end of it...........



        //Add two fragments to the Home screen
        //final Fragment headFragment = new HeadFragment();
        Fragment bottomFragment = new BottomFragment();
        final Fragment calendarFragment = new CalendarFragment();
        final FragmentManager fragmentManager = getChildFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container2, bottomFragment, "fragment_bottom");
        fragmentTransaction.commit();
        //Set an action when the button is clicked
        // FIXME: 9/7/2015 The animation doesn't work on all screen sizes it's not dynamic
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFabClicked) {
                    isFabClicked = true;

                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    //fragmentTransaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    //fragmentTransaction2.setCustomAnimations(R.anim.enter, android.R.anim.fade_out);

                    if (Build.VERSION.SDK_INT >= 21) { //For Lollipop -.-
                        fab.setHideAnimation(anim);
                        fab.playHideAnimation();


                    } else {
                        fragmentTransaction2.setTransition(fragmentTransaction2.TRANSIT_FRAGMENT_OPEN);
                        fab.setHideAnimation(anim2);//without animation
                        fab.playHideAnimation();
                    }


                    fragmentTransaction2.replace(R.id.fragment_container1, calendarFragment, "fragment_calendar");
                    fragmentTransaction2.commit();

                    // change the position of the button when clicked
                } else {
                    isFabClicked = false;
                    fab.setShowAnimation(anim3);
                    fab.playShowAnimation();

                    //if (Build.VERSION.SDK_INT >= 21 )
                    //headFragment.setEnterTransition(new Explode());
                    //fragmentTransaction1.commit();
                    // change the position of the button when clicked
                }
            }
        });


        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                int cx = circularFragment.getWidth() - 190;
                int cy = circularFragment.getHeight();
                int finalRadius = Math.max(circularFragment.getWidth(), circularFragment.getHeight());
                animator = ViewAnimationUtils.createCircularReveal(circularFragment, cx, cy, 0, finalRadius);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(350);
                animator.start();
                circularFragment.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fab.setImageResource(R.drawable.logo);
                fab.setShowAnimation(ActionButton.Animations.SCALE_UP);
                fab.playShowAnimation();
                fab.setButtonColor(getResources().getColor(R.color.fab_material_white));
                fab.setButtonColorPressed(getResources().getColor(R.color.fab_second_pressed));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        anim3.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                int cx = circularFragment.getWidth() - 190;
                int cy = circularFragment.getHeight();
                int finalRadius = Math.max(circularFragment.getWidth(), circularFragment.getHeight());
                animator1 = ViewAnimationUtils.createCircularReveal(circularFragment, cx, cy, finalRadius, 0);
                animator1.setInterpolator(new AccelerateInterpolator());
                animator1.setDuration(350);
                animator1.start();
                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        circularFragment.setVisibility(View.INVISIBLE);
                    }
                });


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fab.setImageResource(R.drawable.calendar);
                fab.setShowAnimation(ActionButton.Animations.SCALE_UP);
                fab.playShowAnimation();
                fab.setButtonColor(getResources().getColor(R.color.circular_fragment_color));




            }

            @Override
            public void onAnimationRepeat(Animation animation) {


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

