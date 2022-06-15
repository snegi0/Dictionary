package com.example.dictionary.ParserInterpreter;

import java.util.ArrayList;

public class Command {
    private String comand;
    private ArrayList<String> arg;
    private String str;

    public Command(String str) {
        this.str = str.trim();
        comand = new String();
        arg = new ArrayList<String>();
    }
    private void reader_str(){
    }
}
