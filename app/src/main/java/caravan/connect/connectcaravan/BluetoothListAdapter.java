package caravan.connect.connectcaravan;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class BluetoothListAdapter extends BaseAdapter {
    private HashMap<String, String> mapDevice = new HashMap<String, String>();
    Context context;

    public BluetoothListAdapter(Context context, HashMap<String, String> devices){
        this.context = context;
        mapDevice = devices;
    }



    @Override
    public int getCount() {
        return mapDevice.size();
    }

    @Override
    public Object getItem(int position) {
        return mapDevice.get(mapDevice.keySet().toArray(new String[mapDevice.size()])[position]);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void refreshEvents(HashMap<String, String> events) {
        mapDevice = events;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShowDevice holder = new ShowDevice();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        String[]  keys = mapDevice.keySet().toArray(new String[mapDevice.size()]);
        String idDevice = keys[position];
        String nameDevice = (String) getItem(position);

        convertView = messageInflater.inflate(R.layout.fila_canal, null);
        holder.avatar       = (View) convertView.findViewById(R.id.icon_device);
        holder.nameDevice   = (TextView) convertView.findViewById(R.id.name_device);
        holder.idDevice    = (TextView) convertView.findViewById(R.id.id_device);
        convertView.setTag(holder);

        holder.nameDevice.setText(nameDevice);
        holder.idDevice.setText(idDevice);



        return convertView;
    }




}


class ShowDevice {
    public View avatar;
    public TextView nameDevice;
    public TextView idDevice;




}





