package com.example.csconnect;

import android.app.Person;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class CollegeForumAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Question> arr;

    public CollegeForumAdapter(Context context,ArrayList<Question> arr) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.question_layout,viewGroup,false);
        }
        Question q = (Question) getItem(i);
        TextView question = view.findViewById(R.id.layoutQuestion);
        TextView user = view.findViewById(R.id.layoutUser);
        TextView noOfAnswers = view.findViewById(R.id.layoutAnswersCount);

        question.setText(q.getQuestion());
        user.setText(q.getUsername());
        noOfAnswers.setText(""+q.getNoOfAnswers());

        return view;
    }
}
