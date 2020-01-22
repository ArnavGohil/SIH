package com.example.sih1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class Home extends AppCompatActivity {
    TextView txt;
    ProgressBar progressBar;
    static String URL ;
    public static HttpURLConnection urlConnection = null;
    public static InputStream inputStream = null;
    public static String jsonResponse = null;
    public static ArrayList<Card> cards = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        txt = findViewById(R.id.ff);
        progressBar = findViewById(R.id.pb);
        String appName = "test@4";
        URL = "https://thicchaindb.lunchb0ne.me/api/v1/assets?search=" + appName ;

        AsyncTask<Void, Void, Void> execute = new Utlis().execute();

    }

    class Utlis extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            java.net.URL url = null;
            try
            {
                url = new URL(Home.URL);
                Log.e("JSON", "URL WALA" + url);
            }
            catch (MalformedURLException e) { }

            try
            {

                try
                {

                    urlConnection = (HttpsURLConnection) url.openConnection();


                    // If the request was successful (response code 200),
                    // then read the input stream and parse the response.
                    if (urlConnection.getResponseCode() == 200)
                    {
                        inputStream = urlConnection.getInputStream();
                        StringBuilder output = new StringBuilder();
                        if (inputStream != null)
                        {
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                            BufferedReader reader = new BufferedReader(inputStreamReader);
                            String line = reader.readLine();
                            while (line != null)
                            {
                                output.append(line);
                                line = reader.readLine();
                            }
                        }
                        jsonResponse = output.toString();
                        Log.e("JSON", "JSON WALA" + jsonResponse);

                    }
                }
                catch (IOException e) {
                }
                finally
                {
                    if (urlConnection != null)
                    {
                        urlConnection.disconnect();
                    }
                    if (inputStream != null)
                    {
                        inputStream.close();
                    }
                }
            }
            catch (IOException e)
            {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.e("Final", "onPostExecute: " + jsonResponse );
            try {
                JSONArray ini = new JSONArray(jsonResponse);
                for (int i = 0; i < ini.length() ; i++)
                {
                    JSONObject obj = ini.getJSONObject(i);
                    JSONObject data = obj.getJSONObject("data");
                    String desc = data.getString("description");
                    int type = data.getInt("type");
                    long dt = data.getLong("time");
                    Date date = new Date(dt * 1000);
                    String DATE_FORMAT_9 = "h:mm a dd MMMM yyyy" ;
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_9);
                    String dte = dateFormat.format(date);

                    cards.add(new Card(desc , type , dte ));

                }

                CardAdapter flavorAdapter = new CardAdapter(Home.this, cards);

                // Get a reference to the ListView, and attach the adapter to the listView.
                ListView listView = (ListView) findViewById(R.id.list_item);
                listView.setAdapter(flavorAdapter);



            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
