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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.ArrayList;
import java.util.Calendar;
import adapter.HomeListAdapter;
import model.DividerItemDecoration;
import model.HomeDataList;


public class HomeFragment extends Fragment {
    //action button to trigger fragments replacements
    ActionButton fab;
    Animation anim, fade, anim3;
    //store the statue of the button if it's clicked or not
    boolean isFabClicked = false;
    Animator animator, animator1;
    TextView dayOfWeek, monthOfYear, dayOfMonth, year;
    RecyclerView listView;
    HomeListAdapter listAdapter;


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
        anim3 = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_scale_down);
        fade = AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_out);
        fade.setDuration(300);


        //The listView that appeares in the home screen built with RecyclerView.....................
        listView = (RecyclerView) rootView.findViewById(R.id.recycler);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<HomeDataList> homeList = new ArrayList<HomeDataList>();
        homeList.add(new HomeDataList("Reminder", "04:30PM", R.drawable.calendar));
        homeList.add(new HomeDataList("Add note", "No notes added yet", R.drawable.calendar));

        //the listView adapter
        listAdapter = new HomeListAdapter(homeList);
        listView.setAdapter(listAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        listView.addItemDecoration(itemDecoration);
        listView.setItemAnimator(new DefaultItemAnimator());


        //...................................................


        //the Head Fragment
        final View circularFragment = rootView.findViewById(R.id.fragment_container1);


        // the Date that appeare in the home screen...................................

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

        //The end of it..............................



        final Fragment headFragment = new HeadFragment();
        //Fragment bottomFragment = new BottomFragment();
        final Fragment calendarFragment = new CalendarFragment();
        final FragmentManager fragmentManager = getChildFragmentManager();


        //Set an action when the button is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFabClicked) {
                    isFabClicked = true;


                        fab.setHideAnimation(anim);
                        fab.playHideAnimation();



                    // after clicking the button (while on calendar fragment)..............................
                } else {
                    isFabClicked = false;

                        fab.setShowAnimation(anim3);
                        fab.playShowAnimation();

                }
            }
        });


        //ActionButton AnimationListeners..................................


        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //if(Lollipop)
                if (Build.VERSION.SDK_INT >= 21) {
                    int cx = circularFragment.getWidth() - 190;
                    int cy = circularFragment.getHeight();
                    int finalRadius = Math.max(circularFragment.getWidth(), circularFragment.getHeight());
                    animator = ViewAnimationUtils.createCircularReveal(circularFragment, cx, cy, 0, finalRadius);
                    animator.setInterpolator(new AccelerateInterpolator());
                    animator.setDuration(350);
                    animator.start();
                    circularFragment.setVisibility(View.VISIBLE);
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    fragmentTransaction2.replace(R.id.fragment_container1, calendarFragment, "fragment_calendar");
                    fragmentTransaction2.commit();
                }

                else{
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    fragmentTransaction2.setTransition(fragmentTransaction2.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction2.replace(R.id.fragment_container1, calendarFragment, "fragment_calendar");
                    fragmentTransaction2.commit();
                    circularFragment.setVisibility(View.VISIBLE);



                }

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
                //if ( Lollipop )
                if(Build.VERSION.SDK_INT >= 21) {
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


                else{

                    //add a headfragment when it's not on Lollipop!!
                    FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                    fragmentTransaction2.setTransition(fragmentTransaction2.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction2.replace(R.id.fragment_container1, headFragment, "fragment_head");
                    fragmentTransaction2.commit();


                }


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

        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                circularFragment.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        //........................................................




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

