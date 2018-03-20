package com.google.firebase.quickstart.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.firebase.quickstart.auth.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ObservationsAdd extends AppCompatActivity {
    private Observation observation;
    private EditText titleView, authorView, publisherView, priceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observations_add);
    }


    public void addObservation2(View view) {
        String ObservationIdString = ((EditText) findViewById(R.id.add_observation_id)).getText().toString();
        String BirdIdString = ((Spinner) findViewById(R.id.add_observation_birdId)).toString();
        String UserId = ((EditText) findViewById(R.id.add_Observation_UserId)).getText().toString();
        String LatitudeString = ((EditText) findViewById(R.id.add_observation_latitude)).getText().toString();
        String LongtitudeString = ((EditText) findViewById(R.id.add_observation_longtitude)).getText().toString();
        String Placename = ((EditText) findViewById(R.id.add_observation_placement)).getText().toString();
        String PopulationString = ((EditText) findViewById(R.id.add_observation_population)).getText().toString();
        String Comment = ((EditText) findViewById(R.id.add_observation_comment)).getText().toString();
        Integer ObservationId = Integer.parseInt(ObservationIdString);
        Integer BirdId = Integer.parseInt(BirdIdString);
        Double Latitude = Double.parseDouble(LatitudeString);
        Double Longtitude = Double.parseDouble(LongtitudeString);
        Integer Population = Integer.parseInt(PopulationString);


        TextView messageView = findViewById(R.id.add_observation_message_textview);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id", ObservationId);
            jsonObject.put("BirdId", BirdId);
            jsonObject.put("UserId", UserId);
            jsonObject.put("Latitude", Latitude);
            jsonObject.put("Longtitude", Longtitude);
            jsonObject.put("Placename", Placename);
            jsonObject.put("Population", Population);
            jsonObject.put("Comment", Comment);
            jsonObject.put("Latitude", Latitude);
            String jsonDocument = jsonObject.toString();
            messageView.setText(jsonDocument);
            PostObservationTask task = new PostObservationTask();
            task.execute("http://anbo-restserviceproviderbooks.azurewebsites.net/Service1.svc/books", jsonDocument);
        } catch (JSONException ex) {
            messageView.setText(ex.getMessage());
        }

    }
    private class PostObservationTask extends AsyncTask<String, Void, CharSequence> {
        //private final String JsonDocument;

        //PostBookTask(String JsonDocument) {
        //    this.JsonDocument = JsonDocument;
        //}

        @Override
        protected CharSequence doInBackground(String... params) {
            String urlString = params[0];
            String jsonDocument = params[1];
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                osw.write(jsonDocument);
                osw.flush();
                osw.close();
                int responseCode = connection.getResponseCode();
                if (responseCode / 100 != 2) {
                    String responseMessage = connection.getResponseMessage();
                    throw new IOException("HTTP response code: " + responseCode + " " + responseMessage);
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                return line;
            } catch (MalformedURLException ex) {
                cancel(true);
                String message = ex.getMessage() + " " + urlString;
                Log.e("OBSERVATION", message);
                return message;
            } catch (IOException ex) {
                cancel(true);
                Log.e("OBSERVATION", ex.getMessage());
                return ex.getMessage();
            }
        }
        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);
            TextView messageView = findViewById(R.id.add_observation_message_textview);
            messageView.setText(charSequence);
            Log.d("MINE", charSequence.toString());
            finish();
        }

        @Override
        protected void onCancelled(CharSequence charSequence) {
            super.onCancelled(charSequence);
            TextView messageView = findViewById(R.id.add_observation_message_textview);
            messageView.setText(charSequence);
            Log.d("MINE", charSequence.toString());
            finish();
        }
    }
}
