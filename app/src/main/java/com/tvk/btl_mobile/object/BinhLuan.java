package com.tvk.btl_mobile.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class BinhLuan implements Serializable {
    private String idTruyen, tentk, cmt, ngayGio;

    public BinhLuan () {

    }
    public BinhLuan (JSONObject o) throws JSONException {
        idTruyen = o.getString("idTruyen");
        tentk = o.getString("tentk");
        cmt = o.getString("cmt");
        ngayGio = o.getString("ngayGio");
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getNgayGio() {
        return ngayGio;
    }

    public void setNgayGio(String ngayGio) {
        this.ngayGio = ngayGio;
    }
}
