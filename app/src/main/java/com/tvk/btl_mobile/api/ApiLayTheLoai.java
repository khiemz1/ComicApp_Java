package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.LayTheLoai;
import com.tvk.btl_mobile.interfaces.LayTruyenVe;

import java.io.IOException;

public class ApiLayTheLoai extends AsyncTask<Void, Void, Void> {
    String data;
    LayTheLoai layTheLoai;

    public ApiLayTheLoai(LayTheLoai layTheLoai){
        this.layTheLoai = layTheLoai;
        this.layTheLoai.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/layTheLoai.php")
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
            this.layTheLoai.biLoi();
        }else {
            this.layTheLoai.ketThuc(data);
        }
    }
}