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

    int countofImageurl=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlist);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);

        FirebaseStorage storage=FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        genderSt=getIntent().getExtras().getString("gender");
        myRef = database.getReference("data").child(genderSt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent gotologinscreen=new Intent(showlist.this,Showuser.class);
                gotologinscreen.putExtra("gender",genderSt);
                gotologinscreen.putExtra("id",movieList.get(position).getRating());
                startActivity(gotologinscreen);
            }
        });
// Read from the database


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                movieList.clear();
                adapter.notifyDataSetChanged();


                for(final DataSnapshot snapshot:dataSnapshot.getChildren())
                {

                    try {

                        jsonObject=new JSONObject(snapshot.getValue().toString());

                        storageRef.child(jsonObject.getString("contactnumberSt")).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String keyString=uri.getLastPathSegment();
                                    try {
                                        Movie   movie=new Movie();
                                    individualObject=new JSONObject(dataSnapshot.child(keyString).getValue().toString());
                                    //Log.e("String-",individualObject.toString());
                                    movie.setTitle(individualObject.getString("firstnameSt")+" "+individualObject.getString("middlenameSt")+" "+individualObject.getString("lastnameSt"));
                                    Log.e("Title-",individualObject.getString("firstnameSt")+" "+individualObject.getString("middlenameSt")+" "+individualObject.getString("lastnameSt"));
                                    movie.setGenre(individualObject.getString("occupation3St"));
                                    Log.e("Occupation-",individualObject.getString("occupation3St"));
                                    movie.setRating(individualObject.getString("contactnumberSt"));
                                    Log.e("Rating-",individualObject.getString("contactnumberSt"));
                                    movie.setYear(individualObject.getString("contactnumberSt"));
                                    Log.e("uniqueid-",individualObject.getString("usernameSt"));
                                    movie.setThumbnailUrl(uri.toString());
                                    Log.e("Url-",uri.toString());
                                    movieList.add(movie);

                                    Log.e("Verify",movieList.get(0).getTitle());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                adapter.notifyDataSetChanged();
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }




            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
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



















