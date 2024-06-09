package com.tvk.btl_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.tvk.btl_mobile.R;
import com.tvk.btl_mobile.object.TheLoai;
import com.tvk.btl_mobile.object.TruyenTranh;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiAdapter extends ArrayAdapter<TheLoai>{
    private Context ct;
    private ArrayList<TheLoai> arr;

    public TheLoaiAdapter(@NonNull Context context, int resource, @NonNull List<TheLoai> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView ==null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_the_loai, null);
        }

        if (arr.size()>0) {
            TheLoai theLoai= this.arr.get(position);
            TextView tenTheLoai = convertView.findViewById(R.id.txvTenTheLoai);

            tenTheLoai.setText(theLoai.getTenTheLoai());
        }
        return convertView;
    }
}
