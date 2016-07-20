package com.epicodus.progresstracker.ui;

import android.content.Intent;
import org.parceler.Parcels;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.epicodus.progresstracker.R;
import com.epicodus.progresstracker.TaskDetailActivity;
import com.epicodus.progresstracker.model.Constants;
import com.epicodus.progresstracker.model.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.newTaskButton) Button mNewTaskButton;
    @Bind(R.id.gridview) GridView mGridView;

    private DatabaseReference mFirebaseRef;
    private ArrayList<String> mTaskNameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNewTaskButton.setOnClickListener(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mFirebaseRef = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_TASK).child(uid);
        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot goalSnapshot : dataSnapshot.getChildren()) {
                    mTaskNameList.add(goalSnapshot.getValue(Task.class).getName());
                }
                for (String name : mTaskNameList) {
                    Log.d("TaskName:", name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent.putExtra("task", Parcels.wrap(mTaskNameList.get(position)));
                startActivity(intent);
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mTaskNameList);
        mGridView.setAdapter(adapter);
    }

    @Override
    public void onClick (View v) {
        if (v == mNewTaskButton) {
            Intent intent  = new Intent(MainActivity.this, NewTaskActivity.class);
            startActivity(intent);
        }
        //int itemPosition = getLayoutPosition();
    }
}
