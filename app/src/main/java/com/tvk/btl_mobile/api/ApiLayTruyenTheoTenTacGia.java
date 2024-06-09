package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.DsTruyenTheoTenTacGiaActivity;
import com.tvk.btl_mobile.interfaces.LayTruyenTheoTenTacGia;

import java.io.IOException;

public class ApiLayTruyenTheoTenTacGia extends AsyncTask<Void, Void, Void> {
    String data;
    LayTruyenTheoTenTacGia layTruyenTheoTenTacGia;

    String tenTacGia;

    public ApiLayTruyenTheoTenTacGia(DsTruyenTheoTenTacGiaActivity layTruyenTheoTenTacGia, String tenTacGia){
        this.layTruyenTheoTenTacGia = layTruyenTheoTenTacGia;
        this.tenTacGia = tenTacGia;
        this.layTruyenTheoTenTacGia.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/layTruyenTheoTenTacGia.php?tentg=" + tenTacGia)
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
            this.layTruyenTheoTenTacGia.biLoi();
        }else {
            this.layTruyenTheoTenTacGia.ketThuc(data);
        }
    }
}