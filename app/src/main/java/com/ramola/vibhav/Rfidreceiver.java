package com.ramola.vibhav;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

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
                String resultLocation = "";
                String data=intent.getStringExtra(Rfid);
                String location = data.trim().substring(Math.max(data.trim().length() - 2, 0));
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

                builder.setContentTitle("RFID "+resultLocation);
                builder.setSmallIcon(R.drawable.female);
                notificationManager.notify(UPLOAD_ID,builder.build());
                break;
        }
    }
}
