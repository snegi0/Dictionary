package com.example.dictionary.FileProc;

import android.content.Context;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class RWSelected {

    private Context context;
    private File FileDir;
    private File file;
    private ArrayList<String> Folder, Name_file, dict;
    JSONArray selecteditem;

    public RWSelected(Context context){
        this.context = context;
        FileDir = context.getCacheDir();
        file = new File(FileDir,"selected");
        Folder = new ArrayList<String>();
        Name_file = new ArrayList<String>();
        dict = new ArrayList<String>();
        selecteditem = new JSONArray();
    }
    public void ClearFile(){
        try {
            selecteditem = new JSONArray();
            FileOutputStream is = new FileOutputStream(file);
            is.write(("").getBytes());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JSONArray Read(){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            return (JSONArray) new JSONParser().parse(str);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Write(String res){
        try {
            FileOutputStream is = new FileOutputStream(file,false);
            is.write(res.getBytes());
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
