package com.example.diemdanhsv;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;

import java.util.ArrayList;

public class DiemdanhSV extends AppCompatActivity {


    public static final int DEFAULT_VIEW = 0x22;
    private static final int REQUEST_CODE_SCAN = 0x01;
    private static int buoihocId;
    private static int monhocId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diemdanh_sv);

         buoihocId = getIntent().getIntExtra("ddsv_buoihoc_id",-1);
         monhocId = getIntent().getIntExtra("ddsv_monhoc_id",-1);

         hienthithongtin();

        ImageView backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gobackbuoihoc = new Intent(getApplicationContext(), Buoihoc.class);
                gobackbuoihoc.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(gobackbuoihoc);
            }
        });

        ImageView addSVBtn = (ImageView)findViewById(R.id.addSVBtn);
        addSVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diemdanhsvTothemsv = new Intent(getApplicationContext(), Them_sinhvien.class);
                diemdanhsvTothemsv.putExtra("ddsv_buoihoc_id",buoihocId);
                diemdanhsvTothemsv.putExtra("ddsv_monhoc_id",monhocId);
                startActivity(diemdanhsvTothemsv);

            }
        });

        Button diemdanhBtn = (Button)findViewById(R.id.diamdanhBtn);
        diemdanhBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newViewBtnClick(v);
            }
        });

    }


    public void newViewBtnClick(View view) {
        // DEFAULT_VIEW is customized for receiving the permission verification result.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                    DEFAULT_VIEW);

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions == null || grantResults == null || grantResults.length < 2 || grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        int result = ScanUtil.startScan(DiemdanhSV.this, REQUEST_CODE_SCAN, new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.ALL_SCAN_TYPE).create());

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //receive result after your activity finished scanning
        super.onActivityResult(requestCode, resultCode, data);
        SQLiteDiemdanh sqLiteDiemdanh = new SQLiteDiemdanh(getApplicationContext());
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        // Obtain the return value of HmsScan from the value returned by the onActivityResult method by using ScanUtil.RESULT as the key value.
        if (requestCode == REQUEST_CODE_SCAN) {
            Object obj = data.getParcelableExtra(ScanUtil.RESULT);
            if (obj instanceof HmsScan) {
                if (!TextUtils.isEmpty(((HmsScan) obj).getOriginalValue())) {
                    String msv = ((HmsScan) obj).getOriginalValue();
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
            }
        }

        hienthithongtin();


    }
    public void hienthithongtin(){

        SQLiteDiemdanh sqLiteDiemdanh = new SQLiteDiemdanh(getApplicationContext());

        int tongsv ;
        int tongsvdd;

        tongsv=sqLiteDiemdanh.tongSVmonhoc(monhocId);
        tongsvdd=sqLiteDiemdanh.tongSVbuoihoc(buoihocId);
        TextView tvsisosv = (TextView)findViewById(R.id.tvSisobuoihoc);
        tvsisosv.setText("Diem danh "+String.valueOf(tongsvdd)+"/"+ String.valueOf(tongsv));
        ArrayList<ArrayList<String>> arrLaydssvdiemdanh = sqLiteDiemdanh.laydsvsdiemdanh(buoihocId);
        ListView lvDiemdanh = (ListView)findViewById(R.id.lvDiemdanh);
        Adapter_diemdanh adapter_diemdanh =new Adapter_diemdanh(getApplicationContext(),arrLaydssvdiemdanh.get(0),arrLaydssvdiemdanh.get(1));
        lvDiemdanh.setAdapter(adapter_diemdanh);

    }

}