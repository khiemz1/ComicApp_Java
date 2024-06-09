package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.LayTruyenFollow;
import com.tvk.btl_mobile.interfaces.LayTruyenVe;

import java.io.IOException;

public class ApiLayTruyenFollow extends AsyncTask<Void, Void, Void> {
    String data;
    LayTruyenFollow layTruyenFollow;
    String tentk;
    public ApiLayTruyenFollow(LayTruyenFollow layTruyenFollow, String tentk){
        this.layTruyenFollow = layTruyenFollow;
        this.tentk = tentk;
        this.layTruyenFollow.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/layTruyenFollow.php?tentk=" + tentk)
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
        }else {
            this.layTruyenFollow.ketThuc(data);
        }
    }
}