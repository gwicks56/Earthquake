package gwicks.com.ebayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetEarthquakeJsonData.OnDataAvailable{

    private ListView listEarthquakes;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listEarthquakes = (ListView) findViewById(R.id.JsonListView);
//
//        GetRawData getRawData = new GetRawData(this);
//        getRawData.execute("http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: starts");
        super.onResume();
        GetEarthquakeJsonData getEarthquakeJsonData = new GetEarthquakeJsonData("http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman", this);
        getEarthquakeJsonData.executeOnSameThread();
    }

    @Override
    public void onDataAvailable(List<Earthquake> data, DownloadStatus status){

        if(status == DownloadStatus.OK){
            Log.d(TAG, "onDataAvailable: data is :" + data);
        }else{
            Log.e(TAG, "onDataAvailable: failed with status: " + status );
        }
    }
}
