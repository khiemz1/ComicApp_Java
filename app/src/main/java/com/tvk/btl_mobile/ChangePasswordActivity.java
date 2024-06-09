package com.tvk.btl_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tvk.btl_mobile.api.ApiDangNhap;
import com.tvk.btl_mobile.api.ApiDoiMatKhau;
import com.tvk.btl_mobile.interfaces.DangNhap;

public class ChangePasswordActivity extends AppCompatActivity implements DangNhap {
    EditText edttentk, edtmk, edtconfirm, edtnewPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edttentk = findViewById(R.id.edtTentk);
        edtmk = findViewById(R.id.edtPass1);
        edtconfirm = findViewById(R.id.edtPass2);
        edtnewPass = findViewById(R.id.edtNewPass);
    }

    public void change(View view) {
        String tentk = edttentk.getText().toString().trim();
        String mk = edtmk.getText().toString().trim();
        String mk2 = edtconfirm.getText().toString().trim();
        String mk3 = edtnewPass.getText().toString().trim();
        if (tentk.isEmpty() || mk.isEmpty() || mk2.isEmpty() || mk3.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else if (!mk2.equals(mk3)){
            Toast.makeText(this, "Vui kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
            edtmk.setText("");
            edtconfirm.setText("");
        }
        else {
            showConfirmationDialog();
        }


    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn đổi mật khẩu không?");

        // Nút Ok
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xác nhận thay đổi mật khẩu
                String tentk = edttentk.getText().toString().trim();
                String mk = edtmk.getText().toString().trim();
                new ApiDangNhap(ChangePasswordActivity.this, tentk, mk).execute();
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
    public void back(View view) {
        super.onBackPressed();
        finish();
    }

    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {
        // Xử lý kết quả từ API
        if ("1".equals(data)) {
            String tentk = edttentk.getText().toString().trim();
            String mk3 = edtnewPass.getText().toString().trim();
            new ApiDoiMatKhau(this, tentk, mk3).execute();
            Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UserActivity.class);
            finish();
            startActivity(intent);
        } else if ("2".equals(data)) {
            Toast.makeText(this, "Password khong dung", Toast.LENGTH_SHORT).show();
            edtmk.setText("");
            edtconfirm.setText("");
            edtnewPass.setText("");
        } else if ("3".equals(data)) {
            Toast.makeText(this, "Khong ton tai username ban da nhap", Toast.LENGTH_SHORT).show();
            edttentk.setText("");
            edtmk.setText("");
            edtconfirm.setText("");
            edtnewPass.setText("");
        }

    }

    @Override
    public void biLoi() {

    }
}