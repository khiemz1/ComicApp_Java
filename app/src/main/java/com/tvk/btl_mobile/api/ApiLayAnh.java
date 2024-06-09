package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.LayAnhVe;
import com.tvk.btl_mobile.interfaces.LayTruyenVe;

import java.io.IOException;

public class ApiLayAnh extends AsyncTask<Void, Void, Void> {
    String data;
    String idChap, idTruyen;
    LayAnhVe layAnhVe;

    public ApiLayAnh(LayAnhVe layAnhVe, String idChap, String idTruyen){
        this.layAnhVe = layAnhVe;
        this.idChap = idChap;
        this.idTruyen = idTruyen;
        this.layAnhVe.batDau();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/layAnh.php?idChap=" + idChap+"&idTruyen="+idTruyen)
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
            this.layAnhVe.biLoi();
        }else {
            this.layAnhVe.ketThuc(data);
        }
    }
}