package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.DangNhap;
import com.tvk.btl_mobile.interfaces.TaoTaiKhoan;

import java.io.IOException;

public class ApiTaoTK extends AsyncTask<Void, Void, Void> {
    String data;
    String tentk, mk, email;
    TaoTaiKhoan taoTaiKhoan;

    public ApiTaoTK(TaoTaiKhoan taoTaiKhoan, String tentk, String mk, String email){
        this.taoTaiKhoan = taoTaiKhoan;
        this.tentk = tentk;
        this.mk = mk;
        this.email = email;
        this.taoTaiKhoan.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/taoTK.php?tentk=" + tentk + "&mk="+ mk + "&email=" + email)
                .build();
        data=null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        } catch (IOException e) {
            data=null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(data == null) {
            this.taoTaiKhoan.biLoi();
        }else {
            this.taoTaiKhoan.ketThuc(data);
        }
    }
}