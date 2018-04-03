package com.google.firebase.quickstart.auth;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.quickstart.auth.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView listHeader = new TextView(this);
        listHeader.setText("Observations");
        listHeader.setTextAppearance(this, android.R.style.TextAppearance_Large);
        ListView listView = findViewById(R.id.main_observation_listview);
        listView.addHeaderView(listHeader);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id==R.id.action_settings){
            Toast.makeText(getApplicationContext(), "You selected Settings", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ReadTask task = new ReadTask();
        task.execute("http://birdobservationservice.azurewebsites.net/service1.svc/observations");
    }

    public void addObservations(View view) {
        Intent intent = new Intent(this, ObservationsAdd.class);
        startActivity(intent);
    }

    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence jsonString) {
            TextView messageTextView = findViewById(R.id.main_message_textview);
            Log.d("mine", jsonString.toString());


            Gson gson = new GsonBuilder().create();

            final Observation[] observations = gson.fromJson(jsonString.toString(), Observation[].class);



            ListView listView = findViewById(R.id.main_observation_listview);

            //ArrayAdapter<Book> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, books);
            ObservationListItemAdapter adapter = new ObservationListItemAdapter(getBaseContext(), R.layout.observationlist_item, observations);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), ObservationActivity.class);
                    //Book book = books.get((int) id);
                    Observation observation = observations[(int) id];
                    intent.putExtra("OBSERVATION", observation);
                    startActivity(intent);
                }
            });
           /* } catch (JSONException ex) {
                messageTextView.setText(ex.getMessage());
                Log.e("BOOKS", ex.getMessage());
            }*/
        }
        @Override
        protected void onCancelled(CharSequence message) {
            TextView messageTextView = findViewById(R.id.main_message_textview);
            messageTextView.setText(message);
            Log.e("OBSERVATIONS", message.toString());



        }
    }


}
