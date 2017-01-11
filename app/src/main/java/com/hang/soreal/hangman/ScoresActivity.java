package com.hang.soreal.hangman;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.revmob.RevMob;
import com.revmob.ads.banner.RevMobBanner;


public class ScoresActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_scores);
        //=========================================
        //BANNER AD
        //=========================================
        RevMob revmob = RevMob.start(this); // RevMob Media ID configured in the AndroidManifest.xml file
        RevMobBanner banner = revmob.createBanner(this);
        ViewGroup view = (ViewGroup) findViewById(R.id.banner);
        view.addView(banner);

        TextView title = (TextView)findViewById(R.id.TVscoretitle);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/lily.ttf");
        title.setTypeface(type);
        //get the scores

        SharedPreferences preferences = getSharedPreferences("MYPREFERENCE", MODE_PRIVATE);
        String scores = preferences.getString("SCORES","NO SCORE");

        TextView TVscores = (TextView)findViewById(R.id.TVscores);
        TVscores.setText(scores);

    }



}
