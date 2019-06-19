package com.example.listview_exercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview_exercise.model.Product;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Activity activity = this;
    ListView listView;
    MyAdapter adapter;
    ArrayList<Product> items;
    EditText etItem;
    EditText etUnits;
    EditText etUrl;
    CheckBox bought;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<Product>();

        listView = findViewById(R.id.lv_list);
        etItem = findViewById(R.id.et_item);
        etUnits = findViewById(R.id.et_units);
        bought = findViewById(R.id.bought);
        etUrl = findViewById(R.id.et_url);


        adapter = new MyAdapter(activity, R.layout.row, items);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                items.get(position).setIsBought(true);

                adapter.notifyDataSetChanged();
                // Toast.makeText(activity, position + " - " + itemValue, Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openDialog(position);
                return true;
            }
        });
    }


    public void addItemToList(View view) {

        String item = etItem.getText().toString();
        String units = etUnits.getText().toString();
        String url = etUrl.getText().toString();
        boolean isBought = bought.isChecked();
        if (checkFields(item,units,url)) {
            items.add(new Product(item, Integer.parseInt(units), isBought, url));
            adapter.notifyDataSetChanged();

            etItem.setText("");
            etUnits.setText("");
            etUrl.setText("");
            bought.setChecked(false);
        } else {

        }
    }

    private boolean checkFields(String item, String units, String url) {
        boolean result = true;
        if ("" .equals(item)) {
            result = false;
            etItem.setError(getString(R.string.empty));
        }
        if ("" .equals(units)) {
            result = false;
            etUnits.setError(getString(R.string.empty));
        }
        if ("".equals(url)){
            result = false;
            etUrl.setError(getString(R.string.empty));
        }
        return result;
    }


        public void openDialog ( final int position){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(getString(R.string.app_name));
            alertDialogBuilder.setMessage(getString(R.string.delete)).setCancelable(false)
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int d) {
                            items.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }
