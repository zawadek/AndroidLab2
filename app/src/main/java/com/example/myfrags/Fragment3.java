package com.example.myfrags;
/*

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

*/
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 *//*

public class Fragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment3() {
        // Required empty public constructor
    }

    */
/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3.
     *//*

    // TODO: Rename and change types and number of parameters
    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false);
    }
}*/
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment3 extends Fragment {

    //podłączanie danych 1.
    private FragsData fragsData;
    private Observer<Integer> numberObserver;
    //obsługa widokow 2.
    private TextView text;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        //dostep do pola tekstowego i przycisku
        text = (TextView) view.findViewById(R.id.current);
        button = (Button) view.findViewById(R.id.button_minus);

        //pobranie obiektu ViewModel
        fragsData = new ViewModelProvider((requireActivity())).get(FragsData.class);

        //tworzenie obserwatora
        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer newInteger) {
                text.setText(newInteger.toString());
            }
        };

        //podlaczenie obserwatora do zmiennej
        fragsData.counter.observe(getViewLifecycleOwner(),numberObserver);

        //obsluga przycisku plus
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer i = fragsData.counter.getValue();
                fragsData.counter.setValue(--i);
            }
        });

        return view;
    }
}