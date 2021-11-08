package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ThemDSSV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dssv);

        Integer monhocID = getIntent().getIntExtra("ddsv_monhoc_id",-1);
        ImageView backdssvBtn = (ImageView)findViewById(R.id.backdsBtn);
        backdssvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gobackDsSV = new Intent(ThemDSSV.this,DanhsachSV.class);
                gobackDsSV.putExtra("ddsv_monhoc_id",monhocID);
                startActivity(gobackDsSV);

            }
        });
        EditText Tensv = (EditText)findViewById(R.id.TenSV);
        EditText Msv = (EditText)findViewById(R.id.Msv);

        Button themsvBtn = (Button)findViewById(R.id.addSVBtn);
        themsvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSv = Tensv.getText().toString();
                String MaSV = Msv.getText().toString();

                SQLiteDiemdanh sqLite = new SQLiteDiemdanh(getApplicationContext());
                if ( sqLite.check_tontaiSV(MaSV,monhocID) !=0){
                    Toast.makeText(getApplicationContext(),"Sinh vien da ton tai",Toast.LENGTH_LONG).show();

                }
                else {
                    int kq = sqLite.insertsinhvien(tenSv,MaSV,monhocID);

                    if (kq == -1){
                        Toast.makeText(getApplicationContext(),"Them that bai",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Them thanh cong",Toast.LENGTH_LONG).show();
                        Tensv.setText("");
                        Msv.setText("");
                    }
                }


            }
        });

    }

}