package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Thongke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);

        SQLiteDiemdanh sqLiteDiemdanh = new SQLiteDiemdanh(getApplicationContext());
        Integer monhocId = getIntent().getIntExtra("ddsv_monhoc_id",-1);


        sqLiteDiemdanh.thongkemonhoc(monhocId);
        ArrayList<String> tensv ;
        ArrayList<String> masv;
        ArrayList<String> sobuoinghi;
        ArrayList<String> camthi;
        ArrayList<ArrayList<String>> arrDSSV =sqLiteDiemdanh.thongkemonhoc(monhocId);
        tensv=arrDSSV.get(0);
        masv=arrDSSV.get(1);
        sobuoinghi=arrDSSV.get(2);
        camthi= arrDSSV.get(3);

        int tongsosv = tensv.size();
        int tongcamthi =0;
        for (int i =0; i<camthi.size();i++){
            if (camthi.get(i).equals("Cam thi")){
                tongcamthi++;
            }
        }
        ListView lvdssv = (ListView)findViewById(R.id.lv_dssvCamthi);
        Adapter_thongke adapter_thongke = new Adapter_thongke(getApplicationContext(),tensv,masv,sobuoinghi,camthi);
        lvdssv.setAdapter(adapter_thongke);

        TextView tvTongsv = (TextView)findViewById(R.id.tvTongsv_thongke);
        TextView tvTongcamthi = (TextView)findViewById(R.id.tvCamthi_thongke);
        tvTongsv.setText("Tổng sinh vien: "+tongsosv);
        tvTongcamthi.setText("Cấm thi: "+tongcamthi);

        ImageView backhBtn= (ImageView) findViewById(R.id.backchitiet);
        backhBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gobackChitietmonhoc = new Intent(getApplicationContext(),chitietMonhoc.class);
                gobackChitietmonhoc.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gobackChitietmonhoc);

            }
        });

    }
}