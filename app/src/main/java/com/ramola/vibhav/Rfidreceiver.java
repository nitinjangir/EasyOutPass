package com.ramola.vibhav;

import android.Manifest;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ramola.vibhav.Model.UpdateStudentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by spider on 2/4/17.
 */

public class Rfidreceiver extends BroadcastReceiver {

    public static final String RFidrecrive ="Receive";
   public static final String Rfid="qwer1234";
    private static final int UPLOAD_ID=11126741;

    public void onReceive(Context context, Intent intent)
    {
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        switch (intent.getAction())
        {
            case RFidrecrive:
                String resultLocation = "", resultRFID="";
                String data=intent.getStringExtra(Rfid);
                Log.d("data1234567890",data);
                String location = data.trim().substring(Math.max(data.trim().length() - 2, 0));
                if(data.trim().length()>0)
                resultRFID = data.trim().substring(0,data.trim().length()-2).trim();
                if(location.equalsIgnoreCase("03")){
                    resultLocation = "Library";
                }
                else if(location.equalsIgnoreCase("04")){
                    resultLocation = "Home";
                }
                else if(location.equalsIgnoreCase("05")){
                    resultLocation = "Nescafe";
                }
                else if(location.equalsIgnoreCase("06")){
                    resultLocation = "CC";
                }
                else if(location.equalsIgnoreCase("07")){
                    resultLocation = "OAT";
                }
                else if(location.equalsIgnoreCase("01")){
                    resultLocation = "MARKET";
                }
                else if(location.equalsIgnoreCase("02")){
                    resultLocation = "SBI";
                }
                else if(location.equalsIgnoreCase("00")){
                    resultLocation = "HOSTEL";
                }

                builder.setContentTitle("DESTINATION "+resultLocation);
                builder.setSmallIcon(R.drawable.female);
                update(context,resultRFID,System.currentTimeMillis(),resultLocation);
                notificationManager.notify(UPLOAD_ID,builder.build());
                break;
        }
    }

    private void update(final Context context, String rfId, long updateTime, final String location){
        Call<UpdateStudentResponse> call= Util.getRetrofitService().updateStudentDetail(rfId, updateTime, location);
        call.enqueue(new Callback<UpdateStudentResponse>() {
            @Override
            public void onResponse(Call<UpdateStudentResponse> call, Response<UpdateStudentResponse> response) {

                UpdateStudentResponse r=response.body();
                if(r!=null&&response.isSuccess()){
                    Toast.makeText(context,r.getMessage(),Toast.LENGTH_LONG).show();
                    if (location.equalsIgnoreCase("Home")){
                        if(ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_DENIED){

                            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                                AppCompatActivity appCompatActivity= (AppCompatActivity) context;
                                appCompatActivity.requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 121);
                            }

                            return;

                        }
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(r.getPhoneNumber(),null,"Your Ward Has Gone To Home",null,null);
                    }
                }

            }

            @Override
            public void onFailure(Call<UpdateStudentResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
