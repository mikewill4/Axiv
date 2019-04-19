package edu.umd.cmsc434.axiv;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.umd.cmsc434.axiv.AppData.PrivateCompetitionInfo;
import edu.umd.cmsc434.axiv.AppData.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompeteFragment extends Fragment {

    private ListView featuredListView;
    private ListView privatelistView;
    private ListView invites;




    public CompeteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View competeView = inflater.inflate(R.layout.fragment_compete, container, false);

        MyPrivateListAdapter privateListAdapter = new MyPrivateListAdapter(this.getActivity(),AppData.userPrivateCompetitions);
        privatelistView = (ListView) competeView.findViewById(R.id.compete_private_listview);
        privatelistView.setAdapter(privateListAdapter);
        UIUtils.setListViewHeightBasedOnItems(privatelistView);

        MyFeaturedListAdapter featuredListAdapter = new MyFeaturedListAdapter(this.getActivity(),AppData.userFeaturedCompetitions);
        featuredListView = (ListView) competeView.findViewById(R.id.compete_featured_listview);
        featuredListView.setAdapter(featuredListAdapter);
        UIUtils.setListViewHeightBasedOnItems(featuredListView);

        MyInvitesListAdapter invitesListAdapter = new MyInvitesListAdapter(this.getActivity(),AppData.userCompetitionInvites, privateListAdapter);
        invites = (ListView) competeView.findViewById(R.id.compete_invites_listview);
        invites.setAdapter(invitesListAdapter);
        UIUtils.setListViewHeightBasedOnItems(invites);


        privatelistView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent indvComp = new Intent(getActivity(),IndividualCompetition.class);
                indvComp.putExtra("userList", position);

                startActivity(indvComp);
            }
        });



        // Inflate the layout for this fragment
        return competeView;
    }

}


class MyFeaturedListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<AppData.FeaturedCompetitionInfo> featuredList;

    public MyFeaturedListAdapter(Activity context, ArrayList<AppData.FeaturedCompetitionInfo> featuredList) {
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
        private final ArrayList<AppData.PrivateCompetitionInfo> privateList;

        public MyPrivateListAdapter(Activity context, ArrayList<AppData.PrivateCompetitionInfo> privateList) {
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



class MyInvitesListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<AppData.InvitesInfo> inviteList;
    private MyPrivateListAdapter privateListAdapter;
    private ListView privateListView;

    public MyInvitesListAdapter(Activity context, ArrayList<AppData.InvitesInfo> inviteList, MyPrivateListAdapter privateListAdapter) {
        super(context, R.layout.listitem_compete_invite);

        this.context=context;
        this.inviteList = inviteList;
        this.privateListAdapter = privateListAdapter;
        this.privateListView = privateListView;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return inviteList.size();
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(final int position, View view, ViewGroup parent) {


        LayoutInflater inflater=LayoutInflater.from(context);
        View rowView=inflater.inflate(R.layout.listitem_compete_invite, null,true);

        TextView compName = (TextView) rowView.findViewById(R.id.invites_listitem_competition_name);
        TextView orgName = (TextView) rowView.findViewById(R.id.invites_listitem_org_name);

        compName.setText(inviteList.get(position).competitionName);
        orgName.setText(inviteList.get(position).orgName);

        ImageButton accept = (ImageButton) rowView.findViewById(R.id.accept_invite_compete);
        ImageButton reject = (ImageButton) rowView.findViewById(R.id.reject_invite_compete);

        accept.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<AppData.User> emptyUserList = new ArrayList<User>();
                emptyUserList.add(AppData.appUser);
                emptyUserList.add(new AppData.User("Becky Invites", 75.4));

                AppData.userPrivateCompetitions.add(new PrivateCompetitionInfo(
                        inviteList.get(position).competitionName,
                        inviteList.get(position).orgName,
                        emptyUserList.size(),
                        emptyUserList
                        ));

                privateListAdapter.notifyDataSetChanged();

                inviteList.remove(position);
                notifyDataSetChanged();

                Intent indvComp = new Intent(getContext(),IndividualCompetition.class);
                indvComp.putExtra("userList", position);

                getContext().startActivity(indvComp);



            }
        });

        reject.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Rejecting Invite");
                inviteList.remove(position);
                notifyDataSetChanged();


            }
        });







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

