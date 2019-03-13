package edu.umd.cmsc434.axiv;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyHealthFragment extends Fragment {

    // Min and max values for Radar Chart
    public static final float MAX = 12f, MIN = 1f;

    // Number of metrics measured in Radar Chart
    public static final int NUM_METRICS = 5;

    private RadarChart chart;

    public MyHealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_health, container, false);

        // Configure radar chart
        chart = rootView.findViewById(R.id.health_radar_chart);
        chart.setBackgroundColor(Color.rgb(60, 65, 82));
        chart.getDescription().setEnabled(false);
        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.WHITE);
        chart.setWebColorInner(Color.WHITE);
        chart.setWebAlpha(100);
        setData();

        // Animate the chart
        chart.animateXY(1400, 1400);

        // Define x axis
        XAxis x = chart.getXAxis();
        x.setTextSize(9f);
        x.setYOffset(0);
        x.setXOffset(0);
        x.setValueFormatter(new IAxisValueFormatter() {

            private String[] metrics = new String[] {"Heart Rate", "Sleep", "Diet", "Steps", "Workouts"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return metrics[(int) value % metrics.length];
            }
        });

        x.setTextColor(Color.WHITE);

        // Define y axis
        YAxis y = chart.getYAxis();
        y.setLabelCount(NUM_METRICS, false);
        y.setTextSize(9f);
        y.setAxisMinimum(MIN);
        y.setAxisMaximum(MAX);
        y.setDrawLabels(false);

        // Configure legend
        Legend legend = chart.getLegend();
        legend.setTextSize(15f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(5f);
        legend.setTextColor(Color.WHITE);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.radar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.refreshChart:
                setData();
                chart.invalidate();
                break;
            case R.id.toggleValues:
                for (IDataSet<?> set : chart.getData().getDataSets()) {
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }
                chart.invalidate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setData() {
        // Generate random test data
        ArrayList<RadarEntry> user = new ArrayList<>();
        for (int i = 0; i < NUM_METRICS; i++) {
            float val = (int) (Math.random() * MAX) + MIN;
            user.add(new RadarEntry(val));
        }

        // Create Radar dataset
        RadarDataSet dataset = new RadarDataSet(user, "You");
        dataset.setColor(Color.RED);
        dataset.setFillColor(Color.RED);
        dataset.setDrawFilled(true);
        dataset.setFillAlpha(180);
        dataset.setLineWidth(2f);
        dataset.setDrawHighlightIndicators(false);
        dataset.setDrawHighlightCircleEnabled(true);

        // Create radar data object with dataset
        ArrayList<IRadarDataSet> datasetList = new ArrayList<>();
        datasetList.add(dataset);
        RadarData data = new RadarData(datasetList);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();

    }
}