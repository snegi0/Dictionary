package com.example.dictionary;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Search extends Fragment {
    WebView result;
    @SuppressLint("SetJavaScriptEnabled")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);


        result = rootView.findViewById(R.id.result_search);
        WebSettings settings = result.getSettings(); settings.setJavaScriptEnabled(true); settings.setJavaScriptCanOpenWindowsAutomatically(true); settings.setDatabaseEnabled(true); settings.setDomStorageEnabled(true); settings.setLoadsImagesAutomatically(true); settings.setRenderPriority(WebSettings.RenderPriority.HIGH); settings.setSupportZoom(false); settings.setAllowFileAccess(true); settings.setSavePassword(false); settings.setSupportMultipleWindows(false); settings.setAppCacheEnabled(true); settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        result.loadUrl("javascript:(function(){"+
                "l=document.getElementById('mA');"+
                "e=document.createEvent('HTMLEvents');"+
                "e.initEvent('click',true,true);"+
                "l.dispatchEvent(e);"+
                "})()");

        ImageButton button = rootView.findViewById(R.id.searchButton);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView search_w = rootView.findViewById(R.id.word_in);
                String message = search_w.getText().toString().replaceAll("( +)"," ").trim();
                if(message.equals("")){
                    Toast.makeText(rootView.getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
                else {
                    Parser pars = new Parser(message, "https://www.oxfordlearnersdictionaries.com/definition/english/", result);
                }

            }
        });



        return rootView;
    }
}