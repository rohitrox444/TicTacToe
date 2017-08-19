package com.example.rohit.tictactoeai;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityMainScreenActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Button play;
    EditText playername1, playername2;
    TextView noofmatchestextview;
    SeekBar matches;
    RadioGroup choice;
    RadioButton vshuman, vscomputer, vsonline;
    int noofmathces;
    int matchesarray[] = {1,2,3,4,5,6,7,8,9,10};
    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_mainscreen);

        playername1 =(EditText)findViewById(R.id.player1name);
        playername2 =(EditText)findViewById(R.id.player2name);

        play=(Button)findViewById(R.id.playbutton);

        matches=(SeekBar)findViewById(R.id.matches);

        matches.setOnSeekBarChangeListener(this);

        noofmatchestextview=(TextView)findViewById(R.id.matchestextview);

        choice = (RadioGroup)findViewById(R.id.choicegroup);
        vshuman=(RadioButton)findViewById(R.id.vshuman);
        vscomputer=(RadioButton)findViewById(R.id.vscomputer);
        vsonline=(RadioButton)findViewById(R.id.vsonline);

        choice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.vscomputer){
                    playername2.setText("Computer");
                    playername2.setEnabled(false);
                }
                else if(checkedId==R.id.vsonline){
                    playername2.setText("Rohit12345");
                    playername2.setEnabled(false);
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playername1.getText().toString().trim()=="" || playername2.getText().toString().trim()==""){
                    Toast.makeText(ActivityMainScreenActivity.this,"please fill the player name",Toast.LENGTH_SHORT).show();
                }

                String player1name = playername1.getText().toString();
                String player2name = playername2.getText().toString();
            }
        });
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        noofmatchestextview.setText("No of Matches :"+matchesarray[progress]);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}