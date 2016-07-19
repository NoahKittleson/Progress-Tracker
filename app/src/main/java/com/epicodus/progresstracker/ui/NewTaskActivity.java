package com.epicodus.progresstracker.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.progresstracker.R;
import com.epicodus.progresstracker.model.Constants;
import com.epicodus.progresstracker.model.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewTaskActivity extends AppCompatActivity implements View.OnClickListener {
    private Task mTask;

    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.goalEditText) EditText mGoalEditText;
    @Bind(R.id.descriptionEditText) EditText mDescriptionEditText;
    @Bind(R.id.createTaskButton) Button mCreateTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ButterKnife.bind(this);
        mCreateTaskButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateTaskButton) {
            String name = mNameEditText.getText().toString().trim();
            int goal = Integer.parseInt(mGoalEditText.getText().toString());
            String description = mDescriptionEditText.getText().toString().trim();
            Task newTask = new Task(name, description, goal);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference taskRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TASK)
                    .child(uid);

            DatabaseReference pushRef = taskRef.push();
            String pushId = pushRef.getKey();
            newTask.setPushId(pushId);
            pushRef.setValue(newTask);
        }
    }


}
