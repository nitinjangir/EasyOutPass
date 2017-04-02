package com.ramola.vibhav;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sahil on 1/4/17.
 */

public class BluetoothDeviceAdapter extends RecyclerView.Adapter<BluetoothDeviceAdapter.viewHolder> {

private ArrayList<BluetoothDevice> list=new ArrayList<>();

    public  void refresh(ArrayList<BluetoothDevice> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bluetooth_device,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

   BluetoothDevice device = list.get(position);

            holder.deviceTextView.setText(device.getName()+" "+device.getAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private TextView deviceTextView;
        public viewHolder(View itemView) {
            super(itemView);
            deviceTextView = (TextView) itemView.findViewById(R.id.text_device);
        }
    }
}
