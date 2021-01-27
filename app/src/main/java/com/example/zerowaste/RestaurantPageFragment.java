package com.example.zerowaste;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RestaurantPageFragment} factory method to
 * create an instance of this fragment.
 */
public class RestaurantPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NOME = "nome";
    private static final String HORARIO = "horario";
    private static final String MORADA = "morada";
    private static final String MAGIC_BOX = "magic_box";
    private static final String IMAGEM = "imagem";
    private static final String PRECO = "preco";
    private static final String TEL = "tel";
    private static final String TAG = "RESTAURANTE FRAGMENT";



    // TODO: Rename and change types of parameters
    private String nome;
    private String horario;
    private String morada;
    private String magic_box;
    private String imagem;
    private String preco;
    private String tel;

    private TextView vnome;
    private TextView vhorario;
    private TextView vmorada;
    private TextView vmagic_box;
    private TextView vpreco;
    private ImageView vimagem;
    private Button orderb;


    public RestaurantPageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_page, container, false);

        Log.d(TAG, "ARGUMENTOS"+ getArguments());
        if (getArguments() != null) {
            nome = getArguments().getString(NOME);
            horario = getArguments().getString(HORARIO);
            morada = getArguments().getString(MORADA);
            preco = getArguments().getString(PRECO);
            magic_box = getArguments().getString(MAGIC_BOX);
            imagem = getArguments().getString(IMAGEM);
            tel    = getArguments().getString(TEL);
        }

        vnome = (TextView) rootView.findViewById(R.id.page_name);
        vmorada = (TextView) rootView.findViewById(R.id.page_address);
        vhorario = (TextView) rootView.findViewById(R.id.page_schedule);
        vmagic_box = (TextView) rootView.findViewById(R.id.page_magic_box);
        vpreco = (TextView) rootView.findViewById(R.id.page_preco);
        vimagem = (ImageView) rootView.findViewById(R.id.page_image);
        orderb = (Button) rootView.findViewById(R.id.order);

        Picasso.with(getContext()).load(imagem).into(vimagem);
        vnome.setText(nome);
        vmorada.setText(morada);
        vhorario.setText("Horário:"+horario);
        vmagic_box.setText("Categoria: "+magic_box);
        vpreco.setText("Preço: " +preco);


        orderb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment TrayFragment = new TrayFragment();
                Bundle bundle = new Bundle();
                bundle.putString(MAGIC_BOX, magic_box);
                bundle.putString(PRECO,preco);
                bundle.putString(MORADA,morada);
                bundle.putString(TEL,tel);
                TrayFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content1_frame,TrayFragment).
                        commit();


            }
        });



        // Inflate the layout for this fragment
       return rootView;
    }
}