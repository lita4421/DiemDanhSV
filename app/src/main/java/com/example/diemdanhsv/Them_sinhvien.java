package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Them_sinhvien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinhvien);

        Integer buoihocId = getIntent().getIntExtra("ddsv_buoihoc_id",-1);
        Integer monhocId = getIntent().getIntExtra("ddsv_monhoc_id",-1);
        ImageView backdiemdanhBtn = (ImageView)findViewById(R.id.backdiemdanhBtn);
        backdiemdanhBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gobackdiemdanhsv = new Intent(getApplicationContext(),DiemdanhSV.class);
                gobackdiemdanhsv.putExtra("ddsv_buoihoc_id",buoihocId);
                gobackdiemdanhsv.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gobackdiemdanhsv);
            }
        });

        SQLiteDiemdanh sqLiteDiemdanh = new SQLiteDiemdanh(getApplicationContext());
        TextView tvMSV = (TextView) findViewById(R.id.edtMsv);
        Button themsvBtn = (Button)findViewById(R.id.themsvBtn);
        themsvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msv = tvMSV.getText().toString();
                if (sqLiteDiemdanh.check_tontaiSV(msv,monhocId)==1){
                    if (!sqLiteDiemdanh.checkMSV(msv ,String.valueOf(buoihocId))){
                        sqLiteDiemdanh.insertdiemdanhsv(msv,buoihocId,monhocId);
                        Toast.makeText(getApplicationContext(),"diem danh thanh cong",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"sinh vien da diem danh ",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"sinh vien khong co trong lop",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}