package com.hellohasan.ninthclass.WebScraping;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class HtmlParser extends AsyncTask<String, Void, ArticleModel> {

    private ParserResponseInterface parserResponseInterface;

    public HtmlParser(ParserResponseInterface parserResponseInterface){
        this.parserResponseInterface = parserResponseInterface;
    }

    @Override
    protected ArticleModel doInBackground(String... params) {

        String url = params[0];
        ArticleModel articleModel = null;

        String headline;
        String article = "";

        Document pageDocument;
        Elements elements;
        Elements articleElements;

        try {
            pageDocument = Jsoup.connect(url).get();

            elements = pageDocument.select("#body-content");
            articleElements = pageDocument.select(".wrap .cols .col-1of2 p");
            headline = elements.select("h1").text();

            for(Element element: articleElements){
                article = article + element.text() + "\n\n";
            }

            articleModel = new ArticleModel(headline, article);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return articleModel;
    }

    @Override
    protected void onPostExecute(ArticleModel articleModel) {
        super.onPostExecute(articleModel);

        parserResponseInterface.onParsingDone(articleModel);
    }
}
