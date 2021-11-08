package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Thongtincanhan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan);

        SQLiteDiemdanh sqLiteDiemdanh = new SQLiteDiemdanh(getApplicationContext());
        TextView tenTK = (TextView)findViewById(R.id.tvTaikhoan);
        tenTK.setText(sqLiteDiemdanh.laytaikhoan(MainActivity.user_id));

        Button logoutBtn = (Button)findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ck_login = false;
                MainActivity.user_id=-1;
                Intent gobackhome = new Intent(getApplicationContext(),Home.class);
                startActivity(gobackhome);
            }
        });

        ImageView backhomeBtn =(ImageView)findViewById(R.id.backhomeBtn);
        backhomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thongtincanhanTohome = new Intent(getApplicationContext(),Home.class);
                startActivity(thongtincanhanTohome);
            }
        });


    }
}