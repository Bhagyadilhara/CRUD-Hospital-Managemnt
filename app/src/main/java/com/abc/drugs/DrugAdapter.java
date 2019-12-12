package com.abc.drugs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DrugAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<DrugsModel> drugsModelArrayList;

    public DrugAdapter(Context context, ArrayList<DrugsModel> drugsModelArrayList) {

        this.context = context;
        this.drugsModelArrayList = drugsModelArrayList;
    }


    @Override
    public int getCount() {
        return drugsModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return drugsModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvmanufacturer = (TextView) convertView.findViewById(R.id.manufacturer);
            holder.tvquantity = (TextView) convertView.findViewById(R.id.quantity);
            holder.tvprice = (TextView) convertView.findViewById(R.id.price);
            holder.tvdescription = (TextView) convertView.findViewById(R.id.description);



            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvname.setText           ("Name           : "+ drugsModelArrayList.get(position).getName());
        holder.tvmanufacturer.setText   ("Manufacturer  : "+ drugsModelArrayList.get(position).getManufacturer());
        holder.tvquantity.setText       ("Quantity      ' : "+ drugsModelArrayList.get(position).getQuantity());
        holder.tvprice.setText          ("Price         : "+ drugsModelArrayList.get(position).getPrice());
        holder.tvdescription.setText    ("Description   : "+ drugsModelArrayList.get(position).getDescription());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvmanufacturer,tvquantity,tvprice,tvdescription;
    }
}
