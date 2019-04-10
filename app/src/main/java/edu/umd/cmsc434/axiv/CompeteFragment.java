package edu.umd.cmsc434.axiv;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompeteFragment extends Fragment {

    private ListView featuredListView;
    private ListView privatelistView;
    private ListView invites;

    private ArrayList<FeaturedCompetitionInfo> featuredInfoList;
    private ArrayList<PrivateCompetitionInfo> privateInfoList;



    public CompeteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View competeView = inflater.inflate(R.layout.fragment_compete, container, false);

        featuredInfoList = new ArrayList<FeaturedCompetitionInfo>();
        featuredInfoList.add(new FeaturedCompetitionInfo("Nandos Peri Peri","50% off Chicken","steps",55));
        featuredInfoList.add(new FeaturedCompetitionInfo("Maryland Dairy","2 Free Scoop","nutrition",25));

        privateInfoList = new ArrayList<PrivateCompetitionInfo>();
        privateInfoList.add(new PrivateCompetitionInfo("Workplace Step Challenge","UMD CS Department",15));
        privateInfoList.add(new PrivateCompetitionInfo("Friend Workouts","Dylan's Friends",4));
        privateInfoList.add(new PrivateCompetitionInfo("Nutrition Activity","KNES240",120));

        MyPrivateListAdapter privateListAdapter = new MyPrivateListAdapter(this.getActivity(),privateInfoList);
        privatelistView = (ListView) competeView.findViewById(R.id.compete_private_listview);
        privatelistView.setAdapter(privateListAdapter);
        UIUtils.setListViewHeightBasedOnItems(privatelistView);

        MyFeaturedListAdapter featuredListAdapter = new MyFeaturedListAdapter(this.getActivity(),featuredInfoList);
        featuredListView = (ListView) competeView.findViewById(R.id.compete_featured_listview);
        featuredListView.setAdapter(featuredListAdapter);
        UIUtils.setListViewHeightBasedOnItems(featuredListView);


        privatelistView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity(),IndividualCompetition.class));
            }
        });



        // Inflate the layout for this fragment
        return competeView;
    }

}


class FeaturedCompetitionInfo{

    public String sponsorName;
    public String deal;
    public String compType;
    public int progressPercentage;

    public FeaturedCompetitionInfo(String sponsorName, String deal, String compType, int progressPercentage){
        this.sponsorName = sponsorName;
        this.deal = deal;
        this.compType = compType;
        this.progressPercentage = progressPercentage;
    }



}

class PrivateCompetitionInfo{

    public String competitionName;
    public String orgName;
    public int numMembers;

    public PrivateCompetitionInfo(String competitionName, String orgName, int numMembers){
        this.competitionName = competitionName;
        this.orgName = orgName;
        this.numMembers = numMembers;
    }



}

class MyFeaturedListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<FeaturedCompetitionInfo> featuredList;

    public MyFeaturedListAdapter(Activity context, ArrayList<FeaturedCompetitionInfo> featuredList) {
        super(context, R.layout.listitem_featured_competition);

        this.context = context;
        this.featuredList = featuredList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return featuredList.size();
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.listitem_featured_competition, null, true);

        TextView sponsor = (TextView) rowView.findViewById(R.id.featured_listitem_sponsor);
        TextView deal = (TextView) rowView.findViewById(R.id.featured_listitem_deal);
        ProgressBar progress = (ProgressBar) rowView.findViewById(R.id.featured_listitem_progressbar);
        TextView compType = (TextView) rowView.findViewById(R.id.featured_listitem_typeofcomp);

        sponsor.setText(featuredList.get(position).sponsorName);
        deal.setText(featuredList.get(position).deal);
        progress.setProgress(featuredList.get(position).progressPercentage);
        compType.setText(featuredList.get(position).compType);

        return rowView;

    };
}

class MyPrivateListAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private final ArrayList<PrivateCompetitionInfo> privateList;

        public MyPrivateListAdapter(Activity context, ArrayList<PrivateCompetitionInfo> privateList) {
            super(context, R.layout.listitem_private_competition);

            this.context=context;
            this.privateList = privateList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return privateList.size();
        }


        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }


        public View getView(int position,View view,ViewGroup parent) {


            LayoutInflater inflater=LayoutInflater.from(context);
            View rowView=inflater.inflate(R.layout.listitem_private_competition, null,true);

            TextView compName = (TextView) rowView.findViewById(R.id.private_listitem_competition_name);
            TextView orgName = (TextView) rowView.findViewById(R.id.private_listitem_org_name);
            TextView numMembers = (TextView) rowView.findViewById(R.id.private_listitem_num_members);

            compName.setText(privateList.get(position).competitionName);
            orgName.setText(privateList.get(position).orgName);
            numMembers.setText("Members:\n" + privateList.get(position).numMembers);

            return rowView;

        };
    }

class UIUtils {

    /**
     * Sets ListView height dynamically based on the height of the items.
     *
     * @param listView to be resized
     * @return true if the listView is successfully resized, false otherwise
     */
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}

