package com.example.dictionary;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dictionary.databinding.ActivityMainBinding;
import com.google.mlkit.vision.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser{
    private String Text;
    private String Word;
    private String URL;
    private TextView activity;
    static StringBuffer builder = new StringBuffer();
    //private static Document doc;
    //rivate static Element base;

    public Parser(String word, String URL, TextView activity) {
        this.Word = word;
        this.URL = URL;
        this.activity=activity;
        getWebsite(URL,word);
    }

    public String getText() {
        return Text;
    }

    private void getWebsite(final String url_q,final String word) {

        builder = new StringBuffer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //builder.append("2Выполнено");
                    Document doc = Jsoup.connect(URL+Word).get();
                    Element infor = doc.select("div[class=entry]").first();

                    builder.append(infor);


                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                activity.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        activity.setText(Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT));
                    }
                });
            }
        }).start();

        this.Text=builder.toString();
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