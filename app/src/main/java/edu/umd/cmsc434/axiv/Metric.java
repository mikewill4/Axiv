package edu.umd.cmsc434.axiv;

import java.util.Date;

public interface Metric{

    public MetricType getMetricType();
    public Date getEventOccuranceDate();

    static class ExerciseMetric implements Metric{

        public Date eventOccurance;
        public String exerciseType;
        public int caloriesBurned;
        public int durationMinutes;


        public ExerciseMetric(Date eventOccurance, String exerciseType, int caloriesBurned, int durationMinutes){
            this.eventOccurance = eventOccurance;
            this.exerciseType = exerciseType;
            this.caloriesBurned = caloriesBurned;
            this.durationMinutes = durationMinutes;
        }

        @Override
        public MetricType getMetricType() {
            return MetricType.EXERCISE;
        }

        public Date getEventOccuranceDate(){
            return eventOccurance;
        }

        public String toString(){
            return "Type: " + getMetricType() + " - " + AppData.standardDateFormat.format(eventOccurance) + " - " + exerciseType + " - " + caloriesBurned + " - " + durationMinutes;
        }
    }

    static class WeightMetric implements Metric{

        public Date eventOccurance;
        public double updatedWeightLbs;


        public WeightMetric(Date eventOccurance, double updatedWeightLbs){
            this.eventOccurance = eventOccurance;
            this.updatedWeightLbs = updatedWeightLbs;
        }

        @Override
        public MetricType getMetricType() {
            return MetricType.WEIGHT;
        }

        public Date getEventOccuranceDate(){
            return eventOccurance;
        }

        public String toString(){
            return "Type: " + getMetricType() + " - " + AppData.standardDateFormat.format(eventOccurance) + " - " + updatedWeightLbs;
        }

    }

    static class SleepMetric implements Metric{

        public Date eventOccurance;
        public Date sleepDurationStart;
        public Date sleepDurationEnd;


        public SleepMetric(Date eventOccurance,Date sleepDurationStart,Date sleepDurationEnd){
            this.eventOccurance = eventOccurance;
            this.sleepDurationEnd = sleepDurationEnd;
            this.sleepDurationStart = sleepDurationStart;
        }

        @Override
        public MetricType getMetricType() {
            return MetricType.SLEEP;
        }

        public Date getEventOccuranceDate(){
            return eventOccurance;
        }

        public String toString(){
            return "Type: " + getMetricType() + " - " + AppData.standardDateFormat.format(eventOccurance) + " - " +
                    AppData.standardDateFormat.format(sleepDurationStart) + " - " + AppData.standardDateFormat.format(sleepDurationEnd);
        }
    }

    static class StepsMetric implements Metric{

        public Date eventOccurance;
        public int numSteps;


        public StepsMetric(Date eventOccurance,int numSteps){
            this.eventOccurance = eventOccurance;
            this.numSteps = numSteps;
        }

        @Override
        public MetricType getMetricType() {
            return MetricType.STEPS;
        }

        public Date getEventOccuranceDate(){
            return eventOccurance;
        }

        public String toString(){
            return "Type: " + getMetricType() + " - " + AppData.standardDateFormat.format(eventOccurance) + " - " + numSteps;
        }
    }

    static class HydrationMetric implements Metric{

        public Date eventOccurance;
        public int waterIntakeML;


        public HydrationMetric(Date eventOccurance,int waterIntakeML){
            this.eventOccurance = eventOccurance;
            this.waterIntakeML = waterIntakeML;
        }

        @Override
        public MetricType getMetricType() {
            return MetricType.HYDRATION;
        }

        public Date getEventOccuranceDate(){
            return eventOccurance;
        }

        public String toString(){
            return "Type: " + getMetricType() + " - " + AppData.standardDateFormat.format(eventOccurance) + " - " + waterIntakeML;
        }




    }

    static class MealMetric implements Metric{

        public Date eventOccurance;
        public String foodType;
        public int servings;
        public int numCaloriesPerServing;
        public int totalCalories;

        public MealMetric(Date eventOccurance,String foodType, int servings, int numCaloriesPerServing){
            this.eventOccurance = eventOccurance;
            this.foodType = foodType;
            this.servings = servings;
            this.numCaloriesPerServing = numCaloriesPerServing;
            totalCalories = servings * numCaloriesPerServing;
        }

        @Override
        public MetricType getMetricType() {
            return MetricType.MEAL;
        }

        public Date getEventOccuranceDate(){
            return eventOccurance;
        }

        public String toString(){
            return "Type: " + getMetricType() + " - " + AppData.standardDateFormat.format(eventOccurance) + " - " + foodType + " " + servings + " " + numCaloriesPerServing + " " +totalCalories;

        }




    }

    static class HeartRateMetric implements Metric{

        public Date eventOccurance;
        public double heartRate;


        public HeartRateMetric(Date eventOccurance, double heartRate){
            this.eventOccurance = eventOccurance;
            this.heartRate = heartRate;
        }

        @Override
        public MetricType getMetricType() {
            return MetricType.HEART_RATE;
        }

        public Date getEventOccuranceDate(){
            return eventOccurance;
        }

        public String toString(){
            return "Type: " + getMetricType() + " - " + AppData.standardDateFormat.format(eventOccurance) + " - " + heartRate;
        }

    }

    static class BloodPressureMetric implements Metric{

        public Date eventOccurance;
        public int systolicBP;
        public int diastolicBP;


        public BloodPressureMetric(Date eventOccurance, int systolicBP, int diastolicBP){
            this.eventOccurance = eventOccurance;
            this.systolicBP = systolicBP;
            this.diastolicBP = diastolicBP;
        }

        @Override
        public MetricType getMetricType() {
            return MetricType.BLOOD_PRESSURE;
        }

        public Date getEventOccuranceDate(){
            return eventOccurance;
        }

        public String toString(){
            return "Type: " + getMetricType() + " - " + AppData.standardDateFormat.format(eventOccurance) + " - " + systolicBP + " " + diastolicBP;
        }

    }






}



