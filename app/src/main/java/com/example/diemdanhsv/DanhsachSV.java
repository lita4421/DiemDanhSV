package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhsachSV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_sv);

        Integer monhocId = getIntent().getIntExtra("ddsv_monhoc_id",-1);
        Button themdssvBtn = (Button)findViewById(R.id.themdssvBtn);
        themdssvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoThemdssv = new Intent(getApplicationContext(),ThemDSSV.class);
                gotoThemdssv.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gotoThemdssv);
            }
        });

        ImageView backbuoihocBtn = (ImageView)findViewById(R.id.backbuoihocBtn);
        backbuoihocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gobackBuoihoc = new Intent(getApplicationContext(),chitietMonhoc.class);
                gobackBuoihoc.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gobackBuoihoc);
            }
        });

        SQLiteDiemdanh sqLite = new SQLiteDiemdanh(getApplicationContext());
        ArrayList<ArrayList<String>> arrSinhvien= sqLite.laysinhvien(monhocId);
        ArrayList<String> TenSv ;
        ArrayList<String> MaSV;
        TenSv = arrSinhvien.get(0);
        MaSV = arrSinhvien.get(1);

        ListView lvdsSinhvien = (ListView)findViewById(R.id.lvDSSV);
        Adapter_danhsachSV adapter_danhsachSV= new Adapter_danhsachSV(getApplicationContext(),TenSv,MaSV);
        lvdsSinhvien.setAdapter(adapter_danhsachSV);

    }
}