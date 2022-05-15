package com.example.dictionary;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileProces {
    private Context context;
    private File FileDir;
    private File file;
    public FileProces(Context context) {
        this.context = context;
    }
    public void setcod(){
        FileDir = context.getDir("EN",context.MODE_PRIVATE);
        file = new File(FileDir,"MerriamWeb.psc");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("pars_for_search:\n" +
                    "request(site ,\"https://www.merriam-webster.com/dictionary/\");\n" +
                    "chek(\"div[class=\"widget spelling-suggestion\"]\");\n" +
                    "pars_for_word:\n" +
                    "delete_element(1,\"\");").getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void CreateLdir(){
        FileDir = context.getFilesDir();
        file = new File(FileDir,"languagesDIR");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write("EN \n RU".getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void CreateENdir(){
        FileDir = context.getDir("EN",context.MODE_PRIVATE);
        file = new File(FileDir,"list");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("\"Oxford Learner's Dictionaries\" OxfordLD.psc\n" +
                    "\"Cambridge Dictionary\" CambridgeD.psc\n" +
                    "\"Merriam-Webster\" MerriamWeb.psc").getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        file = new File(FileDir,"OxfordLD.psc");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("").getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        file = new File(FileDir,"CambridgeD.psc");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("").getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        file = new File(FileDir,"MerriamWeb.psc");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("").getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void CreateRUdir(){
        FileDir = context.getDir("RU",context.MODE_PRIVATE);
        file = new File(FileDir,"list");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("\"Толковые онлайн-словари " +
                    "русского языка\" lexicography.psc\n").getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        file = new File(FileDir,"lexicography.psc");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("").getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String Get(){
        String S = null;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            StringBuilder text = new StringBuilder();
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                if (!str.contains(":"))
                    str = "    " + str.trim();
                text.append(str).append("\n");
                S = text.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return S;
    }
}
