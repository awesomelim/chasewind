package com.chasewind.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(this);
        if(p.getString("weather", null) != null){
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
        //String res = scalesvg("M19.35,10.04C18.67,6.59 15.64,4 12,4 9.11,4 6.6,5.64 5.35,8.04 2.34,8.36 0,10.91 0,14c0,3.31 2.69,6 6,6h13c2.76,0 5,-2.24 5,-5 0,-2.64 -2.05,-4.78 -4.65,-4.96z", 24f, 108f);
        //Log.d("svg", res);
    }


    public String scalesvg(String path, float src, float dest){
        StringBuilder curnums = new StringBuilder();
        StringBuilder res = new StringBuilder();
        float scale = dest/src;
        char c;
        for(int i=0;i<path.length();i++){
            c = path.charAt(i);
            if(c>='0'&&c<='9'||c=='-'){
                curnums.append(c);
            }else if(c=='.'){
                curnums.append(c);
            }else{
                if(curnums.length()>0){
                    try{
                        res.append((Math.round(scale*Float.parseFloat(curnums.toString())*100))/100f);
                        curnums.delete(0, curnums.length());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                res.append(c);

            }
        }
        return res.toString();
    }
}