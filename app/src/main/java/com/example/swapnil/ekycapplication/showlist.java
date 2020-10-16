package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class showlist extends AppCompatActivity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url

    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();

    private List<String> imageurls = new ArrayList<String>();
    private ListView listView;
    private CustomListAdapter adapter;
    JSONObject individualObject;
    TextView response;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    JSONObject jsonObject;
    String genderSt,imageurl,firstName;
    StorageReference storageRef;
    String getgroomurl="greenleafpureveg.in/utsavapplication/getgroom.php",getbrideurl="greenleafpureveg.in/utsavapplication/getbride.php";
    String urlrequest;
    int countofImageurl=0;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlist);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);



        genderSt=getIntent().getExtras().getString("gender");

        if(genderSt.equals("M")){
            urlrequest=getgroomurl;
        }else{
            urlrequest=getbrideurl;
        }

      stringRequest=new StringRequest(Request.Method.GET, urlrequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent gotologinscreen=new Intent(showlist.this,Showuser.class);
                startActivity(gotologinscreen);
            }
        });*/


            /*try {
                Movie movie = new Movie();
                individualObject = new JSONObject("");

                movie.setTitle(individualObject.getString("firstnameSt") + " " + individualObject.getString("middlenameSt") + " " + individualObject.getString("lastnameSt"));
                Log.e("Title-", individualObject.getString("firstnameSt") + " " + individualObject.getString("middlenameSt") + " " + individualObject.getString("lastnameSt"));
                movie.setGenre(individualObject.getString("occupation3St"));
                Log.e("Occupation-", individualObject.getString("occupation3St"));
                movie.setRating(individualObject.getString("contactnumberSt"));
                Log.e("Rating-", individualObject.getString("contactnumberSt"));
                movie.setYear(individualObject.getString("contactnumberSt"));
                Log.e("uniqueid-", individualObject.getString("usernameSt"));
                movie.setThumbnailUrl(uri.toString());
                Log.e("Url-", uri.toString());
                movieList.add(movie);

                Log.e("Verify", movieList.get(0).getTitle());

            } catch (JSONException e) {
                e.printStackTrace();
                adapter.notifyDataSetChanged();
            };*/





    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}



















