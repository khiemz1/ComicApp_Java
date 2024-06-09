package com.tvk.btl_mobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tvk.btl_mobile.adapter.TruyenTranhAdapter;
import com.tvk.btl_mobile.api.ApiLayTruyen;
import com.tvk.btl_mobile.api.ApiLayTruyenFollow;
import com.tvk.btl_mobile.interfaces.LayTruyenFollow;
import com.tvk.btl_mobile.interfaces.LayTruyenVe;
import com.tvk.btl_mobile.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DsTruyenFollowActivity extends AppCompatActivity implements LayTruyenFollow {
    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList <TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_truyen_follow);
        init();
        anhXa();
        setUp();
        setClick();
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tentk = preferences.getString("logged_in_user", "");
        new ApiLayTruyenFollow(this, tentk).execute();
    }
    private void init(){
        truyenTranhArrayList = new ArrayList<>();
        adapter= new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }
    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
    }
    private void setUp(){
        gdvDSTruyen.setAdapter(adapter);
    }
    private void setClick(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s=edtTimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                TruyenTranh truyenTranh = truyenTranhArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(DsTruyenFollowActivity.this, ChapActivity.class);
                intent.putExtra("data", b);
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
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0;i<arr.length();i++) {
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter= new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
            gdvDSTruyen.setAdapter(adapter);
        } catch (JSONException e) {

        }
    }

    @Override
    public void biLoi() {

    }

    @Override
    public void kiemtra(String data) {

    }

    public void refresh(View view) {
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tentk = preferences.getString("logged_in_user", "");
        new ApiLayTruyenFollow(this, tentk).execute();
    }

    public void home(View view) {
        Intent intent = new Intent(DsTruyenFollowActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void theLoai(View view) {
        Intent intent = new Intent(DsTruyenFollowActivity.this, TheLoaiActivity.class);
        finish();
        startActivity(intent);
    }

    public void user(View view) {
        Intent intent = new Intent(DsTruyenFollowActivity.this, UserActivity.class);
        finish();
        startActivity(intent);
    }
}