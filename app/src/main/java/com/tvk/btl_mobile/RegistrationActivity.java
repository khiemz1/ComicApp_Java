package com.tvk.btl_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.tvk.btl_mobile.adapter.TruyenTranhAdapter;
import com.tvk.btl_mobile.api.ApiLayTruyen;
import com.tvk.btl_mobile.api.ApiTaoTK;
import com.tvk.btl_mobile.interfaces.TaoTaiKhoan;
import com.tvk.btl_mobile.object.TruyenTranh;
import com.tvk.btl_mobile.object.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity implements TaoTaiKhoan {

    EditText edtname, edtemail, edtpassword;
    Button register;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
    }
    private void anhXa(){

        edtname = findViewById(R.id.edtname);
        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
    }
    private void setUp(){
    }
    private void setClick(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentk = edtname.getText().toString().trim();
                String mk = edtpassword.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                if (tentk.isEmpty() || mk.isEmpty() || email.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                new ApiTaoTK(RegistrationActivity.this, tentk, mk, email).execute();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển hướng đến màn hình đăng nhập
                 Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                finish();
                 startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(RegistrationActivity.this, "Dang Login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        if ("1".equals(data)) {
            Toast.makeText(RegistrationActivity.this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            edtname.setText("");
            edtemail.setText("");
            edtpassword.setText("");
        } else if ("2".equals(data)) {
            Toast.makeText(RegistrationActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            finish();
            startActivity(intent);
        } else if("3".equals(data))  {
            Toast.makeText(RegistrationActivity.this, "Đã xảy ra lỗi khi tạo tài khoản", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(RegistrationActivity.this, "Da xay ra loi", Toast.LENGTH_SHORT).show();
    }
}