package com.example.sach.retrofit;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.sach.retrofit.model.Details;
import com.example.sach.retrofit.model.Stocks;
import com.example.sach.retrofit.network.api;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class MainActivity extends AppCompatActivity {

    api apiobj;
  Details detail;

    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> stock = new ArrayList<String>();

    LinearLayout linlaHeaderProgress,linlaHeaderProgressone,linlaHeaderProgresstwo;

ListView l,lo,lt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l=(ListView)findViewById(R.id.list);
        lo=(ListView)findViewById(R.id.listone);
        lt=(ListView)findViewById(R.id.listtwo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
        linlaHeaderProgressone = (LinearLayout) findViewById(R.id.linlaHeaderProgressone);

        linlaHeaderProgresstwo = (LinearLayout) findViewById(R.id.linlaHeaderProgresstwo);

        linlaHeaderProgress.setVisibility(View.VISIBLE);
        linlaHeaderProgressone.setVisibility(View.VISIBLE);
        linlaHeaderProgresstwo.setVisibility(View.VISIBLE);

        setone();
        settwo();
        setthree();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setone() {

        final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("http://www.location_of_file.com/").build();
        //Call function
        apiobj = restadapter.create(api.class);
        apiobj.getStations(new Callback<String[]>() {
            @Override
            public void success(String[] flowers, retrofit.client.Response response) {


                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, flowers);
           l.setAdapter(adapter);



                linlaHeaderProgress.setVisibility(View.GONE);

            }

            @Override
            public void failure(RetrofitError error) {
                linlaHeaderProgress.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), "Failed" + error, Toast.LENGTH_SHORT).show();
            }

        });


    }

    private void settwo() {

       final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("http://www.location_of_file.com/").build();
        //Call function
        apiobj = restadapter.create(api.class);
        apiobj.getname(new Callback<List<Details>>() {
            @Override
            public void success(List<Details> details, retrofit.client.Response response) {

                int s = details.size();
                for (int i = 0; i < s; i++) {
                    detail = details.get(i);
                    name.add(detail.getU_name());

                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, name);
                lo.setAdapter(adapter);

                linlaHeaderProgressone.setVisibility(View.GONE);

            }

            @Override
            public void failure(RetrofitError error) {
                linlaHeaderProgressone.setVisibility(View.GONE);

            }
        });

    }


    private void setthree() {

        final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint("http://www.location_of_file.com/").build();
         //Call function
        apiobj = restadapter.create(api.class);
        apiobj.getstock(new Callback<Details>() {
            @Override
            public void success(Details details, retrofit.client.Response response) {
                for (Stocks u : details.getData()) {
                    stock.add(u.getSymbol() + ":" + u.getLastPrice());
                    // etc
                }

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, stock);
                lt.setAdapter(adapter);
                linlaHeaderProgresstwo.setVisibility(View.GONE);


            }

            @Override
            public void failure(RetrofitError error) {
                linlaHeaderProgresstwo.setVisibility(View.GONE);

            }
        });



    }


}
