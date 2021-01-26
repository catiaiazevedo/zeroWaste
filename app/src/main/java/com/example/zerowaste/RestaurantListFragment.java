package com.example.zerowaste;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class RestaurantListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   // private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;
    private static final Integer MAX_REST = 2;
    private static final String TAG = "RESTAURANT";
    private RestaurantListViewAdapter restaurantListAdapter;
    private ListView rListView;
    public RestaurantListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        restaurantListAdapter = new RestaurantListViewAdapter(getActivity(),
                R.layout.list_item_restaurant, new ArrayList<Restaurant>());

        rListView = (ListView) rootView.findViewById(R.id.restaurant_list);
        rListView.setAdapter(restaurantListAdapter);

        //FireBase
        CollectionReference collRef = FirebaseFirestore.getInstance().collection("restaurantes");
        collRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Restaurant r = document.toObject(Restaurant.class);
                        updateRestaurantList(r);
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }

        });

        // Inflate the layout for this fragment
        return rootView;
    }
    private void updateRestaurantList(Restaurant r) {
        if (restaurantListAdapter.getCount() >= MAX_REST) {
            restaurantListAdapter.remove( restaurantListAdapter.getItem( 0 ) );
        }
        restaurantListAdapter.insert(r, 0);
    }


}