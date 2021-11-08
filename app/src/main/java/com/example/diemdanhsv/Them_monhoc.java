package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Them_monhoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_monhoc);

        EditText etAddMonhoc = (EditText)findViewById(R.id.etAddMonhoc);
        EditText etMamon = (EditText)findViewById(R.id.etMamon);
        EditText etSotin = (EditText)findViewById(R.id.etSotin);
        EditText etTongso = (EditText)findViewById(R.id.etTongso);

        ImageView backmhHome = (ImageView)findViewById(R.id.backmhHome);
        Button addmonBtn = (Button)findViewById(R.id.addmonBtn);
        addmonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themTemmon = etAddMonhoc.getText().toString();
                String themMamon = etMamon.getText().toString();
                String sotin=etSotin.getText().toString();
                String tongsoSV=etTongso.getText().toString();
                if (sotin.equals("") || tongsoSV.equals("") || themMamon.equals("") || themTemmon.equals("")){
                    Toast.makeText(getApplicationContext(),"Dien thieu thong tin",Toast.LENGTH_LONG).show();
                    return;
                }
                int themsotin = Integer.parseInt(etSotin.getText().toString()) ;
                int themTongso = Integer.parseInt(etTongso.getText().toString()) ;


                int userid = MainActivity.user_id;

                SQLiteDiemdanh sqLite = new SQLiteDiemdanh(getApplicationContext());

                if (sqLite.insertMonhoc(themTemmon,themsotin,themMamon,userid,themTongso)){
                    Toast.makeText(getApplicationContext(),"Them thanh cong",Toast.LENGTH_LONG).show();
                    Intent gobackHome = new Intent(getApplicationContext(),Home.class);
                    startActivity(gobackHome);
                }else {
                    Toast.makeText(getApplicationContext(),"Them that bai",Toast.LENGTH_LONG).show();
                }

            }
        });

        backmhHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent themmonhocTohome = new Intent(getApplicationContext(),Home.class);
                startActivity(themmonhocTohome);

            }
        });


    }
}