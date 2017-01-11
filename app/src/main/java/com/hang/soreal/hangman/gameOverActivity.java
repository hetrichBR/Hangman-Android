package com.hang.soreal.hangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aerserv.sdk.AerServConfig;
import com.aerserv.sdk.AerServInterstitial;
import com.aerserv.sdk.AerServSdk;
import com.revmob.RevMob;
import com.revmob.ads.fullscreen.RevMobFullscreen;


public class gameOverActivity extends ActionBarActivity {


    int mpoints;
    AerServInterstitial interstitial;
    AerServConfig config;

    RevMobFullscreen fullscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

// These calls will load an ad in the background
       // AerServSdk.init(this, "SITE ID"); //onCreate(Bundle) in your main activity.
         //config = new AerServConfig(this,"100074").setPreload(true);
         //interstitial = new AerServInterstitial(config);

        RevMob revmob = RevMob.start(this); // RevMob Media ID configured in the AndroidManifest.xml file
        fullscreen = revmob.createFullscreen(this, null); // pre-load it without showing it


        //get the extra then put a default value
        int points = getIntent().getIntExtra("POINTS", 0);
        String word = getIntent().getStringExtra("Mult_word");

        TextView tvPoints, missedWord;
         tvPoints =(TextView)findViewById(R.id.TVpoints);
        //cant set int to string so use valueOf
        tvPoints.setText(String.valueOf(points) + "  word(s) guessed");

        //display missed word
       // missedWord = (TextView)findViewById(R.id.TVmissedword);
        //missedWord.setText("Word was\n" + word);
        mpoints = points;

    }
    public void saveScore(View v)
    {
        //storing score and name to a file
        SharedPreferences preference = getSharedPreferences("MYPREFERENCE", Context.MODE_PRIVATE);
        //Name X Words Guessed /n
        EditText ETname =  (EditText)findViewById(R.id.ETname);
        String name = ETname.getText().toString();

        //name and words guessed

        //use editor to write to preference file
        SharedPreferences.Editor  editor = preference.edit();


        //the previous score
        String previousScore = preference.getString("SCORES","");

        //store score
        editor.putString("SCORES", name + " " + mpoints + " Point(s) \n"+previousScore);

        //runs editor
        editor.commit();

        //notify user score was saved
        Toast toast = Toast.makeText(getApplicationContext(), "Score was saved", Toast.LENGTH_SHORT);
        toast.show();
        //interstitial.show();
        fullscreen.show();

        finish();



    }

}
