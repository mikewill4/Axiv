package edu.umd.cmsc434.axiv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AppData {

    static SimpleDateFormat standardDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    static SimpleDateFormat standardDateFormatNoTime = new SimpleDateFormat("MM/dd/yyyy");


    static int userScore = 100;
    static User appUser = new User("My Username", userScore);

    static ArrayList<FeaturedCompetitionInfo> userFeaturedCompetitions = new ArrayList<FeaturedCompetitionInfo>();
    static ArrayList<PrivateCompetitionInfo> userPrivateCompetitions = new ArrayList<PrivateCompetitionInfo>();
    static ArrayList<InvitesInfo> userCompetitionInvites = new ArrayList<InvitesInfo>();
    static ArrayList<Metric> userMetricHistory = new ArrayList<Metric>();





    public AppData(){

        //CREATE PRIVATE COMPETITIONS
        ArrayList<User> participantListOne = new ArrayList<User>();
        participantListOne.add(appUser);
        participantListOne.add(new User("John Doe1", 100));
        participantListOne.add(new User("John Doe2", 47));
        participantListOne.add(new User("John Doe3", 140));
        participantListOne.add(new User("John Doe4", 500));

        ArrayList<User> participantListTwo = new ArrayList<User>();
        participantListTwo.add(appUser);
        participantListTwo.add(new User("Jane Deer1", 100));
        participantListTwo.add(new User("Jane Deer2", 47));
        participantListTwo.add(new User("Jane Deer3", 140));

        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Workplace Step Challenge","UMD CS Department",participantListOne.size(),participantListOne));
        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Friend Workouts","Dylan's Friends",participantListTwo.size(),participantListTwo));
        userPrivateCompetitions.add(new AppData.PrivateCompetitionInfo("Nutrition Activity","KNES240",participantListTwo.size(),participantListTwo));


        //CREATE FEATURED COMPETITIONS
        userFeaturedCompetitions.add(new AppData.FeaturedCompetitionInfo("Nandos Peri Peri","50% off Chicken","steps",55));
        userFeaturedCompetitions.add(new AppData.FeaturedCompetitionInfo("Maryland Dairy","2 Free Scoop","nutrition",25));

        //CREATE INVITES

        userCompetitionInvites.add(new AppData.InvitesInfo("Competition Invite #1","Too Lazy to Think of text"));
        userCompetitionInvites.add(new AppData.InvitesInfo("Competition Invite #2","Also Text"));
    }

    static class User implements Comparable<User> {

        public String userName;
        public double score;

        public User(String userName, double score){
            this.userName = userName;
            this.score = score;
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
