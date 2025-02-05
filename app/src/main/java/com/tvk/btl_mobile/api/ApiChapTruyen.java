package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.LayChapVe;
import com.tvk.btl_mobile.interfaces.LayTruyenVe;

import java.io.IOException;

public class ApiChapTruyen extends AsyncTask<Void, Void, Void> {
    String data;
    LayChapVe layChapVe;
    String idTruyen;

    public ApiChapTruyen(LayChapVe layChapVe, String idTruyen){
        this.layChapVe = layChapVe;
        this.layChapVe.batDau();
        this.idTruyen = idTruyen;

    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/layChap.php?id=" + idTruyen)
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
            this.layChapVe.biLoi();
        }else {
            this.layChapVe.ketThuc(data);
        }
    }
}