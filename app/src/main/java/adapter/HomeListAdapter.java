package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.act.R;

import java.util.ArrayList;
import java.util.List;

import model.HomeDataList;

/**
 * Created by ayham on 9/12/15.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private ArrayList<HomeDataList> myList;

    public HomeListAdapter(ArrayList<HomeDataList> myList)
    {
        this.myList = myList;
    }

    public HomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }




    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(myList.get(position).getTitle());
        holder.description.setText(myList.get(position).getDescription());
        holder.icon.setImageResource(myList.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, description;
        public ImageView icon;

        public ViewHolder(View v)
        {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            description = (TextView) v.findViewById(R.id.description);
            icon = (ImageView) v.findViewById(R.id.icon);

        }

    }
}
