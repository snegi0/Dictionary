package com.example.dictionary.ParserInterpreter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class LoadWEB {
    private static Element res;


    public LoadWEB(String url_q) throws InterruptedException {
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url_q).get();
                    res = doc;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        T.start();
        T.join();
    }


    public static Element getRes() {
        return res;
    }
}
