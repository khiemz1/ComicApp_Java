package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.LayTruyenFollow;

import java.io.IOException;

public class ApiXoaFollow extends AsyncTask<Void, Void, Void> {
    String data;
    LayTruyenFollow layTruyenFollow;
    String tentk;
    String idtruyen;
    public ApiXoaFollow(LayTruyenFollow layTruyenFollow, String tentk, String idtruyen){
        this.layTruyenFollow = layTruyenFollow;
        this.tentk = tentk;
        this.idtruyen =idtruyen;
        this.layTruyenFollow.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/xoaFollow.php?tentk=" + tentk+"&idtruyen=" + idtruyen)
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
            this.layTruyenFollow.biLoi();
        }
    }
}