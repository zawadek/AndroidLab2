package com.example.myfrags;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements  Fragment1.OnButtonClickListener{

    //Wersja z lab 2 poniżej
    /*private FragmentManager fragmentManager;

    private Fragment fragment1, fragment2, fragment3, fragment4;*/

    //Wersja z lab 3 poniżej
    private int frames[];
    private boolean hiden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //zakomentowana wersja z lab2
        /*fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame1, fragment1);
        transaction.add(R.id.frame2, fragment2);
        transaction.add(R.id.frame3, fragment3);
        transaction.add(R.id.frame4, fragment4);
        transaction.addToBackStack(null);
        transaction.commit();*/

        //wersja lab 3
        if (savedInstanceState == null) {
            frames = new int[]{R.id.frame1, R.id.frame2, R.id.frame3, R.id.frame4};
            hiden = false;

            Fragment[] fragments = new Fragment[]{new Fragment1(), new Fragment2(), new Fragment3(), new Fragment4()};
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            for (int i = 0; i < 4; i++) {
                transaction.add(frames[i], fragments[i]);
            }

            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            frames = savedInstanceState.getIntArray("FRAMES");
            hiden = savedInstanceState.getBoolean("HIDEN");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putIntArray("FRAMES", frames);
        outState.putBoolean("HIDEN", hiden);
    }


    @Override
    public void onButtonClickShuffle() {
        Toast.makeText(getApplicationContext(),"Shuffle", Toast.LENGTH_SHORT).show();

        List<Integer> list = new ArrayList<Integer>(Arrays.asList(frames[0], frames[1], frames[2], frames[3]));
        Collections.shuffle(list);
        for(int i = 0; i < 4; i++){
            frames[i] = list.get(i).intValue();
        }

        newFragments();
    }

    @Override
    public void onButtonClickClockwise() {
        Toast.makeText(getApplicationContext(), "Clockwise", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClickHide() {
        Toast.makeText(getApplicationContext(), "Hide", Toast.LENGTH_SHORT).show();

        if(hiden) return;

        FragmentManager fragmentManager = getSupportFragmentManager();

        for(Fragment f: fragmentManager.getFragments()){
            if(f instanceof Fragment1) continue;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(f);

            transaction.addToBackStack(null);
            transaction.commit();
        }

        hiden = true;
    }

    @Override
    public void onButtonClickRestore() {
        Toast.makeText(getApplicationContext(), "Restore", Toast.LENGTH_SHORT).show();

        if(!hiden) return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for(Fragment f: fragmentManager.getFragments()) {
            if (f instanceof Fragment1) continue;
            transaction.show(f);
        }

        transaction.addToBackStack(null);
        transaction.commit();

        hiden = false;
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

        if(fragment instanceof Fragment1){
            ((Fragment1) fragment).setOnButtonClickListener(this);
        }
    }

    private  void newFragments(){
        Fragment[] newFragments = new Fragment[]{new Fragment1(), new Fragment2(), new Fragment3(), new Fragment4()};

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for(int i = 0; i < 4; i++){
            transaction.replace(frames[i],newFragments[i]);
            if(hiden && !(newFragments[i] instanceof Fragment1))
                transaction.hide(newFragments[i]);
        }

        transaction.addToBackStack(null);
        transaction.commit();
    }
}
