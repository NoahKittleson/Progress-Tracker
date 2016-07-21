package com.epicodus.progresstracker;

import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.charts.SeriesLabel;
import com.hookedonplay.decoviewlib.events.DecoEvent;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        DecoView arcView = (DecoView) findViewById(R.id.chartView);

        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(127, 64, 196, 0))
                .setRange(0, 100, 0)
                .setInset(new PointF(100f, 200f))
                .setLineWidth(32f)
//                .setSeriesLabel(new SeriesLabel.Builder("Percent %.0f%%")
//                        .setColorBack(Color.argb(218, 0, 0, 0))
//                        .setColorText(Color.argb(255, 255, 255, 255))
//                        .build())
                .build();
        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.argb(127, 196, 64, 0))
                .setRange(0, 100, 0)
                .setInset(new PointF(0f, 300f))
                .setLineWidth(32f)
                .build();
        SeriesItem seriesItem3 = new SeriesItem.Builder(Color.argb(127, 32, 64, 90))
                .setRange(0, 100, 0)
                .setInset(new PointF(200f, 100f))
                .setLineWidth(32f)
                .build();
        SeriesItem seriesItem4 = new SeriesItem.Builder(Color.argb(127, 196, 64, 200))
                .setRange(0, 100, 0)
                .setInset(new PointF(300f, 0f))
                .setLineWidth(32f)
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

        arcView.addEvent(new DecoEvent.Builder(-100).setIndex(series1Index).setDelay(3000).build());
        arcView.addEvent(new DecoEvent.Builder(100).setIndex(series2Index).setDelay(2000).build());
        arcView.addEvent(new DecoEvent.Builder(-100).setIndex(series3Index).setDelay(2333).build());
        arcView.addEvent(new DecoEvent.Builder(100).setIndex(series4Index).setDelay(2666).build());
        arcView.addEvent(new DecoEvent.Builder(100).setIndex(series5Index).setDelay(3000).build());
        arcView.addEvent(new DecoEvent.Builder(-100).setIndex(series6Index).setDelay(2000).build());
        arcView.addEvent(new DecoEvent.Builder(100).setIndex(series7Index).setDelay(2333).build());
        arcView.addEvent(new DecoEvent.Builder(-100).setIndex(series8Index).setDelay(2666).build());

//        arcView.addEvent(new DecoEvent.Builder(10).setIndex(series1Index).setDelay(12000).build());

    }
}
