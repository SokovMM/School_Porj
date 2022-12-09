package com.example.school_porj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom extends BaseAdapter {
    private ArrayList<StudentClass> postArrayList;
    private Context context;
    private int layout;

    public Custom(ArrayList<StudentClass> postArrayList, Context context, int layout) {
        this.postArrayList = postArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return postArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return postArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{

        TextView idtext,nametext;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getView");
        ViewHolder viewHolder = new ViewHolder();

        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(layout,null);
        viewHolder.idtext=convertView.findViewById(R.id.idtext);
        viewHolder.nametext=convertView.findViewById(R.id.nametext);

        StudentClass post=postArrayList.get(position);
        //  viewHolder.idtext.setText(post.getId().);
        viewHolder.idtext.setText(Integer.toString(post.getId()));
        viewHolder.nametext.setText(post.getName());
        System.out.println(post.getId());

        return convertView;
    }

}
