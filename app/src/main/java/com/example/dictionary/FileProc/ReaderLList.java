package com.example.dictionary.FileProc;

import android.content.Context;

import androidx.collection.ArraySet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReaderLList {
    private Context context;
    private File FileDir;
    private File file;
    private Map<String, String> states;
    private ArrayList<String> S;
    public ReaderLList(Context context, String DIR) {
        this.context = context;
        FileDir = context.getDir(DIR ,context.MODE_PRIVATE);
        file = new File(FileDir,"list");
        states = new HashMap<String, String>();
        S = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            StringBuilder text = new StringBuilder();
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                String resultStr1 = str.substring(str.indexOf('"')+1 , str.lastIndexOf('"')).trim();
                S.add(resultStr1);
                String resultStr2 = str.substring(str.lastIndexOf('"')+1, str.length()).trim();
                states.put(resultStr1,resultStr2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getStates() {
        return states;
    }

    public ArrayList<String> getS() {
        return S;
    }
}
