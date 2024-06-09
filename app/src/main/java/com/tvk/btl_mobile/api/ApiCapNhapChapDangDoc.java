package com.tvk.btl_mobile.api;

import android.os.AsyncTask;
import android.widget.AdapterView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.tvk.btl_mobile.interfaces.CapNhapChapDangDoc;
import com.tvk.btl_mobile.interfaces.LayChapDangDoc;

import java.io.IOException;

public class ApiCapNhapChapDangDoc extends AsyncTask<Void, Void, Void> {
    String data;
    CapNhapChapDangDoc capNhapChapDangDoc;
    String tentk;
    String idTruyen, idChap;
    public ApiCapNhapChapDangDoc(CapNhapChapDangDoc capNhapChapDangDoc, String tentk, String idTruyen, String idChap){
        this.capNhapChapDangDoc = capNhapChapDangDoc;;
        this.tentk = tentk;
        this.idTruyen =idTruyen;
        this.idChap = idChap;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://tvkz1.000webhostapp.com/capNhapChapDangDoc.php?idTruyen=" + idTruyen+"&tentk=" + tentk +"&idChap="+idChap )
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
            this.capNhapChapDangDoc.loi1();
        }
    }
}