package com.ramola.vibhav;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sahil on 1/4/17.
 */

public class ClientSide extends AsyncTask<String, Void, Void> {
    private final String TAG=ClientSide.class.getSimpleName();
    private BluetoothAdapter bluetoothAdapter;

    private BluetoothSocket bluetoothSocket;


    public ClientSide(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    @Override
    protected Void doInBackground(String... strings) {
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(strings[0]);
        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(ServerSide.uuid);
            bluetoothSocket.connect();
            Log.d(TAG,"connected Succesffully");
            sendData(bluetoothSocket.getOutputStream(),"Y".getBytes());
            recieveData(bluetoothSocket.getInputStream());

        } catch (IOException e) {
            try {
                Log.d("reconnecting","reconnecting");

                bluetoothSocket =(BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(device,1);
                bluetoothSocket.connect();
                sendData(bluetoothSocket.getOutputStream(),"Y".getBytes());
                Log.d(TAG,"connected Succesffully");
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

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG,"data recieve");

    }

    private  void  recieveData(InputStream inputStream){
        while (true){
            byte[] buffer = new byte[1024];
            int bytes;
            Log.d(TAG, "Data Has Start Receiving");
            try {

                bytes = inputStream.read(buffer);
                Log.d(TAG, "Data");
                String strReceived = new String(buffer, 0, bytes);
                 Log.d(TAG,strReceived);
            } catch (IOException e) {
                e.printStackTrace();
            }
             //Prints the string content read from input stream
        }
    }

    private void  sendData(OutputStream stream,byte[] data){
        try {
            stream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
