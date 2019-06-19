package com.example.listview_exercise;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listview_exercise.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Product> {

    int layoutResourceId;
    Context context;
    ArrayList<Product> data;

    public MyAdapter(Context context, int layoutResourceId, ArrayList<Product> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        TextView tv_text = (TextView) row.findViewById(R.id.text);
        TextView tv_units = (TextView) row.findViewById(R.id.units);
        ImageView iv_url = (ImageView) row.findViewById(R.id.image);


        String value = data.get(position).getName();
        boolean bought = data.get(position).isBought();
       String url = data.get(position).getUrl();

        tv_units.setText(String.valueOf(data.get(position).getUnits()));
        tv_text.setText(value);

        Picasso.get().load(url).into(iv_url);

        setRed(bought, tv_text);

        return row;
    }

   /* private void setRed(String value, TextView tv_text) {
        if (value.toUpperCase().startsWith("P") || value.toUpperCase().startsWith("C") ){
            tv_text.setTextColor(context.getResources().getColor(R.color.red));
        }
    }
*/

    private void setRed(boolean bought, TextView tv_text) {

        if (bought) {
            tv_text.setTextColor(context.getResources().getColor(R.color.red));

        }

    }
}
