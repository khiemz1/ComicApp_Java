package com.tvk.btl_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tvk.btl_mobile.R;
import com.tvk.btl_mobile.object.BinhLuan;
import com.tvk.btl_mobile.object.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class BinhLuanAdapter extends ArrayAdapter<BinhLuan>{
    private Context ct;
    private ArrayList<BinhLuan> arr;

    public BinhLuanAdapter(@NonNull Context context, int resource, @NonNull List<BinhLuan> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView ==null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cmt, null);
        }

        if (arr.size()>0) {
            BinhLuan binhLuan= this.arr.get(position);
            TextView tenTaiKhoan = convertView.findViewById(R.id.txvTenTaiKhoan);
            TextView cmt = convertView.findViewById(R.id.txvComment);
            TextView ngayGio = convertView.findViewById(R.id.txvngayGio);

            tenTaiKhoan.setText(binhLuan.getTentk());
            cmt.setText(binhLuan.getCmt());
            ngayGio.setText(binhLuan.getNgayGio());
        }
        return convertView;
    }
}
