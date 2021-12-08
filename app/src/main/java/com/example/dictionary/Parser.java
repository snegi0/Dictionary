package com.example.dictionary;
import android.content.Intent;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
    private String Text;
    private String Word;
    private String URL;

    private static Document doc;
    private static Element base;

    public Parser(String word, String URL) {
        Word = word;
        this.URL = URL;
    }

    public String getText() {
        return Text;
    }

    private void getWebsite(final String url_q, final String word) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuffer builder = new StringBuffer();
                try {
                    Document doc = Jsoup.connect(url_q + word).get();
                    Element info = doc.select("div[class=entry]").first();

                    builder.append(getWord(info)).append("\n").append(getForm(info)).append("\n").append(getINFOR(info));


                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
                Text = builder.toString();
            }
        }).start();
    }

    private static void getBase(String request) {
        base = doc.select(request).first();
    }

    private static String getWord(Element P) {
        Element blok1 = P.select("div[class=webtop]").first();
        return blok1.select("h1[class=headword]").first().text();
    }

    private static String getForm(Element P) {
        Element blok1 = P.select("div[class=webtop]").first();
        return blok1.select("span[class=pos]").first().text();
    }

    private static String getINFOR(Element P) {
        Element blok1 = P.select("ol").first();
        return blok1.text();
    }
}