package com.example.csconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Subject> arr;

    public ReviewAdapter(Context context,ArrayList<Subject> arr) {
        this.mContext = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.subject_layout,viewGroup,false);
        }
        Subject s = (Subject) getItem(i);
        TextView subject = view.findViewById(R.id.layoutSubName);
        TextView rating = view.findViewById(R.id.layoutRating);
        TextView sem = view.findViewById(R.id.layoutSem);

        ArrayList<UserReview> userReviews = new ArrayList<>();
        subject.setText(s.getSubName());
        sem.setText("Sem-"+s.getSem());

        try {
            HashMap<String,UserReview> map = new HashMap<>();
            map.putAll(s.getUserReviews());
            for(String key:map.keySet()){
                userReviews.add(map.get(key));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (userReviews.size()!=0) {
            int count = userReviews.size();
            float rating_sum = 0;
            for (UserReview user_rating : userReviews) {
                rating_sum += Float.parseFloat(user_rating.getRating());
            }
            float avg_rating = rating_sum / count;

            String a_rating = String.format("%.2f",avg_rating);

            rating.setText(a_rating);
        }
        else {
            rating.setText("No rating");
        }

        return view;
    }
}
