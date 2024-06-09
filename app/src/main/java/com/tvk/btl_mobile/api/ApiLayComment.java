package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.LayComment;
import com.tvk.btl_mobile.interfaces.LayTruyenFollow;

import java.io.IOException;

public class ApiLayComment extends AsyncTask<Void, Void, Void> {
    String data;
    LayComment layComment;
    String idTruyen;
    public ApiLayComment(LayComment layComment, String idTruyen){
        this.layComment = layComment;
        this.idTruyen = idTruyen;
        this.layComment.batDau1();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/layCmt.php?idTruyen=" + idTruyen)
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
            this.layComment.biLoi1();
        }else {
            this.layComment.ketThuc1(data);
        }
    }
}