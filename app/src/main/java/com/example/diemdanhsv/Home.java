package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ImageView userBtn =(ImageView) findViewById(R.id.userBtn);
        ImageView themmonBtn = (ImageView)findViewById(R.id.themmonBtn);
        themmonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.ck_login){

                    Intent gotoThemmonhoc = new Intent(getApplicationContext(),Them_monhoc.class);
                    startActivity(gotoThemmonhoc);
                }
                else {
                    Intent homeTologin = new Intent(getApplicationContext(),Login.class);
                    startActivity(homeTologin);
                }

            }
        });

        SQLiteDiemdanh sqLite = new SQLiteDiemdanh(getApplicationContext());
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.ck_login){
                    
                    Intent homegotoThongtincanhan = new Intent(getApplicationContext(),Thongtincanhan.class);
                    startActivity(homegotoThongtincanhan);
                }
                else {
                    Intent homeTologin = new Intent(getApplicationContext(),Login.class);
                    startActivity(homeTologin);
                }

            }
        });

        ArrayList<ArrayList<String>> arrmonhoc =sqLite.laymonhoc();
        ArrayList<String> tenmonhoc ;
        ArrayList<String> idmonhoc ;
        tenmonhoc = arrmonhoc.get(0);
        idmonhoc = arrmonhoc.get(1);


        GridView GridViewMonhoc = (GridView)findViewById(R.id.GridView_Monhoc);

        Adapter_monhoc adapter_monhoc = new Adapter_monhoc(getApplicationContext(),idmonhoc,tenmonhoc);
        GridViewMonhoc.setAdapter(adapter_monhoc);

        GridViewMonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Integer monhocID = Integer.parseInt(idmonhoc.get(i));

                Intent homeTobuoihoc = new Intent(getApplicationContext(),chitietMonhoc.class);
                homeTobuoihoc.putExtra("ddsv_monhoc_id",monhocID);
                homeTobuoihoc.putExtra("dsvs_tenmon",tenmonhoc.get(i));
                startActivity(homeTobuoihoc);
            }
        });

    }

}
