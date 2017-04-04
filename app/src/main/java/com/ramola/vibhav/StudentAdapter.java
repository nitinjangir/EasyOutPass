package com.ramola.vibhav;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ramola.vibhav.Model.Student;

import java.util.ArrayList;
import java.util.Calendar;

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
            {
                holder.userLocation.setText(student.getLocation());
                holder.inTime.setVisibility(View.GONE);
                holder.cardView.setBackgroundColor(Color.RED);}
            else
            holder.cardView.setBackgroundColor(Color.GREEN);
            if(student.getName()!=null)
                holder.userName.setText(student.getName());
            if(student.getRoomNo()!=null)
                holder.userRoomNo.setText(student.getRoomNo());
            if(student.getRollNo()!=null)
                holder.userRollNo.setText(student.getRollNo());
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(student.getWhenIn());

            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH)+1;
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            int mHour=calendar.get(Calendar.HOUR_OF_DAY);
            int mMinute=calendar.get(Calendar.MINUTE);
            int mSecond=calendar.get(Calendar.SECOND);

            holder.inTime.setText("In Time is "+mDay+"/"+mMonth+"/"+mYear+" "+mHour+":"+mMinute+":"+mSecond);

            Calendar calendarOut = Calendar.getInstance();
            calendarOut.setTimeInMillis(student.getWhenOut());

            int mYearOut = calendarOut.get(Calendar.YEAR);
            int mMonthOut = calendarOut.get(Calendar.MONTH)+1;
            int mDayOut = calendarOut.get(Calendar.DAY_OF_MONTH);
            int mHourOut=calendarOut.get(Calendar.HOUR_OF_DAY);
            int mMinuteOut=calendarOut.get(Calendar.MINUTE);
            int mSecondOut=calendarOut.get(Calendar.SECOND);


            holder.outTime.setText("Out Time is "+mDayOut+"/"+mMonthOut+"/"+mYearOut+" "+mHourOut+":"+mMinuteOut+":"+mSecondOut);
            Glide.with(context).load(student.getPicUrl()).placeholder(R.drawable.female).error(R.drawable.female).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.userPic);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        private ImageView userPic;
        private TextView userName,userRollNo, userRoomNo,inTime,outTime,userLocation;
        private CardView cardView;
        public viewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_student);
            userPic = (ImageView) itemView.findViewById(R.id.userPic);
            userName = (TextView) itemView.findViewById(R.id.userName);
            userRollNo = (TextView) itemView.findViewById(R.id.userRollNo);
            userRoomNo = (TextView) itemView.findViewById(R.id.userRoomNo);
            inTime = (TextView) itemView.findViewById(R.id.inTime);
            outTime = (TextView) itemView.findViewById(R.id.outTime);
            userLocation= (TextView) itemView.findViewById(R.id.userLocation);
        }
    }
}
