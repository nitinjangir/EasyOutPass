package com.ramola.vibhav;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ramola.vibhav.Model.Student;

import java.util.ArrayList;

/**
 * Created by sahil on 30/3/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.viewHolder> {

    private ArrayList<Student> list =new ArrayList<>();
    private Context context;

    public StudentAdapter(Context context) {
        this.context = context;
    }

    public  void refresh(ArrayList<Student> list){
        this.list= list;
        notifyDataSetChanged();
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
         Student student = list.get(position);
        if(student!=null){
            if(student.isOut())
                holder.cardView.setBackgroundColor(Color.RED);
            else
            holder.cardView.setBackgroundColor(Color.GREEN);
            if(student.getName()!=null)
                holder.userName.setText(student.getName());
            if(student.getRoomNo()!=null)
                holder.userRoomNo.setText(student.getRoomNo());
            if(student.getRollNo()!=null)
                holder.userRollNo.setText(student.getRollNo());
            Glide.with(context).load(student.getPicUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.userPic);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        private ImageView userPic;
        private TextView userName,userRollNo, userRoomNo;
        private CardView cardView;
        public viewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_student);
            userPic = (ImageView) itemView.findViewById(R.id.userPic);
            userName = (TextView) itemView.findViewById(R.id.userName);
            userRollNo = (TextView) itemView.findViewById(R.id.userRollNo);
            userRoomNo = (TextView) itemView.findViewById(R.id.userRoomNo);
        }
    }
}
