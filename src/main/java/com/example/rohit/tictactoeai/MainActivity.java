package com.example.rohit.tictactoeai;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int activeplayer = 0; //0 for 0 and  1 for cross
    int gamestate[] = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 means unplayed
    int winnigLocations[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}
            , {2, 4, 6}};
    boolean gameover = false;
    int count=0;


    @Override
    protected void onStart() {
        super.onStart();
        /* before changing
        onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); */

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }// this brace added by me

    public void gamelogic(View view) {
        ImageView tappedImageView = (ImageView) view;
        int tappedlocation = Integer.parseInt(view.getTag().toString());
        if (gamestate[tappedlocation] == 2 && gameover == false) {
            gamestate[tappedlocation] = activeplayer;

            tappedImageView.setTranslationY(-3000f);
            if (activeplayer == 0) {
                tappedImageView.setImageResource(R.drawable.tictactoezero);
                activeplayer = 1;
            } else if (activeplayer == 1) {
                tappedImageView.setImageResource(R.drawable.tictactoecross);
                activeplayer = 0;
            }
            tappedImageView.animate().translationYBy(3000f).setDuration(500);
        }

        for (int[] winningPosition : winnigLocations) {

            if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]]
                    && gamestate[winningPosition[1]] == gamestate[winningPosition[2]]
                    && gamestate[winningPosition[0]] != 2) {

                if (activeplayer == 0) {
                    Toast.makeText(getApplicationContext(), "cross is winner", Toast.LENGTH_SHORT).show();
                }
                if (activeplayer == 1) {
                    Toast.makeText(getApplicationContext(), "zero is winner", Toast.LENGTH_SHORT).show();


                }
                gameover = true;
            }
            else if(count==64){
                Toast.makeText(getApplicationContext(), "DRAW!!!!!!!", Toast.LENGTH_SHORT).show();
                count=64;
                gameover=true;
            }
            count++;
//           else if (gamestate[winningPosition[0]] != gamestate[winningPosition[1]]
//                    && gamestate[winningPosition[1]] != gamestate[winningPosition[2]]
//                    && gamestate[winningPosition[0]] != 2){
//                Toast.makeText(getApplicationContext(), "DRAW!!!!!!!", Toast.LENGTH_SHORT).show();
//            }


//            else {
//                for(int i=0;i<gamestate.length;i++){
//                    if(gamestate[i]!=2){
//                        Toast.makeText(getApplicationContext(), "DRAW!!!!!!!", Toast.LENGTH_SHORT).show();
//                        gameover=true;
//                    }
//                }
//            }
            //code by me
//            else {
//                Toast.makeText(getApplicationContext(), "DRAW!!!!!!!!", Toast.LENGTH_SHORT).show();
//                gameover = true;
//            }

        }
        Log.d("counter",String.valueOf(count));
    }

    public void aiogic(View view){

        int tappedlocation ;

//        for(;;){
//        ImageView tappedImageView = (ImageView) view;

        //this code knowing user's tapped location(will go in if condition will be (if user's turn))
        if(activeplayer==0){
        tappedlocation = Integer.parseInt(view.getTag().toString());
            domove(tappedlocation,view);
        }
        else if(activeplayer==1){
            Random r = new Random();
            tappedlocation = r.nextInt(9 - 1+1) + 1;

            for(int i=0;i<9;i++){
                if(gamestate[tappedlocation] ==2){
                    Log.d("Random",Integer.toString(tappedlocation));
                    domove(tappedlocation,view);
                    Log.d("Random","after domove");
                    break;
                }
                else{
                    tappedlocation = r.nextInt(9 - 1+1) + 1;
                    Log.d("Random",Integer.toString(tappedlocation));
                }
            }
        }
        //now ai code will start from here the tapped location will be generated according to
        //gamestate array
        /*if easy then
        * Random r = new Random();
            int i1 = r.nextInt(max - min + 1) + min;
            This gives a random integer between 65 (inclusive) and 80 (exclusive)
         */
        /*this is for easy level

        Random r = new Random();
        int randomnumber = r.nextInt(9 - 1+1) + 1;
        //now tappedlocation = randomnumber
        /*
        if(activeplayer==aiturn){
        for(int i=0;i<9;i++){
        if(gamestate[tappedlocation] ==2){
        domove(tappedlocation);
        break;
        }
        }
        }
         *
         * */

        /*
         *for medium and hard level
         * (first turn will be for user)
         * if(activeuser==userturn){
         * domove();
         * }
         *
         * if(activeuser==aiturn){
         * usertaploccopy= tappedlocation ;
         * int ailogic=3;
         * if(usertaploccopy<7){
         * for(int i=0;i<6;i++){
         * if(gamestate[usertaploc+ailogic]==2){
         *  domove(usertaploc+ailogic)
         * }else{
         * ailogic--;
         * }
         * }
         * }
         * }
         * if(usertaploccopy>=7){
         * for(int i =0;i<6i++){
         * if(gamestate[i]==2){
         * domove(i);
         * break;
         * }
         * else{
         * for(inti=9;i<=7;i--){
         * if(gamestate[i]==2){
         * domove(i);
         * }
         * }
         * }
         * }
         * }
         * */


//        if (gamestate[tappedlocation] == 2 && gameover == false) {
//            gamestate[tappedlocation] = activeplayer;
//
//            tappedImageView.setTranslationY(-3000f);
//            if (activeplayer == 0) {
//                tappedImageView.setImageResource(R.drawable.tictactoezero);
//                activeplayer = 1;
//            } else if (activeplayer == 1) {
//                tappedImageView.setImageResource(R.drawable.tictactoecross);
//                activeplayer = 0;
//            }
//            tappedImageView.animate().translationYBy(3000f).setDuration(500);
//        }
//
//        for (int[] winningPosition : winnigLocations) {
//
//            if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]]
//                    && gamestate[winningPosition[1]] == gamestate[winningPosition[2]]
//                    && gamestate[winningPosition[0]] != 2) {
//
//                if (activeplayer == 0) {
//                    Toast.makeText(getApplicationContext(), "cross is winner", Toast.LENGTH_SHORT).show();
//                }
//                if (activeplayer == 1) {
//                    Toast.makeText(getApplicationContext(), "zero is winner", Toast.LENGTH_SHORT).show();
//
//
//                }
//                gameover = true;
//            }
//            else if(count==64){
//                Toast.makeText(getApplicationContext(), "DRAW!!!!!!!", Toast.LENGTH_SHORT).show();
//                count=64;
//                gameover=true;
//            }
//            count++;


//           else if (gamestate[winningPosition[0]] != gamestate[winningPosition[1]]
//                    && gamestate[winningPosition[1]] != gamestate[winningPosition[2]]
//                    && gamestate[winningPosition[0]] != 2){
//                Toast.makeText(getApplicationContext(), "DRAW!!!!!!!", Toast.LENGTH_SHORT).show();
//            }


//            else {
//                for(int i=0;i<gamestate.length;i++){
//                    if(gamestate[i]!=2){
//                        Toast.makeText(getApplicationContext(), "DRAW!!!!!!!", Toast.LENGTH_SHORT).show();
//                        gameover=true;
//                    }
//                }
//            }
            //code by me
//            else {
//                Toast.makeText(getApplicationContext(), "DRAW!!!!!!!!", Toast.LENGTH_SHORT).show();
//                gameover = true;
//            }

        }



    public void domove(int tappedlocation ,View view){

//        for(;;){
        ImageView tappedImageView = (ImageView) view;

        Log.d("Random","domove called");


        if (gamestate[tappedlocation] == 2 && gameover == false) {
            gamestate[tappedlocation] = activeplayer;

            tappedImageView.setTranslationY(-3000f);
            if (activeplayer == 0) {
                tappedImageView.setImageResource(R.drawable.tictactoezero);
                activeplayer = 1;
                //this.ailogic();
                /*
                * we can also pass image view in this function by finding gridlayout.getchildatfunction() and getting imageview by
                * using random function
                * OR
                * by directly passing view object
                * */
                this.aiogic(view);
            } else if (activeplayer == 1) {
                tappedImageView.setImageResource(R.drawable.tictactoecross);
                activeplayer = 0;
           }
            tappedImageView.animate().translationYBy(3000f).setDuration(500);
        }

        for (int[] winningPosition : winnigLocations) {

            if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]]
                    && gamestate[winningPosition[1]] == gamestate[winningPosition[2]]
                    && gamestate[winningPosition[0]] != 2) {

                if (activeplayer == 0) {
                    Toast.makeText(getApplicationContext(), "cross is winner", Toast.LENGTH_SHORT).show();
                }
                if (activeplayer == 1) {
                    Toast.makeText(getApplicationContext(), "zero is winner", Toast.LENGTH_SHORT).show();


                }
                gameover = true;
            }
            else if(count==64){
                Toast.makeText(getApplicationContext(), "DRAW!!!!!!!", Toast.LENGTH_SHORT).show();
                count=64;
                gameover=true;
            }
            count++;

        }
        Log.d("counter",String.valueOf(count));
//            break;
    }
}

/*
 how to get and element at grilayout

        final int numVisibleChildren = gridView.getChildCount();
        final int firstVisiblePosition = gridView.getFirstVisiblePosition();

        for ( int i = 0; i < numVisibleChildren; i++ ) {
        int positionOfView = firstVisiblePosition + i;

        if (positionOfView == positionIamLookingFor) {
        View view = gridView.getChildAt(i);
        }
        }
 *  */