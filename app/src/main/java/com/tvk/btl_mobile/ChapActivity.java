package com.tvk.btl_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tvk.btl_mobile.adapter.BinhLuanAdapter;
import com.tvk.btl_mobile.adapter.ChapTruyenAdapter;
import com.tvk.btl_mobile.api.ApiCapNhapChapDangDoc;
import com.tvk.btl_mobile.api.ApiChapTruyen;
import com.tvk.btl_mobile.api.ApiDangNhap;
import com.tvk.btl_mobile.api.ApiKiemTraChapDangDoc;
import com.tvk.btl_mobile.api.ApiKiemTraFollow;
import com.tvk.btl_mobile.api.ApiLayComment;
import com.tvk.btl_mobile.api.ApiThemComment;
import com.tvk.btl_mobile.api.ApiThemFollow;
import com.tvk.btl_mobile.api.ApiXoaFollow;
import com.tvk.btl_mobile.interfaces.CapNhapChapDangDoc;
import com.tvk.btl_mobile.interfaces.LayChapDangDoc;
import com.tvk.btl_mobile.interfaces.LayChapVe;
import com.tvk.btl_mobile.interfaces.LayComment;
import com.tvk.btl_mobile.interfaces.LayTruyenFollow;
import com.tvk.btl_mobile.interfaces.ThemComment;
import com.tvk.btl_mobile.object.BinhLuan;
import com.tvk.btl_mobile.object.ChapTruyen;
import com.tvk.btl_mobile.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements LayChapVe, LayTruyenFollow, LayComment, ThemComment, LayChapDangDoc, CapNhapChapDangDoc {
    TextView txvTenTruyens, txvTenTacGia, txvTheLoai;
    ImageView imgAnhTruyens;

    TruyenTranh truyenTranh;
    ListView lsvDanhSachChap, lsvDanhSachComment;
    ArrayList<ChapTruyen> arrChap;
    ArrayList<BinhLuan> arrBinhLuan;
    ChapTruyenAdapter chapTruyenAdapter;
    BinhLuanAdapter binhLuanAdapter;
    Button followButton, btnAddComment, readButton;
    EditText edtBinhLuan;
    String idChapDangDoc="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tentk = preferences.getString("logged_in_user", "");
        new ApiKiemTraFollow(this, tentk, truyenTranh.getId()).execute();
        new ApiKiemTraChapDangDoc(this, tentk, truyenTranh.getId()).execute();
        new ApiChapTruyen(this, truyenTranh.getId()).execute();
        new ApiLayComment(this, truyenTranh.getId()).execute();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh =(TruyenTranh) b.getSerializable("truyen");
        arrChap =new ArrayList<>();
        arrBinhLuan = new ArrayList<>();
    };
    private void anhXa(){
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        txvTenTacGia = findViewById(R.id.txvTenTacGia);
        txvTheLoai = findViewById(R.id.txvTheLoai);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
        lsvDanhSachComment = findViewById(R.id.lsvDanhSachComment);
        edtBinhLuan = findViewById(R.id.edtBinhLuan);
        btnAddComment = findViewById(R.id.btnAddComment);
    };
    private void setUp(){
        txvTenTruyens.setText(truyenTranh.getTenTruyen());
        txvTenTacGia.setText(truyenTranh.getTenTacGia());
        txvTheLoai.setText(truyenTranh.getTheLoai());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhTruyens);
    };
    private void setClick(){
        lsvDanhSachChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String tentk = preferences.getString("logged_in_user", "");
                new ApiCapNhapChapDangDoc(ChapActivity.this, tentk, truyenTranh.getId(), arrChap.get(i).getId()).execute();
                Bundle b = new Bundle();
                b.putString("idChap", arrChap.get(i).getId());
                b.putString("idTruyen", arrChap.get(i).getIdTruyen());
                b.putInt("soChap", arrChap.size());
                b.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
                intent.putExtra("data", b);
                finish();
                startActivity(intent);
            }
        });
        followButton = findViewById(R.id.followButton);
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFollowState(followButton);
            }
        });
        readButton = findViewById(R.id.readButton);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleReadingState(readButton);
            }
        });
    };
    private void toggleFollowState(Button followButton) {
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tentk = preferences.getString("logged_in_user", "");
        if (followButton.getText().equals("Theo dõi")) {
            new ApiThemFollow(this,tentk,truyenTranh.getId()).execute();
            followButton.setText("Hủy theo dõi");
            Toast.makeText(ChapActivity.this, "Đã theo dõi", Toast.LENGTH_SHORT).show();
        } else {
            new ApiXoaFollow(this,tentk,truyenTranh.getId()).execute();
            followButton.setText("Theo dõi");
            Toast.makeText(ChapActivity.this, "Đã hủy theo dõi", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleReadingState(Button readButton) {
        if (readButton.getText().equals("Đọc ngay")) {
            SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            String tentk = preferences.getString("logged_in_user", "");
            new ApiCapNhapChapDangDoc(ChapActivity.this, tentk, truyenTranh.getId(), "1").execute();
            Bundle b = new Bundle();
            b.putString("idChap", String.valueOf(1));
            b.putString("idTruyen", truyenTranh.getId());
            b.putInt("soChap", arrChap.size());
            Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
            intent.putExtra("data", b);
            startActivity(intent);
        } else {
            Bundle b = new Bundle();
            b.putString("idChap", idChapDangDoc);
            b.putString("idTruyen", truyenTranh.getId());
            b.putInt("soChap", arrChap.size());
            Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
            intent.putExtra("data", b);
            startActivity(intent);
        }
    }

    @Override
    public void batDau1() {

    }

    @Override
    public void ketThuc1(String data) {
        try {
            arrBinhLuan.clear();
            JSONArray array = new JSONArray(data);
            for (int i=0;i<array.length();i++) {
                BinhLuan binhLuan = new BinhLuan(array.getJSONObject(i));
                arrBinhLuan.add(binhLuan);
            }
            binhLuanAdapter= new BinhLuanAdapter(this, 0, arrBinhLuan);
            lsvDanhSachComment.setAdapter (binhLuanAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi1() {

    }

    @Override
    public void kiemtra(String data) {
        if ("1".equals(data)) {
            followButton.setText("Hủy theo dõi");
        } else  {
            followButton.setText("Theo dõi");
        }
    }

    public void Author(View view) {
        Intent intent = new Intent(this, DsTruyenTheoTenTacGiaActivity.class);
        intent.putExtra("tenTacGia", truyenTranh.getTenTacGia());
        finish();
        startActivity(intent);
    }


    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {
        try {
            JSONArray array = new JSONArray(data);
            for (int i=0;i<array.length();i++) {
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }
            chapTruyenAdapter= new ChapTruyenAdapter(this,0 , arrChap);
            lsvDanhSachChap.setAdapter (chapTruyenAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {

    }

    @Override
    public void batDau2() {

    }

    @Override
    public void ketThuc2(String data) {

    }

    @Override
    public void biLoi2() {

    }

    public void comment(View view) {
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tentk = preferences.getString("logged_in_user", "");
        edtBinhLuan = findViewById(R.id.edtBinhLuan);
        String cmt = edtBinhLuan.getText().toString().trim();
        String idTruyen = truyenTranh.getId();
        if (cmt.isEmpty()) {
            Toast.makeText(ChapActivity.this, "Vui lòng nhập bình luận!", Toast.LENGTH_SHORT).show();
        }
        else {
            new ApiThemComment(this, tentk, idTruyen,cmt).execute();
            edtBinhLuan.setText("");
            new ApiLayComment(this, idTruyen).execute();
        }
    }

    public void back(View view) {
        super.onBackPressed();
        finish();
    }

    @Override
    public void kiemtraChap(String data) {
        if ("0".equals(data)) {
            readButton.setText("Đọc ngay");
        } else  {
            readButton.setText("Đọc tiếp");
            idChapDangDoc = data;
        }
    }

    @Override
    public void loi() {

    }

    @Override
    public void kiemtraChap1(String data) {

    }

    @Override
    public void loi1() {

    }

}