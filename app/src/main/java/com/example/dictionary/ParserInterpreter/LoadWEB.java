package com.example.dictionary.ParserInterpreter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class LoadWEB {
    private Document res;


    public LoadWEB(String url_q) throws InterruptedException {
        try {
            res = Jsoup.connect(url_q).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Thread T = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    res = Jsoup.connect(url_q).get();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        T.start();
//        T.join();
    }


    public Document getRes() {
        return res;
    }
}
