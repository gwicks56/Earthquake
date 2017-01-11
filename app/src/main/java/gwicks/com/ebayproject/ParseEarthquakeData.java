package gwicks.com.ebayproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gwicks on 9/01/2017.
 */

class ParseEarthquakeData {

    private static final String TAG = "ParseEarthquakeData";

    private ArrayList<Earthquake> earthquakeList;

    ParseEarthquakeData() {
        this.earthquakeList = new ArrayList<Earthquake>();
    }

    ArrayList<Earthquake> getEarthquakes() {
        return earthquakeList;
    }

    void onDownloadComplete(String data) {
        Log.d(TAG, "onDownloadComplete: starts Status: ");

        earthquakeList = new ArrayList<>();

        try {
            JSONObject jsonData = new JSONObject(data);
            JSONArray earthquakeArray = jsonData.getJSONArray("earthquakes");

            for (int i = 0; i < earthquakeArray.length(); i++) {
                JSONObject jsonEarthquake = earthquakeArray.getJSONObject(i);
                String date = jsonEarthquake.getString("datetime");
                double depth = jsonEarthquake.getDouble("depth");
                double longitude = jsonEarthquake.getDouble("lng");
                double latitude = jsonEarthquake.getDouble("lat");
                String eqid = jsonEarthquake.getString("eqid");
                double magnitude = jsonEarthquake.getDouble("magnitude");

                Earthquake earthquakeObject = new Earthquake(date, depth, longitude, latitude, eqid, magnitude);
                earthquakeList.add(earthquakeObject);

                Log.d(TAG, "onDownloadComplete: " + earthquakeObject.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "onDownloadComplete: error processing json data: " + e.getMessage());


//        if(mCallBack != null){
//            //inform caller proccessing is done
//
//            mCallBack.onDataAvailable(earthquakeList, status);
//

        }

        //Log.d(TAG, "onDownloadComplete: ends");
    }
}

