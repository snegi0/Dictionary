package com.example.dictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Search extends Fragment {
    TextView result;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);


        result = rootView.findViewById(R.id.textView);
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