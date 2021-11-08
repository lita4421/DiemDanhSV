package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class chitietMonhoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_monhoc);

        SQLiteDiemdanh sqLite = new SQLiteDiemdanh(getApplicationContext());
        String tenmonhoc = getIntent().getStringExtra("dsvs_tenmon");
        Integer monhocId = getIntent().getIntExtra("ddsv_monhoc_id",-1);
        TextView tvTenmon =(TextView)findViewById(R.id.tvTenmon);
        tvTenmon.setText(tenmonhoc);

        ImageView delete_img = (ImageView)findViewById(R.id.delete_img);
        delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLite.deleteMonhoc(monhocId);
                Intent gobackHome = new Intent(getApplicationContext(),Home.class);
                startActivity(gobackHome);
            }
        });

        ImageView dssinhvien_img = (ImageView)findViewById(R.id.dssinhvien_img);
        dssinhvien_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDsSV = new Intent(getApplicationContext(),DanhsachSV.class);
                gotoDsSV.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gotoDsSV);
            }
        });

        ImageView dsbuoihoc_img = (ImageView)findViewById(R.id.listBuoihoc_img);
        dsbuoihoc_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotodsbuoihoc = new Intent(getApplicationContext(),Buoihoc.class);
                gotodsbuoihoc.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gotodsbuoihoc);
            }
        });

        ImageView thongke_img = (ImageView)findViewById(R.id.thongke_img);
        thongke_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotoThongke = new Intent(getApplicationContext(),Thongke.class);
                gotoThongke.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gotoThongke);
            }
        });

        ImageView backMonhoc = (ImageView)findViewById(R.id.backMonhocBtn);
        backMonhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backMonhoc = new Intent(getApplicationContext(),Home.class);
                startActivity(backMonhoc);
            }
        });


    }
}