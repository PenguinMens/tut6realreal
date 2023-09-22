package com.example.tut6realreal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MapFragment mapFrag;
    private SelectorFragment selectFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        mapFrag = new MapFragment();
        selectFrag = new SelectorFragment();
        //
        fm.beginTransaction()
                .replace(R.id.selector,selectFrag)
                .replace(R.id.map,mapFrag)
                .commit();
    }
}