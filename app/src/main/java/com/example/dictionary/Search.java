package com.example.dictionary;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.FileProc.RWSelected;
import com.example.dictionary.ParserInterpreter.FileReader;
import com.example.dictionary.ParserInterpreter.LoadWEB;
import com.example.dictionary.ParserInterpreter.PFSearch;


public class Search extends Fragment {
    WebView result;
    String result2;
    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        Context context = rootView.getContext();
        result = rootView.findViewById(R.id.result_search);
        WebSettings settings = result.getSettings(); settings.setJavaScriptEnabled(true); settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true); settings.setDomStorageEnabled(true); settings.setLoadsImagesAutomatically(true);
        settings.setAllowFileAccess(true); settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING); settings.setSupportMultipleWindows(false);
        settings.setAppCacheEnabled(true); settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        result.loadUrl("javascript:(function(){"+
                "l=document.getElementById('mA');"+
                "e=document.createEvent('HTMLEvents');"+
                "e.initEvent('click',true,true);"+
                "l.dispatchEvent(e);"+
                "})()");

        ImageButton button = rootView.findViewById(R.id.searchButton);


        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                RWSelected rSelected = new RWSelected(context);
                rSelected.Read();
                AutoCompleteTextView search_w = rootView.findViewById(R.id.word_in);
                String message = search_w.getText().toString().replaceAll("( +)"," ").trim();
                RWSelected rwSelected = new RWSelected(context);
                rwSelected.Read();
                FileReader freader = new FileReader(rwSelected.getFolder(), rwSelected.getName_file(), context);



                if(message.equals("")){
                    Toast.makeText(rootView.getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
                else {

                    String str = "pars_for_search:\n" +
                            "request(get ,\"https://www.oxfordlearnersdictionaries.com/search/english/\",\"?q=\");\n" +
                            "chek(\"div[id=\"results-container-all\"]\");\n" +
                            "select_element(1,\"\");\n" +
                            "select_elements(1,\"\");\n" +
                            "pars_for_word:\n" +
                            "delete_element(1,\"\");";
                    PFSearch pfSearch = new PFSearch(freader.GetCodeS(), message,rootView);
                    result.loadData(pfSearch.getRes().toString(),"text/html; charset=utf-8", "utf-8");
                    //Parser pars = new Parser(message, "https://www.oxfordlearnersdictionaries.com/definition/english/", result);
                }

            }
        });



        return rootView;
    }
}