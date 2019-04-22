package edu.umd.cmsc434.axiv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class AppData {

    static SimpleDateFormat standardDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    static SimpleDateFormat standardDateFormatNoTime = new SimpleDateFormat("MM/dd/yyyy");


    static int userScore = 100;
    static User appUser;

    static ArrayList<FeaturedCompetitionInfo> userFeaturedCompetitions = new ArrayList<FeaturedCompetitionInfo>();
    static ArrayList<PrivateCompetitionInfo> userPrivateCompetitions = new ArrayList<PrivateCompetitionInfo>();
    static ArrayList<InvitesInfo> userCompetitionInvites = new ArrayList<InvitesInfo>();
    static ArrayList<Metric> userMetricHistory = new ArrayList<Metric>();
    static ArrayList<RewardInfo> recentlyAddedRewards = new ArrayList<>();
    static ArrayList<RewardInfo> trendingRewards = new ArrayList<>();
    static ArrayList<RewardInfo> foodRewards = new ArrayList<>();
    static ArrayList<RewardInfo> clothingRewards = new ArrayList<>();
    static ArrayList<RewardInfo> accessoriesRewards = new ArrayList<>();

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
        appUser = new User("You", userScore, userMetrics);

        // CREATE PRIVATE COMPETITIONS
        ArrayList<User> participantListOne = new ArrayList<User>();
        participantListOne.add(appUser);
        participantListOne.add(new User("Sophia Holloway", 100, null));
        participantListOne.add(new User("Albert May", 47, null));
        participantListOne.add(new User("Cedric West", 140, null));
        participantListOne.add(new User("Chelsea Sims", 500, null));

        ArrayList<User> participantListTwo = new ArrayList<User>();
        participantListTwo.add(appUser);
        participantListTwo.add(new User("Tommy Myers", 100, null));
        participantListTwo.add(new User("Isaac Tran", 47, null));
        participantListTwo.add(new User("Diane Mitchell", 140, null));

        ArrayList<User> participantListThree = new ArrayList<>();
        participantListThree.add(appUser);
        participantListThree.add(new User("Wanda Jones", 390, null));
        participantListThree.add(new User("Beth Gill", 168, null));
        participantListThree.add(new User("Henry Hogan", 282, null));
        participantListThree.add(new User("Josh Clayton", 411, null));
        participantListThree.add(new User("Johnny Foster", 124, null));
        participantListThree.add(new User("Jackie Perkins", 96, null));

        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Workplace Step Challenge","Engineering Team",participantListOne.size(),participantListOne));
        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Friend Workouts","Friends",participantListTwo.size(),participantListTwo));
        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Nutrition Activity","KNES240",participantListThree.size(),participantListThree));


        // CREATE FEATURED COMPETITIONS
        userFeaturedCompetitions.add(new AppData.FeaturedCompetitionInfo("sweetgreen","30% off any salad","steps",55));
        userFeaturedCompetitions.add(new AppData.FeaturedCompetitionInfo("Amazon","$5 gift card","nutrition",25));
        userFeaturedCompetitions.add(new AppData.FeaturedCompetitionInfo("Patagonia","10% off","heart rate",100));

        // CREATE INVITES
        userCompetitionInvites.add(new AppData.InvitesInfo("Fitness Challenge","UMD Club Soccer"));
        userCompetitionInvites.add(new AppData.InvitesInfo("Workout Buddies","Friends"));

        // CREATE REWARDS
        recentlyAddedRewards.add(new AppData.RewardInfo("Adidas", "Buy one get one free shorts", 250));
        recentlyAddedRewards.add(new AppData.RewardInfo("NuVegan Cafe", "$3 off", 80));
        recentlyAddedRewards.add(new AppData.RewardInfo("Apple", "$5 off iPhone chargers", 100));

        trendingRewards.add(new AppData.RewardInfo("Under Armour", "20% off shirts", 120));
        trendingRewards.add(new AppData.RewardInfo("Amazon", "15% off electronics", 150));

        foodRewards.add(new AppData.RewardInfo("sweetgreen", "10% off next meal", 60));
        foodRewards.add(new AppData.RewardInfo("NuVegan Cafe", "$3 off", 80));

        clothingRewards.add(new AppData.RewardInfo("Nike", "10% off apparel", 90));
        clothingRewards.add(new AppData.RewardInfo("Under Armour", "20% off shirts", 120));
        clothingRewards.add(new AppData.RewardInfo("Adidas", "Buy one get one free shorts", 250));

        accessoriesRewards.add(new AppData.RewardInfo("Apple", "$5 off iPhone chargers", 100));
        accessoriesRewards.add(new AppData.RewardInfo("Amazon", "15% off electronics", 150));
        accessoriesRewards.add(new AppData.RewardInfo("Citizen", "$10 off watches over $100", 170));
        accessoriesRewards.add(new AppData.RewardInfo("Chaps", "Buy one tie get one 50% off", 110));
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

    static class RewardInfo {
        public String sponsorName;
        public String deal;
        public int pointValue;

        public RewardInfo(String sponsorName, String deal, int pointValue) {
            this.sponsorName = sponsorName;
            this.deal = deal;
            this.pointValue = pointValue;
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
