package com.hellohasan.ninthclass.WebScraping;

public class ArticleModel {

    private String headline;
    private String article;

    public ArticleModel(String headline, String article) {
        this.headline = headline;
        this.article = article;
    }

    public String getHeadline() {
        return headline;
    }

    public String getArticle() {
        return article;
    }
}
