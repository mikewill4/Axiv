package edu.umd.cmsc434.axiv;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MetricsFragment extends Fragment {



    public MetricsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View metricView = inflater.inflate(R.layout.fragment_metrics, container, false);

        Button trackMealsButton = (Button) metricView.findViewById(R.id.track_meals);
        Button trackSleepButton = (Button) metricView.findViewById(R.id.track_sleep);
        Button trackHydrationButton = (Button) metricView.findViewById(R.id.track_hydration);
        Button trackStepsButton = (Button) metricView.findViewById(R.id.track_steps);
        Button trackExerciseButton = (Button) metricView.findViewById(R.id.track_exercise);
        Button trackWeightButton = (Button) metricView.findViewById(R.id.track_weight);
        Button trackHRButton = (Button) metricView.findViewById(R.id.track_heart_rate);
        Button trackBPButton = (Button) metricView.findViewById(R.id.track_blood_pressure);
        Button metricsHistory = (Button) metricView.findViewById(R.id.metrics_history_button);

        trackMealsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),MealSelectionActivity.class));
            }
        });

        trackSleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),TrackSleepActivity.class));
            }
        });
        trackHydrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),TrackHydrationActivity.class));
            }
        });
        trackExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),TrackExerciseActivity.class));
            }
        });

        trackWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),TrackWeightActivity.class));
            }
        });

        trackHRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),TrackHRActivity.class));
            }
        });

        trackBPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),TrackBPActivity.class));
            }
        });


        trackStepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),TrackStepsActivity.class));
            }
        });

        metricsHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MetricHistoryActivity.class));
            }
        });

        return metricView;
    }

}
