package edu.umd.cmsc434.axiv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class AppData {

    static SimpleDateFormat standardDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    static SimpleDateFormat standardDateFormatNoTime = new SimpleDateFormat("MM/dd/yyyy");


    static int userScore = 100;
    static User appUser;

    static ArrayList<FeaturedCompetitionInfo> userFeaturedCompetitions = new ArrayList<FeaturedCompetitionInfo>();
    static ArrayList<PrivateCompetitionInfo> userPrivateCompetitions = new ArrayList<PrivateCompetitionInfo>();
    static ArrayList<InvitesInfo> userCompetitionInvites = new ArrayList<InvitesInfo>();
    static ArrayList<Metric> userMetricHistory = new ArrayList<Metric>();





    public AppData(){

        // CREATE CURRENT USER
        Map<String, Float> userMetrics = new LinkedHashMap<>();
        userMetrics.put("Heart rate", 5.0f);
        userMetrics.put("Hydration", 5.0f);
        userMetrics.put("Steps", 1.0f);
        userMetrics.put("Calories", 3.0f);
        userMetrics.put("Blood pressure", 2.0f);
        userMetrics.put("Exercise", 8.0f);
        userMetrics.put("Sleep", 9.0f);
        userMetrics.put("Weight", 4.0f);
        appUser = new User("My Username", userScore, userMetrics);

        //CREATE PRIVATE COMPETITIONS
        ArrayList<User> participantListOne = new ArrayList<User>();
        participantListOne.add(appUser);
        participantListOne.add(new User("John Doe1", 100, null));
        participantListOne.add(new User("John Doe2", 47, null));
        participantListOne.add(new User("John Doe3", 140, null));
        participantListOne.add(new User("John Doe4", 500, null));

        ArrayList<User> participantListTwo = new ArrayList<User>();
        participantListTwo.add(appUser);
        participantListTwo.add(new User("Jane Deer1", 100, null));
        participantListTwo.add(new User("Jane Deer2", 47, null));
        participantListTwo.add(new User("Jane Deer3", 140, null));

        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Workplace Step Challenge","UMD CS Department",participantListOne.size(),participantListOne));
        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Friend Workouts","Dylan's Friends",participantListTwo.size(),participantListTwo));
        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Nutrition Activity","KNES240",participantListTwo.size(),participantListTwo));


        //CREATE FEATURED COMPETITIONS
        userFeaturedCompetitions.add(new AppData.FeaturedCompetitionInfo("Nandos Peri Peri","50% off Chicken","steps",55));
        userFeaturedCompetitions.add(new AppData.FeaturedCompetitionInfo("Maryland Dairy","2 Free Scoop","nutrition",25));
        userFeaturedCompetitions.add(new AppData.FeaturedCompetitionInfo("Patagonia","Free Water Bottle","heart rate",100));

        //CREATE INVITES

        userCompetitionInvites.add(new AppData.InvitesInfo("Competition Invite #1","Too Lazy to Think of text"));
        userCompetitionInvites.add(new AppData.InvitesInfo("Competition Invite #2","Also Text"));
    }

    static class User implements Comparable<User> {

        public String userName;
        public double score;
        public Map<String, Float> metrics;
        public int points;

        public User(String userName, double score, Map<String, Float> metrics){
            this.userName = userName;
            this.score = score;
            this.metrics = metrics;
            this.points = 0;
        }

        public void updateScore(double scoreDelta) {
            score += scoreDelta;
        }

        public void updateMetricScore(String metric, float scoreDelta) {
            if (scoreDelta > 2.0) scoreDelta = 2.0f;
            if (scoreDelta < -2.0) scoreDelta = -2.0f;
            if (scoreDelta >= 0 && metrics.get(metric) + scoreDelta <= 10)
                metrics.put(metric, metrics.get(metric) + scoreDelta);
            else if (scoreDelta < 0 && metrics.get(metric) + scoreDelta >= 0)
                metrics.put(metric, metrics.get(metric) + scoreDelta);
        }

        public void updatePoints(int pointsDelta) {
            points += pointsDelta;
        }

        public int compareTo(User other){
            if(this.score<other.score){
                return -1;
            }else if(this.score > other.score){
                return 1;
            } else return 0;
        }



    }


    static class FeaturedCompetitionInfo {
        public String sponsorName;
        public String deal;
        public String compType;
        public int progressPercentage;

        public FeaturedCompetitionInfo(String sponsorName, String deal, String compType, int progressPercentage) {
            this.sponsorName = sponsorName;
            this.deal = deal;
            this.compType = compType;
            this.progressPercentage = progressPercentage;
        }
    }


    static class PrivateCompetitionInfo{
        public String competitionName;
        public String orgName;
        public int numMembers;
        public ArrayList<User> participants;

        public PrivateCompetitionInfo(String competitionName, String orgName, int numMembers,ArrayList<User> participants){
            this.competitionName = competitionName;
            this.orgName = orgName;
            this.numMembers = numMembers;
            this.participants = participants;
        }

        public void addParticipant(User newParticipant){
            participants.add(newParticipant);
        }




    }

    static class InvitesInfo{
        public String competitionName;
        public String orgName;

        public InvitesInfo(String competitionName, String orgName){
            this.competitionName = competitionName;
            this.orgName = orgName;
        }


    }



}
