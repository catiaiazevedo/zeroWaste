package com.example.zerowaste;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RestaurantListViewAdapter extends ArrayAdapter<Restaurant> {
    private static final String TAG = "ADAPTER" ;
    private Context context;

    public RestaurantListViewAdapter(@NonNull Context context, int resource, List<Restaurant> items) {
        super(context, resource, items);
        this.context = context;
        Log.d(TAG, "OU TOU AQUI");
    }
    private class ViewHolder{
        TextView nome;
        TextView morada;
        ImageView imagem;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Restaurant r = getItem(position);
        LayoutInflater mInflater  = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_restaurant,null);
            holder = new ViewHolder();
            holder.nome = (TextView) convertView.findViewById(R.id.r_name);
            holder.morada = (TextView) convertView.findViewById(R.id.r_address);
            holder.imagem = (ImageView) convertView.findViewById(R.id.r_image);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nome.setText(r.getNome());
        holder.morada.setText(r.getMorada());
        Picasso.with(context).load(r.getImagem()).into(holder.imagem);
        return convertView;

    }
}
