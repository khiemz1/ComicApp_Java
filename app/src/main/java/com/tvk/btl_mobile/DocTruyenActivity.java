package com.tvk.btl_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tvk.btl_mobile.adapter.TruyenTranhAdapter;
import com.tvk.btl_mobile.api.ApiCapNhapChapDangDoc;
import com.tvk.btl_mobile.api.ApiLayAnh;
import com.tvk.btl_mobile.api.ApiLayTruyen;
import com.tvk.btl_mobile.interfaces.CapNhapChapDangDoc;
import com.tvk.btl_mobile.interfaces.LayAnhVe;
import com.tvk.btl_mobile.interfaces.LayChapDangDoc;
import com.tvk.btl_mobile.object.ChapTruyen;
import com.tvk.btl_mobile.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhVe, CapNhapChapDangDoc {
    ImageView imgAnh;
    ArrayList<String> arrUrlAnh;
    int soTrang, soTrangDangDoc;
    TextView txvSoTrang;
    String idChap, idTruyen;
    int soChap;
    TruyenTranh truyenTranh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayAnh(this, idChap, idTruyen).execute();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        idChap = b.getString("idChap");
        idTruyen = b.getString("idTruyen");
        soChap = b.getInt("soChap");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");
    }
    private void anhXa(){
        imgAnh =findViewById(R.id.imgAnh);
        txvSoTrang =findViewById(R.id.txvSoTrang);
    }
    private void setUp(){
//        docTheoTrang(0);
    }
    private void setClick(){

    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    public void right(View view) {
        docTheoTrang(1);
    }

    private void docTheoTrang(int i) {
        soTrangDangDoc = soTrangDangDoc+i;
        if(soTrangDangDoc==0) {
            soTrangDangDoc=1;
        }
        if(soTrangDangDoc>soTrang) {
            soTrangDangDoc=soTrang;
        }
        txvSoTrang.setText(soTrangDangDoc+ "/" + soTrang);
        Glide.with(this).load(arrUrlAnh.get(soTrangDangDoc-1)).into(imgAnh);
    }

    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {
        arrUrlAnh = new ArrayList<>();
        try{
            JSONArray arr = new JSONArray(data);
            for (int i=0;i<arr.length();i++) {
                arrUrlAnh.add(arr.getString(i));
            }
            soTrangDangDoc=1;
            soTrang=arrUrlAnh.size();
            docTheoTrang(0);
        }catch (JSONException e) {

        }

    }

    @Override
    public void biLoi() {

    }

    public void previous(View view) {
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tentk = preferences.getString("logged_in_user", "");
        int idInt = Integer.parseInt(idChap);
        if (idInt>1) {
            idInt = idInt-1;
            idChap = String.valueOf(idInt);
            new ApiLayAnh(this, idChap, idTruyen).execute();
            new ApiCapNhapChapDangDoc(this, tentk, idTruyen, idChap).execute();
        }
        else {
            Toast.makeText(DocTruyenActivity.this, "Đây là chapter đầu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void next(View view) {
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tentk = preferences.getString("logged_in_user", "");
        int idInt = Integer.parseInt(idChap);
        if(idInt < soChap) {
            idInt = idInt+1;
            idChap = String.valueOf(idInt);
            new ApiLayAnh(this, idChap, idTruyen).execute();
            new ApiCapNhapChapDangDoc(this, tentk, idTruyen, idChap).execute();
        }
        else {
            Toast.makeText(DocTruyenActivity.this, "Đây là chapter mới nhất!", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        Bundle b = new Bundle();
        b.putSerializable("truyen", truyenTranh);
        Intent intent = new Intent(DocTruyenActivity.this, ChapActivity.class);
        intent.putExtra("data", b);
        finish();
        startActivity(intent);
//        super.onBackPressed();

    }

    @Override
    public void kiemtraChap1(String data) {

    }

    @Override
    public void loi1() {

    }
}