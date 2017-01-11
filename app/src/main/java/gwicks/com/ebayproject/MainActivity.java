package gwicks.com.ebayproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gwicks on 9/01/2017.
 */

public class MainActivity extends AppCompatActivity {

    private ListView listEarthquakes;
    private static final String TAG = "MainActivity";
    public static final String EARTHQUAKE_URL = "http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman"; // Given URL to parse


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listEarthquakes = (ListView) findViewById(R.id.json_list_view);

        // Start the AsyncTask to download the raw JSON from URL
        DownloadData downloadData = new DownloadData();
        downloadData.execute(EARTHQUAKE_URL);
        Log.d(TAG, "onCreate: Count is: " + listEarthquakes.getCount());


        // OnClickListener for the ListView
        // Clicking on an item in the ListView will load Google Maps, and show the epicenter of the earthquake
        listEarthquakes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long iD) {

                EarthquakeAdaptor adaptor = (EarthquakeAdaptor)parent.getAdapter();
                Earthquake earthquake = adaptor.getItem(position);

                Intent mapIntent = new Intent(getApplicationContext(), MapActivity.class);
                double latitude = earthquake.getLatAsDouble();
                double longitude = earthquake.getLongAsDouble();
                double magitude = earthquake.getMagnitude();
                String eqid = earthquake.getEqid();

                // add extra data to the intent, lat and long are used for position, id and mag are currently unused, but could be useful if marker is made clickable
                mapIntent.putExtra("lat", latitude);
                mapIntent.putExtra("long", longitude);
                mapIntent.putExtra("id", eqid );
                mapIntent.putExtra("mag", magitude);

                startActivity(mapIntent);

            }
        });

    }

    // AsyncTask to download the JSON data from the internet off the UI Thread

    private class DownloadData extends AsyncTask<String, Void, String>{
        private static final String TAG = "DownloadData";

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            if (strings == null) {
                return null;
            }

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int response = connection.getResponseCode();
                Log.d(TAG, "doInBackground: The response code was: " + response);
                StringBuilder result = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    result.append(line).append("\n");
                }

                return result.toString();


            } catch (MalformedURLException e) {
                Log.e(TAG, "doInBackground: Invalid URL:" + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: IO Exception reading data " + e.getMessage());
            } catch (SecurityException e) {
                Log.e(TAG, "doInBackground: Security exception: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "doInBackground: Error closing stream " + e.getMessage());
                    }
                }
            }

            return null;
        }

        // After download parse the JSON data and send it to the custom array adaptor
        // NOTE: JSON parsing is done on the UI thread, for large datasets, this should be moved to another AsyncTask

        @Override
        protected void onPostExecute(String strings) {
            Log.d(TAG, "onPostExecute: Paramater = " + strings);
            ParseEarthquakeData parseEarthquakeData = new ParseEarthquakeData();
            parseEarthquakeData.onDownloadComplete(strings);
            EarthquakeAdaptor earthquakeAdaptor = new EarthquakeAdaptor(MainActivity.this, R.layout.list_quake, parseEarthquakeData.getEarthquakes());
            listEarthquakes.setAdapter(earthquakeAdaptor);
            Log.d(TAG, "onPostExecute: Count is: " + listEarthquakes.getCount());


        }
    }

}
