package com.example.dictionary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.airbnb.paris.Paris;
import com.example.dictionary.FileProc.RWSelected;
import com.example.dictionary.ParserInterpreter.FileReader;
import com.example.dictionary.ParserInterpreter.PFSearch;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
                pfSearchMap = new HashMap<String,PFSearch>();
                linearLayout.removeAllViews();
                if(message.equals("")){
                    Toast.makeText(rootView.getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
                else {
                    JSONArray search = rwSelected.Read();
                    ArrayList<PFSearch> pfSearchs= new ArrayList<PFSearch>();
                    ArrayList<TextView> seached_dict_list = new ArrayList<TextView>();

                    for (Object i:search) {
                        JSONObject obj = (JSONObject)i;
                        FileReader freader = new FileReader(obj.get("folder").toString(), obj.get("file_name").toString(), context);
                        PFSearch pfSearch = new PFSearch(freader.GetCodeS(), message);
                        pfSearchs.add(pfSearch);
                        if(pfSearch.isAcces()){
                            pfSearchMap.put(obj.get("d_name").toString(),pfSearch);

                            Context themedContext = new ContextThemeWrapper(context, R.style.grayb);
                            TextView textView = new TextView(themedContext);
                            seached_dict_list.add(textView);
                            textView.setText(obj.get("d_name").toString());
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.setMargins(10,5,10,5);
                            textView.setLayoutParams(params);


                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    result.loadUrl("javascript:document.open();document.close();");
                                    Context tContext = new ContextThemeWrapper(context, R.style.redb);
                                    TextView tv = (TextView) v;
                                    for (TextView t:seached_dict_list) {
                                        Paris.style(t).apply(R.style.grayb);
                                    }
                                    Paris.style(tv).apply(R.style.redb);
                                    result.loadData(pfSearchMap.get((String) tv.getText()).getRes().toString() ,"text/html; charset=utf-8", "utf-8");
                                }
                            });
                            linearLayout.addView(textView);

                        }
                    }
                    if(seached_dict_list.size()>0)
                    seached_dict_list.get(0).callOnClick();
                }

            }
        });

        return rootView;
    }


}