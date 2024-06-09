package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.DangNhap;
import com.tvk.btl_mobile.interfaces.TaoTaiKhoan;

import java.io.IOException;

public class ApiDangNhap extends AsyncTask<Void, Void, Void> {
    String data;
    String tentk, mk;
    DangNhap dangNhap;

    public ApiDangNhap(DangNhap dangNhap, String tentk, String mk){
        this.dangNhap = dangNhap;
        this.tentk = tentk;
        this.mk = mk;
        this.dangNhap.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/dangNhap.php?tentk=" + tentk + "&mk="+ mk)
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
            this.dangNhap.biLoi();
        }else {
            this.dangNhap.ketThuc(data);
        }
    }
}