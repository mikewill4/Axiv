package edu.umd.cmsc434.axiv;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
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

        LimitLine upperHealthyLimit = new LimitLine(60f, "Healthy upper bound");
        upperHealthyLimit.setLineWidth(4f);
        upperHealthyLimit.enableDashedLine(10f, 10f, 0f);
        upperHealthyLimit.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        upperHealthyLimit.setTextSize(16f);
        LimitLine lowerHealthyLimit = new LimitLine(40f, "Healthy lower bound");
        lowerHealthyLimit.setLineWidth(4f);
        lowerHealthyLimit.enableDashedLine(10f, 10f, 0f);
        lowerHealthyLimit.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        lowerHealthyLimit.setTextSize(16f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upperHealthyLimit);
        leftAxis.addLimitLine(lowerHealthyLimit);

        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        //leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawLimitLinesBehindData(true);

        chart.getAxisRight().setEnabled(false);

        // Heart rate data
        ArrayList<Entry> heartRateValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            heartRateValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        LineDataSet heartRateSet = new LineDataSet(heartRateValues, "Heart rate");

        heartRateSet.setFillAlpha(110);
        heartRateSet.setColor(Color.RED);
        heartRateSet.setLineWidth(3f);
        heartRateSet.setValueTextSize(16);

        // Hydration
        ArrayList<Entry> hydrationValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            hydrationValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        LineDataSet hydrationSet = new LineDataSet(hydrationValues, "Hydration");

        hydrationSet.setFillAlpha(110);
        hydrationSet.setColor(Color.BLUE);
        hydrationSet.setLineWidth(3f);
        hydrationSet.setValueTextSize(16);

        // Sleep
        ArrayList<Entry> sleepValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            sleepValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        LineDataSet sleepSet = new LineDataSet(sleepValues, "Sleep");

        sleepSet.setFillAlpha(110);
        sleepSet.setColor(Color.GREEN);
        sleepSet.setLineWidth(3f);
        sleepSet.setValueTextSize(16);

        // Calories
        ArrayList<Entry> caloriesValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            caloriesValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        LineDataSet caloriesSet = new LineDataSet(caloriesValues, "Calories");

        caloriesSet.setFillAlpha(110);
        caloriesSet.setColor(Color.MAGENTA);
        caloriesSet.setLineWidth(3f);
        caloriesSet.setValueTextSize(16);

        // Exercise
        ArrayList<Entry> exerciseValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            exerciseValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        LineDataSet exerciseSet = new LineDataSet(exerciseValues, "Exercise");

        exerciseSet.setFillAlpha(110);
        exerciseSet.setColor(Color.CYAN);
        exerciseSet.setLineWidth(3f);
        exerciseSet.setValueTextSize(16);

        // Blood pressure
        ArrayList<Entry> bloodPressureValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            bloodPressureValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        LineDataSet bloodPressureSet = new LineDataSet(bloodPressureValues, "Blood Pressure");

        bloodPressureSet.setFillAlpha(110);
        bloodPressureSet.setColor(Color.YELLOW);
        bloodPressureSet.setLineWidth(3f);
        bloodPressureSet.setValueTextSize(16);

        // Weight
        ArrayList<Entry> weightValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            weightValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        LineDataSet weightSet = new LineDataSet(weightValues, "Weight");

        weightSet.setFillAlpha(110);
        weightSet.setColor(Color.BLACK);
        weightSet.setLineWidth(3f);
        weightSet.setValueTextSize(16);

        // Steps
        ArrayList<Entry> stepsValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            stepsValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        LineDataSet stepsSet = new LineDataSet(stepsValues, "Steps");

        stepsSet.setFillAlpha(110);
        stepsSet.setColor(Color.DKGRAY);
        stepsSet.setLineWidth(3f);
        stepsSet.setValueTextSize(16);

        // Set data
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(heartRateSet);
        dataSets.add(hydrationSet);
        dataSets.add(sleepSet);
        dataSets.add(caloriesSet);
        dataSets.add(exerciseSet);
        dataSets.add(bloodPressureSet);
        dataSets.add(weightSet);
        dataSets.add(stepsSet);
        LineData data = new LineData(dataSets);
        chart.setData(data);

        String[] values = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"};

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new XAxisValueFormatter(values));
        //xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

    }

    public class XAxisValueFormatter implements IAxisValueFormatter {
        private String[] values;
        public XAxisValueFormatter(String[] values) {
            this.values = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return values[(int)value];
        }
    }
}
