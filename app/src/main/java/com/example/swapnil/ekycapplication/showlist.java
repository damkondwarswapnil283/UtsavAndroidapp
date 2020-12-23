package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class showlist extends AppCompatActivity {
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url

    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    JSONArray jsonArray;
    JSONObject jsonObject;
    private List<String> imageurls = new ArrayList<String>();
    private ListView listView;
    private CustomListAdapter adapter;
    JSONObject individualObject;
    TextView response;
    EditText searching;


    String genderSt,imageurl,firstName;

    String getgroomurl="http://greenleafpureveg.in/utsavapplication/getgroom.php",
            getbrideurl="http://greenleafpureveg.in/utsavapplication/getbride.php",
            getbysearchurl="http://greenleafpureveg.in/utsavapplication/getsearch.php";
    ;
    String urlrequest;

    StringRequest stringRequest,searchstringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlist);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        searching=(EditText)findViewById(R.id.searching);
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
                Log.e("Response",response);

                try {

                    movieList.clear();
                    jsonArray =new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        try {
                            jsonObject=jsonArray.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        individualObject= new JSONObject(jsonObject.getString("jsondata"));

                        try {
                            Movie movie = new Movie();

                            movie.setTitle(individualObject.getString("firstname")+" "+individualObject.getString("middlename")+" "+individualObject.getString("lastname"));
                            //Log.e("Title-", individualObject.getString("firstnameSt") + " " + individualObject.getString("middlenameSt") + " " + individualObject.getString("lastnameSt"));
                            movie.setGenre(individualObject.getString("occupatio"));
                            // Log.e("Occupation-", jsonObject.getString("occupation3St"));
                            movie.setRating(individualObject.getString("aboutme"));
                            // Log.e("Rating-", individualObject.getString("gender"));
                            movie.setYear(individualObject.getString("dateofbirth"));
                            // Log.e("uniqueid-", individualObject.getString("usernameSt"));
                            movie.setThumbnailUrl(jsonObject.getString("image"));
                            //Log.e("Url-", uri.toString());
                            movieList.add(movie);
                            adapter.notifyDataSetChanged();
                            Log.e("Verify", movieList.get(0).getTitle());

                        } catch (JSONException e) {
                            e.printStackTrace();
                            adapter.notifyDataSetChanged();
                        };
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    movieList.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.toString());
                movieList.clear();
                adapter.notifyDataSetChanged();
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showuser=new Intent(showlist.this,Showuser.class);
                String ID = null;
                try {
                   ID=jsonArray.getJSONObject(position).getString("id");
                    Toast.makeText(showlist.this, ID, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showuser.putExtra("id",ID);
                startActivity(showuser);
            }
        });

      searchstringRequest=new StringRequest(Request.Method.POST, getbysearchurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response",response);

                try {

                    jsonArray =new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        try {
                            jsonObject=jsonArray.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       individualObject= new JSONObject(jsonObject.getString("jsondata"));

                        try {
                        Movie movie = new Movie();

                        movieList.clear();

                        movie.setTitle(individualObject.getString("firstname")+" "+individualObject.getString("middlename")+" "+individualObject.getString("lastname"));
                        //Log.e("Title-", individualObject.getString("firstnameSt") + " " + individualObject.getString("middlenameSt") + " " + individualObject.getString("lastnameSt"));
                        movie.setGenre(individualObject.getString("occupatio"));
                        // Log.e("Occupation-", jsonObject.getString("occupation3St"));
                        movie.setRating(individualObject.getString("aboutme"));
                        // Log.e("Rating-", individualObject.getString("gender"));
                        movie.setYear(individualObject.getString("dateofbirth"));
                        // Log.e("uniqueid-", individualObject.getString("usernameSt"));
                        movie.setThumbnailUrl(jsonObject.getString("image"));
                        //Log.e("Url-", uri.toString());
                        movieList.add(movie);
                        adapter.notifyDataSetChanged();
                        Log.e("Verify", movieList.get(0).getTitle());

                    } catch (JSONException e) {
                        e.printStackTrace();
                        adapter.notifyDataSetChanged();
                    };
                }

            } catch (JSONException e) {
                    e.printStackTrace();
                    movieList.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.toString());
                movieList.clear();
                adapter.notifyDataSetChanged();
            }
        }){

          @Override
          protected Map<String, String> getParams() {
              Map<String, String> params = new HashMap<String, String>();
              params.put("forsearching", searching.getText().toString());
              params.put("type",genderSt);
              return params;
          }

      };

      searching.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              //AppController.getInstance().addToRequestQueue(searchstringRequest);
          }

          @Override
          public void afterTextChanged(Editable s) {

              if(searching.getText().toString().equals("")){
                  stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                          20000,
                          0,
                          DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                  AppController.getInstance().addToRequestQueue(stringRequest);
              }else{
                  searchstringRequest.setRetryPolicy(new DefaultRetryPolicy(
                          20000,
                          0,
                          DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                  AppController.getInstance().addToRequestQueue(searchstringRequest);
              }
              Toast.makeText(showlist.this, searching.getText().toString(), Toast.LENGTH_SHORT).show();
          }
      });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(stringRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent showuser=new Intent(showlist.this,Showuser.class);
                String ID="";
                try {
                    ID=jsonArray.getJSONObject(position).getString("id");
                   Toast.makeText(showlist.this, ID, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showuser.putExtra("id",ID);
                startActivity(showuser);
            }
        });








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



















