package com.example.zerowaste;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrayFragmentFinal}  method to
 * create an instance of this fragment.
 */
public class TrayFragmentFinal extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MORADA = "morada";
    private static final String MAGIC_BOX = "magic_box";
    private static final String PRECO = "preco";
    private static final String TEL = "tel";
    private static final String TAG = "TRAY" ;
    private static final int REQUEST_PHONE_CALL = 1;

    // TODO: Rename and change types of parameters
    private String morada;
    private String magic_box;
    private String preco;
    private String tel;

    private TextView vMorada;
    private TextView vMagic_box;
    private TextView vPreco;
    private Button chamada;


    public TrayFragmentFinal() {
        // Required empty public constructor
    }
    public static TrayFragmentFinal newInstance(String magic_box, String preco, String morada, String tel){
        TrayFragmentFinal trayFragmentFinal = new TrayFragmentFinal();
        Bundle args = new Bundle();
        args.putString(MAGIC_BOX, magic_box);
        args.putString(PRECO, preco);
        args.putString(MORADA,morada);
        args.putString(TEL,tel);
        trayFragmentFinal.setArguments(args);
        return trayFragmentFinal;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tray_complete, container, false);

        vMagic_box = (TextView) rootView.findViewById(R.id.tray_box_final);
        vMorada    = (TextView) rootView.findViewById(R.id.tray_address_final);
        vPreco     = (TextView) rootView.findViewById(R.id.tray_price_final_final);
        chamada    = (Button) rootView.findViewById(R.id.tray_call);

        if (getArguments() != null) {
            magic_box = getArguments().getString(MAGIC_BOX);
            morada    = getArguments().getString(MORADA);
            preco     = getArguments().getString(PRECO);
            tel       = getArguments().getString(TEL);

        }



        vMagic_box.setText("Categoria:" + magic_box);
        vMorada.setText(morada);
        vPreco.setText(preco);

        chamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ tel));


                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    getActivity().startActivity(callIntent);
                }

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }


}