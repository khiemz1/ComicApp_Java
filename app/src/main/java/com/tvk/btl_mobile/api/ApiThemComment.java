package com.tvk.btl_mobile.api;

import android.os.AsyncTask;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.ThemComment;

import java.io.IOException;

public class ApiThemComment extends AsyncTask<Void, Void, Void> {
    String data;
    ThemComment themComment;
    String tentk, idTruyen, cmt;
    public ApiThemComment(ThemComment themComment, String tentk, String idTruyen, String cmt){
        this.tentk = tentk;
        this.idTruyen =idTruyen;
        this.cmt = cmt;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://tvkz1.000webhostapp.com/themComment.php?tentk=" + tentk+"&idTruyen=" + idTruyen +"&cmt=" + cmt)
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

}