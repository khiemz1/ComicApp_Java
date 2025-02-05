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
import com.tvk.btl_mobile.object.ChapTruyen;

import java.util.ArrayList;
import java.util.List;

public class ChapTruyenAdapter extends ArrayAdapter<ChapTruyen> {
    private Context ct;
    private ArrayList <ChapTruyen> arr;
    public ChapTruyenAdapter (Context context, int resource, List<ChapTruyen> objects) {
        super(context,resource,objects);
        this.ct = context;
        this.arr = new ArrayList<>((objects));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chap_truyen,null);

        }
        if (arr.size()>0) {
            TextView txvTenChaps, txvNgayNhap;
            txvTenChaps = convertView.findViewById(R.id.txvTenChaps);
            txvNgayNhap = convertView.findViewById(R.id.txvNgayNhap);

            ChapTruyen chapTruyen = arr.get(position);
            txvTenChaps.setText(chapTruyen.getTenChap());
            txvNgayNhap.setText(chapTruyen.getNgayDang());

        }
        return convertView;
    }
}
