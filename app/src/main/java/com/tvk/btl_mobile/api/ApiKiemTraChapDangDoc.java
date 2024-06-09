package com.tvk.btl_mobile.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.LayChapDangDoc;
import com.tvk.btl_mobile.interfaces.LayTruyenFollow;

import java.io.IOException;

public class ApiKiemTraChapDangDoc extends AsyncTask<Void, Void, Void> {
    String data;
    LayChapDangDoc layChapDangDoc;
    String tentk;
    String idTruyen;
    public ApiKiemTraChapDangDoc(LayChapDangDoc layChapDangDoc, String tentk, String idTruyen){
        this.layChapDangDoc = layChapDangDoc;;
        this.tentk = tentk;
        this.idTruyen =idTruyen;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://tvkz1.000webhostapp.com/kiemTraChapDangDoc.php?idTruyen=" + idTruyen+"&tentk=" + tentk)
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
            this.layChapDangDoc.loi();
        }else {
            this.layChapDangDoc.kiemtraChap(data);
        }
    }
}