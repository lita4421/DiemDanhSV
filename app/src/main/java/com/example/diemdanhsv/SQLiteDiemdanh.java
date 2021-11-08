package com.example.diemdanhsv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.huawei.hms.scankit.C;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SQLiteDiemdanh extends SQLiteOpenHelper {
    private String dbName = "dbDiemdanh1";

    public SQLiteDiemdanh( Context context) {
        super(context,"dbDiemdanh1",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table users " +
                        "(user_id integer primary key, username text,password text,sdt text)"
        );
        db.execSQL(
                "create table sinhvien " +
                        "(sv_id integer primary key, nameSv text,maSv text,monhoc_id integer)"
        );

        db.execSQL(
                "create table monhoc " +
                        "(monhoc_id integer primary key, nameMonhoc text,sotin integer,mamon text,user_id integer,tongso integer)"
        );

        db.execSQL(
                "create table buoihoc " +
                        "(buoihoc_id integer primary key, tenbuoihoc text,sisobuoi integer, monhoc_id integer)"
        );
        db.execSQL(
                "create table diemdanhsv " +
                        "(dd_id integer primary key, maSv text,nameSv text,buoihoc_id integer, thoigian text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS sinhvien");
        db.execSQL("DROP TABLE IF EXISTS monhoc");
        db.execSQL("DROP TABLE IF EXISTS sinhvien_monhoc");
        db.execSQL("DROP TABLE IF EXISTS buoihoc");
        db.execSQL("DROP TABLE IF EXISTS diemdanhsv");
        onCreate(db);

    }

    public int insertUser (String username, String password, String sdt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("sdt", sdt);

        return (int)db.insert("users", null, contentValues);
    }
    public boolean insertMonhoc ( String nameMonhoc ,int sotin ,String mamon, int user_id ,int tongso){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameMonhoc",nameMonhoc);
        contentValues.put("sotin",sotin);
        contentValues.put("mamon",mamon);
        contentValues.put("tongso",tongso);
        contentValues.put("user_id",user_id);

        int idmonhoc = (int)db.insert("monhoc",null,contentValues) ;

        if (idmonhoc<0){
            return false;
        }
        int sobuoi;
        sobuoi=sotin*3;

        for (int i=0;i<sobuoi;i++){
            insertBuoihoc("Buoi"+(i+1),0,  idmonhoc);
        }
        return true;

    }

    public  boolean insertBuoihoc ( String tenbuoihoc ,int sisobuoi ,int monhoc_id){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenbuoihoc",tenbuoihoc);
        contentValues.put("sisobuoi",sisobuoi);
        contentValues.put("monhoc_id",monhoc_id);
        db.insert("buoihoc",null,contentValues);
        return true;
    }

    public int insertsinhvien (String nameSv,String maSv ,int monhoc_id ){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameSv",nameSv);
        contentValues.put("maSv",maSv);
        contentValues.put("monhoc_id",monhoc_id);

        int result = (int)db.insert("sinhvien",null,contentValues);
        return result;
    }
    public boolean insertdiemdanhsv (String maSv, int buoihoc_id, int monhoc_id){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from sinhvien where monhoc_id =? and maSv = ? limit 1",new String[] {String.valueOf(monhoc_id),maSv});
        String tenSV ;
        if (res != null){
            if (res.moveToFirst()){
                tenSV=res.getString(1);
            }
            else
                return false;

        }else
            return false;

        ContentValues contentValues = new ContentValues();
        Date time = Calendar.getInstance().getTime();
        contentValues.put("maSv",maSv);
        contentValues.put("nameSv",tenSV);
        contentValues.put("buoihoc_id",buoihoc_id);
        contentValues.put("thoigian",time.toString());
        int result = (int)db.insert("diemdanhsv",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public int check_tontaiSV (String maSv ,int monhoc_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from sinhvien where monhoc_id = ? and maSv=?", new String[] {String.valueOf(monhoc_id),maSv});
        int count = res.getCount();
        res.close();

        return count;
    }

    public int check_tk (String tk, String mk) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT user_id FROM users WHERE username = ? AND password = ?", new String[]{tk, mk});

        if (res.getCount()==0){
            res.close();
            return -1;
        }else {
            int userid ;
            res.moveToFirst();
            userid = res.getInt(res.getColumnIndex("user_id"));
            res.close();
            return userid;
        }

    }
    public boolean check_tktontai(String tk){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select user_id from users where username = ? ", new String[] {tk});

        if(res.getCount()!= 0){
            res.close();
            return true;
        }
        else {
            res.close();
            return false;

        }
    }

    public ArrayList<ArrayList<String>> laymonhoc () {

        ArrayList<String> monhoc = new ArrayList<String>();
        ArrayList<String> id = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from monhoc where user_id = ? ", new String [] {String.valueOf(MainActivity.user_id)} );

        res.moveToFirst();

        while(res.isAfterLast() == false){
            monhoc.add(res.getString(res.getColumnIndex("nameMonhoc")));
            id.add(res.getString(res.getColumnIndex("monhoc_id")));
            res.moveToNext();
        }
        ArrayList<ArrayList<String>> arrMonhoc = new ArrayList<ArrayList<String>>();
        arrMonhoc.add(monhoc);
        arrMonhoc.add(id);

        res.close();
        return arrMonhoc ;

    }
    public ArrayList<ArrayList<String>> laybuoihoc(int monhocid){

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select *from buoihoc where monhoc_id =?", new String[] {String.valueOf(monhocid)});

        ArrayList<String> tenbuoi = new ArrayList<String>();
        ArrayList<String> sisobuoi = new ArrayList<>();
        ArrayList<String> idb = new ArrayList<>();

        res.moveToFirst();
        while (res.isAfterLast()== false){
            tenbuoi.add(res.getString(res.getColumnIndex("tenbuoihoc")));
            sisobuoi.add(res.getString(res.getColumnIndex("sisobuoi")));
            idb.add(res.getString(res.getColumnIndex("buoihoc_id")));
            res.moveToNext();
        }
        ArrayList<ArrayList<String>> arrBuoihoc = new ArrayList<ArrayList<String>>();
        arrBuoihoc.add(tenbuoi);
        arrBuoihoc.add(sisobuoi);
        arrBuoihoc.add(idb);
        res.close();
        return arrBuoihoc;

    }
    public ArrayList<ArrayList<String>> laysinhvien (int monhocId){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select *from sinhvien where monhoc_id=?",new String[] {String.valueOf(monhocId)} );
        ArrayList<String> TenSv = new ArrayList<>();
        ArrayList<String> MaSv = new ArrayList<>();
        res.moveToFirst();
        while (res.isAfterLast()==false){
            TenSv.add(res.getString(res.getColumnIndex("nameSv")));
            MaSv.add(res.getString(res.getColumnIndex("maSv")));
            res.moveToNext();
        }
        ArrayList<ArrayList<String>> arrSinhvien = new ArrayList<ArrayList<String>>();
        arrSinhvien.add(TenSv);
        arrSinhvien.add(MaSv);
        return arrSinhvien;
    }

    public ArrayList<ArrayList<String>> laydsvsdiemdanh (int buoihocId){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from diemdanhsv where buoihoc_id =?",new String[] {String.valueOf(buoihocId)});
        ArrayList<String> TenSv = new ArrayList<>();
        ArrayList<String> MaSv = new ArrayList<>();
        res.moveToFirst();
        while (res.isAfterLast()==false){
            TenSv.add(res.getString(res.getColumnIndex("nameSv")));
            MaSv.add(res.getString(res.getColumnIndex("maSv")));
            res.moveToNext();
        }
        ArrayList<ArrayList<String>> arrSinhvien = new ArrayList<ArrayList<String>>();
        arrSinhvien.add(TenSv);
        arrSinhvien.add(MaSv);
        return arrSinhvien;

    }
    public boolean checkMSV(String msv,String buoihocid) {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from diemdanhsv where maSv= ? and buoihoc_id =?",new String [] {msv,buoihocid});
        int count = res.getCount();

        res.close();
        if (count<1){
            return false;
        }else {
            return true;
        }
    }
    public int tongSVmonhoc (int monhocid){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from sinhvien where monhoc_id=?",new String[] {String.valueOf(monhocid)});
        int count = res.getCount();

        res.close();
        return count;
    }
    public int tongSVbuoihoc (int buoihocid){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from diemdanhsv where buoihoc_id=?",new String[] {String.valueOf(buoihocid)});
        int count = res.getCount();

        res.close();
        return count;
    }
    public String laytaikhoan (int userid){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select username from users where user_id=? ", new String[] {String.valueOf(userid)});
        if (res.getCount()==0){
            res.close();
         return "";
        }else {
            String tenTK ;
            res.moveToFirst();
            tenTK = res.getString(res.getColumnIndex("username"));
            res.close();
            return tenTK;
        }
    }
    public boolean deleteMonhoc (int monhocid){
        SQLiteDatabase db =this.getReadableDatabase();
       db.delete("sinhvien","monhoc_id =?",new String[] {String.valueOf(monhocid)});
       db.delete("buoihoc","monhoc_id =?",new String[] {String.valueOf(monhocid)});
        return  db.delete("monhoc","monhoc_id =?",new String[] {String.valueOf(monhocid)})>0;
    }
    public int sobuoinghi (String msv, int monhocid){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from diemdanhsv where maSv=? and buoihoc_id in (select buoihoc_id from buoihoc where monhoc_id=?)",
                new String[] {msv,String.valueOf(monhocid)} );
       int count =res.getCount();
       res.close();
       return count;
    }
    public int tongbuoihoc (int monhocid){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select buoihoc_id from buoihoc where monhoc_id=?",
                new String[] {String.valueOf(monhocid)} );
        int count =res.getCount();
        res.close();
        return count;
    }
    public ArrayList<ArrayList<String>> thongkemonhoc(int monhocid){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select *from sinhvien where monhoc_id=?",new String[] {String.valueOf(monhocid)} );
        ArrayList<String> TenSv = new ArrayList<>();
        ArrayList<String> MaSv = new ArrayList<>();
        ArrayList<String> Sobuoinghi = new ArrayList<>();
        ArrayList<String> Camthi = new ArrayList<>();
        int tongbuoi = tongbuoihoc(monhocid);

        res.moveToFirst();
        while (res.isAfterLast()==false){
            String masv = res.getString(res.getColumnIndex("maSv"));
            int sobuoinghi= tongbuoi-sobuoinghi(masv,monhocid);
            if (sobuoinghi>tongbuoi*30/100){
                Camthi.add("Cam thi");
            }else {
                Camthi.add("");
            }
            Sobuoinghi.add(String.valueOf(sobuoinghi));
            TenSv.add(res.getString(res.getColumnIndex("nameSv")));
            MaSv.add(masv);

            res.moveToNext();
        }
        ArrayList<ArrayList<String>> arrSinhvien = new ArrayList<ArrayList<String>>();
        arrSinhvien.add(TenSv);
        arrSinhvien.add(MaSv);
        arrSinhvien.add(Sobuoinghi);
        arrSinhvien.add(Camthi);
        return arrSinhvien;
    }


}
