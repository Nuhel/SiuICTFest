package com.siu.siuictfest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference myRef;

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));




        myRef = GetDataBaseReference.GetDataBaseInstance().getReference("ACM");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //DataSnapshot ds  = (DataSnapshot) dataSnapshot.child("m1").getValue();


                Toast.makeText(getBaseContext(),dataSnapshot.child("team").toString(),Toast.LENGTH_SHORT).show();

               /* for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Toast.makeText(getBaseContext(),ds.getKey(),Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
