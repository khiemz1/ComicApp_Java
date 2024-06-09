package com.tvk.btl_mobile.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class TheLoai implements Serializable {
    private String idTheLoai, tenTheLoai, idTruyen;

    public TheLoai() {

    }
    public TheLoai (JSONObject o) throws JSONException {
        idTheLoai = o.getString("idTheLoai");
        tenTheLoai = o.getString("tenTheLoai");
        idTruyen = o.getString("idTruyen");
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }
}
