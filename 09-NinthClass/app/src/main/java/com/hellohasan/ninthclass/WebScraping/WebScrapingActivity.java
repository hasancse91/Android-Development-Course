package com.hellohasan.ninthclass.WebScraping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hellohasan.ninthclass.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebScrapingActivity extends AppCompatActivity implements ParserResponseInterface{

    @BindView(R.id.headline)
    TextView headlineTextView;
    @BindView(R.id.article)
    TextView articleTextView;
    @BindView(R.id.errorMessage)
    TextView errorMessageTextView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_scraping);
        ButterKnife.bind(this);

        //Execute AsyncTask for Parsing HTML
        new HtmlParser(this).execute("https://developer.android.com/training/index.html");
    }

    @Override
    public void onParsingDone(ArticleModel articleModel) {
        progressBar.setVisibility(View.GONE);

        if(articleModel!=null){
            headlineTextView.setText(articleModel.getHeadline());
            articleTextView.setText(articleModel.getArticle());
        }
        else
            errorMessageTextView.setText(getResources().getString(R.string.error_message));
    }
}
