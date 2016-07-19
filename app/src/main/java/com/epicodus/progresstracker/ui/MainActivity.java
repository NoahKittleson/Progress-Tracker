package com.epicodus.progresstracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.progresstracker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.newTaskButton) Button mNewTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNewTaskButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        if (v == mNewTaskButton) {
            Intent intent  = new Intent(MainActivity.this, NewTaskActivity.class);
            startActivity(intent);
        }
    }
}
