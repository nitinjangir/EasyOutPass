package com.ramola.vibhav;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sahil on 2/4/17.
 */

public class ClientThread extends IntentService {

    private static  final  String TAG=ClientThread.class.getSimpleName();
    public static  final String ADDRESS="address";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ClientThread(String name) {
        super(name);
    }

    public ClientThread(){
        this(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"reciecve");
        if(intent.hasExtra(ADDRESS)) {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothSocket bluetoothSocket = null;
            if (bluetoothAdapter != null) {
                Log.d(TAG,"Trying Connecting");
                BluetoothDevice device = bluetoothAdapter.getRemoteDevice(intent.getStringExtra(ADDRESS));
                try {
                    bluetoothSocket = device.createRfcommSocketToServiceRecord(ServerSide.uuid);
                    bluetoothSocket.connect();
                    Log.d(TAG, "connected Succesffully");
                    sendData(bluetoothSocket.getOutputStream(), "Y".getBytes());
                    recieveData(bluetoothSocket.getInputStream());

                } catch (IOException e) {
                    try {
                        Log.d("reconnecting", "reconnecting");

                        bluetoothSocket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(device, 1);
                        bluetoothSocket.connect();
                        sendData(bluetoothSocket.getOutputStream(), "Y".getBytes());
                        Log.d(TAG, "connected Succesffully");
                        recieveData(bluetoothSocket.getInputStream());
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    } catch (InvocationTargetException e1) {
                        e1.printStackTrace();
                    } catch (NoSuchMethodException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }

        }
    }


    private  void  recieveData(InputStream inputStream){
        byte[] buffer = new byte[1024];
       int bytes;

        while (true){
            try {
              Log.d("data1234567890","Data id recieving");
                bytes = inputStream.read(buffer);
                String strReceived = new String(buffer, 0, bytes);

                Intent i =new Intent(Rfidreceiver.RFidrecrive);
                i.putExtra(Rfidreceiver.Rfid,strReceived);
                sendBroadcast(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void  sendData(OutputStream stream, byte[] data){
        try {
            stream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
