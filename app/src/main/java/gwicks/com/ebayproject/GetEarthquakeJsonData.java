package gwicks.com.ebayproject;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwicks on 9/01/2017.
 */

class GetEarthquakeJsonData extends AsyncTask<String, Void, List<Earthquake>> implements GetRawData.OnDownloadComplete  {

    private static final String TAG = "GetEarthquakeJsonData";
    private String Url = "http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman";

    private List<Earthquake> earthquakeList = null;
    private String baseURL;

    private final OnDataAvailable mCallBack;

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete: starts Status: " + status);
        if(status == DownloadStatus.OK){
            earthquakeList = new ArrayList<>();

            try{
                JSONObject jsonData = new JSONObject(data);
                JSONArray earthquakeArray = jsonData.getJSONArray("earthquakes");

                for(int i = 0; i<earthquakeArray.length(); i++){
                    JSONObject jsonEarthquake = earthquakeArray.getJSONObject(i);
                    String date = jsonEarthquake.getString("datetime");
                    double depth = jsonEarthquake.getDouble("depth");
                    double longitude = jsonEarthquake.getDouble("lng");
                    double latitude = jsonEarthquake.getDouble("lat");
                    String eqid = jsonEarthquake.getString("eqid");
                    double magnitude = jsonEarthquake.getDouble("magnitude");

                    Earthquake earthquakeObject = new Earthquake(date, depth, longitude, latitude,eqid, magnitude);
                    earthquakeList.add(earthquakeObject);

                    Log.d(TAG, "onDownloadComplete: " + earthquakeObject.toString());
                }
            }catch(JSONException e){
                e.printStackTrace();
                Log.e(TAG, "onDownloadComplete: error processing json data: " + e.getMessage() );
                status = DownloadStatus.FAILED_OR_EMPTY;
            }
        }

        if(mCallBack != null){
            //inform caller proccessing is done

            mCallBack.onDataAvailable(earthquakeList, status);


        }

        Log.d(TAG, "onDownloadComplete: ends");
    }

    interface OnDataAvailable {
        void onDataAvailable(List<Earthquake> data, DownloadStatus status);
    }

    public GetEarthquakeJsonData(String baseURL, OnDataAvailable mCallBack) {
        Log.d(TAG, "GetEarthquakeJsonData: Called");
        this.baseURL = baseURL;
        this.mCallBack = mCallBack;
    }

    void executeOnSameThread(){
        Log.d(TAG, "executeOnSameThread: starting");
        String destinatioUri = Url;
        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(destinatioUri);
    }

    @Override
    protected List<Earthquake> doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(List<Earthquake> earthquakes) {
        super.onPostExecute(earthquakes);
    }
}
