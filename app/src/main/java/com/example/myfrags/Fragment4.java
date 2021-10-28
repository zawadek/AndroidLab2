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
 * Use the {@link Fragment4#newInstance} factory method to
 * create an instance of this fragment.
 *//*

public class Fragment4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment4() {
        // Required empty public constructor
    }

    */
/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment4.
     *//*

    // TODO: Rename and change types and number of parameters
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
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
        return inflater.inflate(R.layout.fragment_4, container, false);
    }
}*/

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Fragment4 extends Fragment {

    //podłączanie danych 1.
    private FragsData fragsData;
    private Observer<Integer> numberObserver;
    //obsluga pola edycyjnego 2.
    private EditText edit;
    private TextWatcher textWatcher;
    private boolean turnOffWatccher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_4, container, false);

        //dostep do pola edycyjnego
        edit = view.findViewById(R.id.editTextNumber);

        //pobranie obiektu ViewModel
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        //tworzenie obserwatora
        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer newInteger) {
                turnOffWatccher = true;
                edit.setText(newInteger.toString());
            }
        };

        //podlaczenie obserwatora do zmiennej
        fragsData.counter.observe(getViewLifecycleOwner(),numberObserver);

        //stworzenie obiektu TextWatchera
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!turnOffWatccher){
                    Integer i;
                    try{
                        i = Integer.parseInt(editable.toString());
                    }catch(NumberFormatException e){
                        i = fragsData.counter.getValue();
                    }
                    fragsData.counter.setValue(i);
                }else{
                    turnOffWatccher = !turnOffWatccher;
                }
            }
        };

        //podlaczenie textwatchera do pola edycyjnego
        edit.addTextChangedListener(textWatcher);

        return view;
    }
}
