package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Buoihoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buoihoc);

        Integer monhocId = getIntent().getIntExtra("ddsv_monhoc_id",-1);
        SQLiteDiemdanh sqLite = new SQLiteDiemdanh(getApplicationContext());


        ArrayList<ArrayList<String>> arrBuoihoc = sqLite.laybuoihoc(monhocId);


        ArrayList<String> tenbuoi;
        ArrayList<String> siso;
        ArrayList<String> bhID;
        tenbuoi = arrBuoihoc.get(0);
        siso =arrBuoihoc.get(1);
        bhID = arrBuoihoc.get(2);

        ArrayList<String> sisosv = new ArrayList<>();
        int tongsv ;
        tongsv = sqLite.tongSVmonhoc(monhocId);
        for (int i =0 ; i< bhID.size();i++){
            int tongsvbuoihoc ;
            tongsvbuoihoc=sqLite.tongSVbuoihoc(Integer.parseInt(bhID.get(i)));
            sisosv.add(tongsvbuoihoc+"/"+tongsv);
        }
        ListView lvBuoihoc = (ListView)findViewById(R.id.lvBuoihoc);
        Adapter_buoihoc adapter_buoihoc = new Adapter_buoihoc(getApplicationContext(),tenbuoi,sisosv,bhID);
        lvBuoihoc.setAdapter(adapter_buoihoc);

        lvBuoihoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView buoihocID = view.findViewById(R.id.buoihocID);
                String IDbuoihoc = buoihocID.getText().toString();
                Integer buoihocId = Integer.parseInt(IDbuoihoc);
                Intent gotoDiemdanhSV = new Intent(getApplicationContext(),DiemdanhSV.class);
                gotoDiemdanhSV.putExtra("ddsv_buoihoc_id",buoihocId);
                gotoDiemdanhSV.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gotoDiemdanhSV);
            }
        });


        ImageView backhBtn= (ImageView) findViewById(R.id.backhBtn);
        backhBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent monhoocTohome = new Intent(getApplicationContext(),chitietMonhoc.class);
                monhoocTohome.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(monhoocTohome);

            }
        });

    }
}