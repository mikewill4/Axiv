package edu.umd.cmsc434.axiv;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LegendEntry;
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
    private int currSelectedMetric;

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

        // Remove description
        final Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        chart.setClickable(false);

        // Disable legend
        chart.getLegend().setEnabled(false);

        // Heart rate data
        ArrayList<Entry> heartRateValues = new ArrayList<>();

        for (int i = 0; i <= 110; i += 10) {
            heartRateValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        final LineDataSet heartRateSet = new LineDataSet(heartRateValues, "Heart rate");

        heartRateSet.setFillAlpha(110);
        heartRateSet.setColor(Color.RED);
        heartRateSet.setLineWidth(3f);
        heartRateSet.setValueTextSize(16);
        heartRateSet.setCircleColor(Color.RED);
        heartRateSet.setCircleRadius(8f);
        heartRateSet.setCircleHoleRadius(4f);
        heartRateSet.setVisible(true);
        heartRateSet.setDrawValues(false);

        // Hydration
        ArrayList<Entry> hydrationValues = new ArrayList<>();

        for (int i = 0; i <= 110; i += 10) {
            hydrationValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        final LineDataSet hydrationSet = new LineDataSet(hydrationValues, "Hydration");

        hydrationSet.setFillAlpha(110);
        hydrationSet.setColor(Color.RED);
        hydrationSet.setLineWidth(3f);
        hydrationSet.setValueTextSize(16);
        hydrationSet.setCircleColor(Color.RED);
        hydrationSet.setCircleRadius(8f);
        hydrationSet.setCircleHoleRadius(4f);
        hydrationSet.setVisible(false);
        hydrationSet.setDrawValues(false);

        // Sleep
        ArrayList<Entry> sleepValues = new ArrayList<>();

        for (int i = 0; i <= 110; i += 10) {
            sleepValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        final LineDataSet sleepSet = new LineDataSet(sleepValues, "Sleep");

        sleepSet.setFillAlpha(110);
        sleepSet.setColor(Color.RED);
        sleepSet.setLineWidth(3f);
        sleepSet.setValueTextSize(16);
        sleepSet.setCircleColor(Color.RED);
        sleepSet.setCircleRadius(8f);
        sleepSet.setCircleHoleRadius(4f);
        sleepSet.setVisible(false);
        sleepSet.setDrawValues(false);

        // Calories
        ArrayList<Entry> caloriesValues = new ArrayList<>();

        for (int i = 0; i <= 110; i += 10) {
            caloriesValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        final LineDataSet caloriesSet = new LineDataSet(caloriesValues, "Calories");

        caloriesSet.setFillAlpha(110);
        caloriesSet.setColor(Color.RED);
        caloriesSet.setLineWidth(3f);
        caloriesSet.setValueTextSize(16);
        caloriesSet.setCircleColor(Color.RED);
        caloriesSet.setCircleRadius(8f);
        caloriesSet.setCircleHoleRadius(4f);
        caloriesSet.setVisible(false);
        caloriesSet.setDrawValues(false);

        // Exercise
        ArrayList<Entry> exerciseValues = new ArrayList<>();

        for (int i = 0; i <= 110; i += 10) {
            exerciseValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        final LineDataSet exerciseSet = new LineDataSet(exerciseValues, "Exercise");

        exerciseSet.setFillAlpha(110);
        exerciseSet.setColor(Color.RED);
        exerciseSet.setLineWidth(3f);
        exerciseSet.setValueTextSize(16);
        exerciseSet.setCircleColor(Color.RED);
        exerciseSet.setCircleRadius(8f);
        exerciseSet.setCircleHoleRadius(4f);
        exerciseSet.setVisible(false);
        exerciseSet.setDrawValues(false);

        // Blood pressure
        ArrayList<Entry> bloodPressureValues = new ArrayList<>();

        for (int i = 0; i <= 110; i += 10) {
            bloodPressureValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        final LineDataSet bloodPressureSet = new LineDataSet(bloodPressureValues, "Blood Pressure");

        bloodPressureSet.setFillAlpha(110);
        bloodPressureSet.setColor(Color.RED);
        bloodPressureSet.setLineWidth(3f);
        bloodPressureSet.setValueTextSize(16);
        bloodPressureSet.setCircleColor(Color.RED);
        bloodPressureSet.setCircleRadius(8f);
        bloodPressureSet.setCircleHoleRadius(4f);
        bloodPressureSet.setVisible(false);
        bloodPressureSet.setDrawValues(false);

        // Weight
        ArrayList<Entry> weightValues = new ArrayList<>();

        for (int i = 0; i <= 110; i += 10) {
            weightValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        final LineDataSet weightSet = new LineDataSet(weightValues, "Weight");

        weightSet.setFillAlpha(110);
        weightSet.setColor(Color.RED);
        weightSet.setLineWidth(3f);
        weightSet.setValueTextSize(16);
        weightSet.setCircleColor(Color.RED);
        weightSet.setCircleRadius(8f);
        weightSet.setCircleHoleRadius(4f);
        weightSet.setVisible(false);
        weightSet.setDrawValues(false);

        // Steps
        ArrayList<Entry> stepsValues = new ArrayList<>();

        for (int i = 0; i <= 110; i += 10) {
            stepsValues.add(new Entry(i / 10, (float)(100 * Math.random())));
        }

        final LineDataSet stepsSet = new LineDataSet(stepsValues, "Steps");

        stepsSet.setFillAlpha(110);
        stepsSet.setColor(Color.RED);
        stepsSet.setLineWidth(3f);
        stepsSet.setValueTextSize(16);
        stepsSet.setCircleColor(Color.RED);
        stepsSet.setCircleRadius(8f);
        stepsSet.setCircleHoleRadius(4f);
        stepsSet.setVisible(false);
        stepsSet.setDrawValues(false);

        // Set data
        final ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(heartRateSet);
        dataSets.add(hydrationSet);
        dataSets.add(sleepSet);
        dataSets.add(caloriesSet);
        dataSets.add(exerciseSet);
        dataSets.add(bloodPressureSet);
        dataSets.add(weightSet);
        dataSets.add(stepsSet);
        final LineData data = new LineData(dataSets);
        chart.setData(data);

        // Set date correctly
        // Current month in the middle
        String[] months = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new XAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // Spinner for toggling line graph
        Spinner metricSpinner = (Spinner) findViewById(R.id.track_progress_metric_spinner);

        currSelectedMetric = 0;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.metrics ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        metricSpinner.setAdapter(adapter);
        metricSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long l) {
                switch (currSelectedMetric) {
                    case 0:
                        heartRateSet.setVisible(false);
                        dataSets.set(currSelectedMetric, heartRateSet);
                        break;
                    case 1:
                        hydrationSet.setVisible(false);
                        dataSets.set(currSelectedMetric, hydrationSet);
                        break;
                    case 2:
                        sleepSet.setVisible(false);
                        dataSets.set(currSelectedMetric, sleepSet);
                        break;
                    case 3:
                        caloriesSet.setVisible(false);
                        dataSets.set(currSelectedMetric, caloriesSet);
                        break;
                    case 4:
                        exerciseSet.setVisible(false);
                        dataSets.set(currSelectedMetric, exerciseSet);
                        break;
                    case 5:
                        bloodPressureSet.setVisible(false);
                        dataSets.set(currSelectedMetric, bloodPressureSet);
                        break;
                    case 6:
                        weightSet.setVisible(false);
                        dataSets.set(currSelectedMetric, weightSet);
                        break;
                    case 7:
                        stepsSet.setVisible(false);
                        dataSets.set(currSelectedMetric, stepsSet);
                        break;
                    default:
                        break;
                }

                switch (position) {
                    case 0:
                        heartRateSet.setVisible(true);
                        dataSets.set(currSelectedMetric, heartRateSet);
                        currSelectedMetric = 0;
                        break;
                    case 1:
                        hydrationSet.setVisible(true);
                        dataSets.set(currSelectedMetric, hydrationSet);
                        currSelectedMetric = 1;
                        break;
                    case 2:
                        sleepSet.setVisible(true);
                        dataSets.set(currSelectedMetric, sleepSet);
                        currSelectedMetric = 2;
                        break;
                    case 3:
                        caloriesSet.setVisible(true);
                        dataSets.set(currSelectedMetric, caloriesSet);
                        currSelectedMetric = 3;
                        break;
                    case 4:
                        exerciseSet.setVisible(true);
                        dataSets.set(currSelectedMetric, exerciseSet);
                        currSelectedMetric = 4;
                        break;
                    case 5:
                        bloodPressureSet.setVisible(true);
                        dataSets.set(currSelectedMetric, bloodPressureSet);
                        currSelectedMetric = 5;
                        break;
                    case 6:
                        weightSet.setVisible(true);
                        dataSets.set(currSelectedMetric, weightSet);
                        currSelectedMetric = 6;
                        break;
                    case 7:
                        stepsSet.setVisible(true);
                        dataSets.set(currSelectedMetric, stepsSet);
                        currSelectedMetric = 7;
                        break;
                    default:
                        break;
                }
                LineData tmpData = new LineData(dataSets);
                chart.setData(tmpData);
                chart.notifyDataSetChanged();
                chart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
