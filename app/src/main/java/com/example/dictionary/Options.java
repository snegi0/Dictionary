package com.example.dictionary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.FileProc.RWSelected;
import com.example.dictionary.FileProc.ReadLDIR;
import com.example.dictionary.FileProc.ReaderLList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Options extends Fragment {
    ReaderLList Llist;
    Integer langPos;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_options, container, false);
        Context context = rootView.getContext();

        RadioGroup radioGroup = rootView.findViewById(R.id.dictionary_choice);
        Spinner spinner = rootView.findViewById(R.id.spinner_language);
        List<String> lang = new ArrayList<String>(Arrays.asList(rootView.getResources().getStringArray(R.array.sp_lang)));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext() ,android.R.layout.simple_spinner_item,lang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ReadLDIR ldir = new ReadLDIR(rootView.getContext());



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                ArrayList<String> name =new ArrayList<String>();
                langPos = position;
                Llist = new ReaderLList(context,ldir.getStates().get(position));
                ArrayList<String> S = Llist.getS();
                ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();

                radioGroup.removeAllViews();

                for (int i=0; i< S.size();i++){
                    CheckBox CBox = new CheckBox(rootView.getContext());
                    checkBoxes.add(CBox);
                    CBox.setText(S.get(i));
                    CBox.setTextSize(20);
                    CBox.setId(i);
                    CBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            Toast.makeText(context,"ok",
                                    Toast.LENGTH_SHORT).show();
                            RWSelected RWS = new RWSelected(context);
                            RWS.ClearFile();
                            for (CheckBox C: checkBoxes) {
                                if (C.isChecked()){
                                    Toast.makeText(context,C.getText(),
                                            Toast.LENGTH_SHORT).show();
                                    RWS.Write(ldir.getStates().get(langPos), Llist.getStates().get(Llist.getS().get(C.getId())), (String) C.getText());
                                }
                            }

                    }
                    });
                    if (i == 0) {
                        CBox.setChecked(true);
                    }

                    radioGroup.addView(CBox);
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