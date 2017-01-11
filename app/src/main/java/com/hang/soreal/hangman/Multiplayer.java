package com.hang.soreal.hangman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class Multiplayer extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        TextView title = (TextView)findViewById(R.id.TVmult);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/lily.ttf");
        title.setTypeface(type);


    }

    public void play(View v)
    {
        EditText multiword = (EditText)findViewById(R.id.ETmultiword);

        String word = multiword.getText().toString();
        //checs for spaces if none continue
        if(word.contains(" ")) {
            int duration = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();

            YoYo.with(Techniques.BounceInDown.Shake).duration(800).playOn(multiword);
            Toast toast = Toast.makeText(context, "Please remove space(s) from word", duration);
            toast.show();
        }
        else
        {
            Intent play = new Intent(this, MultiActivity.class);
            //set all caps

            //sending the word
            play.putExtra("multiWord_ID", word);
            startActivity(play);
            finish();
        }
    }

}
