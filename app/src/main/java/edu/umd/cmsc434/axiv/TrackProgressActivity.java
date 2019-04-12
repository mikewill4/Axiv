package edu.umd.cmsc434.axiv;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class TrackProgressActivity extends AppCompatActivity  {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_progress);

        // Get display width
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // Configure radar chart
        chart = findViewById(R.id.progress_line_chart);
        //chart.setOnChartGestureListener(TrackProgressActivity.this);
        //chart.setOnChartValueSelectedListener(TrackProgressActivity.this);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);
        chart.setMinimumHeight(size.x);
        chart.setMinimumWidth(size.x);

        ArrayList<Entry> yValues = new ArrayList<>();

        for (int i = 0; i <= 100; i += 10) {
            yValues.add(new Entry(0, (float)(i * Math.random())));
        }

        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");

        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(16);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        chart.setData(data);
    }
}
