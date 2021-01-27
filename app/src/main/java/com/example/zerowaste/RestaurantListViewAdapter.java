package com.example.zerowaste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RestaurantListViewAdapter extends ArrayAdapter<Restaurant> {
    private static final String NOME = "nome";
    private static final String HORARIO = "horario";
    private static final String MORADA = "morada";
    private static final String MAGIC_BOX = "magic_box";
    private static final String PRECO = "preco";
    private static final String IMAGEM = "imagem";
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
        holder.imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment RestaurantPageFragment = new RestaurantPageFragment();
                Bundle bundle = new Bundle();
                bundle.putString(NOME,r.getNome());
                bundle.putString(HORARIO,r.getHorario());
                bundle.putString(MORADA,r.getMorada());
                bundle.putString(MAGIC_BOX,r.getMagic_box());
                bundle.putString(PRECO,r.getPreco());
                bundle.putString(IMAGEM,r.getImagem());
                RestaurantPageFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content1_frame,RestaurantPageFragment).
                        addToBackStack(null).commit();
                /*
                Log.d(TAG,"cliquei");
                Fragment RestaurantPageFragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString(NOME,r.getNome());
                bundle.putString(HORARIO,r.getHorario());
                bundle.putString(MORADA,r.getMorada());
                bundle.putString(MAGIC_BOX,r.getMagic_box());
                Log.d(TAG,"BUNDLE A ENVIAR"+bundle);
               // RestaurantPageFragment.setArguments(bundle);
                RestaurantPageFragment.getArguments();
                Log.d(TAG,"ARGS"+RestaurantPageFragment.getArguments());
                Intent intent = new Intent(context , RestaurantPage.class);
                context.startActivity(intent); // if needed, add myContext before starting myContext.startActivity...

                *//*if (getContext() instanceof FragmentActivity) {
                    // We can get the fragment manager
                    FragmentTransaction t = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                    t.add(R.id.content1_frame, RestaurantPageFragment).commit();
                }*/
         }
    });

        return convertView;

    }

}
