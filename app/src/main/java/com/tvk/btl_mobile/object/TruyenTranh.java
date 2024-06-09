package com.tvk.btl_mobile.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class TruyenTranh implements Serializable {
    private String tenTruyen, tenChap, LinkAnh, id, tenTacGia, theLoai;

    public TruyenTranh() {

    }

    public TruyenTranh (JSONObject o) throws JSONException {
        id = o.getString("id");
        tenTruyen = o.getString("tenTruyen");
        tenChap = o.getString("tenChap");
        LinkAnh = o.getString("linkAnh");
        tenTacGia = o.getString("tenTacGia");
        theLoai = o.getString("theLoai");
    }

    public TruyenTranh(String tenTruyen, String tenChap, String linkAnh, String tenTacGia, String theLoai) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.LinkAnh = linkAnh;
        this.tenTacGia = tenTacGia;
        this.theLoai = theLoai;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.LinkAnh = linkAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }
}
