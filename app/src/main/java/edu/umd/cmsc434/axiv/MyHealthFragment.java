package edu.umd.cmsc434.axiv;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.Map;

import edu.umd.cmsc434.axiv.AppData.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyHealthFragment extends Fragment {

    // Min and max values for Radar Chart
    public static final float MAX = 10f, MIN = 0f;

    // Number of metrics measured in Radar Chart
    public static final int NUM_METRICS = 8;

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

        // Set user score button
        Button userScoreButton = (Button) rootView.findViewById(R.id.your_score_button);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        userScoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                builder.setTitle("Your overall health score");
                builder.setMessage("Your baseline overall health score is computed by comparing each metric to a healthy level for your personal body type. From there any healthy activity will increase your score and any unhealthy activity will decrease your score. There is no maximum score. You can always improve.");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });


        // Set user score
        TextView userScore = (TextView) rootView.findViewById(R.id.user_score);
        userScore.setText(String.valueOf(AppData.appUser.score));

        // Get display width
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // Configure radar chart
        chart = rootView.findViewById(R.id.health_radar_chart);
        chart.setBackgroundColor(Color.WHITE);
        chart.getDescription().setEnabled(false);
        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.GRAY);
        chart.setWebColorInner(Color.GRAY);
        chart.setWebAlpha(100);
        chart.setRotationEnabled(false);
        chart.setMinimumHeight(size.x);
        chart.setMinimumWidth(size.x);
        setData();

        // Animate the chart
        chart.animateXY(1400, 1400);

        // Define x axis
        XAxis x = chart.getXAxis();
        x.setTextSize(16f);
        x.setYOffset(0);
        x.setXOffset(0);
        x.setValueFormatter(new IAxisValueFormatter() {

            private String[] metrics = new String[] {"Heart rate", "Hydration", "Steps", "Calories", "Blood pressure", "Exercise", "Sleep", "Weight" };

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return metrics[(int) value % metrics.length];
            }
        });

        x.setTextColor(Color.DKGRAY);

        // Define y axis
        YAxis y = chart.getYAxis();
        y.setLabelCount(NUM_METRICS, false);
        y.setTextSize(16f);
        y.setAxisMinimum(MIN);
        y.setAxisMaximum(MAX);
        y.setDrawLabels(false);

        // Configure legend. Removed for now since it is only the user's data being displayed
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
//        legend.setTextSize(15f);
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        legend.setDrawInside(false);
//        legend.setXEntrySpace(7f);
//        legend.setYEntrySpace(5f);
//        legend.setTextColor(Color.WHITE);

        // Health suggestions button
        Button healthSuggestions = (Button) rootView.findViewById(R.id.health_suggestions_button);
        healthSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HealthSuggestionsActivity.class));
            }
        });

        // Daily targets button
        Button dailyTargets = (Button) rootView.findViewById(R.id.daily_targets_button);
        dailyTargets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DailyTargetsActivity.class));
            }
        });

        // Track progress button
        Button trackProgress = (Button) rootView.findViewById(R.id.track_progress_button);
        trackProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TrackProgressActivity.class));
            }
        });

        // Reminders
        Button reminders = (Button) rootView.findViewById(R.id.reminders_button);
        reminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RemindersActivity.class));
            }
        });

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
            case R.id.refreshValues:
                setData();
                break;
//            case R.id.toggleValues:
//                for (IDataSet<?> set : chart.getData().getDataSets()) {
//                    set.setDrawValues(!set.isDrawValuesEnabled());
//                }
//                chart.invalidate();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setData() {
        // Generate random test data
        ArrayList<RadarEntry> user = new ArrayList<>();
//        for (int i = 0; i < NUM_METRICS; i++) {
//            float val = (int) (Math.random() * MAX) + MIN;
//            user.add(new RadarEntry(val));
//        }
        for (Map.Entry<String, Float> metricValue : AppData.appUser.metrics.entrySet()) {
            user.add(new RadarEntry(metricValue.getValue()));
        }

        // Create Radar dataset
        RadarDataSet dataset = new RadarDataSet(user, "You");
        dataset.setColor(Color.BLUE);
        dataset.setFillColor(Color.BLUE);
        dataset.setDrawFilled(true);
        dataset.setFillAlpha(180);
        dataset.setLineWidth(2f);
        dataset.setDrawHighlightIndicators(false);
        dataset.setDrawHighlightCircleEnabled(true);

        // Create radar data object with dataset
        ArrayList<IRadarDataSet> datasetList = new ArrayList<>();
        datasetList.add(dataset);
        RadarData data = new RadarData(datasetList);
        data.setValueTextSize(15f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.DKGRAY);

        chart.setData(data);
        chart.invalidate();

    }

}
