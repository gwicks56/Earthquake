package gwicks.com.ebayproject;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gwicks on 9/01/2017.
 */

/*
    Custom adaptor for the listView
    Uses ViewHolder inner class to speed up scrolling
*/

class EarthquakeAdaptor extends ArrayAdapter {

    // Class to hold each earthquake to prevent listView repeatedly calling findViewByID
    private static class ViewHolder {

        TextView tvEqid;
        TextView tvTime;
        TextView tvDepth;
        TextView tvLat;
        TextView tvLong;
        TextView tvMag;


    }

    private static final String TAG = "EarthquakeAdaptor";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Earthquake> earthquakes;

    EarthquakeAdaptor(Context context, int resource, List<Earthquake> earthquakes) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.earthquakes = earthquakes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder; // ViewHolder to store each earthquake to speed up scrolling

        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvDepth = (TextView) convertView.findViewById(R.id.idDepth);
            viewHolder.tvEqid = (TextView) convertView.findViewById(R.id.idEqid);
            viewHolder.tvLat = (TextView) convertView.findViewById(R.id.idLat);
            viewHolder.tvLong = (TextView) convertView.findViewById(R.id.idLong);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.idDateAndTime);
            viewHolder.tvMag = (TextView) convertView.findViewById(R.id.idMagitude);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Earthquake current;
        current = earthquakes.get(position);

        viewHolder.tvEqid.setText("ID: " + current.getEqid());
        viewHolder.tvLat.setText(current.getLatitude());
        viewHolder.tvTime.setText(current.getDataTime());
        viewHolder.tvLong.setText(current.getLongitude());
        viewHolder.tvMag.setText(current.getMagitudeAsString());
        viewHolder.tvDepth.setText(current.getDepth());

        /* The magnitude of earthquake is color coded:
            GREEN: less than 4
            YELLOW: between 4 and 6
            ORANGE: between 6 and 8
            RED: above 8
        */

        double color = current.getMagnitude();
        if (color <= 4) {
            viewHolder.tvMag.setTextColor(Color.GREEN);
        } else if (color > 4 && color <= 6) {
            viewHolder.tvMag.setTextColor(Color.YELLOW);
        } else if (color > 6 && color <= 8) {
            viewHolder.tvMag.setTextColor(Color.rgb(255, 201, 102));
        } else {
            viewHolder.tvMag.setTextColor(Color.RED);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return earthquakes.size();
    }

    @Override
    public Earthquake getItem(int position) {
        return earthquakes.get(position);
    }
}


