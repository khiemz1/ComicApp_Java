package com.tvk.btl_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tvk.btl_mobile.api.ApiDangNhap;
import com.tvk.btl_mobile.api.ApiTaoTK;
import com.tvk.btl_mobile.interfaces.DangNhap;

public class LoginActivity extends AppCompatActivity implements DangNhap {
    EditText tentk, password;
    Button login;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        anhXa();
        setUp();
        setClick();
    }

    private void init() {
    }

    private void anhXa() {
        tentk = findViewById(R.id.tentk);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
    }

    private void setUp() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ người dùng
                String tentk1 = tentk.getText().toString().trim();
                String mk = password.getText().toString().trim();

                // Kiểm tra tính hợp lệ của thông tin đăng nhập (có thể thêm kiểm tra khác nếu cần)
                if (tentk1.isEmpty() || mk.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi API đăng nhập
                new ApiDangNhap(LoginActivity.this, tentk1, mk).execute();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển hướng đến màn hình đăng ký
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void setClick() {

    }

    @Override
    public void batDau() {
        Toast.makeText(LoginActivity.this, "Dang Login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        // Xử lý kết quả từ API
        if ("1".equals(data)) {
            Toast.makeText(LoginActivity.this, "Login thanh cong", Toast.LENGTH_SHORT).show();
            saveLoggedInUser(tentk.getText().toString().trim());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
        } else if ("2".equals(data)) {
            // Mật khẩu không đúng, thông báo cho người dùng
            Toast.makeText(LoginActivity.this, "Password khong dung", Toast.LENGTH_SHORT).show();
        } else if ("3".equals(data)) {
            // Tài khoản không tồn tại, thông báo cho người dùng
            Toast.makeText(LoginActivity.this, "Khong ton tai username ban da nhap", Toast.LENGTH_SHORT).show();
        }

    }
    private void saveLoggedInUser(String userName) {
        // Lưu tên tài khoản vào SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("logged_in_user", userName);
        editor.apply();
    }

    @Override
    public void biLoi() {
        Toast.makeText(LoginActivity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
    }
}