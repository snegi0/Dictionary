package com.example.dictionary.FileProc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadLDIR {
    private Context context;
    private File FileDir;
    private File file;
    private Map<Integer, String> states;

    public ReadLDIR(Context context) {
        this.context = context;
        FileDir = context.getFilesDir();
        file = new File(FileDir,"languagesDIR");
        states = new HashMap<Integer, String>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            int i=0;
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                states.put(i,str.trim());
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> getStates() {
        return states;
    }
}
