package com.hang.soreal.hangman;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.revmob.RevMob;
import com.revmob.ads.fullscreen.RevMobFullscreen;


public class Multiresult extends ActionBarActivity {

    RevMobFullscreen fullscreen;
    String mWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiresult);

        RevMob revmob = RevMob.start(this); // RevMob Media ID configured in the AndroidManifest.xml file
        fullscreen = revmob.createFullscreen(this, null); // pre-load it without showing it

        mWord = getIntent().getStringExtra("Mult_word");
        TextView result = (TextView)findViewById(R.id.TVResult);
        result.setText(mWord);



    }

    public void nextRound(View v)
    {
        Intent newRound = new Intent(this, Multiplayer.class);
        startActivity(newRound);
        fullscreen.show();
        finish();
    }



}
