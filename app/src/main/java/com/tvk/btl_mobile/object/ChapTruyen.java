package com.tvk.btl_mobile.object;

import org.json.JSONException;
import org.json.JSONObject;

public class ChapTruyen {
    private String tenChap, ngayDang, id, idTruyen;

    public void ChapTruyen(){

    }

    public ChapTruyen(String tenChap, String ngayDang) {
        this.tenChap = tenChap;
        this.ngayDang = ngayDang;
    }

    public ChapTruyen (JSONObject o) throws JSONException {
        tenChap = o.getString("tenchap");
        ngayDang = o.getString("ngaynhap");
        id = o.getString("id");
        idTruyen = o.getString("idtruyen");
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
