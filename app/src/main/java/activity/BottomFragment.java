package activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.act.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {
    ImageView icon;
    TextView title;
    TextView description;
    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //populate the second fragment with random data
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.test));
//        setListAdapter(arrayAdapter);
        View root = inflater.inflate(R.layout.fragment_bottom, null);
        icon = (ImageView) root.findViewById(R.id.icon);
        title = (TextView) root.findViewById(R.id.title);
        description = (TextView) root.findViewById(R.id.description);
        return root;
    }


}
