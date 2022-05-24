package com.example.dictionary.FileProc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RWSelected {

    private Context context;
    private File FileDir;
    private File file;
    private ArrayList<String> Folder, Name_file, dict;

    public RWSelected(Context context){
        this.context = context;
        FileDir = context.getCacheDir();
        file = new File(FileDir,"selected");
        Folder = new ArrayList<String>();
        Name_file = new ArrayList<String>();
        dict = new ArrayList<String>();
    }
    public void ClearFile(){
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("").getBytes());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Read(){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                str = str.trim();
                if(str.length()>3){
                Folder.add(str.substring(0 , str.indexOf(' ')).trim());
                String tmp = str.substring(str.indexOf(' '), str.length()).trim();
                Name_file.add(tmp.substring(0 , tmp.indexOf(' ')));
                dict.add(tmp.substring(tmp.indexOf('"')+1,tmp.lastIndexOf('"')));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Write(String fold, String name, String dictionary){
        try {
            FileOutputStream is = new FileOutputStream(file,true);
            is.write((fold + ' ' + name + ' ' + '"' + dictionary + '"' +'\n').getBytes());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getFolder() {
        return Folder;
    }

    public ArrayList<String> getName_file() {
        return Name_file;
    }

    public ArrayList<String> getDict() {
        return dict;
    }
}
