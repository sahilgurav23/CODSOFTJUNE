package com.example.currency_converter;

import static android.content.ContentValues.TAG;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton imageconverter, first_sub_button, first_add_button, second_sub_button, second_add_button;
    TextView btc_textview, usd_textview, red_btc_texview, blue_usd_texview, first_textview, first_under_textview, second_textview, second_under_textview;
    Button buy_button;
    ListView buy_listview;

    ArrayList<String> dataList = new ArrayList<>();

    float btcValue, usdValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageconverter = (ImageButton) findViewById(R.id.convert_image);
        first_sub_button = (ImageButton) findViewById(R.id.first_sub_sign);
        first_add_button = (ImageButton) findViewById(R.id.first_add_sign);
        second_sub_button = (ImageButton) findViewById(R.id.second_sub_sign);
        second_add_button = (ImageButton) findViewById(R.id.second_add_sign);
        btc_textview = (TextView) findViewById(R.id.btc_word);
        usd_textview = (TextView) findViewById(R.id.usd_word);
        red_btc_texview = (TextView) findViewById(R.id.btc_red);
        blue_usd_texview = (TextView) findViewById(R.id.usd_blue);
        first_textview = (TextView) findViewById(R.id.firts_textview);
        first_under_textview = (TextView) findViewById(R.id.first_white);
        second_textview = (TextView) findViewById(R.id.second_texview);
        second_under_textview = (TextView) findViewById(R.id.second_white);
        buy_button = (Button) findViewById(R.id.buy_id);
        buy_listview = (ListView) findViewById(R.id.listview);
        getData();
        imageconverter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(btc_textview.getText() == "BTC"){
                    btc_textview.setText("USD");
                    usd_textview.setText("BTC");
                    red_btc_texview.setText("USD");
                    blue_usd_texview.setText("BTC");
                }
                else{
                    btc_textview.setText("BTC");
                    usd_textview.setText("USD");
                    red_btc_texview.setText("BTC");
                    blue_usd_texview.setText("USD");
                }
            }
        });

        first_sub_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(first_textview.getText().toString());
                first_textview.setText(Integer.toString(num-1));
                second_textview.setText(Double.toString(num*btcValue));
            }
        });

        second_sub_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(second_textview.getText().toString());
                second_textview.setText(Integer.toString(num-1));
                first_textview.setText(Double.toString(num*usdValue));
            }
        });

        first_add_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(first_textview.getText().toString());
                first_textview.setText(Integer.toString(num+1));
                second_textview.setText(Double.toString(num*btcValue));
            }
        });

        second_add_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(second_textview.getText().toString());
                second_textview.setText(Integer.toString(num+1));
                first_textview.setText(Double.toString(num*usdValue));
            }
        });

        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.add(second_textview.getText().toString());
                CustomListAdapter adapter = adapter = new CustomListAdapter(MainActivity.this, dataList);
                buy_listview.setAdapter(adapter);
            }
        });
    }

    private void getData() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    usdValue = (float) jsonObject.getJSONObject("bitcoin").getDouble("usd");
                    btcValue = (float) (1/usdValue);

                    first_under_textview.setText("~1 BTC = "+usdValue+" USD");
                    second_under_textview.setText("~1 USD = "+btcValue+" BTC");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error: " + error.toString());
            }
        });
        mRequestQueue.add(stringRequest);
    }
}
