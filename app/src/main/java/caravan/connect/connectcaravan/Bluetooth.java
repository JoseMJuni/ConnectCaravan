package caravan.connect.connectcaravan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Set;

public class Bluetooth {
    private static HashMap<String, String> devices = new HashMap<String, String>();

    public HashMap<String, String> getDevices(){
        return (devices);
    }

    public void addDevice(String id, String name){
        devices.put(id, name);
    }

   /* public final static  BroadcastReceiver bReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Log.i("bluetooth: ",device.getName());
                    devices.put(device.getAddress(), device.getName());

            }

        }
    };*/

/*
    public void startFindDevice() {

        new Thread(new Runnable() {
            public void run() {
                Controller.getController().bReciever = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            addDevice(device.getAddress(), device.getName());
                        }
                    }
                };
            }
        }).start();
        /*
        new Thread(new Runnable() {
            public void run() {
                Set<BluetoothDevice> pairedDevices = Controller.getController().mBluetoothAdapter.getBondedDevices();
                if(pairedDevices.size() > 0)
                {
                    for(BluetoothDevice device : pairedDevices)
                    {
                        Log.i("bluetooth: ",device.getName());
                        addDevice(device.getAddress(), device.getName());

                    }
                }
            }
    }).start();


    }*/
}
