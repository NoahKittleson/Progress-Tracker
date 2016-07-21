package com.epicodus.progresstracker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.epicodus.progresstracker.model.Task;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.charts.SeriesLabel;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TaskDetailActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.chartView) DecoView mArcView;
    @Bind(R.id.currentGoalsTextView) TextView mNameView;
    @Bind(R.id.textView2) TextView mDescriptionView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;
    @Bind(R.id.button) Button mButton;
    SeriesItem seriesItem4;
    Task mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        //createCoolGraph();
        mTask = Parcels.unwrap(getIntent().getExtras().getParcelable("task"));
        mProgressBar.setProgress(mTask.getCurrent());
        mProgressBar.setMax(mTask.getGoal());
        mNameView.setText(mTask.getName());
        mDescriptionView.setText(mTask.getDescription() + "\n" + mTask.getCurrent() + "/" + mTask.getGoal());
        mButton.setOnClickListener(this);
    }


    private void createCoolGraph() {
        DecoView arcView = (DecoView) findViewById(R.id.chartView);
        //DecoView arcView2 = (DecoView) findViewById(R.id.chartView2);

        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(127, 64, 196, 0))
                .setRange(0, 100, 0)
                .setShowPointWhenEmpty(false)
                .setInset(new PointF(100f, 200f))
                .setLineWidth(32f)
                .setInitialVisibility(false)
                .setSpinDuration(9000)
//                .setSeriesLabel(new SeriesLabel.Builder("Percent %.0f%%")
//                        .setColorBack(Color.argb(218, 0, 0, 0))
//                        .setColorText(Color.argb(255, 255, 255, 255))
//                        .build())
                .build();
        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.argb(127, 196, 64, 0))
                .setRange(0, 100, 0)
                .setShowPointWhenEmpty(false)
                .setInset(new PointF(0f, 300f))
                .setLineWidth(32f)
                .setSpinDuration(10000)
                .setInitialVisibility(false)
                .build();
        SeriesItem seriesItem3 = new SeriesItem.Builder(Color.argb(127, 32, 64, 90))
                .setRange(0, 100, 0)
                .setInset(new PointF(200f, 100f))
                .setLineWidth(32f)
                .setSpinDuration(11000)
                .setInitialVisibility(false)
                .setShowPointWhenEmpty(false)
                .build();
        seriesItem4 = new SeriesItem.Builder(Color.argb(127, 196, 64, 200))
                .setRange(0, 100, 0)
                .setInset(new PointF(300f, 0f))
                .setLineWidth(32f)
                .setSpinDuration(12000)
                .setInitialVisibility(false)
                .setShowPointWhenEmpty(false)
                .build();


        int series1Index = arcView.addSeries(seriesItem1);
        int series2Index = arcView.addSeries(seriesItem2);
        int series3Index = arcView.addSeries(seriesItem3);
        int series4Index = arcView.addSeries(seriesItem4);
        int series5Index = arcView.addSeries(seriesItem1);
        int series6Index = arcView.addSeries(seriesItem2);
        int series7Index = arcView.addSeries(seriesItem3);
        int series8Index = arcView.addSeries(seriesItem4);


        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(1000)
                .setDuration(2000)
                .build());

        arcView.addEvent(new DecoEvent.Builder(-100).setIndex(series1Index).setDelay(3000).setColor(Color.parseColor("#FFEAEAEA")).build());
        arcView.addEvent(new DecoEvent.Builder(100).setIndex(series2Index).setDelay(2000).setColor(Color.parseColor("#FFEAEAEA")).build());
        arcView.addEvent(new DecoEvent.Builder(-100).setIndex(series3Index).setDelay(2333).setColor(Color.parseColor("#FFEAEAEA")).build());
        arcView.addEvent(new DecoEvent.Builder(100).setIndex(series4Index).setDelay(2666)
                .setColor(Color.parseColor("#FFEAEAEA"))
                .setListener(new DecoEvent.ExecuteEventListener() {
                    @Override
                    public void onEventStart(DecoEvent event) {
                        Log.d("TaskDetailActivity", "Hide of DecoView Starting");
                    }

                    @Override
                    public void onEventEnd(DecoEvent event) {
                        Log.d("TaskDetailActivity", 9/0 + "");
                    }
                }).build());
//        arcView2.addEvent(new DecoEvent.Builder(100).setIndex(series5Index).setDelay(3000).build());
//        arcView2.addEvent(new DecoEvent.Builder(-100).setIndex(series6Index).setDelay(2000).build());
//        arcView.addEvent(new DecoEvent.Builder(100).setIndex(series7Index).setDelay(2333).build());
//        arcView.addEvent(new DecoEvent.Builder(-100).setIndex(series8Index).setDelay(2666).build());

        arcView.configureAngles(360, 315);
        //arcView.configureAngles(360, 157);

        arcView.setVisibility(View.VISIBLE);

        final String format = "%.0f%%";

        seriesItem4.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                if (percentComplete > .93) {
                    int crash = 1/0;
                }
                if (format.contains("%%")) {
                    float percentFilled = ((currentPosition - seriesItem4.getMinValue()) / (seriesItem4.getMaxValue() - seriesItem4.getMinValue()));
                    mDescriptionView.setText(String.format(format, percentFilled * 100f));
                } else {
                    mDescriptionView.setText(String.format(format, currentPosition));
                }
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == mButton) {
            mTask.addCurrent(1);
            mProgressBar.setProgress(mTask.getCurrent());
            mDescriptionView.setText(mTask.getDescription() + "\n" + mTask.getCurrent() + "/" + mTask.getGoal());
        }
    }
}
