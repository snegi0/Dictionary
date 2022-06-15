package com.example.dictionary.ParserInterpreter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class PFWord {
    private String[] subStr;
    private Document WebDict;
    private Map<String,Element> listElement;

    public PFWord(String code, Document WebDict) {
        this.subStr = code.substring(code.indexOf(':')+1, code.length()).replaceAll("\n","").split(";");
        this.WebDict = WebDict;
        listElement = new HashMap<String, Element>();
        listElement.put("body", WebDict.body());
        listElement.put("head", WebDict.head());
        //String s = WebDict.toString();
        for (String kod:subStr) {
            String comand = kod.substring(0 , kod.indexOf('(')).trim();
            String value = kod.substring(kod.indexOf('(')+1, kod.lastIndexOf(')'));
            switch(comand) {
                case "addCSS_site":
                    addCSS_site(value);
                    break;
                case "addJS_site":
                    addJS_site(value);
                    break;
                case "clear_body":
                    clear_body();
                    break;
                case "clear_head":
                    clear_head();
                    break;
                case "select_element":
                    select_element(value);
                    break;
                case "insert":
                    insert(value);
                    break;
                case "clear":
                    clear(value);
                    break;
                case "remove":
                    remove(value);
                    break;
                default:
                    // code block
            }
        }
    }

    private void addCSS_site(String value) {
        String url = value.substring(value.indexOf('"')+1, value.lastIndexOf('"'));
        try {
            String s = "<style type=\"text/css\">" + new LoadWEB(url).getRes().text()+"</style>";
            listElement.get("head").append(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void addJS_site(String value) {
        String url = value.substring(value.indexOf('"')+1, value.lastIndexOf('"'));
        try {
            //<script> var eclipse_org_common = {"settings":{"cookies_class":{"name":"eclipse_settings","enabled":1}}}</script>
            String s = "<script>" + new LoadWEB(url).getRes().text()+"</script>";
            listElement.get("head").append(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void clear_body(){
        listElement.get("body").html("");
    }
    private void clear_head(){
        listElement.get("head").html("");
    }
    private void clear(String value){
        listElement.get(value.trim()).html("");
    }
    private void remove(String value){
        listElement.get(value.trim()).remove();
        listElement.remove(value.trim());
    }
    private void clear_before(String value){
        String[] subS = value.split(",", 2);

        listElement.get(subS[0].trim());
    }
    private void clear_after(String value){

        listElement.get(value.trim()).html("");
    }


    private void select_element (String value){
        String[] subS = value.split(",", 3);
        switch (subS.length){
            case 2:
                String qery1 = subS[1].trim();
                qery1 = qery1.substring(1,qery1.length()-1);
                select_element(subS[0].trim(),qery1);
                break;
            case 3:
                String qery2 = subS[2].trim();
                qery2 = qery2.substring(1,qery2.length()-1);
                select_element(subS[0].trim(),subS[1].trim(),qery2);
                break;
            default:
                break;
        }
    }
    private void select_element (String in_out, String cssQery){
        listElement.put(in_out,listElement.get(in_out).select(cssQery).first());
    }
    private void select_element (String in, String out, String cssQery){
        String s = listElement.get(in).toString();
        Element e = listElement.get(in).select(cssQery).first();
        listElement.put(out,e);
    }
    private void insert(String value){
        String[] subS = value.split(",", 2);
        String s = WebDict.toString();
        String from = subS[0];
        String to =subS[1];
        listElement.get(to).append(listElement.get(from).toString());
    }
    public Document getWebDict() {
        return WebDict;
    }
//    void reqest(String in){
//        String[] subS = in.split(",");
//        String type = subS[0].trim();
//        String Site = subS[1].trim();
//        Site =  Site.substring(1, Site.length()-1);
//
//        switch(type) {
//            case "get":
//                if (subS.length == 3){
//                    String q = subS[2].trim();;
//                    q = q.substring(1, q.length()-1) + word;
//                    try {
//                        LoadWEB loadWEB = new LoadWEB(Site+q);
//                        res = loadWEB.getRes();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;
//            case "site":
//                try {
//                    LoadWEB loadWEB = new LoadWEB(Site+word);
//                    res = loadWEB.getRes();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                break;
//            default:
//                // code block
//        }
//    }
//
//
//    boolean Chek(String in){
//        String query = in.substring(in.indexOf('"')+1, in.lastIndexOf('"'));
//        Element C = res.select(query).first();
//        if (C != null)
//            return false;
//        else
//            return true;
//
//    }
//
//    public boolean isAcces() {
//        return acces;
//    }
//
//    public Object getRes() {
//        if(acces)
//            return res;
//        else
//            return "not found";
//    }
}
