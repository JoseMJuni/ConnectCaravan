package caravan.connect.connectcaravan;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    Controller controller = Controller.getController();
    BluetoothAdapter mBluetoothAdapter ;
    BluetoothListAdapter blueAdapter = new BluetoothListAdapter(this,controller.getDevicesBluetooth());
    ListView canalView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        overridePendingTransition(0,0);





        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Log.i("Permisos", "Se tienen los permisos!");
        } else {
            requestPermissions(new String[] { Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION }, 1222);
        }


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receive, filter); // Hay que eliminarlo en el onDestroy!!

        registerReceiver(receive, filter); // Hay que eliminarlo en el onDestroy!!

        enableBluetooth();
        showListDevices();
        //addDeviceSynchronized();
        //refreshList();


    }

    private void addDeviceSynchronized() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                controller.addDevice(device.getAddress(), device.getName());
                blueAdapter.notifyDataSetChanged();
            }
        }

    }


    private final BroadcastReceiver receive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Receive: ","dentro");
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                String id   = device.getAddress();
                if(device.getName() == null){

                }
                controller.addDevice(device.getAddress(), device.getName());
                blueAdapter.notifyDataSetChanged();



            }
        }};;





    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Don't forget to unregister the ACTION_FOUND receiver.
        mBluetoothAdapter.cancelDiscovery();
        unregisterReceiver(receive);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



    private void showListDevices(){
        canalView = (ListView) findViewById(R.id.listChat);
        canalView.setAdapter(blueAdapter);


        ListView listChat = ((ListView)findViewById(R.id.listChat));
        listChat.setSelectionAfterHeaderView();
        listChat.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3)
            {
                connectDevice(adapter, v, position);

            }
        });
    }

    private void connectDevice(AdapterView<?> adapter, View v, int position) {

        AlertDialog.Builder confirmConnect = new AlertDialog.Builder(this);
        confirmConnect.setTitle("Connecto to device");
        confirmConnect.setMessage("Connect to "+blueAdapter.getItem(position));
        confirmConnect.setCancelable(false);
        confirmConnect.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //aceptar();
            }
        });
        confirmConnect.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //cancelar();
            }
        });
        confirmConnect.show();



    }


    private void enableBluetooth()
    {


        if(mBluetoothAdapter == null)
        {

        }

        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth,1);
            mBluetoothAdapter.enable();

        }

        if(mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();

        }
        mBluetoothAdapter.startDiscovery();



    }



    public void refreshList(){
        Updategui up = new Updategui(this, 1);

    }

    public class Updategui extends TimerTask {
        Activity context;
        Timer timer;

        public Updategui(Activity context, int seconds) {
            this.context = context;
            timer = new Timer();
            timer.schedule(this,
                    seconds * 1000,  // initial delay
                    seconds * 1000); // subsequent rate
        }

        @Override
        public void run() {
            if(context == null || context.isFinishing()) {
                this.cancel();
                return;
            }

            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    try {
                      /*  Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                        if(pairedDevices.size() > 0)
                        {
                            for(BluetoothDevice device : pairedDevices)
                            {
                                Log.d("bluetooth asociados: ",device.getName());
                                controller.addDevice(device.getAddress(), device.getName());

                            }
                        }*/
                        blueAdapter.refreshEvents(controller.getDevicesBluetooth());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }}





}
