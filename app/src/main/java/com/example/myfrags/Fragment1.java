package com.example.myfrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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

        //animacje
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        Button buttonShuffle = (Button) view.findViewById(R.id.button_shuffle);
        Button buttonClockwise = (Button) view.findViewById(R.id.button_clockwise);
        Button buttonHide = (Button) view.findViewById(R.id.button_hide);
        Button buttonRestore = (Button) view.findViewById(R.id.button_restore);

        buttonShuffle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(callback != null)
                    callback.onButtonClickShuffle();
            }
        });

        buttonClockwise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(callback != null)
                    callback.onButtonClickClockwise();
            }
        });

        buttonHide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(callback != null)
                    callback.onButtonClickHide();
            }
        });

        buttonRestore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(callback != null)
                    callback.onButtonClickRestore();
            }
        });

        return view;
    }

    public interface OnButtonClickListener {
        public  void onButtonClickShuffle();
        public  void onButtonClickClockwise();
        public  void onButtonClickHide();
        public  void onButtonClickRestore();
    }

    private OnButtonClickListener callback = null;

    public void setOnButtonClickListener(OnButtonClickListener callback){
        this.callback = callback;
    }
}