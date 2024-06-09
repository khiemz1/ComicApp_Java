package com.tvk.btl_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.tvk.btl_mobile.adapter.TheLoaiAdapter;
import com.tvk.btl_mobile.adapter.TruyenTranhAdapter;
import com.tvk.btl_mobile.api.ApiLayTheLoai;
import com.tvk.btl_mobile.api.ApiLayTruyen;
import com.tvk.btl_mobile.interfaces.LayTheLoai;
import com.tvk.btl_mobile.object.TheLoai;
import com.tvk.btl_mobile.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TheLoaiActivity extends AppCompatActivity implements LayTheLoai {
    GridView gdvDSTheLoai;
    TheLoaiAdapter adapter;
    ArrayList<TheLoai> theLoaiArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTheLoai(this).execute();
    }
    private void init(){
        theLoaiArrayList = new ArrayList<>();
        adapter= new TheLoaiAdapter(this, 0, theLoaiArrayList);
    }
    private void anhXa(){
        gdvDSTheLoai = findViewById(R.id.gdvDSTheLoai);
    }
    private void setUp(){
        gdvDSTheLoai.setAdapter(adapter);
    }
    private void setClick(){
        gdvDSTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Bundle b = new Bundle();
                b.putString("tenTheLoai", theLoaiArrayList.get(i).getTenTheLoai());
                b.putString("idTruyen", theLoaiArrayList.get(i).getIdTruyen());
                Intent intent = new Intent(TheLoaiActivity.this, DsTruyenTheoTheLoai.class);
                intent.putExtra("data1", b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {
        try {
            theLoaiArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0;i<arr.length();i++) {
                JSONObject o = arr.getJSONObject(i);
                theLoaiArrayList.add(new TheLoai(o));
            }
            adapter= new TheLoaiAdapter(this, 0, theLoaiArrayList);
            gdvDSTheLoai.setAdapter(adapter);
        } catch (JSONException e) {

        }
    }

    @Override
    public void biLoi() {

    }

    public void follow(View view) {
        Intent intent = new Intent(TheLoaiActivity.this, DsTruyenFollowActivity.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(TheLoaiActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void user(View view) {
        Intent intent = new Intent(TheLoaiActivity.this, UserActivity.class);
        startActivity(intent);
    }
}