package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
    private ListView listView;
    private CustomListAdapter adapter;
    String imageurl;
    TextView response;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("data").child("male");
    JSONObject jsonObject;
    StorageReference storageRef;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlist);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);
        response=(TextView)findViewById(R.id.responsetxt) ;

        FirebaseStorage storage=FirebaseStorage.getInstance();
         storageRef = storage.getReference();



        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");

// Read from the database


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    response.setText(snapshot.getValue().toString());
                    try {
                      jsonObject=new JSONObject(snapshot.getValue().toString());
                        //Toast.makeText(showlist.this, jsonObject.getString("usernameSt"), Toast.LENGTH_SHORT).show();
                        movie = new Movie();
                        movie.setTitle(jsonObject.getString("firstnameSt")+" "+jsonObject.getString("middlenameSt")+" "+jsonObject.getString("lastnameSt"));

                        storageRef.child("splashimg.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override

                            public void onSuccess(Uri uri) {
                            // Got the download URL for 'users/me/profile.png'
                                imageurl= "https://firebasestorage.googleapis.com/v0/b/utsav-matrimonial-application.appspot.com/o/splashimg.png?alt=media&token=3138452a-b357-42ec-bde1-5ff9aa7d5110";

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(showlist.this, exception.toString(), Toast.LENGTH_SHORT).show();                        }
                        });


                        movie.setThumbnailUrl(imageurl);

                        movie.setRating(1);
                        movie.setYear(jsonObject.getInt("contactnumberSt"));


                        movie.setGenre(null);

                        // adding movie to movies array
                        movieList.add(movie);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                String value = dataSnapshot.getValue().toString();
                Log.d(TAG, "Value is: " + value);


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



















