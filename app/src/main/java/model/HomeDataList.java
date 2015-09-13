package model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ayham on 9/12/15.
 */
public class HomeDataList {

    int icon;
    String  title, description;

    public HomeDataList(String title, String description, int icon)
    {
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
