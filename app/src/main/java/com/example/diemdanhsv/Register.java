package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etTaiKhoanRegister = (EditText)findViewById(R.id.etTaiKhoanRegister);
        EditText etPassWRegister = (EditText)findViewById(R.id.etPassWRegister);
        EditText etsdt = (EditText)findViewById(R.id.etSdt);
        Button registerBtn = (Button)findViewById(R.id.registerBtn);

        TextView tvhello = (TextView)findViewById(R.id.tvhello);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk = etTaiKhoanRegister.getText().toString();
                String pw = etPassWRegister.getText().toString();
                String sdt= etsdt.getText().toString();
                SQLiteDiemdanh sqLite = new SQLiteDiemdanh(getApplicationContext());


                boolean tontaiuser = sqLite.check_tktontai(tk);

                if (tontaiuser){
                    Toast.makeText(getApplicationContext(),"tk da ton tai",Toast.LENGTH_LONG).show();

                }
                else {
                    int userid ;
                    userid= sqLite.insertUser(tk,pw,sdt);
                    if ( userid >= 0){
                        Log.d("register", "dk thanh cong");
                        tvhello.setText("dk thanh ocng");
                        MainActivity.ck_login=true;
                        MainActivity.user_id=userid;
                        Intent registerTohome = new Intent(getApplicationContext(), Home.class);
                        startActivity(registerTohome);

                    }
                    else {
                        Log.d("elsee", " dk thatbaiiiiiiiiiii");
                        tvhello.setText("dk that bai");
                    }
                }

            }
        });

        TextView loginAcc = (TextView)findViewById(R.id.loginAcc);
        loginAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerTologin = new Intent(getApplicationContext(),Login.class);
                startActivity(registerTologin);
            }
        });
    }
}