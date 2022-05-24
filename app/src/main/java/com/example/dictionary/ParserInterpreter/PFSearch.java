package com.example.dictionary.ParserInterpreter;

import android.view.View;

import org.jsoup.nodes.Element;

public class PFSearch {
    private String[] subStr;
    private String word;
    private Element res;
    private boolean acces;
    public PFSearch(String code, String word) {
        this.word = word;
        this.subStr = code.substring(code.indexOf(':')+1, code.length()).replaceAll("\n","").split(";");
        for (String kod:subStr) {
            String comand = kod.substring(0 , kod.indexOf('(')).trim();
            String value = kod.substring(kod.indexOf('(')+1, kod.lastIndexOf(')'));
            switch(comand) {
                case "request":
                    reqest(value);
                    break;
                case "chek":
                    acces = Chek(value);
                    break;
                default:
                    // code block
            }
        }
    }

    void reqest(String in){
        String[] subS = in.split(",");
        String type = subS[0].trim();
        String Site = subS[1].trim();
        Site =  Site.substring(1, Site.length()-1);

        switch(type) {
            case "get":
                if (subS.length == 3){
                    String q = subS[2].trim();;
                    q = q.substring(1, q.length()-1) + word;
                    try {
                        LoadWEB loadWEB = new LoadWEB(Site+q);
                        res = loadWEB.getRes();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "site":
                try {
                    LoadWEB loadWEB = new LoadWEB(Site+word);
                    res = loadWEB.getRes();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:
                // code block
        }
    }


    boolean Chek(String in){
        String query = in.substring(in.indexOf('"')+1, in.lastIndexOf('"'));
        Element C = res.select(query).first();
        if (C != null)
            return false;
        else
            return true;

    }

    public boolean isAcces() {
        return acces;
    }

    public Object getRes() {
        if(acces)
            return res;
        else
            return "not found";
    }
}
