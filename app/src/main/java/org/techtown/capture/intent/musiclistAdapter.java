package org.techtown.capture.intent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class musiclistAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<musiclistVO> data;
    private LayoutInflater inflater;
    // test2

    //생성자
    public musiclistAdapter(Context context, int layout, ArrayList<musiclistVO> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) { return data.get(i);}

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = inflater.inflate(layout, viewGroup, false);

        }

        TextView tvName = view.findViewById(R.id.tvName);
        tvName.setText(data.get(i).getName());

        TextView getTitle = view.findViewById(R.id.tvTitle);
        tvName.setText(data.get(i).getTitle());

        return null;
    }
}
