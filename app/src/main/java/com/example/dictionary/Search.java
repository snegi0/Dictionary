package com.example.dictionary;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.FileProc.RWSelected;
import com.example.dictionary.ParserInterpreter.FileReader;
import com.example.dictionary.ParserInterpreter.PFSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Search extends Fragment {
    WebView result;
    String result2;
    Map<String,PFSearch> pfSearchMap;
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
        LinearLayout linearLayout = rootView.findViewById(R.id.list_result_search);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;




        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                AutoCompleteTextView search_w = rootView.findViewById(R.id.word_in);
                String message = search_w.getText().toString().replaceAll("( +)"," ").trim();
                RWSelected rwSelected = new RWSelected(context);
                rwSelected.Read();
                pfSearchMap = new HashMap<String,PFSearch>();
                linearLayout.removeAllViews();
                if(message.equals("")){
                    Toast.makeText(rootView.getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
                else {
                ArrayList<String> Folder = rwSelected.getFolder();
                ArrayList<String> Name_file = rwSelected.getName_file();
                ArrayList<String> dict = rwSelected.getDict();
                ArrayList<PFSearch> pfSearchs= new ArrayList<PFSearch>();
                for (int i = 0; i < dict.size(); i++) {
                    FileReader freader = new FileReader(Folder.get(i), Name_file.get(i), context);
                    PFSearch pfSearch = new PFSearch(freader.GetCodeS(), message);
                    pfSearchs.add(pfSearch);
                    if(pfSearch.isAcces()){
                        pfSearchMap.put(dict.get(i),pfSearch);
                        String res = "pfSearch.getRes().toString()";
                        Context themedContext = new ContextThemeWrapper(context, R.style.shadoweffect);
                        TextView textView = new TextView(themedContext);
                        textView.setText(dict.get(i));
//                        textView.setTextSize(20);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(10,5,10,5);
                        textView.setLayoutParams(params);
//                        textView.setBackgroundColor(500);
//                        textView.setMaxWidth(width-80);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView tv = (TextView) v;
                                //Toast.makeText(rootView.getContext(), pfSearchMap.get((String) tv.getText()).getRes().toString(), Toast.LENGTH_SHORT).show();
                                result.loadData(pfSearchMap.get((String) tv.getText()).getRes().toString() ,"text/html; charset=utf-8", "utf-8");
                            }
                        });
                        linearLayout.addView(textView);
                    }
                }
                    //Parser pars = new Parser(message, "https://www.oxfordlearnersdictionaries.com/definition/english/", result);
                }

            }
        });

        return rootView;
    }


}