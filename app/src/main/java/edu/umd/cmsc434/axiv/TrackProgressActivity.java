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

        LimitLine upperHealthyLimit = new LimitLine(60f, "Healthy");
        upperHealthyLimit.setLineWidth(4f);
        upperHealthyLimit.enableDashedLine(10f, 10f, 0f);
        upperHealthyLimit.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        upperHealthyLimit.setTextSize(16f);
        LimitLine lowerHealthyLimit = new LimitLine(40f, "Healthy");
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

        ArrayList<Entry> yValues = new ArrayList<>();

        for (int i = 0; i <= 50; i += 10) {
            yValues.add(new Entry(i / 10, (float)(100 * Math.random())));
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
