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

class EarthquakeAdaptor extends ArrayAdapter {

    private static final String TAG = "EarthquakeAdaptor";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Earthquake> earthquakes;

    public EarthquakeAdaptor(Context context, int resource, List<Earthquake> earthquakes) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.earthquakes = earthquakes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource, parent, false);
        TextView tvEqid = (TextView) view.findViewById(R.id.idEqid);
        TextView tvTime = (TextView) view.findViewById(R.id.idDateAndTime);
        TextView tvDepth = (TextView) view.findViewById(R.id.idDepth);
        TextView tvLat = (TextView) view.findViewById(R.id.idLat);
        TextView tvLong = (TextView) view.findViewById(R.id.idLong);
        TextView tvMag = (TextView) view.findViewById(R.id.idMagitude);

        Earthquake currentApp = earthquakes.get(position);

        tvEqid.setText("ID: "  + currentApp.getEqid());
        tvLat.setText( currentApp.getLatitude());
        tvTime.setText(currentApp.getDataTime());
        tvLong.setText( currentApp.getLongitude());
        tvMag.setText(currentApp.getMagitudeAsString());
        tvDepth.setText(currentApp.getDepth());

        double color = currentApp.getMagitude();
        if(color <= 4){
            tvMag.setTextColor(Color.GREEN);
        }
        else if(color > 4 && color <=6){
            tvMag.setTextColor(Color.YELLOW);
        }
        else if(color > 6 && color <=8) {
            tvMag.setTextColor(Color.rgb(255,201,102));
        }else{
            tvMag.setTextColor(Color.RED);
        }



        return view;
    }

    @Override
    public int getCount() {
        return earthquakes.size();
    }
}
