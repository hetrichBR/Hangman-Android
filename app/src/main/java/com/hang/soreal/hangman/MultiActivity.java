package com.hang.soreal.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

//cntrl+shft+0 and alt+enter
//TODO's let you find stuff and keep list of what is to be done
public class MultiActivity extends ActionBarActivity {
    //declare class variable(can be used in any method)
    //TODO for multiplayer come here then set mword equal to word entered if there is no word entered pick from array

    String mWord = "";

    int mFailCounter = 0;

    int mGuessedLetter = 0;

    int mPoints = 0;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_game);
        spinner = (Spinner) findViewById(R.id.spinner);
//      Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.letters_array, R.layout.spinner_item);
//      Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
//      Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //word user tyed in is stored in mWord
        mWord = getIntent().getStringExtra("multiWord_ID").toUpperCase();

        Log.d("MYLOG", "MWORD IS: " + mWord);
        createTextView(mWord);

    }

    public void createTextView(String word)
    {
        LinearLayout layoutLetters = (LinearLayout)findViewById(R.id.layoutLetters);
        for(int i = 0; i < word.length(); i++){
            //reference layou file textview to make x number of those text views

            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview,null);
            layoutLetters.addView(newTextView);
        }
    }


  /*
  * gets letter user guesses stores it in ETletter then goes to checkLetter if only one char
  * */
    public void introduceLetter(View v)
    {
        String letter= spinner.getSelectedItem().toString();

        //message on the logcat
        Log.d("MYLOG","The letter is " + letter);

        //if there is a letter entered call checkLetter
        if(letter.length() == 1)

            checkLetter(letter.toUpperCase());
        else
            Toast.makeText(this,"Please enter a letter", Toast.LENGTH_SHORT).show();


    }
    /*Checking if letters match
    * @param introduceLetter
    * */
    public void checkLetter(String introduceLetter)
    {
        //char is value of entered letter
        char charintroduced = introduceLetter.charAt(0);
        boolean letterGuessed = false;

        //check whole word for the letter
        for( int i = 0; i < mWord.length(); i++)
        {
            //where we are at in the word
            char charFromTheWord =  mWord.charAt(i);

            Log.d("MYLOG", "Checking for " + charFromTheWord);

            if(charFromTheWord == charintroduced)
            {
                Log.d("MYLOG", "There is a match");

                letterGuessed = true;

                //can be either char because they are a match
                showLettersAtIndex(i,charintroduced);

                //ince letter guessed right
                mGuessedLetter ++;
            }
        }
        //went through loop guessed letter was not in word
        if(letterGuessed == false)
        {
            letterFailed(Character.toString(charintroduced));

        }
        else if(mGuessedLetter == mWord.length())
        {

            clearScreen();

            //score one point and move to next word

        }

    }


     /*If we fail the guess then change the image*/
    public void letterFailed(String letterFailed)
    {
        mFailCounter ++;
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        //display failed letter
        TextView failedLetters = (TextView)findViewById(R.id.TVfail);
        //keeps track of previous letters failed
        String previousFails = failedLetters.getText().toString();

        //displays failed letters
        failedLetters.setText(previousFails + letterFailed);

        //change image
        switch(mFailCounter)
        {

            case 1:
                 imageView.setImageResource(R.drawable.hangdroid_1);
                YoYo.with(Techniques.Shake).duration(800).playOn(imageView);
                break;
            case 2:
                imageView.setImageResource(R.drawable.hangdroid_2);
                YoYo.with(Techniques.Shake).duration(800).playOn(imageView);
                break;
            case 3:
                imageView.setImageResource(R.drawable.hangdroid_3);
                YoYo.with(Techniques.Shake).duration(800).playOn(imageView);
                YoYo.with(Techniques.Shake).duration(800).playOn(imageView);
                break;
            case 4:
                imageView.setImageResource(R.drawable.hangdroid_4);
                YoYo.with(Techniques.Shake).duration(800).playOn(imageView);
                break;
            case 5:
                imageView.setImageResource(R.drawable.hangdroid_5);
                YoYo.with(Techniques.Shake).duration(800).playOn(imageView);
                break;
            case 6:
                //Game Over
                imageView.setImageResource(R.drawable.hangdroid_6);

                Intent multResult = new Intent(this, Multiresult.class);
                multResult.putExtra("Mult_word",mWord);
                startActivity(multResult);
                finish();
                break;

        }



    }
    /*Displays letter
    * @param position, position of letter
    * @param letterGuessed, letter user guessed
    * */
    public void showLettersAtIndex(int position, char letterGuessed)
    {
        LinearLayout layoutLetter =(LinearLayout) findViewById(R.id.layoutLetters);

        //position is i or spot in the word
        TextView textView = (TextView)layoutLetter.getChildAt(position);

        textView.setText(Character.toString(letterGuessed));
        YoYo.with(Techniques.Pulse).duration(1500).playOn(textView);


    }

    public void clearScreen()
    {
       /* ImageView imageView = (ImageView)findViewById(R.id.imageView);
        TextView failedLetters = (TextView)findViewById(R.id.TVfail);
        //set to default image
        imageView.setImageResource(R.drawable.hangdroid_0);

        //reset failed letters
        failedLetters.setText("");

        //set counter to 0
        mGuessedLetter = 0;
        mFailCounter = 0;

        //set text to underscores
        LinearLayout layoutLetter =(LinearLayout) findViewById(R.id.layoutLetters);
        for(int i = 0; i < layoutLetter.getChildCount(); i++)
        {
            TextView currentTextView = (TextView)layoutLetter.getChildAt(i);
            currentTextView.setText("_");
        }*/

        Intent multResult = new Intent(this, Multiresult.class);
        multResult.putExtra("Mult_word",mWord);
        startActivity(multResult);
        finish();
    }

}
