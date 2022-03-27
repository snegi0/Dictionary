package com.example.dictionary.ParserInterpreter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileReader {
    private static ArrayList<String> ParsSearchCode;
    private static ArrayList<String> ParsWordCode;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static void main(String[] args) throws IOException {
        ParsSearchCode = new ArrayList<String>();
        ParsWordCode = new ArrayList<String>();
        File file = new File("C:\\Users\\Snegi\\IdeaProjects\\FileReader\\src\\oxfordun.pscript");
        Scanner obj = new Scanner(file);
        String Scan = obj.nextLine();
        while (obj.hasNextLine() && !Pattern.matches("\s*pars_for_search:\s*", Scan))
            Scan = obj.nextLine();
        String SeartchWorkspace = new String();
        while (obj.hasNextLine()){
            Scan = obj.nextLine();
            if(Pattern.matches("\s*pars_for_word:\s*", Scan))
                break;
            SeartchWorkspace += Scan;
        }
        String WordWorkspace = new String();
        while (obj.hasNextLine()){
            Scan = obj.nextLine();
            WordWorkspace += Scan;
        }
        ParsSearchCode.addAll(List.of(SeartchWorkspace.split(";")));
        ParsWordCode.addAll(List.of(WordWorkspace.split(";")));
        System.out.println(ParsSearchCode);
        System.out.println(ParsWordCode);
    }
}
