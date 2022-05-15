package com.example.dictionary.FileProc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RWSelected {

    private Context context;
    private File FileDir;
    private File file;
    private String Folder, Name_file;

    public RWSelected(Context context) {
        this.context = context;
    }
    public void Read(){
        FileDir = context.getCacheDir();
        file = new File(FileDir,"selected");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            int i=0;
            while ((str = br.readLine()) != null) {
                str = str.trim();
                Folder = str.substring(0 , str.lastIndexOf(' ')).trim();
                Name_file = str.substring(str.lastIndexOf(' ')+1, str.length()).trim();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Write(String fold, String name){
        File FileDir = context.getCacheDir();
        File file = new File(FileDir,"selected");;
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write((fold + ' ' + name).getBytes());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFolder() {
        return Folder;
    }

    public String getName_file() {
        return Name_file;
    }
}
