package com.example.dictionary;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Options extends Fragment {
    private Spinner spinner;
    private Context context;
    int pos=0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_options, container, false);

        RadioGroup radioGroup = rootView.findViewById(R.id.dictionary_choice);

        Spinner spinner = rootView.findViewById(R.id.spinner_language);
        List<String> catNames = new ArrayList<String>(Arrays.asList(rootView.getResources().getStringArray(R.array.sp_lang)));
        //catNames.add("Spanish");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext() ,android.R.layout.simple_spinner_item,catNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);


                if (position == 0){
                    ArrayList<String> name =new ArrayList<String>();
                    name.add("Oxford Learner's Dictionaries");
                    name.add("Oxford Learner's Dictionaries1");
                    name.add("Oxford Learner's Dictionaries2");
                    Dictionari_list.name=name;
                    //Dictionari_list.URL.add("https://www.oxfordlearnersdictionaries.com/");
                }
                if (position == 1){
                    ArrayList<String> name =new ArrayList<String>();
                    name.add("Толковый словарь Ожегова");
                    Dictionari_list.name=name;
                    //Dictionari_list.URL.add("slovarozhegova.ru");
                }


                radioGroup.removeAllViews();

                for (int i=0; i< Dictionari_list.name.size();i++){
                    RadioButton newRadioButton = new RadioButton(rootView.getContext());
                    newRadioButton.setText(Dictionari_list.name.get(i));
                    newRadioButton.setTextSize(20);
                    newRadioButton.setId(i);
                    newRadioButton.callOnClick();
                    radioGroup.addView(newRadioButton);
                }

                TextView result = (TextView) rootView.findViewById(R.id.textView2);
                result.setText( parent.getSelectedItem().toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        return rootView;

    }
    public void loadData(Context context){


    }
}