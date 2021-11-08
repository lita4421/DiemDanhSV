package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etTaiKhoanLogin = (EditText) findViewById(R.id.etTaiKhoanLogin);
        EditText etPassWLogin = (EditText)findViewById(R.id.etPassWLogin);
        Button loginBtn = findViewById(R.id.loginBtn);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mk = etPassWLogin.getText().toString();
                String tk = etTaiKhoanLogin.getText().toString();
                SQLiteDiemdanh sqLite = new SQLiteDiemdanh(getApplicationContext());


                int userid = sqLite.check_tk(tk,mk);

                if (userid==-1){

                    Toast.makeText(getApplicationContext(),"Tia khoan khong dung",Toast.LENGTH_LONG).show();

                }
                if (userid!=-1){
                    MainActivity.ck_login = true;
                    MainActivity.user_id=userid;

                    Intent gotoHome = new Intent(getApplicationContext(),Home.class);
                    startActivity(gotoHome);
                }

            }
        });

        TextView newAcc = (TextView)findViewById(R.id.newAcc);
        newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegister = new Intent(getApplicationContext(),Register.class);
                startActivity(gotoRegister);
            }
        });



    }
}