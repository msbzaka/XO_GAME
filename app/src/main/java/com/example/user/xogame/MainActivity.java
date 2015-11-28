package com.example.user.xogame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button PlayAgainButton;
    private Button[] b = new Button[9];
    private TextView ResultText;
    private int[][] XO=new int[9][9];
    private char Turn='X';
    private boolean isGameEnded=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResultText = (TextView) findViewById(R.id.result);
        ResultText.setText(Turn+"-Player Turn ^_^");

        PlayAgainButton=(Button) findViewById(R.id.button10);
        PlayAgainButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Turn='X';
                isGameEnded=false;
                MainActivity.this.recreate();
            }
        });
        b[0]=(Button) findViewById(R.id.button);
        b[1]=(Button) findViewById(R.id.button2);
        b[2]=(Button) findViewById(R.id.button3);
        b[3]=(Button) findViewById(R.id.button4);
        b[4]=(Button) findViewById(R.id.button5);
        b[5]=(Button) findViewById(R.id.button6);
        b[6]=(Button) findViewById(R.id.button7);
        b[7]=(Button) findViewById(R.id.button8);
        b[8]=(Button) findViewById(R.id.button9);
        View.OnClickListener press = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Button CurrentB=(Button) v;
                CurrentB.setClickable(false);
                CurrentB.setTextColor((Turn=='X') ? Color.BLUE:Color.RED);
                CurrentB.setText(Turn + "");
                XO_Pressed(v);
                CheckRes();
                Turn = (Turn == 'X') ? 'O' : 'X';
                if(!isGameEnded) {
                    ResultText.setText(Turn + "-Player Turn ^_^");
                    ResultText.setTextColor((Turn == 'X') ? Color.BLUE : Color.RED);
                }
            }
        };
        for(int i=0;i<9;i++)
        b[i].setOnClickListener(press);

    }

    private void XO_Pressed(View v){
        int id=v.getId();
        if(id==R.id.button)
                XO[0][0]=Turn=='X' ? 50:1000;

            else if(id== R.id.button2)
                XO[0][1]=Turn=='X' ? 50:1000;
                
            else if(id== R.id.button3)
                XO[0][2]=Turn=='X' ? 50:1000;
                
            else if(id== R.id.button4)
                XO[1][0]=Turn=='X' ? 50:1000;
                
            else if(id== R.id.button5)
                XO[1][1]=Turn=='X' ? 50:1000;
                
            else if(id== R.id.button6)
                XO[1][2]=Turn=='X' ? 50:1000;
                
            else if(id== R.id.button7)
                XO[2][0]=Turn=='X' ? 50:1000;
                
            else if(id==R.id.button8)
                XO[2][1]=Turn=='X' ? 50:1000;

            else if(id==R.id.button9)
                XO[2][2]=Turn=='X' ? 50:1000;
        
    }
    private void CheckRes(){
        int line;
        boolean draw=true;
        for(int a=0;a<3;a++){
            line=0;
            for(int b=0;b<3;b++)
                line+=XO[a][b];
            Log.v("ERROR ! = ",line+ "");
            if(line==150||line==3000){
                endGame(line);
                return;
            }
            line=0;
            for(int b=0;b<3;b++)
                line+=XO[b][a];
            Log.v("ERROR ! = ",line+ "");
            if(line==150||line==3000){
                endGame(line);
                return;
            }
        }
        line=XO[0][0]+XO[1][1]+XO[2][2];
        Log.v("ERROR ! = ",line+ "");
        if(line==150||line==3000){
            endGame(line);
            return;
        }
        line=XO[2][0]+XO[1][1]+XO[0][2];
        Log.v("ERROR ! = ",line+ "");
        if(line==150||line==3000){
            endGame(line);
            return;
        }
        for(int a=0;a<3;a++)
            for(int b=0;b<3;b++)
                if(XO[a][b]==0){
                    draw=false;
                    return;
                }
        if(draw)
            endGame(0);
    }

    private void endGame(int line){
        isGameEnded=true;
        for(int i=0;i<9;i++)
            b[i].setClickable(false);
        if(line==0)
            ResultText.setText("DRAW !");
        if(line==150)
            ResultText.setText("X WON !");
        if(line==3000)
            ResultText.setText("O WON !");
    }

}
