package com.hang.soreal.hangman;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO MAKE BIGGER SIZED RESOURCES
        //fullscreen

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView)findViewById(R.id.TVtitle);
        YoYo.with(Techniques.BounceInDown).duration(3000).playOn(title);


        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/lily.ttf");
        title.setTypeface(type);

    }

    public void startSinglePlayerGame(View v){
        animate();
        Intent singlePlayer = new Intent(this,Categories.class);
        startActivity(singlePlayer);

    }

    public void startMultiplayerGame(View v){
        animate();
        Intent singlePlayer = new Intent(this,Multiplayer.class);
        startActivity(singlePlayer);

    }

    public void startScore(View v){
        animate();
        Intent singlePlayer = new Intent(this,ScoresActivity.class);
        startActivity(singlePlayer);

    }

    public void animate()
    {
        //YoYo.with(Techniques.Hinge).duration(60).playOn(findViewById(R.id.TVtitle));

    }



}
