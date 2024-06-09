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
import android.widget.TextView;
import android.widget.Toast;

import com.tvk.btl_mobile.adapter.TruyenTranhAdapter;
import com.tvk.btl_mobile.api.ApiLayTruyenTheoTheLoai;
import com.tvk.btl_mobile.interfaces.LayTruyenTheoTheLoai;
import com.tvk.btl_mobile.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DsTruyenTheoTheLoai extends AppCompatActivity implements LayTruyenTheoTheLoai {
    GridView gdvDSTruyen;
    TextView txvTenTheLoais;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;
    String tenTheLoai, idTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_truyen_theo_the_loai);
        init();
        anhXa();
        setUp();
        setClick();
        txvTenTheLoais.setText("Thể loại: " + tenTheLoai);
        new ApiLayTruyenTheoTheLoai(this, idTruyen).execute();
    }
    private void init(){
        truyenTranhArrayList = new ArrayList<>();
        adapter= new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
        Bundle b = getIntent().getBundleExtra("data1");
        tenTheLoai = b.getString("tenTheLoai");
        idTruyen = b.getString("idTruyen");
    }
    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        txvTenTheLoais = findViewById(R.id.txvTenTheLoais);
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
                Intent intent = new Intent(DsTruyenTheoTheLoai.this, ChapActivity.class);
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
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();

    }

    public void refresh(View view) {
        new ApiLayTruyenTheoTheLoai(this, idTruyen).execute();
    }

    public void home(View view) {
        Intent intent = new Intent(DsTruyenTheoTheLoai.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void follow(View view) {
        Intent intent = new Intent(DsTruyenTheoTheLoai.this, DsTruyenFollowActivity.class);
        finish();
        startActivity(intent);
    }

    public void theLoai(View view) {
        Intent intent = new Intent(DsTruyenTheoTheLoai.this, TheLoaiActivity.class);
        finish();
        startActivity(intent);
    }

    public void user(View view) {
        Intent intent = new Intent(DsTruyenTheoTheLoai.this, UserActivity.class);
        finish();
        startActivity(intent);
    }
}