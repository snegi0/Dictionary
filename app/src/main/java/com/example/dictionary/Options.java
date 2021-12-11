package com.example.dictionary;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Options extends Fragment {
    private Spinner spinner;
    private Context context;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_options, container, false);
        Spinner spinner = rootView.findViewById(R.id.spinner_language);

        List<String> catNames = new ArrayList<String>();
        catNames.add("Барсик");
        catNames.add("Мурзик");
        catNames.add("Рыжик");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext() ,android.R.layout.simple_spinner_item,catNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return rootView;

    }
    public void loadData(Context context){


    }
}