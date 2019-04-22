package edu.umd.cmsc434.axiv;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.umd.cmsc434.axiv.Metric.BloodPressureMetric;
import edu.umd.cmsc434.axiv.Metric.ExerciseMetric;
import edu.umd.cmsc434.axiv.Metric.HeartRateMetric;
import edu.umd.cmsc434.axiv.Metric.HydrationMetric;
import edu.umd.cmsc434.axiv.Metric.MealMetric;
import edu.umd.cmsc434.axiv.Metric.SleepMetric;
import edu.umd.cmsc434.axiv.Metric.StepsMetric;
import edu.umd.cmsc434.axiv.Metric.WeightMetric;

public class MetricHistoryActivity extends AppCompatActivity {

    ListView historyListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric_history);

        ArrayList<Metric> userHistory = AppData.userMetricHistory;



        MyMetricHistoryAdapter adapter = new MyMetricHistoryAdapter(this,userHistory);
        historyListView = (ListView) findViewById(R.id.metrics_history_listview);
        historyListView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent newIntent = new Intent("android.intent.action.HOMEPAGE");
        newIntent.putExtra("currId", 1);
        startActivity(newIntent);
    }
}



class MyMetricHistoryAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<Metric> userHistory;

    public MyMetricHistoryAdapter(Activity context, ArrayList<Metric> userHistory) {
        super(context, R.layout.listitem_metric_history);

        this.context = context;
        this.userHistory = userHistory;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return userHistory.size();
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.listitem_metric_history, null, true);

        TextView metricType = (TextView) rowView.findViewById(R.id.metric_history_listitem_metrictype);
        TextView eventDateTime = (TextView) rowView.findViewById(R.id.metric_history_listitem_eventoccurance);
        TextView itemData1 = (TextView) rowView.findViewById(R.id.metric_history_listitem_itemdata1);
        TextView itemData2 = (TextView) rowView.findViewById(R.id.metric_history_listitem_itemdata2);
        TextView itemData3 = (TextView) rowView.findViewById(R.id.metric_history_listitem_itemdata3);

        ImageButton deleteButton = (ImageButton) rowView.findViewById(R.id.delete_metric);

        metricType.setText(userHistory.get(position).getMetricType().toString().replace("_", " "));
        eventDateTime.setText(AppData.standardDateFormat.format(userHistory.get(position).getEventOccuranceDate()));

        Metric m = userHistory.get(position);

        if(m instanceof ExerciseMetric){
            itemData1.setText( ((ExerciseMetric)m).exerciseType);
            itemData2.setText("Cal Burned: " + ((ExerciseMetric)m).caloriesBurned);
            itemData3.setText("Score Delta: +" + ((ExerciseMetric)m).caloriesBurned/20);
        } else if(m instanceof WeightMetric){
            itemData1.setText("Weight: " + ((WeightMetric)m).updatedWeightLbs + " lbs");
            itemData2.setText("");
            itemData3.setText("Score Delta: +" + ((WeightMetric)m).updatedWeightLbs/20);
        } else if(m instanceof SleepMetric){
            long duration = ((SleepMetric)m).sleepDurationEnd.getTime() - ((SleepMetric)m).sleepDurationEnd.getTime();
            int hours= (int) Math.floor(duration/3600000);
            int minutes = (int) Math.floor((duration - hours*3600000) / 60000);
            itemData1.setText("Duration: " + hours + " h : " + minutes + "min");
            itemData2.setText("");
            itemData3.setText("Score Delta: +" + minutes/3);
        } else if(m instanceof StepsMetric){
            itemData1.setText("Steps: " + ((StepsMetric)m).numSteps);
            itemData2.setText("");
            itemData3.setText("Score Delta: +" + ((StepsMetric)m).numSteps/500);
        } else if(m instanceof HydrationMetric){
            itemData1.setText("Water Intake: " + ((HydrationMetric)m).waterIntakeML + "ml");
            itemData2.setText("");
            itemData3.setText("Score Delta: -" + ((HydrationMetric)m).waterIntakeML/200);
        } else if(m instanceof MealMetric){
            itemData1.setText(((MealMetric)m).foodType);
            itemData2.setText("Cal Intake: " + ((MealMetric)m).totalCalories);
            itemData3.setText("Score Delta: -" + ((MealMetric)m).totalCalories/100);
        } else if(m instanceof HeartRateMetric){
            itemData1.setText("Heart Rate: " + ((HeartRateMetric)m).heartRate + " bpm");
            itemData2.setText("");
            itemData3.setText("Score Delta: +" + ((HeartRateMetric)m).heartRate/20);
        } else if(m instanceof BloodPressureMetric){
            itemData1.setText("BP: " + ((BloodPressureMetric)m).systolicBP + "/" + ((BloodPressureMetric)m).diastolicBP +" mmHg");
            itemData2.setText("");
            itemData3.setText("Score Delta: -" + ((BloodPressureMetric)m).systolicBP/10);
        }

        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Deleting Metric");
                Metric tmp = userHistory.get(position);
                if(tmp instanceof ExerciseMetric){

                } else if(tmp instanceof WeightMetric){

                } else if(tmp instanceof SleepMetric){

                } else if(tmp instanceof StepsMetric){

                } else if(tmp instanceof HydrationMetric){

                } else if(tmp instanceof MealMetric){

                } else if(tmp instanceof HeartRateMetric){

                } else if(tmp instanceof BloodPressureMetric){

                }
                userHistory.remove(position);
                notifyDataSetChanged();
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        deleteButton.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle(userHistory.get(position).getMetricType().toString().replace("_"," "));
                builder.setMessage("Are you sure you want to delete this metric?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("Deleting Metric");
                        userHistory.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });


        return rowView;

    };
}