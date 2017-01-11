package com.hang.soreal.hangman;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

//cntrl+shft+0 and alt+enter
//TODO's let you find stuff and keep list of what is to be done
public class GameActivity extends ActionBarActivity {
    //declare class variable(can be used in any method)
    //TODO for multiplayer come here then set mword equal to word entered if there is no word entered pick from array

    String mWord = "";

    int mFailCounter = 0;

    int mGuessedLetter = 0;

    int mPoints = 0;
    String category = "";
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

         spinner = (Spinner) findViewById(R.id.spinner);
//      Create an ArrayAdapter using the string array and layout from xml file
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.letters_array, R.layout.spinner_item);
//      Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
//      Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        //get category from last class

        category = getIntent().getStringExtra("CAT_CHOICE");
        Log.d("Category", "Category is " + category);

        setRandomWord();
        createTextView(mWord);

    }

    public void setRandomWord()
    {
        TextView tvHint;
        Dialog hintDetail = new Dialog(this);
        //title
        hintDetail.setTitle("Here is some help!");
        hintDetail.setContentView(R.layout.hint);

        tvHint = (TextView)hintDetail.findViewById(R.id.TVHint);

        String listcountries = "Turkey Canada Germany Brazil Mexico France Greece Iran Iraq Syria Denmark Switzerland Aruba Belize Cuba Egypt Hungary Iceland Italy Japan China poland portugal pakistan russia england  ";
        String listanimals = "pig cow bear tiger lion rhino ostrich kangaroo giraffe monkey gorilla elephant snake crocodile spider shark crab fox panda parrot horse chicken turtle deer owl sheep rabbit frog mouse hippo camel goose koala bat dingo emu turkey beaver whale skunk wolf lizard zebra ";
        String liststates = "Alabama Alaska Arizona Arkansas California Colorado Connecticut Delaware Florida Georgia Hawaii Idaho Illinois Indiana Iowa Kansas Kentucky Louisiana Maine Maryland Michigan Minnesota Missouri Montana Nebraska Nevada Ohio Oklahoma Oregon Pennsylvania texas Utah Virginia Washington";
        String listmisc = "ball sea ocean end start hit pat belt bankroll edit enemy attack equal ink glamor describe collide contain adequate daring complicated barber disease volume easy dense bang preserve grain blur champion foreign pull sky eyes eat switch robot wolves muscle collar brother puppet beach bug liquid bags man friend moon earth party music rapper dream review candy fuzzy pillow pool car ship soccer game xbox feel";
       //list.toUpperCase();
        String countryHint[] = {"Istanbul", "Hockey aye", "Ashamed of past", "2014 World Cup", "US Southern Neighbor", "Eiffel Tower", "May we have more Euro's?", "I didn't walk I __", "Middle Eatern Country", "HOT HOT HOT", "I am in Europe", "Neutral", "Dutch Island","Central America", "Missle Crisis", "Pyramid", "Haven't eaten in awhile", "Cold and north", "Pasta", "Asain Country", "Has highest population", "The people love sausage", "Europe", "Middle East", "Cold War", "Tea Party!"  };
        String animalHint[] = {"Oink", "Moo", "___ Hug", "Striped Predator", "King", "Scary Horn", "Fast Bird", "Has a Pouch", "Long Neck", "Banana", "Pounds Chest", "Has a trunk", "Moves with no legs", "Reptile", "Hangs on a web", "Ocean Predator", "Delicious Seafood", "Small and digs holes", "Loves Bamboo", "Quit mocking me", "Equus ferus", "bock, bock, bock", "Slow and steady", "Hunting Season", "Woo Woo", "Baahhh", "Cotton Tail", "Lily pad", "Scared of cats", "Big Big Big", "Lives in desert", "Migrates in winter", "Loves taking naps", "Sleeps upside down", "Ate your baby", "Dromaius novaehollandiae", "Thanksgiving Dinner", "Builds a dam", "Largest Mammal", "Don't spray me!", "Hunts in packs", "Scales", "Black and White"};
        //every element separated by a space becomes a word in array                                                                                                                                                                                                                                                                                                              rabbit frog mouse hippo camel goose koala bat dingo emu turkey beaver whale skunk wolf lizard zebra
        //the category picked involves a different array
        String words[] = {};
        String hints[] = {};
        switch(category)
        {
            case "animals":
                 words = listanimals.toUpperCase().split(" ");
                 hints = animalHint.clone();
                break;
            case "U.S. States":
                words = liststates.toUpperCase().split(" ");
                break;

            case "miscellaneous":
                 words = listmisc.toUpperCase().split(" ");
                break;
            case "Countries":
                words = listcountries.toUpperCase().split(" ");
                hints = countryHint.clone();
                break;

        }
        int randomNum1 , randomNum2, i = 0, j = 0 ;
        String tempWord, tempHint;


        Log.d("COUNTLOG","THE NUMBER OF WORDS IS: " + words.length);

        //shuffle array
        for (i = 0; i < 1000; i++) {

            randomNum1 = (int)(Math.random() * words.length);

            randomNum2 = (int)(Math.random() * words.length);

            tempWord = words[randomNum1];
            words[randomNum1] = words[randomNum2];
            words[randomNum2] = tempWord;

            if(category.equals("animals") || category.equals("Countries")) {
                tempHint = hints[randomNum1];
                hints[randomNum1] = hints[randomNum2];
                hints[randomNum2] = tempHint;
            }

        }

        mWord = words[j];
        if(category.equals("animals") || category.equals("Countries"))
        {
            tvHint.setText("Hint: " + hints[j]);
            hintDetail.show();
            YoYo.with(Techniques.BounceInDown).duration(2000).playOn(hintDetail.findViewById(R.id.TVHint));
        }

        //is we are at end of array set back to 0 else keep going
        if(j == words.length)
            j = 0;
        else
             j++;
    }
    /*Sets number of textViews equal to word*/
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

        //gets text and stores in string
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
            mPoints++;
            clearScreen();
            setRandomWord();
            createTextView(mWord);

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
                Intent gameOver = new Intent(this,gameOverActivity.class);
                //send points to gameover activity
                gameOver.putExtra("POINTS", mPoints);
                gameOver.putExtra("Mult_word",mWord);
                startActivity(gameOver);
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
        YoYo.with(Techniques.Pulse).duration(1000).playOn(textView);



    }

    public void clearScreen()
    {
        int duration = Toast.LENGTH_LONG;
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, "The word was " + mWord + "\n" + mPoints + "word(s) guessed", duration);
        toast.show();

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        TextView failedLetters = (TextView)findViewById(R.id.TVfail);
        //set to default image
        imageView.setImageResource(R.drawable.hangdroid_0);

        //reset failed letters
        failedLetters.setText("");

        //set counter to 0
        mGuessedLetter = 0;
        mFailCounter = 0;

        //remove the textviews

        LinearLayout layoutLetter =(LinearLayout) findViewById(R.id.layoutLetters);
        layoutLetter.removeAllViews();
    }

}
