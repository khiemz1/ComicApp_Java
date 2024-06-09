package com.tvk.btl_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tvk.btl_mobile.api.ApiDangNhap;

public class UserActivity extends AppCompatActivity {
    TextView txvtentk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String tentk = preferences.getString("logged_in_user", "");
        txvtentk = findViewById(R.id.txvTentaikhoan);
        txvtentk.setText(tentk);

    }

    public void infor(View view) {
        Intent intent = new Intent(UserActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void changePass(View view) {
        Intent intent = new Intent(UserActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?");

        // Nút Ok
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Nút Hủy
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng dialog
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void home(View view) {
        Intent intent = new Intent(UserActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void follow(View view) {
        Intent intent = new Intent(UserActivity.this, DsTruyenFollowActivity.class);
        finish();
        startActivity(intent);
    }

    public void theLoai(View view) {
        Intent intent = new Intent(UserActivity.this, TheLoaiActivity.class);
        finish();
        startActivity(intent);
    }
}