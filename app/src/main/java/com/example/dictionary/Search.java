package com.example.dictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


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
                Parser pars =new Parser("hi","https://www.oxfordlearnersdictionaries.com/definition/english/",result);





//                String Text = null;
//                while (true){
//                    Text = pars.getText();
//                    if(Text.length()<5){
//                        result.setText("Загрузка...");
//                    }else {
//                        result.setText(Text);
//                        break;
//                    }
//                }
                //result.setText((String)pars.getText());
            }
        });



        return rootView;
    }
}