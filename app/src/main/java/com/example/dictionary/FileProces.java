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
        file = new File(FileDir,"OxfordLD.psc");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("pars_for_search:\n" +
                    "request(get ,\"https://www.oxfordlearnersdictionaries.com/search/english/\",\"?q=\");\n" +
                    "chek(\"div[id=\"results-container-all\"]\");\n" +
                    "pars_for_word:" +
                    "select_element(body, bodyres,\"div[id=ox-wrapper]\");\n" +
                    "clear_body();\n" +
                    "insert(bodyres,body);").getBytes());
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
            is.write(("pars_for_search:\n" +
                    "request(get ,\"https://www.oxfordlearnersdictionaries.com/search/english/\",\"?q=\");\n" +
                    "chek(\"div[id=\"results-container-all\"]\");\n" +
                    "pars_for_word:\n" +
                    "select_element(body, bodyres,\"div[id=\"ox-header\"]\");\n" +
                    "remove(bodyres);\n" +
                    "select_element(body, bodyres,\"div[id=\"searchbar\"]\");\n" +
                    "remove(bodyres);\n" +
                    "select_element(body, bodyres,\"div[class=\"responsive_entry_center_left\"]\");\n" +
                    "remove(bodyres);\n" +
                    "select_element(body, bodyres,\"div[id=\"ring-links-box\"]\");\n" +
                    "remove(bodyres);\n" +
                    "select_element(body, bodyres,\"div[id=\"rightcolumn\"]\");\n" +
                    "remove(bodyres);\n" +
                    "select_element(body, bodyres,\"footer[id=\"ox-footer\"]\");\n" +
                    "remove(bodyres);" ).getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        file = new File(FileDir,"CambridgeD.psc");
        try {
            FileOutputStream is = new FileOutputStream(file);

            is.write(("pars_for_search:\n" +
                    "request(get ,\"https://dictionary.cambridge.org/search/direct/\",\"?datasetsearch=english&q=\");\n" +
                    "chek(\"ul[class=\"hul-u\"]\");\n" +
                    "pars_for_word:\n" +
                    "addCSS_site(\"https://dictionary.cambridge.org/common.css?version=5.0.243\");\n" +
                    "addCSS_site(\"https://raw.githubusercontent.com/snegi0/Dictionary/master/app/src/main/res/tmp/1.css\");\n" +
                    "select_element(body, bodyres,\"div[class=entry]\");\n" +
                    "clear_body();\n" +
                    "insert(bodyres,body);").getBytes());
            is.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        file = new File(FileDir,"MerriamWeb.psc");
        try {
            FileOutputStream is = new FileOutputStream(file);
            is.write(("pars_for_search:\n" +
                    "request(site ,\"https://www.merriam-webster.com/dictionary/\");\n" +
                    "chek(\"p[class=\"spelling-suggestions\"]\");\n" +
                    "pars_for_word:\n" +
                    "select_element(body, bodyres,\"div[class=\"menu-filler\"]\");\n" +
                    "remove(bodyres);\n" +
                    "select_element(body, bodyres,\"header[class=\"shrinkheader\"]\");\n" +
                    "remove(bodyres);").getBytes());
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
                    "русского языка(не работает)\" lexicography.psc\n").getBytes());
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
