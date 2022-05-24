package com.example.dictionary.ParserInterpreter;
import java.io.*;
import android.content.Context;



public class FileReader {
    String fold;
    String name;
    String code;

    public FileReader(String fold, String name, Context context) {
        this.fold = fold;
        this.name = name;

        File FileDir = context.getDir(fold,context.MODE_PRIVATE);
        File file = new File(FileDir,name);
        try {
            java.io.FileReader fr = new java.io.FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String str;
            int i=0;
            while ((str = br.readLine()) != null) {
                str = str.trim();
                sb.append(str+" ");
            }
            code = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String GetCodeS(){
        return code.substring(code.lastIndexOf("pars_for_search:") , code.lastIndexOf("pars_for_word:")).trim();
    }
    public String GetCodeW(){
        return code.substring(code.lastIndexOf("pars_for_word:") , code.length()).trim();
    }

//    String s = "pars_for_search:\n" +
//            "request(get ,\"https://www.oxfordlearnersdictionaries.com/search/english/\",\"?q=\")\n" +
//            "chek(\"div[id=\"results-container-all\"]\");\n" +
//            "select_element(1,\"\");\n" +
//            "select_elements(1,\"\");\n" +
//            "pars_for_word:\n" +
//            "delete_element(1,\"\");";
//    public FileReader() throws FileNotFoundException {
//        ParsSearchCode = new ArrayList<String>();
//        ParsWordCode = new ArrayList<String>();
//        File file = new File("https://raw.githubusercontent.com/snegi0/Dictionary/master/app/src/main/java/com/example/dictionary/ParserInterpreter/oxfordun.pscript");
//        Scanner obj = new Scanner(file);
//        String Scan = obj.nextLine();
//        while (obj.hasNextLine() && !Pattern.matches("\s*pars_for_search:\s*", Scan))
//            Scan = obj.nextLine();
//        String SeartchWorkspace = new String();
//        while (obj.hasNextLine()){
//            Scan = obj.nextLine();
//            if(Pattern.matches("\s*pars_for_word:\s*", Scan))
//                break;
//            SeartchWorkspace += Scan;
//        }
//        String WordWorkspace = new String();
//        while (obj.hasNextLine()){
//            Scan = obj.nextLine();
//            WordWorkspace += Scan;
//        }
//        ParsSearchCode.addAll(List.of(SeartchWorkspace.split(";")));
//        ParsWordCode.addAll(List.of(WordWorkspace.split(";")));
//        System.out.println(ParsSearchCode);
//        System.out.println(ParsWordCode);
//    }
}
