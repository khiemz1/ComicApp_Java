package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.LayTruyenTheoTheLoai;

import java.io.IOException;

public class ApiLayTruyenTheoTheLoai extends AsyncTask<Void, Void, Void> {
    String data;
    LayTruyenTheoTheLoai layTruyenTheoTheLoai;

    String idTruyen;

    public ApiLayTruyenTheoTheLoai(LayTruyenTheoTheLoai layTruyenTheoTheLoai, String idTruyen){
        this.layTruyenTheoTheLoai = layTruyenTheoTheLoai;
        this.idTruyen = idTruyen;
        this.layTruyenTheoTheLoai.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/layTruyenTheoTheLoai.php?idTruyen=" + idTruyen)
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
            this.layTruyenTheoTheLoai.biLoi();
        }else {
            this.layTruyenTheoTheLoai.ketThuc(data);
        }
    }
}