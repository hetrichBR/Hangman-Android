package com.hang.soreal.hangman;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Categories extends ActionBarActivity {

    String choice = "NULL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //change the font of the title
        TextView categoryTitle = (TextView)findViewById(R.id.TVcat);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/lily.ttf");
        categoryTitle.setTypeface(type);


    }

    /*the user chose the animal category*/
    public void animalsCat(View v)
    {
        choice = "animals";
        moveOn();
    }

    /*the user chose the miscellaneous category*/
    public void statesCat(View v)
    {
        choice = "U.S. States";
        moveOn();
    }

    /*the user chose the miscellaneous category*/
    public void miscCat(View v)
    {
        choice = "miscellaneous";
        moveOn();
    }
    public void countriesCat(View v)
    {
        choice = "Countries";
        moveOn();
    }


    public void moveOn()
    {
        /*send category choice to gameactivity*/
        Intent game = new Intent(this, GameActivity.class);
        game.putExtra("CAT_CHOICE",choice);
        startActivity(game);
        finish();
    }

}
