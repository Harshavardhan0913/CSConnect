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

public class CollegeForumAnswerAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Answer> arr;

    public CollegeForumAnswerAdapter(Context context,ArrayList<Answer> arr) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.answers_layout,viewGroup,false);
        }
        Answer a = (Answer) getItem(i);
        TextView answer = view.findViewById(R.id.answerLayoutAnswer);
        TextView user = view.findViewById(R.id.answerLayoutUser);

        answer.setText(a.getAnswer());
        user.setText(a.getUser());

        return view;
    }
}