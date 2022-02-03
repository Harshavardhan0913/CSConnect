package com.example.csconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContestsAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Contest> contests;

    public ContestsAdapter(Context mContext, ArrayList<Contest> contests) {
        this.mContext = mContext;
        this.contests = contests;
    }

    @Override
    public int getCount() {
        return contests.size();
    }

    @Override
    public Object getItem(int i) {
        return contests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.contest_layout,viewGroup,false);
        }
        Contest c = (Contest) getItem(i);
        TextView contestHeading = view.findViewById(R.id.contestHeading);
        TextView contestUser = view.findViewById(R.id.contestUser);

        contestHeading.setText(c.getHeading());
        contestUser.setText(c.getUserName());

        return view;
    }
}
