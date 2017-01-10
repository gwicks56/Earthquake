package gwicks.com.ebayproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ListView listEarthquakes;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listEarthquakes = (ListView) findViewById(R.id.json_list_view);
        DownloadData downloadData = new DownloadData();
        downloadData.execute("http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman");
        Log.d(TAG, "onCreate: Count is: " + listEarthquakes.getCount());



        listEarthquakes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long iD) {
                //EarthquakeAdaptor adaptor = (EarthquakeAdaptor)parent.getAdapter();
                //Earthquake earthquake = (Earthquake) adaptor.getItem(position);
                int count = listEarthquakes.getCount();
                //String string = (String)parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "hello posiion is: " + position, Toast.LENGTH_LONG).show();
                //String item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this, listEarthquakes.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "Count is: " + count, Toast.LENGTH_LONG);
            }
        });



    }


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
            //mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
            return null;
        }

        @Override
        protected void onPostExecute(String strings) {
            Log.d(TAG, "onPostExecute: Paramater = " + strings);
            ParseEarthquakeData parseEarthquakeData = new ParseEarthquakeData();
            parseEarthquakeData.onDownloadComplete(strings);

//            ArrayAdapter<Earthquake> arrayAdapter = new ArrayAdapter<Earthquake>(MainActivity.this, R.layout.list_item, parseEarthquakeData.getEarthquakes());
//            listEarthquakes.setAdapter(arrayAdapter);

            EarthquakeAdaptor earthquakeAdaptor = new EarthquakeAdaptor(MainActivity.this, R.layout.list_quake, parseEarthquakeData.getEarthquakes());
            listEarthquakes.setAdapter(earthquakeAdaptor);
            Log.d(TAG, "onPostExecute: Count is: " + listEarthquakes.getCount());








        }
    }



}
